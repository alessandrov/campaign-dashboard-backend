package campaign.dashboard.repository;

import campaign.dashboard.entity.Creative;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreativeRepository extends CrudRepository<Creative, Long> {

    Optional<Creative> findByPlatformId(Long platformId);

}
