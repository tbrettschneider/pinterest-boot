package de.tb.showroom.pinterest.repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"de.tb.showroom.pinterest.model"})
@EnableJpaRepositories(basePackages = {"de.tb.showroom.pinterest.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {

    @Bean
    public AutowireHelper autowireHelper(){
        return AutowireHelper.getInstance();
    }
}
