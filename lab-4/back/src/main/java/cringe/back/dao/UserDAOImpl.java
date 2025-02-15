package cringe.back.dao;

import cringe.back.dto.UserDTO;
import cringe.back.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

@Stateless
public class UserDAOImpl implements UserDAO, Convert<User, UserDTO> {

    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";

    @Override
    public void save(UserDTO userDTO) {
        try (EntityManager em = PersistenceManager.getEntityManager()) {
            em.getTransaction().begin();
            em.persist(convertToEntity(userDTO));
            em.getTransaction().commit();
        }
    }

    @Override
    public boolean exists(UserDTO userDTO) {
        try (EntityManager em = PersistenceManager.getEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
            query.setParameter(USERNAME, userDTO.getUsername());
            boolean exists = query.getResultStream().findFirst().isPresent();
            em.getTransaction().commit();
            return exists;
        }
    }

    @Override
    public boolean authenticate(UserDTO userDTO) {
        try (EntityManager em = PersistenceManager.getEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createNamedQuery("User.authenticate", User.class);
            query.setParameter(USERNAME, userDTO.getUsername());
            query.setParameter(PASSWORD, userDTO.getPassword());
            boolean authenticated = query.getResultStream().findFirst().isPresent();
            em.getTransaction().commit();
            return authenticated;
        }
    }

    @Override
    public Long getId(UserDTO userDTO) {
        try (EntityManager em = PersistenceManager.getEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createNamedQuery("User.authenticate", User.class);
            query.setParameter(USERNAME, userDTO.getUsername());
            query.setParameter(PASSWORD, userDTO.getPassword());
            Optional<User> user = query.getResultStream().findFirst();
            em.getTransaction().commit();
            return user.map(User::getId).orElse(null);
        }
    }

    @Override
    public User findById(Long id) {
        try (EntityManager em = PersistenceManager.getEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            em.getTransaction().commit();
            return user;
        }
    }

    @Override
    public String getUserPassword(String username) {
        try (EntityManager em = PersistenceManager.getEntityManager()) {
            TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
            query.setParameter(USERNAME, username);
            Optional<User> user = query.getResultStream().findFirst();
            return user.map(User::getPassword).orElse(null);
        }
    }

    @Override
    public User convertToEntity(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getPassword());
    }

    @Override
    public UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUsername(), user.getPassword());
    }
}