package campaign.dashboard.service;

import campaign.dashboard.entity.TargetAudience;
import campaign.dashboard.service.common.AbstractService;

public interface TargetAudienceService extends AbstractService<TargetAudience, Long> {

    TargetAudience findByPlatform(Long platformId);

}
