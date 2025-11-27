package com.ra.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class WorkingManageDTO {

    private Integer manageId; // để dùng cho edit

    @NotBlank(message = "Tên công việc không được để trống")
    @Size(max = 100, message = "Tên công việc tối đa 100 ký tự")
    private String workingName;

    @NotBlank(message = "Người thực hiện không được để trống")
    @Size(max = 50, message = "Người thực hiện tối đa 50 ký tự")
    private String personName;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "Thời gian không được để trống")
    @DecimalMin(value = "0.1", message = "Thời gian phải lớn hơn 0")
    private Float duration;

    @NotBlank(message = "Đơn vị thời gian không được để trống")
    @Size(max = 20, message = "Đơn vị thời gian tối đa 20 ký tự")
    private String durationUnit;

    @NotBlank(message = "Mô tả công việc không được để trống")
    @Size(max = 255, message = "Mô tả tối đa 255 ký tự")
    private String workingDescription;

    @NotBlank(message = "Tiến độ công việc không được để trống")
    @Size(max = 50, message = "Tiến độ tối đa 50 ký tự")
    private String workingProgress;

    @Size(max = 255, message = "Đường dẫn ảnh tối đa 255 ký tự")
    private String workingImage;

    @NotNull(message = "Trạng thái không được để trống")
    @Min(value = 0, message = "Trạng thái phải là 0, 1 hoặc 2")
    @Max(value = 2, message = "Trạng thái phải là 0, 1 hoặc 2")
    private Integer status;
}
