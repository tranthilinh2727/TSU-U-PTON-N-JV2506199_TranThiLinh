package com.ra.repository;

import com.ra.model.WorkingManagement;

import java.util.List;

public interface WorkingManageRepository {

    List<WorkingManagement> findAll();

    List<WorkingManagement> findByPage(int page, int size);

    int countTotal();

    void save(WorkingManagement working);

    void update(WorkingManagement working);

    WorkingManagement findById(Integer id);

    void delete(Integer id);

    List<WorkingManagement> searchByName(String keyword, int page, int size);

    List<WorkingManagement> searchByStatus(Integer status, int page, int size);

    boolean existsByWorkingName(String workingName);
}
