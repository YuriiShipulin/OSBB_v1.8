package ua.kiev.prog.entity;

import javax.persistence.*;

@Entity
@Table (name = "current_payments")
public class CurrentPayments {
    @Id
    @GeneratedValue
    private long id;
    private long prevValue;
    private long currValue;
    private long rate;
    private short status;

    @ManyToOne
    @JoinColumn(name="serv_id")
    private ServicesEntity servicesEntity;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity userEntity;


    public CurrentPayments() {
    }

    public CurrentPayments(long prevValue, long currValue, long rate, ServicesEntity servicesEntity, UserEntity userEntity) {
        this.prevValue = prevValue;
        this.currValue = currValue;
        this.rate = rate;
        this.servicesEntity = servicesEntity;
        this.userEntity = userEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrevValue() {
        return prevValue;
    }

    public void setPrevValue(long prevValue) {
        this.prevValue = prevValue;
    }

    public long getCurrValue() {
        return currValue;
    }

    public void setCurrValue(long currValue) {
        this.currValue = currValue;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public ServicesEntity getServicesEntity() {
        return servicesEntity;
    }

    public void setServicesEntity(ServicesEntity servicesEntity) {
        this.servicesEntity = servicesEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

}
