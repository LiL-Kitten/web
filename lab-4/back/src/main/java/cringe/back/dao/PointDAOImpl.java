package cringe.back.dao;

import cringe.back.dto.PointDTO;
import cringe.back.entity.Point;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Stateless
public class PointDAOImpl implements PointDAO, Convert<Point, PointDTO> {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @Override
    public void save(PointDTO point) {
        entityManager.persist(convertToEntity(point));
    }

    @Override
    public void deleteAll(Long userId) {
        entityManager.createQuery("DELETE FROM Point p WHERE p.user.id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public List<PointDTO> findAll(Long userId) {
        TypedQuery<Point> query = entityManager.createQuery("SELECT p FROM Point p WHERE p.user.id = :userId"
                , Point.class);
        query.setParameter("userId", userId);

        List<Point> points = query.getResultList();

        return points.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Point convertToEntity(PointDTO pointDTO) {
        Point point = new Point();
        point.setX(pointDTO.getX());
        point.setY(pointDTO.getY());
        point.setR(pointDTO.getR());

        var start = Instant.now();

        point.setCondition(pointDTO.areaChecker());

        var finish = Instant.now();
        var time = ChronoUnit.NANOS.between(start, finish);

        point.setTime(time);
        point.setDate();

        return point;
    }

    @Override
    public PointDTO convertToDTO(Point point) {
        return new PointDTO(point.getX(), point.getY(), point.getR(), point.isCondition());
    }
}
