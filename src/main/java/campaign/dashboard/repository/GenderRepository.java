package campaign.dashboard.repository;

import campaign.dashboard.entity.Gender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends CrudRepository<Gender, Long> {

    Gender findByType(Gender.Type type);

}
