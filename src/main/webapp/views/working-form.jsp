<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Form công việc</title>
</head>
<body>
<h2>
    <c:choose>
        <c:when test="${empty working.manageId}">Thêm mới công việc</c:when>
        <c:otherwise>Chỉnh sửa công việc</c:otherwise>
    </c:choose>
</h2>

<!-- Hiển thị lỗi hệ thống -->
<c:if test="${not empty error}">
    <div style="color:red;">${error}</div>
</c:if>

<!-- Hiển thị lỗi validate -->
<c:if test="${not empty org.springframework.validation.BindingResult.working}">
    <div style="color:red;">
        <ul>
            <c:forEach var="err" items="${org.springframework.validation.BindingResult.working.allErrors}">
                <li><c:out value="${err.defaultMessage}"/></li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<!-- Form -->
<form method="post" enctype="multipart/form-data"
      action="${pageContext.request.contextPath}<c:choose>
                  <c:when test='${empty working.manageId}'>/working/create</c:when>
                  <c:otherwise>/working/edit</c:otherwise>
              </c:choose>">

    <input type="hidden" name="manageId" value="${working.manageId}"/>

    <label for="workingName">Tên công việc:</label>
    <input type="text" id="workingName" name="workingName" value="${working.workingName}" required/><br/>

    <label for="personName">Người thực hiện:</label>
    <input type="text" id="personName" name="personName" value="${working.personName}" required/><br/>

    <label for="startDate">Ngày bắt đầu:</label>
    <input type="date" id="startDate" name="startDate" value="${working.startDate}" required/><br/>

    <label for="duration">Thời gian:</label>
    <input type="number" id="duration" step="0.1" name="duration" value="${working.duration}" required/><br/>

    <label for="durationUnit">Đơn vị:</label>
    <input type="text" id="durationUnit" name="durationUnit" value="${working.durationUnit}" required/><br/>

    <label for="workingDescription">Mô tả:</label>
    <input type="text" id="workingDescription" name="workingDescription" value="${working.workingDescription}" required/><br/>

    <label for="workingProgress">Tiến độ:</label>
    <input type="text" id="workingProgress" name="workingProgress" value="${working.workingProgress}" required/><br/>

    <label for="imageFile">Ảnh:</label>
    <input type="file" id="imageFile" name="imageFile"/><br/>

    <c:if test="${not empty working.workingImage}">
        <img src="${working.workingImage}" width="150"/><br/>
        <input type="hidden" name="workingImage" value="${working.workingImage}"/>
    </c:if>

    <label for="status">Trạng thái:</label>
    <select id="status" name="status">
        <option value="0" <c:if test="${working.status == 0}">selected</c:if>>Đã hủy</option>
        <option value="1" <c:if test="${working.status == 1 || empty working.status}">selected</c:if>>Đang thực hiện</option>
        <option value="2" <c:if test="${working.status == 2}">selected</c:if>>Hoàn thành</option>
    </select><br/><br/>

    <button type="submit">Lưu</button>
    <a href="${pageContext.request.contextPath}/working/list">Hủy</a>

    <c:if test="${not empty working.manageId}">
        |
        <a href="${pageContext.request.contextPath}/working/delete/${working.manageId}"
           onclick="return confirm('Bạn có chắc chắn muốn xóa công việc này không?')">🗑️ Xóa</a>
    </c:if>
</form>
</body>
</html>
