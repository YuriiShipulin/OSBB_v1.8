package ua.kiev.prog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.entity.UserInfoEntity;


public interface UserInfoEntityRepository extends JpaRepository<UserInfoEntity, Long> {

}
