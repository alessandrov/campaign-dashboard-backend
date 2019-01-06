package campaign.dashboard.repository;

import campaign.dashboard.entity.Campaign;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends CrudRepository<Campaign, Long> {

}
