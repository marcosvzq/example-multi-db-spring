package com.example.multidb.examplemultidb.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@ComponentScan(basePackages = "com.example.multidb.examplemultidb.cardHolder")
@EnableJpaRepositories(
        basePackages = "com.example.multidb.examplemultidb.cardHolder",
        entityManagerFactoryRef = "cardHolderEntityManager",
        transactionManagerRef = "cardHolderTransactionManager"
)
public class CardHolderDataSourceConfiguration {

    @Bean(name = "cardHolderHibernateProps")
    @ConfigurationProperties(prefix = "cardholder.jpa.hibernate")
    public Map<String, Object> hibernateProps(){
        return new HashMap<>();
    }

    @Bean
    @ConfigurationProperties("cardholder.datasource")
    public DataSourceProperties cardHolderDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource cardHolderDataSource(){
        DataSourceProperties properties = this.cardHolderDataSourceProperties();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(properties.getDriverClassName());
        dataSourceBuilder.url(properties.getUrl());
        dataSourceBuilder.username(properties.getUsername());
        dataSourceBuilder.password(properties.getPassword());
        return dataSourceBuilder.build();
    }

    @Bean(name = "cardHolderEntityManager")
    public LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(cardHolderDataSource())
                .packages("com.example.multidb.examplemultidb.cardHolder")
                .properties(PropertyUtils.parsePropertyMap(this.hibernateProps(), "hibernate"))
                .persistenceUnit("cardholder")
                .build();
    }

    @Bean(name = "cardHolderTransactionManager")
    public PlatformTransactionManager cardHolderTransactionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(cardHolderEntityManagerFactory(builder).getObject());
    }
}
