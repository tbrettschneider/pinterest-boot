package de.tb.showroom.pinterest.repositories;

import de.tb.showroom.pinterest.model.Pinboard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PinboardRepository extends CrudRepository<Pinboard, Long> {

    Pinboard findByTitle(String title);

    Pinboard findBySlug(String slug);

}
