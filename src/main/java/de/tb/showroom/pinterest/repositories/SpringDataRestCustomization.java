package de.tb.showroom.pinterest.repositories;

import de.tb.showroom.pinterest.model.Pinboard;
import de.tb.showroom.pinterest.model.User;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
        config.withEntityLookup().forRepository(PinboardRepository.class, Pinboard::getSlug, PinboardRepository::findBySlug);
        config.withEntityLookup().forRepository(UserRepository.class, User::getUsername, UserRepository::findByUsername);
    }
}
