package ua.kiev.prog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.entity.BuildsEntity;
import ua.kiev.prog.entity.FlatsEntity;

import java.util.List;

public interface FlatsEntityRepository extends JpaRepository<FlatsEntity, Long> {

    List<FlatsEntity> findAllByBuildsEntityAndAreaIsNull(BuildsEntity build);
}
