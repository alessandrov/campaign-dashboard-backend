package campaign.dashboard.repository;

import campaign.dashboard.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    Location findByName(String name);

}
