package campaign.dashboard.service;

import campaign.dashboard.entity.Creative;
import campaign.dashboard.service.common.AbstractService;

public interface CreativeService extends AbstractService<Creative, Long> {

    Creative findByPlatform(Long platformId);

}
