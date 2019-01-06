package campaign.dashboard.repository;

import campaign.dashboard.entity.Interest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends CrudRepository<Interest, Long> {

    Interest findByName(String name);

}
