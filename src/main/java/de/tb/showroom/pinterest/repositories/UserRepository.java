package de.tb.showroom.pinterest.repositories;

import de.tb.showroom.pinterest.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}