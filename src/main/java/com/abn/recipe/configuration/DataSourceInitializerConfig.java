package com.abn.recipe.configuration;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataSourceInitializerConfig {

    @Value("classpath:data-dev.sql")
    private Resource devDataPoputatorResource;

    @Bean
    @Profile(value = "dev")
    public DataSourceInitializer getDataSourceInitializer(final DataSource dataSource) {
        val initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(getDatabasePopulator());
        return initializer;
    }

    private DatabasePopulator getDatabasePopulator() {
        val populator = new ResourceDatabasePopulator();
        populator.addScript(devDataPoputatorResource);
        return populator;
    }
}
