package cringe.backend.dao;

import cringe.backend.entity.Point;
import cringe.backend.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Stateless
public class PointDAOImpl implements PointDAO {

    @PersistenceContext
    private EntityManager em;

    public boolean checkArea(float x, float y, float r) {
        boolean inRectangle = (x <= 0 && x >= -r && y >= -r / 2 && y <= 0);
        boolean inTriangle = (x <= 0 && y >= 0 && y <= 0.5 * (x + r));
        boolean inCircle = (x >= 0 && y <= 0 && (x * x + y * y <= r / 2 * r / 2));

        return inRectangle || inTriangle || inCircle;
    }

    @Override
    public void save(String userName, Point point) {
        if (!exist(userName)) {
            throw new IllegalArgumentException("User does not exist in the database");
        }

        var start = Instant.now();

        point.setCondition(checkArea(point.getX(), point.getY(), point.getR()));

        var finish = Instant.now();
        var time = ChronoUnit.NANOS.between(start, finish);

        point.setTime(time);
        point.setUser(findByUsername(userName));
        point.setDate();

        em.persist(point);
    }

    @Override
    public void deleteAll(Long userId) {
        em.createQuery("DELETE FROM Point p WHERE p.user.id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public List<Point> findAll(Long userId) {
        TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p WHERE p.user.id = :userId", Point.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public boolean exist(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return !query.getResultList().isEmpty();
    }

    @Override
    public User findByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return null;
        }

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);

        List<User> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
