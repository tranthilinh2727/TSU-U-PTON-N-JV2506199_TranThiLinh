<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<!-- Tiêu đề động: nếu flightId == 0 => chế độ thêm, ngược lại => chế độ sửa -->

<form action="${pageContext.request.contextPath}${formAction}" method="post" enctype="multipart/form-data">
    <input type="hidden" name="manageId" value="${WorkingManagement.empty}">
</form>
</body>
</html>
