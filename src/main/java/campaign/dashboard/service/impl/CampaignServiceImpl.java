package campaign.dashboard.service.impl;

import campaign.dashboard.repository.CampaignRepository;
import campaign.dashboard.service.CampaignService;
import campaign.dashboard.entity.Campaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    protected CampaignRepository campaignRepository;

    @Override
    public Campaign find(Long id) {
        Campaign result = null;

        Optional<Campaign> catalog = campaignRepository.findById(id);

        if (catalog.isPresent()) {
            result = catalog.get();
        }

        return result;
    }

    @Override
    public List<Campaign> findAll() {
        return (List<Campaign>) campaignRepository.findAll();
    }

    @Override
    public Campaign save(Campaign entity) {
        return campaignRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        campaignRepository.deleteById(id);
    }

    @Override
    public Campaign update(Campaign entity) {
        return campaignRepository.save(entity);
    }

}
