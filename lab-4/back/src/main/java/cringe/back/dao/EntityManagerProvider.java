package cringe.back.dao;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Singleton
public class EntityManagerProvider {
    private EntityManagerFactory emf;

    public EntityManagerProvider() {
    }

    @PostConstruct
    public void init() {
            emf = Persistence.createEntityManagerFactory("default");
    }

    @PreDestroy
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
