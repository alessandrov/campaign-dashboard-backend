package campaign.dashboard.service.impl;

import campaign.dashboard.service.PlatformTypeService;
import campaign.dashboard.entity.PlatformType;
import campaign.dashboard.repository.PlatformTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PlatformTypeServiceImpl implements PlatformTypeService {

    @Autowired
    protected PlatformTypeRepository platformTypeRepository;

    @Override
    public PlatformType find(Long id) {
        PlatformType result = null;

        Optional<PlatformType> platformType = platformTypeRepository.findById(id);

        if (platformType.isPresent()) {
            result = platformType.get();
        }

        return result;
    }

    @Override
    public List<PlatformType> findAll() {
        return (List<PlatformType>) platformTypeRepository.findAll();
    }

    @Override
    public PlatformType save(PlatformType entity) {
        return platformTypeRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        platformTypeRepository.deleteById(id);
    }

    @Override
    public PlatformType update(PlatformType entity) {
        return platformTypeRepository.save(entity);
    }

}
