package project.server.hibernate.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
/*
@Configuration
@EnableTransactionManagement
@ComponentScan({"project.server.hibernate"})
@PropertySource(value = {"classpath:dboptions.properties"})*/
public class HibernateConfiguration {
/*
    @Autowired
    private Environment environment;

    @Autowired
    public DataSource dataSource;

    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory factory){
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(factory);
        return txManager;
    }

    @Bean
    @Autowired
    public OpenSessionInViewInterceptor sessionInViewInterceptor(SessionFactory sessionFactory){
        OpenSessionInViewInterceptor openInterceptor = new OpenSessionInViewInterceptor();
        openInterceptor.setSessionFactory(sessionFactory);
        return openInterceptor;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[]{"project.server.hibernate.entities"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }*/
}
