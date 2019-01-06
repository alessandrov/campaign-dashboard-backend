package campaign.dashboard.service.impl;

import campaign.dashboard.service.InsightService;
import campaign.dashboard.entity.Insight;
import campaign.dashboard.repository.InsightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class InsightServiceImpl implements InsightService {

    @Autowired
    protected InsightRepository insightRepository;

    @Override
    public Insight find(Long id) {
        Insight result = null;

        Optional<Insight> insight = insightRepository.findById(id);

        if (insight.isPresent()) {
            result = insight.get();
        }

        return result;
    }

    @Override
    public Insight findByPlatform(Long platformId) {
        Insight result = null;

        Optional<Insight> insight = insightRepository.findByPlatformId(platformId);

        if (insight.isPresent()) {
            result = insight.get();
        }

        return result;
    }

    @Override
    public List<Insight> findAll() {
        return (List<Insight>) insightRepository.findAll();
    }

    @Override
    public Insight save(Insight entity) {
        return insightRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        insightRepository.deleteById(id);
    }

    @Override
    public Insight update(Insight entity) {
        return insightRepository.save(entity);
    }

}
