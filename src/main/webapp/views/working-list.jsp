<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách công việc</title>
</head>
<body>
<h2>Danh sách công việc</h2>

<!-- Tìm kiếm -->
<form method="get" action="${pageContext.request.contextPath}/working/list">
    <input type="text" name="keyword" value="${keyword}" placeholder="Tìm theo tên..."/>
    <select name="status">
        <option value="">-- Trạng thái --</option>
        <option value="0" <c:if test="${status == 0}">selected</c:if>>Đã hủy</option>
        <option value="1" <c:if test="${status == 1}">selected</c:if>>Đang thực hiện</option>
        <option value="2" <c:if test="${status == 2}">selected</c:if>>Hoàn thành</option>
    </select>
    <button type="submit">Tìm</button>
    <a href="${pageContext.request.contextPath}/working/create">➕ Thêm mới</a>
</form>

<!-- Bảng danh sách -->
<table border="1" cellpadding="5" cellspacing="0">
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên công việc</th>
        <th>Người thực hiện</th>
        <th>Ngày bắt đầu</th>
        <th>Thời gian</th>
        <th>Tiến độ</th>
        <th>Ảnh</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${workingList}" var="w">
        <tr>
            <td>${w.manageId}</td>
            <td>${w.workingName}</td>
            <td>${w.personName}</td>
            <td>${w.startDate}</td>
            <td>${w.duration} ${w.durationUnit}</td>
            <td>${w.workingProgress}</td>
            <td>
                <c:if test="${not empty w.workingImage}">
                    <img src="${w.workingImage}" width="100"/>
                </c:if>
            </td>
            <td>
                <c:choose>
                    <c:when test="${w.status == 0}">Đã hủy</c:when>
                    <c:when test="${w.status == 1}">Đang thực hiện</c:when>
                    <c:when test="${w.status == 2}">Hoàn thành</c:when>
                </c:choose>
                <br/>
                <c:if test="${w.status != 2}">
                    <a href="${pageContext.request.contextPath}/working/change-status/${w.manageId}">Đổi</a>
                </c:if>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/working/edit/${w.manageId}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/working/delete/${w.manageId}"
                   onclick="return confirm('Xóa công việc này?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Phân trang -->
<c:if test="${totalPage > 1}">
    <div>
        <c:forEach begin="1" end="${totalPage}" var="i">
            <a href="${pageContext.request.contextPath}/working/list?page=${i}&keyword=${keyword}&status=${status}"
               style="${i == currentPage ? 'font-weight:bold;' : ''}">${i}</a>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
