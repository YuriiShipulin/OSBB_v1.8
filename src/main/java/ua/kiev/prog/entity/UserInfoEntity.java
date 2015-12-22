package ua.kiev.prog.entity;

import javax.persistence.*;

/**
 * Created by m.bratyuk on 24.11.2015.
 */
@Entity
@Table(name = "user_info", schema = "", catalog = "prog")
public class UserInfoEntity {
    @Id
    @GeneratedValue
    private int id;
    private String lastName;
    private String firstName;
    private String secondName;
    @Column( unique = true)
    private String phone;

    @OneToOne(targetEntity = UserEntity.class)
    @JoinColumn(name="User_id")
    private UserEntity userEntity;

    @OneToOne (targetEntity = FlatsEntity.class)
    @JoinColumn(name = "flat_id")
    private FlatsEntity flatsEntity;

    public UserInfoEntity() {
    }

    public UserInfoEntity(String lastName, String firstName, String secondName, String phone, UserEntity userEntity, FlatsEntity flatsEntity) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.userEntity = userEntity;
        this.flatsEntity = flatsEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public FlatsEntity getFlatsEntity() {
        return flatsEntity;
    }

    public void setFlatsEntity(FlatsEntity flatsEntity) {
        this.flatsEntity = flatsEntity;
    }
}
