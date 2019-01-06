package campaign.dashboard.service.common;

import java.util.List;

public interface AbstractService<T, Long> {

    T find(final Long id);

    List<T> findAll();

    T save(final T entity);

    T update(final T entity);

    void delete(final Long entityId);

}
