package campaign.dashboard.repository;

import campaign.dashboard.entity.PlatformType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformTypeRepository extends CrudRepository<PlatformType, Long> {

    PlatformType findByName(String name);

}
