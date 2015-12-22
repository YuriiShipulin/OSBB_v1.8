package ua.kiev.prog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.entity.CurrentPayments;
import ua.kiev.prog.entity.UserEntity;

import java.util.List;

public interface CurrentPaymentsRepository extends JpaRepository<CurrentPayments, Long> {
    List<CurrentPayments> findByUserEntity (UserEntity userEntity);
}
