package ua.kiev.prog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", catalog = "prog")
public class UserEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String login;
    private String pass;
    @Column(unique = true)
    private String email;
    private Short type;


    @OneToOne(mappedBy = "userEntity")
    public UserInfoEntity userInfo;

    @ManyToOne(targetEntity = BuildsEntity.class)
    @JoinColumn(name = "build_id")
    private BuildsEntity buildsEntity;

    @OneToMany(mappedBy = "userEntity" , targetEntity = CountData.class)
    private List<CountData> countDatas = new ArrayList<CountData>();

    @OneToMany(mappedBy = "userEntity" , targetEntity = CurrentPayments.class) //
    private List<CurrentPayments> currentPayments;



    public UserEntity(String login, String pass, String email, Short type, BuildsEntity buildsEntity) {
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.type = type;
        this.buildsEntity = buildsEntity;
    }

    public UserEntity() {
    }

    public List<CurrentPayments> getCurrentPayments() {
        return currentPayments;
    }

    public void setCurrentPayments(List<CurrentPayments> currentPayments) {
        this.currentPayments = currentPayments;
    }

    public List<CountData> getCountDatas() {
        return countDatas;
    }

    public void setCountDatas(List<CountData> countDatas) {
        this.countDatas = countDatas;
    }

    public UserInfoEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoEntity userInfo) {
        this.userInfo = userInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public BuildsEntity getBuildsEntity() {
        return buildsEntity;
    }

    public void setBuildsEntity(BuildsEntity buildsEntity) {
        this.buildsEntity = buildsEntity;
    }
}
