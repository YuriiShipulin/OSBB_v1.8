package ua.kiev.prog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.entity.ServicesEntity;

public interface ServicesEntityRepository extends JpaRepository<ServicesEntity, Long> {

}
