package com.ra.repository;

import com.ra.model.WorkingManagement;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class WorkingManageRepositoryImpl implements WorkingManageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WorkingManagement> findAll() {
        return entityManager
                .createQuery("select w from WorkingManagement w order by w.manageId desc",
                        WorkingManagement.class)
                .getResultList();
    }

    @Override
    public List<WorkingManagement> findWorkByPage(int page, int size) {
        return entityManager
                .createQuery("select w from WorkingManagement w order by w.manageId desc",
                        WorkingManagement.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public WorkingManagement findById(int id) {
        return entityManager.find(WorkingManagement.class, id);
    }

    @Override
    public void save(WorkingManagement workingManagement) {
        entityManager.persist(workingManagement);
    }

    @Override
    public void update(WorkingManagement workingManagement) {
        entityManager.merge(workingManagement);
    }

    @Override
    public void delete(int id) {
        WorkingManagement workingManagement = findById(id);
        if (workingManagement != null) {
            entityManager.remove(workingManagement);
        }
    }

    @Override
    public int count() {
        Long count = entityManager
                .createQuery("select count(w) from WorkingManagement w", Long.class)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    public List<WorkingManagement> search(String keyword, int page, int size) {
        String query = "select w from WorkingManagement w " +
                "where w.workingName like :kw or w.personName like :kw " +
                "order by w.manageId desc";

        return entityManager.createQuery(query, WorkingManagement.class)
                .setParameter("kw","%" + keyword + "%")
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
