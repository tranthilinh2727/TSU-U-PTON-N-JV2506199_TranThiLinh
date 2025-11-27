package com.ra.repository.impl;

import com.ra.model.WorkingManagement;
import com.ra.repository.WorkingManageRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class WorkingManageRepositoryImpl implements WorkingManageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WorkingManagement> findAll() {
        String jpql = "SELECT w FROM WorkingManagement w ORDER BY w.manageId DESC";
        return entityManager.createQuery(jpql, WorkingManagement.class).getResultList();
    }

    @Override
    public List<WorkingManagement> findByPage(int page, int size) {
        String jpql = "SELECT w FROM WorkingManagement w ORDER BY w.manageId DESC";
        return entityManager.createQuery(jpql, WorkingManagement.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public int countTotal() {
        String jpql = "SELECT COUNT(w) FROM WorkingManagement w";
        return ((Long) entityManager.createQuery(jpql).getSingleResult()).intValue();
    }

    @Override
    public void save(WorkingManagement working) {
        entityManager.persist(working);
    }

    @Override
    public void update(WorkingManagement working) {
        entityManager.merge(working);
    }

    @Override
    public WorkingManagement findById(Integer id) {
        return entityManager.find(WorkingManagement.class, id);
    }

    @Override
    public void delete(Integer id) {
        WorkingManagement working = findById(id);
        if (working != null) {
            entityManager.remove(working);
        }
    }

    @Override
    public List<WorkingManagement> searchByName(String keyword, int page, int size) {
        String jpql = "SELECT w FROM WorkingManagement w WHERE LOWER(w.workingName) LIKE LOWER(:kw) ORDER BY w.manageId DESC";
        return entityManager.createQuery(jpql, WorkingManagement.class)
                .setParameter("kw", "%" + keyword + "%")
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<WorkingManagement> searchByStatus(Integer status, int page, int size) {
        String jpql = "SELECT w FROM WorkingManagement w WHERE w.status = :status ORDER BY w.manageId DESC";
        return entityManager.createQuery(jpql, WorkingManagement.class)
                .setParameter("status", status)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public boolean existsByWorkingName(String workingName) {
        String jpql = "SELECT COUNT(w) FROM WorkingManagement w WHERE w.workingName = :name";
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("name", workingName)
                .getSingleResult();
        return count > 0;
    }
}
