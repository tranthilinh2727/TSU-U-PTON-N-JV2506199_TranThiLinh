package com.ra.service;

import com.ra.model.WorkingManagement;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkingManageService {

    List<WorkingManagement> findAll();

    List<WorkingManagement> findByPage(int page, int size);

    int totalPage(int size);

    void save(WorkingManagement working, MultipartFile imageFile);

    void update(WorkingManagement working, MultipartFile imageFile);

    WorkingManagement findById(Integer id);

    void delete(Integer id);

    List<WorkingManagement> searchByName(String keyword, int page, int size);

    List<WorkingManagement> searchByStatus(Integer status, int page, int size);

    boolean isWorkingNameExists(String workingName);

}
