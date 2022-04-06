package com.example.multidb.examplemultidb.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.example.multidb.examplemultidb.card")
@EnableJpaRepositories(
        basePackages = "com.example.multidb.examplemultidb.card",
        entityManagerFactoryRef = "cardEntityManager",
        transactionManagerRef = "cardTransactionManager"
)
public class CardDataSourceConfiguration {


    @Bean(name = "cardHibernateProps")
    @ConfigurationProperties(prefix = "card.jpa")
    public Map<String, Object> hibernateProps(){
        return new HashMap<>();
    }

    @Primary
    @Bean
    @ConfigurationProperties("card.datasource")
    public DataSourceProperties cardDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource cardDataSource(){
        DataSourceProperties properties = this.cardDataSourceProperties();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(properties.getDriverClassName());
        dataSourceBuilder.url(properties.getUrl());
        dataSourceBuilder.username(properties.getUsername());
        dataSourceBuilder.password(properties.getPassword());
        return dataSourceBuilder.build();
    }

    @Primary
    @Bean(name = "cardEntityManager")
    public LocalContainerEntityManagerFactoryBean cardEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(cardDataSource())
                .packages("com.example.multidb.examplemultidb.card")
                .properties(PropertyUtils.parsePropertyMap(this.hibernateProps(), "hibernate"))
                .persistenceUnit("card")
                .build();
    }

    @Primary
    @Bean(name = "cardTransactionManager")
    public PlatformTransactionManager cardTransactionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(cardEntityManagerFactory(builder).getObject());
    }
}
