package ua.kiev.prog.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kiev.prog.entity.BuildsEntity;


public interface BuildsEntityRepository extends JpaRepository<BuildsEntity, Long> {

    @Query("SELECT c FROM BuildsEntity c where c.code = :key ")
    BuildsEntity findOneByKey(@Param("key") String key);
}
