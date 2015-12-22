package ua.kiev.prog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "services")
public class ServicesEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(mappedBy = "servicesEntity", targetEntity = BuildServices.class)
    private List<BuildServices> buildservices = new ArrayList<BuildServices>();

    @OneToMany(mappedBy = "servicesEntity", targetEntity = CountData.class)
    private List<CountData> countData = new ArrayList<CountData>();

    public ServicesEntity() {

    }

    public List<CountData> getCountDatas() {
        return countData;
    }

    public void setCountDatas(List<CountData> countDatas) {
        this.countData = countDatas;
    }

    public ServicesEntity(String name) {
        this.name = name;
    }

    public List<BuildServices> getBuildservices() {
        return buildservices;
    }

    public void setBuildservices(List<BuildServices> buildservices) {
        this.buildservices = buildservices;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id).append(" ").append(this.name);
        return sb.toString();
    }
}
