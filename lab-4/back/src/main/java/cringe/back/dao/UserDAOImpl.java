package cringe.back.dao;

import cringe.back.entity.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserDAOImpl implements UserDAO {

    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Override
    public void save(User user) {
        EntityManager entityManager = entityManagerProvider.getEmf().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean exists(String username) {
        EntityManager entityManager = entityManagerProvider.getEmf().createEntityManager();
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            return query.getResultStream().findFirst().isPresent();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean check(String username, long password) {
        EntityManager entityManager = entityManagerProvider.getEmf().createEntityManager();
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getResultStream().findFirst().isPresent();
        } finally {
            entityManager.close();
        }
    }
}
