package ua.kiev.prog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ua.kiev.prog.entity.*;
import ua.kiev.prog.services.Services;
import ua.kiev.prog.utils.Email;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/")
@SessionAttributes(value = {"build", "user"}, types = {BuildsEntity.class, UserEntity.class})
public class MyController {

    static final int USER_TYPE = 0;
    static final int ADMIN_TYPE = 1;

    private UserEntity getCurrUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return services.findOneUserByLogin(login);
    }

    @Autowired
    private Services services;

    @RequestMapping("/")
    public String index(HttpServletResponse response, Model model) {
        try {
            if (getCurrUser() == null) {
                return "all/hello/signIN";
            }
            response.sendRedirect("/sec/signIN");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/signin")
    public String signIn(Model model) {
        return "all/hello/signIN";
    }

    @RequestMapping("/errors/403_Error")
    public String error(Model model) {
        return "all/errors/403_Error";
    }

    @RequestMapping("/all/reg/signup")
    public String signup(Model model) {
        return "all/regist/signup";
    }

    @RequestMapping("/all/reg/add/user")
    public String addUser(@RequestParam String login, @RequestParam String pass, @RequestParam String email,
                          @RequestParam Short group, @RequestParam String key, Model model) {
        BuildsEntity build;

        if (login.isEmpty() || pass.isEmpty() || email.isEmpty()) {
            return "all/errors/403_Error";
        }
        if(services.findOneUserByLogin(login) != null){
            return "all/errors/403_Error";
        }

        UserEntity user = null;
        if (group == USER_TYPE) {
            build = services.findBuildByKey(key);
            user = new UserEntity(login, pass, email, group, build);
            model.addAttribute("listFlat", services.findAllByBuildsEntity(build));
        } else if (group == ADMIN_TYPE) {
            build = new BuildsEntity(null);
            services.addBuild(build);
            user = new UserEntity(login, pass, email, group, build);
            model.addAttribute("build", build);
        }
        services.addUser(user);
        user = services.mergeUser(user);
        model.addAttribute("user", user);
        return group == USER_TYPE ? "all/regist/user/signup2User" : "all/regist/admin/signup2Admin";
    }


    @RequestMapping("/all/reg/admin/buildInfo")
    public String addBuild(@RequestParam Short flatCnt, @RequestParam String city, @RequestParam String street,
                           @RequestParam String buildNum, @ModelAttribute("build") BuildsEntity build, Model model) {
        build.setBuildNum(buildNum);
        services.mergeBuild(build);
        if (build.getId() != 0) {
            for (int i = 1; i <= build.getFlatCnt(); i++) {
                FlatsEntity flat = new FlatsEntity();
                flat.setFlatNumber(i);
                flat.setBuildsEntity(build);
                services.addFlat(flat);
            }
        }
        model.addAttribute("users", services.findAllUsersByBuild(null));
        model.addAttribute("services", services.listServices());
        return "all/regist/admin/addService";
    }

    @RequestMapping("/all/reg/add/userInfo")
    public String addUserInfo(@RequestParam String name, @RequestParam String lastName, @RequestParam String secondName,
                              @RequestParam String phone, @RequestParam long flatNum, @ModelAttribute("user") UserEntity user, Model model) {
        UserInfoEntity userIE;
        FlatsEntity flat = services.getFlatById(flatNum);
        if (name.isEmpty() && lastName.isEmpty() && secondName.isEmpty() && phone.isEmpty())
            return "all/errors/403_Error";

        if (flat == null)
            return "all/errors/403_Error";

        userIE = new UserInfoEntity(lastName, name, secondName, phone, user, flat);
        user.setUserInfo(userIE);
        model.addAttribute("userIE", userIE);
        services.addUserInfo(userIE);
        return "all/regist/user/success";
    }

    @RequestMapping("all/signup/add/goToFlat")
    public String gotoFlat(Model model) {
        return "all/regist/user/signup2Flat";
    }


    @RequestMapping("/all/reg/add/user/flat")
    public String addFlat(@RequestParam int peopleCount, @RequestParam BigDecimal area, @ModelAttribute("user") UserEntity user, Model model) {
        if (peopleCount == 0 || area.equals(null))
            return "all/errors/403_Error";

        UserInfoEntity userIE = user.getUserInfo();
        if (userIE == null)
            return "all/errors/403_Error";

        FlatsEntity flat = userIE.getFlatsEntity();
        flat.setBuildsEntity(user.getBuildsEntity());
        flat.setPeopleCnt(peopleCount);
        flat.setArea(area);
        services.mergeFlat(flat);
        return "all/hello/signIN";
    }

    @RequestMapping("/inviteusers")
    public void inviteUsers(@RequestParam String email, HttpServletResponse response, Model model) {
        try {
            UserEntity user = getCurrUser();
            String code = user.getBuildsEntity().getCode();
            new Email().sendMail(email, code);
            response.sendRedirect("/sec/signIN");
        } catch
                (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/all/reg/add/admin/service")
    public String addService(@RequestParam Map<String, String> allRequestParams, @ModelAttribute("user") UserEntity user, Model model) {
        BuildsEntity build = user.getBuildsEntity();
        for (Map.Entry<String, String> entry : allRequestParams.entrySet()) {
            BuildServices buildServ = new BuildServices();
            buildServ.setRate(Integer.parseInt(entry.getValue()));
            buildServ.setBuildsEntity(build);
            buildServ.setServicesEntity(services.getServiceById(Integer.parseInt(entry.getKey())));
            services.addBuildService(buildServ);
        }
        services.mergeUser(user);
        return "all/hello/signIN";
    }

    @RequestMapping("/hello/signINincorrect")
    public String incorrectLogin(Model model) {
        return "all/hello/signINincorrect";
    }
}
