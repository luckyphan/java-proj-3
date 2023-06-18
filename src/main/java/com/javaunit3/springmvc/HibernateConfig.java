package com.javaunit3.springmvc;

import com.javaunit3.springmvc.model.MovieEntity;
import com.javaunit3.springmvc.model.VoteEntity;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//declares at least one bean, process and generate Spring beans
@Configuration
public class HibernateConfig {
    @Bean
    public SessionFactory getFactory() {
//        SessionFactory factory = new org.hibernate.cfg.Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
        //makes a persistent MovieEntity object to be mapped in DB
        SessionFactory factory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(MovieEntity.class)
                .addAnnotatedClass(VoteEntity.class)
                .buildSessionFactory();

        return factory;
    }
}
