package ua.kiev.prog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.entity.BuildServices;
import ua.kiev.prog.entity.BuildsEntity;
import ua.kiev.prog.entity.ServicesEntity;

import java.util.List;

public interface BuildsServicesRepository extends JpaRepository<BuildServices, Long> {

    List<BuildServices> findAllByServicesEntity(ServicesEntity service);
    List<BuildServices> findAllByBuildsEntity(BuildsEntity build);
    BuildServices findTop1ByBuildsEntityAndServicesEntity(BuildsEntity build,ServicesEntity serviceEntity);
}
