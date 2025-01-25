package cringe.back.dao;

import cringe.back.entity.Point;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Stateless
public class PointDAOImpl implements PointDAO {

    @Inject
    private EntityManagerProvider entityManagerProvider;

    public PointDAOImpl() {}

    @Override
    public boolean checkArea(float x, float y, float r) {
        boolean inRectangle = (x <= 0 && x >= -r && y >= -r / 2 && y <= 0);
        boolean inTriangle = (x <= 0 && y >= 0 && y <= 0.5 * (x + r));
        boolean inCircle = (x >= 0 && y <= 0 && (x * x + y * y <= r / 2 * r / 2));

        return inRectangle || inTriangle || inCircle;
    }

    @Override
    public void save(Point point) {
        var start = Instant.now();

        point.setCondition(checkArea(point.getX(), point.getY(), point.getR()));

        var finish = Instant.now();
        var time = ChronoUnit.NANOS.between(start, finish);

        point.setTime(time);
        point.setDate();

        entityManagerProvider.getEmf().createEntityManager().persist(point);
    }

    @Override
    public void deleteAll(Long userId) {
        EntityManager em = entityManagerProvider.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Point p WHERE p.user.id = :userId")
                    .setParameter("userId", userId)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Point> findAll(Long userId) {
        try (EntityManager em = entityManagerProvider.getEmf().createEntityManager()) {
            TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p WHERE p.user.id = :userId", Point.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        }
    }
}
