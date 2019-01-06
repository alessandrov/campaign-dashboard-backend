package campaign.dashboard.repository;

import campaign.dashboard.entity.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Long> {

    Keyword findByName(String name);

}
