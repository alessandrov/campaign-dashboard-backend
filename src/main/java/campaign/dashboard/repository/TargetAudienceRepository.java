package campaign.dashboard.repository;

import campaign.dashboard.entity.TargetAudience;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetAudienceRepository extends CrudRepository<TargetAudience, Long> {

    TargetAudience findByPlatformId(Long platformId);

}
