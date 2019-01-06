package campaign.dashboard.repository;

import campaign.dashboard.entity.Platform;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformRepository extends CrudRepository<Platform, Long> {

    List<Platform> findByCampaignId(Long campaignId);

}
