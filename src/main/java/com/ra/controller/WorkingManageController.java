package com.ra.controller;

import com.ra.dto.WorkingManageDTO;
import com.ra.model.WorkingManagement;
import com.ra.service.WorkingManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/working")
public class WorkingManageController {

    @Autowired
    private WorkingManageService workingManageService;

    @GetMapping("/list")
    public String showWorkingManagement(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(required = false) Integer status,
            Model model
    ) {
        int size = 5;
        List<WorkingManagement> workingList;

        if (status != null) {
            workingList = workingManageService.searchByStatus(status, page, size);
        } else if (!keyword.isEmpty()) {
            workingList = workingManageService.searchByName(keyword, page, size);
        } else {
            workingList = workingManageService.findByPage(page, size);
        }

        int totalPage = workingManageService.totalPage(size);

        model.addAttribute("workingList", workingList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);

        return "working-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("working", new WorkingManageDTO());
        return "working-form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("working") WorkingManageDTO dto,
                           BindingResult result,
                           @RequestParam("imageFile") MultipartFile imageFile,
                           Model model) {

        if (result.hasErrors()) {
            return "working-form";
        }

        if (workingManageService.isWorkingNameExists(dto.getWorkingName())) {
            result.rejectValue("workingName", "error.working", "Tên công việc đã tồn tại");
            return "working-form";
        }

        try {
            WorkingManagement entity = mapDtoToEntity(dto);
            workingManageService.save(entity, imageFile);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lưu công việc: " + e.getMessage());
            return "working-form";
        }

        return "redirect:/working/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        WorkingManagement entity = workingManageService.findById(id);
        if (entity == null) {
            return "redirect:/working/list";
        }

        WorkingManageDTO dto = mapEntityToDto(entity);
        model.addAttribute("working", dto);
        return "working-form";
    }

    @PostMapping("/edit")
    public String doEdit(@Valid @ModelAttribute("working") WorkingManageDTO dto,
                         BindingResult result,
                         @RequestParam("imageFile") MultipartFile imageFile,
                         Model model) {

        if (result.hasErrors()) {
            return "working-form";
        }

        try {
            WorkingManagement entity = mapDtoToEntity(dto);
            entity.setManageId(dto.getManageId());
            workingManageService.update(entity, imageFile);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật công việc: " + e.getMessage());
            return "working-form";
        }

        return "redirect:/working/list";
    }

    @GetMapping("/change-status/{id}")
    public String changeStatus(@PathVariable("id") Integer id) {
        WorkingManagement working = workingManageService.findById(id);
        if (working != null && working.getStatus() != 2) {
            int nextStatus = (working.getStatus() + 1) % 3;
            working.setStatus(nextStatus);
            workingManageService.update(working, null);
        }
        return "redirect:/working/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        workingManageService.delete(id);
        return "redirect:/working/list";
    }

    private WorkingManagement mapDtoToEntity(WorkingManageDTO dto) {
        WorkingManagement entity = new WorkingManagement();
        entity.setWorkingName(dto.getWorkingName());
        entity.setPersonName(dto.getPersonName());
        entity.setStartDate(dto.getStartDate());
        entity.setDuration(dto.getDuration());
        entity.setDurationUnit(dto.getDurationUnit());
        entity.setWorkingDescription(dto.getWorkingDescription());
        entity.setWorkingProgress(dto.getWorkingProgress());
        entity.setWorkingImage(dto.getWorkingImage());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    private WorkingManageDTO mapEntityToDto(WorkingManagement entity) {
        WorkingManageDTO dto = new WorkingManageDTO();
        dto.setManageId(entity.getManageId());
        dto.setWorkingName(entity.getWorkingName());
        dto.setPersonName(entity.getPersonName());
        dto.setStartDate(entity.getStartDate());
        dto.setDuration(entity.getDuration());
        dto.setDurationUnit(entity.getDurationUnit());
        dto.setWorkingDescription(entity.getWorkingDescription());
        dto.setWorkingProgress(entity.getWorkingProgress());
        dto.setWorkingImage(entity.getWorkingImage());
        dto.setStatus(entity.getStatus());
        return dto;
    }

}
