package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoHibernate implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role findByRolename(String name) {
        return entityManager.createQuery("FROM ROLE WHERE name = :name", Role.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }
}