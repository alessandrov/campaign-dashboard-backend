package campaign.dashboard.service;

import campaign.dashboard.entity.Platform;
import campaign.dashboard.service.common.AbstractService;

import java.util.List;

public interface PlatformService extends AbstractService<Platform, Long> {

    List<Platform> findByCampaign(Long campaignId);

}
