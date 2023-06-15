package com.gft.app.config;

import com.gft.app.elw.model.EventHistory;
import com.gft.app.elw.model.ProcessInstance;
import com.gft.app.elw.repository.EventHistoryRepository;
import com.gft.app.elw.repository.ProcessInstanceRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration(exclude = JpaRepositoriesAutoConfiguration.class)
@ComponentScan(basePackages = {"com.gft.app"})
@EntityScan(basePackageClasses = {ProcessInstance.class, EventHistory.class})
@EnableJpaRepositories(basePackageClasses = {EventHistoryRepository.class, ProcessInstanceRepository.class})
@EnableTransactionManagement
public class AutoConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
