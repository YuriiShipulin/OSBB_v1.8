package ua.kiev.prog.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "flats", schema = "", catalog = "prog")
    public class FlatsEntity {
    @Id
    @GeneratedValue
    private long id;
    private Integer flatNumber;
    private Integer peopleCnt;
    private BigDecimal area;

    @ManyToOne(targetEntity = BuildsEntity.class)
    @JoinColumn(name = "build_id")
    private BuildsEntity buildsEntity;

    @OneToOne (mappedBy = "flatsEntity",targetEntity = UserInfoEntity.class)
    private UserInfoEntity userInfoEntity;

    public FlatsEntity() {
    }

    public FlatsEntity(long id, Integer flatNumber, Integer peopleCnt, BigDecimal area, BuildsEntity buildsEntity) {
        this.id = id;
        this.flatNumber = flatNumber;
        this.peopleCnt = peopleCnt;
        this.area = area;
        this.buildsEntity = buildsEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Integer getPeopleCnt() {
        return peopleCnt;
    }

    public void setPeopleCnt(Integer peopleCnt) {
        this.peopleCnt = peopleCnt;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BuildsEntity getBuildsEntity() {
        return buildsEntity;
    }

    public void setBuildsEntity(BuildsEntity buildsEntity) {
        this.buildsEntity = buildsEntity;
    }
}
