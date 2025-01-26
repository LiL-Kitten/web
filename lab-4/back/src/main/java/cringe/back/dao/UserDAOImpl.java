package cringe.back.dao;

import cringe.back.dto.UserDTO;
import cringe.back.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserDAOImpl implements UserDAO, Convert<User, UserDTO> {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";

    @Override
    public void save(UserDTO userDTO) {
        entityManager.persist(convertToEntity(userDTO));
    }

    @Override
    public boolean exists(UserDTO userDTO) {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findByUsername", User.class);
        query.setParameter(USERNAME, userDTO.getUsername());
        return query.getResultStream().findFirst().isPresent();
    }

    @Override
    public boolean authenticate(UserDTO userDTO) {
        TypedQuery<User> query = entityManager.createNamedQuery("User.authenticate", User.class);
        query.setParameter(USERNAME, userDTO.getUsername());
        query.setParameter(PASSWORD, userDTO.getPassword());
        return query.getResultStream().findFirst().isPresent();
    }

    @Override
    public Long getId(UserDTO userDTO) {
        TypedQuery<User> query = entityManager.createNamedQuery("User.authenticate", User.class);
        query.setParameter(USERNAME, userDTO.getUsername());
        query.setParameter(PASSWORD, userDTO.getPassword());

        User user = query.getResultStream().findFirst().orElse(null);
        return (user != null) ? user.getId() : null;
    }

    @Override
    public User convertToEntity(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getPassword());
        user.setId(getId(userDTO));
        return user;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUsername(), user.getPassword());
    }
}
