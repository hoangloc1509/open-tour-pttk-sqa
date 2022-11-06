<%@ page import="com.example.opentour.dao.NhanVienDAO" %>
<%@ page import="com.example.opentour.model.NhanVien" %>
<%--
  Created by IntelliJ IDEA.
  User: profe
  Date: 11/3/2022
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        NhanVienDAO nhanVienDAO = new NhanVienDAO();

        NhanVien nhanVien = nhanVienDAO.checkLogin(new NhanVien(username, password));
        if (nhanVien == null) response.sendRedirect("gdDangNhap.jsp?err=fail");
        else if (nhanVien.getPosition().equals("staff")) {
            session.setAttribute("staff", nhanVien);
            response.sendRedirect("./nhanvien/gdChinhNV.jsp");
        }
    %>
</body>
</html>
