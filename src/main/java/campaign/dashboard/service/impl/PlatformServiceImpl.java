package campaign.dashboard.service.impl;

import campaign.dashboard.entity.Platform;
import campaign.dashboard.repository.PlatformRepository;
import campaign.dashboard.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    protected PlatformRepository platformRepository;

    @Override
    public Platform find(Long id) {
        Platform result = null;

        Optional<Platform> platform = platformRepository.findById(id);

        if (platform.isPresent()) {
            result = platform.get();
        }

        return result;
    }

    @Override
    public List<Platform> findByCampaign(Long campaignId) {
        List<Platform> result = null;

        List<Platform> platforms = platformRepository.findByCampaignId(campaignId);

        return platforms;
    }

    @Override
    public List<Platform> findAll() {
        return (List<Platform>) platformRepository.findAll();
    }

    @Override
    public Platform save(Platform entity) {
        return platformRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        platformRepository.deleteById(id);
    }

    @Override
    public Platform update(Platform entity) {
        return platformRepository.save(entity);
    }

}
