package campaign.dashboard.service.impl;

import campaign.dashboard.entity.TargetAudience;
import campaign.dashboard.repository.TargetAudienceRepository;
import campaign.dashboard.service.TargetAudienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class TargetAudienceServiceImpl implements TargetAudienceService {

    @Autowired
    protected TargetAudienceRepository targetAudienceRepository;

    @Override
    public TargetAudience find(Long id) {
        TargetAudience result = null;

        Optional<TargetAudience> targetAudience = targetAudienceRepository.findById(id);

        if (targetAudience.isPresent()) {
            result = targetAudience.get();
        }

        return result;
    }

    @Override
    public TargetAudience findByPlatform(Long platformId) {
        TargetAudience result = null;

        TargetAudience targetAudience = targetAudienceRepository.findByPlatformId(platformId);

        return targetAudience;
    }

    @Override
    public List<TargetAudience> findAll() {
        return (List<TargetAudience>) targetAudienceRepository.findAll();
    }

    @Override
    public TargetAudience save(TargetAudience entity) {
        return targetAudienceRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        targetAudienceRepository.deleteById(id);
    }

    @Override
    public TargetAudience update(TargetAudience entity) {
        return targetAudienceRepository.save(entity);
    }

}
