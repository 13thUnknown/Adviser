package edu.suai.recommendations.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "edu.suai.recommendations.repository",
        entityManagerFactoryRef = "recommendationsEntityManagerFactory",
        transactionManagerRef = "recommendationsTransactionManager")
public class RecommendationsDatasourceConfig  {

    @Bean(name = "recommendationsDataSourceProperties")
    @Primary
    @ConfigurationProperties("app.datasource.recommendations")
    public DataSourceProperties parkingDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "recommendationsDataSource")
    @Primary
    public DataSource parkingDataSource(@Qualifier("recommendationsDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "recommendationsEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean parkingEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                              @Qualifier("recommendationsDataSource") DataSource dataSource,
                                                                              HibernateProperties hibernateProperties,
                                                                              JpaProperties jpaProperties) {
        HibernateSettings hibernateSettings = new HibernateSettings();
        return builder
                .dataSource(dataSource)
                .packages("edu.suai.recommendations.model")
                .persistenceUnit("recommendationsEntityManager")
                .properties(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), hibernateSettings))
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager recommendationsTransactionManager(@Qualifier("recommendationsEntityManagerFactory") EntityManagerFactory recommendationsEntityManagerFactory) {
        return new JpaTransactionManager(recommendationsEntityManagerFactory);
    }


}
