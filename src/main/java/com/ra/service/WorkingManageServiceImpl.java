package com.ra.service;

import com.ra.model.WorkingManagement;
import com.ra.repository.WorkingManageRepository;
import com.ra.service.WorkingManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class WorkingManageServiceImpl implements WorkingManageService {

    @Autowired
    private WorkingManageRepository workingRepo;

    @Override
    public List<WorkingManagement> findAll() {
        return workingRepo.findAll();
    }

    @Override
    public List<WorkingManagement> findByPage(int page, int size) {
        return workingRepo.findByPage(page, size);
    }

    @Override
    public int totalPage(int size) {
        int total = workingRepo.countTotal();
        return (int) Math.ceil((double) total / size);
    }

    @Override
    public void save(WorkingManagement working, MultipartFile imageFile) {
        handleImageUpload(working, imageFile);
        workingRepo.save(working);
    }

    @Override
    public void update(WorkingManagement working, MultipartFile imageFile) {
        handleImageUpload(working, imageFile);
        workingRepo.update(working);
    }

    @Override
    public WorkingManagement findById(Integer id) {
        return workingRepo.findById(id);
    }

    @Override
    public void delete(Integer id) {
        workingRepo.delete(id);
    }

    @Override
    public List<WorkingManagement> searchByName(String keyword, int page, int size) {
        return workingRepo.searchByName(keyword, page, size);
    }

    @Override
    public List<WorkingManagement> searchByStatus(Integer status, int page, int size) {
        return workingRepo.searchByStatus(status, page, size);
    }

    @Override
    public boolean isWorkingNameExists(String workingName) {
        return workingRepo.existsByWorkingName(workingName);
    }

    private void handleImageUpload(WorkingManagement working, MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                String uploadPath = "uploads/" + fileName;
                File dest = new File(uploadPath);
                dest.getParentFile().mkdirs(); // tạo thư mục nếu chưa có
                imageFile.transferTo(dest);
                working.setWorkingImage("/" + uploadPath);
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi upload ảnh", e);
            }
        } else if (working.getWorkingImage() == null) {
            working.setWorkingImage("");
        }
    }

}

