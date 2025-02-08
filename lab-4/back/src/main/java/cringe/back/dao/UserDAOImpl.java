package cringe.back.dao;

import cringe.back.dto.UserDTO;
import cringe.back.entity.User;
import cringe.back.exceptions.UserNotFoundException;
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
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(convertToEntity(userDTO));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error saving user", e);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean exists(UserDTO userDTO) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
            query.setParameter(USERNAME, userDTO.getUsername());
            boolean exists = query.getResultStream().findFirst().isPresent();
            em.getTransaction().commit();
            return exists;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new UserNotFoundException("user not found");
        } finally {
            em.close();
        }
    }

    @Override
    public boolean authenticate(UserDTO userDTO) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createNamedQuery("User.authenticate", User.class);
            query.setParameter(USERNAME, userDTO.getUsername());
            query.setParameter(PASSWORD, userDTO.getPassword());
            boolean authenticated = query.getResultStream().findFirst().isPresent();
             em.getTransaction().commit();
            return authenticated;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Authentication error", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Long getId(UserDTO userDTO) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createNamedQuery("User.authenticate", User.class);
            query.setParameter(USERNAME, userDTO.getUsername());
            query.setParameter(PASSWORD, userDTO.getPassword());
            Optional<User> user = query.getResultStream().findFirst();
            em.getTransaction().commit();
            return user.map(User::getId).orElse(null);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error getting user ID", e);
        } finally {
            em.close();
        }
    }

    @Override
    public User findById(Long id) {
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error finding user by ID", e);
        } finally {
            em.close();
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