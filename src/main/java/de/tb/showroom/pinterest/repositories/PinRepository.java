package de.tb.showroom.pinterest.repositories;

import de.tb.showroom.pinterest.model.Pin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PinRepository extends CrudRepository<Pin, Long> {
}
