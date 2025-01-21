package cringe.lab3.db;

import cringe.lab3.entity.Point;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.Serializable;
import java.util.List;

public class DBManager implements Serializable, Observer {

    private final transient EntityManagerFactory entityManagerFactory;
    private final transient EntityManager entityManager;

    public DBManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Point");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void save(List<Point> points) {
        entityManager.getTransaction().begin();
        for (Point point : points) {
            entityManager.persist(point);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Point").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Point> getPoints() {
        entityManager.getTransaction().begin();
        List<Point> points = entityManager.createQuery("SELECT p FROM Point p", Point.class).getResultList();
        entityManager.getTransaction().commit();
        return points;
    }

    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
