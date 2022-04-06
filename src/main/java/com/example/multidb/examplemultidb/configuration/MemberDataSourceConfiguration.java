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
@ComponentScan(basePackages = "com.example.multidb.examplemultidb.member")
@EnableJpaRepositories(
        basePackages = "com.example.multidb.examplemultidb.member",
        entityManagerFactoryRef = "memberEntityManager",
        transactionManagerRef = "memberTransactionManager"
)
public class MemberDataSourceConfiguration {

    @Bean(name = "memberHibernateProps")
    @ConfigurationProperties(prefix = "member.hibernate")
    public Map<String, Object> hibernateProps(){
        return new HashMap<>();
    }

    @Bean
    @ConfigurationProperties("member.datasource")
    public DataSourceProperties memberDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource memberDataSource(){
        DataSourceProperties properties = this.memberDataSourceProperties();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(properties.getDriverClassName());
        dataSourceBuilder.url(properties.getUrl());
        dataSourceBuilder.username(properties.getUsername());
        dataSourceBuilder.password(properties.getPassword());
        return dataSourceBuilder.build();
    }

    @Bean(name = "memberEntityManager")
    public LocalContainerEntityManagerFactoryBean memberEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(memberDataSource())
                .packages("com.example.multidb.examplemultidb.member")
                .properties(PropertyUtils.parsePropertyMap(this.hibernateProps(), "hibernate"))
                .persistenceUnit("member")
                .build();
    }

    @Bean(name = "memberTransactionManager")
    public PlatformTransactionManager memberTransactionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(Objects.requireNonNull(memberEntityManagerFactory(builder).getObject()));
    }
}
