package ua.kiev.prog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kiev.prog.entity.*;
import ua.kiev.prog.repositories.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Services {


    @Autowired
    private BuildsEntityRepository buildsEntityRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private UserInfoEntityRepository userIERepository;
    @Autowired
    private FlatsEntityRepository flatsRepository;
    @Autowired
    private ServicesEntityRepository SERepository;
    @Autowired
    private BuildsServicesRepository buildServicesRepository;
    @Autowired
    private CountDataRepository countDataRepository;
    @Autowired
    private ServicesEntityRepository servicesEntityRepository;
    @Autowired
    private CurrentPaymentsRepository currentPaymentsRepository;


    @Transactional
    public void addCountData(CountData countData) {
        countDataRepository.save(countData);
    }

    @Transactional
    public CountData findLastValue(UserEntity userEntity, ServicesEntity servicesEntity) {
        return countDataRepository.findTop1ByUserEntityAndServicesEntityOrderByIdDesc(userEntity, servicesEntity);//findOneByUserEntityAndServicesEntityOrderByIdDesc
    }

    @Transactional
    public void addUser(UserEntity userEntity) {
        userEntityRepository.saveAndFlush(userEntity);
    }

    @Transactional
    public void addCurrentPayments(CurrentPayments currentPayments) {
        currentPaymentsRepository.saveAndFlush(currentPayments);
    }

    @Transactional
    public UserEntity mergeUser(UserEntity user) {
        return userEntityRepository.save(user);
    }

    @Transactional
    public List<UserEntity> findAllUsersByBuild(BuildsEntity buildsEntity) {
        return (List<UserEntity>) userEntityRepository.findAllByBuildsEntity(buildsEntity);
    }

    @Transactional
    public UserEntity findOneUserByBuild(BuildsEntity buildsEntity) {
        return userEntityRepository.findOneByBuildsEntity(buildsEntity);
    }

    @Transactional
    public UserEntity findOneUserByLogin(String login) {
        return userEntityRepository.findOneByLogin(login);
    }

    @Transactional
    public BuildsEntity findBuildById(Long id) {
        return buildsEntityRepository.findOne(id);
    }

    @Transactional
    public BuildsEntity findBuildByKey(String key) {
        return buildsEntityRepository.findOneByKey(key);
    }

    @Transactional
    public BuildsEntity mergeBuild(BuildsEntity build) {
        return buildsEntityRepository.save(build);
    }

    @Transactional
    public void addBuild(BuildsEntity build) {
        buildsEntityRepository.saveAndFlush(build);
    }

    @Transactional
    public List<FlatsEntity> findAllByBuildsEntity(BuildsEntity build) {
        return flatsRepository.findAllByBuildsEntityAndAreaIsNull(build);
    }

    @Transactional
    public void addFlat(FlatsEntity flat) {
        flatsRepository.saveAndFlush(flat);
    }

    @Transactional
    public FlatsEntity mergeFlat(FlatsEntity flat) {
        return flatsRepository.save(flat);
    }

    @Transactional
    public FlatsEntity getFlatById(long id) {
        return flatsRepository.findOne(id);
    }

    @Transactional
    public void addUserInfo(UserInfoEntity userIE) {
        userIERepository.saveAndFlush(userIE);
    }

    @Transactional
    public List<ServicesEntity> listServices() {
        return SERepository.findAll();
    }

    @Transactional
    public ServicesEntity getServiceById(long id) {
        return SERepository.findOne(id);
    }

    @Transactional
    public void addBuildService(BuildServices buildServices) {
        buildServicesRepository.saveAndFlush(buildServices);
    }

    @Transactional
    public void mergeBuildService(BuildServices buildServices) {
        buildServicesRepository.save(buildServices);
    }

    @Transactional
    public List<ServicesEntity> getAllServicesEntity() {
        return servicesEntityRepository.findAll();
    }

    @Transactional
    public Map<Long, Long> getCurrValuesByUser(UserEntity userEntity) {
        List<BuildServices> buildServices = userEntity.getBuildsEntity().getServices();
        Map<Long, Long> countDatas = new HashMap<Long, Long>();
        for (BuildServices buildService : buildServices) {
            ServicesEntity service = buildService.getServicesEntity();
            CountData cntData = countDataRepository.findTop1ByUserEntityAndServicesEntityOrderByIdDesc(userEntity, service);
            if (cntData != null) {
                countDatas.put(cntData.getServicesEntity().getId(), cntData.getValue());
            } else {
                countDatas.put(service.getId(), (long) 50);
            }
        }
        return countDatas;
    }

    @Transactional
    public Map<Long, Long> getPrevValuesByUser(UserEntity userEntity) {
        List<BuildServices> buildServices = userEntity.getBuildsEntity().getServices(); //достаем все сервисы для этого дома
        Map<Long, Long> countDatas = new HashMap<Long, Long>(); // создаем массив для хранения показателей
        for (BuildServices buildService : buildServices) {
            ServicesEntity service = buildService.getServicesEntity(); //берем сервис 1
            List<CountData> listCount = countDataRepository.findTop2ByUserEntityAndServicesEntityOrderByIdDesc(userEntity, service); //находим для нехо показатели и кладем в список
            int size = listCount.size();
            System.out.println(size);
            System.out.println(buildService.getId());
            if (size == 2) {
                countDatas.put(service.getId(), listCount.get(0).getId() > listCount.get(1).getId() ? listCount.get(1).getValue() : listCount.get(0).getValue());
            } else if (size == 1) {
                countDatas.put(service.getId(), listCount.get(0).getValue());
            } else if (size == 0) {
                countDatas.put(buildService.getServicesEntity().getId(), (long) 0);
            }
        }
        return countDatas;
    }


    @Transactional
    public BuildServices getBuildServiceByBuildAndService(BuildsEntity buildsEntity, ServicesEntity servicesEntity) {
        return
                buildServicesRepository.findTop1ByBuildsEntityAndServicesEntity(buildsEntity, servicesEntity);
    }

    @Transactional
    public List<CurrentPayments> getCurrentPayments(UserEntity userEntity) {
        return currentPaymentsRepository.findByUserEntity(userEntity);
    }
}

