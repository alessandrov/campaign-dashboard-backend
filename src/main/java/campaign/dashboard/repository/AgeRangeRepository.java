package campaign.dashboard.repository;

import campaign.dashboard.entity.AgeRange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeRangeRepository extends CrudRepository<AgeRange, Long> {

    AgeRange findByLowLimitAndHighLimit(int lowLimit, int highLimit);

}
