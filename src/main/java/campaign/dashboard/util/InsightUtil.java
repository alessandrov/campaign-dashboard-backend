package campaign.dashboard.util;

import campaign.dashboard.dto.InsightDTO;
import campaign.dashboard.entity.Insight;
import campaign.dashboard.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class InsightUtil {

    private static PlatformRepository platformRepository;

    @Autowired
    private PlatformRepository platformRepository0;

    public static InsightDTO fromEntity(Insight insight) {

        InsightDTO insightDTO = new InsightDTO();

        insightDTO.setId(insight.getId().toString());

        insightDTO.setPlatformId(insight.getPlatform().getId().toString());

        insightDTO.setAdvancedKpi1(insight.getAdvancedKpi1().toString());

        if (insight.getAdvancedKpi2() != null){
            insightDTO.setAdvancedKpi2(insight.getAdvancedKpi2().toString());
        }

        insightDTO.setClicks(String.valueOf(insight.getClicks()));

        insightDTO.setClickThroughRate(insight.getClickThroughRate().toString());

        insightDTO.setCostPerClick(insight.getCostPerClick().toString());

        insightDTO.setImpressions(String.valueOf(insight.getImpressions()));

        if (insight.getNanosScore() != null){
            insightDTO.setNanosScore(insight.getNanosScore().toString());
        }

        if (insight.getWebsiteVisits() != null){
            insightDTO.setWebsiteVisits(insight.getWebsiteVisits().toString());
        }

        return insightDTO;
    }

    @PostConstruct
    private void initStaticDao() {
        platformRepository = this.platformRepository0;
    }

}
