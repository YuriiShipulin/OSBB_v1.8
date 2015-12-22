package ua.kiev.prog.entity;

import java.math.BigDecimal;
import java.util.Map;

public class User {
    private long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Integer flatNum;
    private BigDecimal area;
    private Integer peopleCNT;
    private Map<String ,String> currentCounter;

    public User(UserEntity user){
        this.id = user.getId();
        this.area = user.getUserInfo().getFlatsEntity().getArea();
        this.email = user.getEmail();
        this.flatNum = user.getUserInfo().getFlatsEntity().getFlatNumber();
        this.name = user.getUserInfo().getFirstName();
        this.surname = user.getUserInfo().getLastName();
        this.peopleCNT = user.getUserInfo().getFlatsEntity().getPeopleCnt();
        this.phone = user.getUserInfo().getPhone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFlatNum() {
        return flatNum;
    }

    public void setFlatNum(Integer flatNum) {
        this.flatNum = flatNum;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Integer getPeopleCNT() {
        return peopleCNT;
    }

    public void setPeopleCNT(Integer peopleCNT) {
        this.peopleCNT = peopleCNT;
    }

    public Map<String, String> getCurrentCounter() {
        return currentCounter;
    }

    public void setCurrentCounter(Map<String, String> currentCounter) {
        this.currentCounter = currentCounter;
    }
}
