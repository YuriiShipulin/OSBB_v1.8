package ua.kiev.prog.entity;

import javax.persistence.*;

@Entity
@Table
public class BuildServices {
    @Id
    @GeneratedValue
    private long id;
    private int rate;

    @ManyToOne
    @JoinColumn(name = "build_id")
    private BuildsEntity buildsEntity;

    @ManyToOne
    @JoinColumn(name = "serv_id")
    private ServicesEntity servicesEntity;


    public BuildServices(int rate, BuildsEntity buildsEntity, ServicesEntity servicesEntity) {
        this.rate = rate;
        this.buildsEntity = buildsEntity;
        this.servicesEntity = servicesEntity;
    }

    public BuildServices() {

    }

    public ServicesEntity getServicesEntity() {
        return servicesEntity;
    }

    public void setServicesEntity(ServicesEntity servicesEntity) {
        this.servicesEntity = servicesEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public BuildsEntity getBuildsEntity() {
        return buildsEntity;
    }

    public void setBuildsEntity(BuildsEntity buildsEntity) {
        this.buildsEntity = buildsEntity;
    }
}
