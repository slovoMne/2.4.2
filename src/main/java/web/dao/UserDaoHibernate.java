package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoHibernate implements UserDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("FROM User u JOIN FETCH u.roles WHERE u.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUserById(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User u JOIN FETCH u.roles", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
//        return entityManager.find(User.class, id);
        return (User) entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.roles where u.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User showUser(long id) {
        return null;
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
