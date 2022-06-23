package com.expertsvision.erp.core;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.util.Properties;



@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	
	@Bean
    public HikariDataSource HikariDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://ec2-54-159-175-113.compute-1.amazonaws.com/d2khvtcfer4l0l");
        dataSource.setUsername("kzfjtseurjgqwa");
        dataSource.setPassword("87764eb0736b39924f886c3a12c2923d294ef884bc39f2f9f46a346044e1150f");
        dataSource.setMaximumPoolSize(3);
        return dataSource;
    }
	
	private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "none");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
        hibernateProperties.setProperty("hibernate.ddl_auto", "none");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
//        hibernateProperties.setProperty("hibernate.format_sql", "true");
//        hibernateProperties.setProperty("hibernate.generate_statistics", "true");          
        return hibernateProperties;
    }

	@Bean
    public LocalSessionFactoryBean sessionFactory(@Autowired DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[] {"com.expertsvision.erp"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(@Autowired SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }


}