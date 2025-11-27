<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: linht
  Date: 27/11/2025
  Time: 10:01 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm / sửa công việc</title>
</head>
<body>
<h2>
    <c:if test="${workingManagements.manageId == 0}">Thêm chuyến bay</c:if>
    <c:if test="${workingManagements.manageId != 0}">Chỉnh sửa chuyến bay</c:if>
</h2>
Lưu ý: dùng c:set/c:if để tránh dùng toán tử ?: trong EL (không ổn định trong JSP cổ điển)
-->
<c:set var="formAction" value="/working/create" />
<c:if test="${flight.flightId != 0}">
    <c:set var="formAction" value="/working/edit" />
</c:if>
<form action="${pageContext.request.getContextPath}${formAction}" method="post"  enctype="multipart/form-data">
    <input type="hidden" name="manageId" value="${workingManagements.workingName}">
</form>
</body>
</html>
