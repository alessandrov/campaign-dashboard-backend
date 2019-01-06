package campaign.dashboard.service.impl;

import campaign.dashboard.entity.Creative;
import campaign.dashboard.repository.CreativeRepository;
import campaign.dashboard.service.CreativeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CreativeServiceImpl implements CreativeService {

    private static final Logger logger = LoggerFactory.getLogger(CreativeServiceImpl.class);

    @Autowired
    protected CreativeRepository creativeRepository;

    @Override
    public Creative find(Long id) {
        Creative result = null;

        Optional<Creative> creative = creativeRepository.findById(id);

        if (creative.isPresent()) {
            result = creative.get();
        }

        return result;
    }

    @Override
    public Creative findByPlatform(Long platformId) {
        Creative result = null;

        Optional<Creative> creative = creativeRepository.findByPlatformId(platformId);

        if (creative.isPresent()) {
            result = creative.get();
        }

        return result;
    }

    @Override
    public List<Creative> findAll() {
        return (List<Creative>) creativeRepository.findAll();
    }

    @Override
    public Creative save(Creative entity) {
        return creativeRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        creativeRepository.deleteById(id);
    }

    @Override
    public Creative update(Creative entity) {
        return creativeRepository.save(entity);
    }

}
