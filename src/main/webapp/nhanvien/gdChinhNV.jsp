<%@ page import="com.example.opentour.model.NhanVien" %>
<%--
  Created by IntelliJ IDEA.
  User: profe
  Date: 11/3/2022
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang chủ</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .main-container {
            width: 30%;
            height: 40%;
            background-color: white;
            padding: 4.8rem;
            border-radius: 1rem;
            display: flex;
            flex-direction: column;
            gap: 4.8rem;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>
    <%
        NhanVien nhanVien = (NhanVien) session.getAttribute("staff");
        if (nhanVien == null) response.sendRedirect("../gdDangNhap.jsp");
    %>
    <div class="container">
        <h1>Trang chủ</h1>
        <main class="main-container">
            <h2 class="title">Chức năng</h2>
            <a class="btn btn-submit" href="gdTimDoiTac.jsp">Thanh toán đối tác</a>
        </main>
    </div>
</body>
</html>
