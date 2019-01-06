package campaign.dashboard.repository;

import campaign.dashboard.entity.Insight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsightRepository extends CrudRepository<Insight, Long> {

    Optional<Insight> findByPlatformId(Long platformId);

}
