package cringe.back.dao;

import cringe.back.dto.PointDTO;
import cringe.back.entity.Point;
import cringe.back.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PointDAOImpl implements PointDAO, Convert<Point, PointDTO> {

    @Override
    public void save(User user, PointDTO pointDTO) {
        Point point = convertToEntity(pointDTO);
        point.setUser(user);

        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(point);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error saving point", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteAll(Long userId) {
        EntityManager em = PersistenceManager.getEntityManager();
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
            throw new RuntimeException("Error deleting points", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<PointDTO> findAll(Long userId) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            TypedQuery<Point> query = em.createQuery(
                    "SELECT p FROM Point p WHERE p.user.id = :userId", Point.class);
            query.setParameter("userId", userId);
            return query.getResultList().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    @Override
    public Point convertToEntity(PointDTO pointDTO) {
        Point point = new Point();
        point.setX(pointDTO.getX());
        point.setY(pointDTO.getY());
        point.setR(pointDTO.getR());

        Instant start = Instant.now();
        point.setCondition(pointDTO.areaChecker());
        long time = ChronoUnit.NANOS.between(start, Instant.now());

        point.setTime(time);
        point.setDate();
        return point;
    }

    @Override
    public PointDTO convertToDTO(Point point) {
        return new PointDTO(
                point.getX(),
                point.getY(),
                point.getR(),
                point.isCondition());
    }
}