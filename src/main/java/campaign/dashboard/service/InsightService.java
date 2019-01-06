package campaign.dashboard.service;

import campaign.dashboard.entity.Insight;
import campaign.dashboard.service.common.AbstractService;

public interface InsightService extends AbstractService<Insight, Long> {

    Insight findByPlatform(Long platformId);

}
