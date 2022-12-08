<%@ page import="com.example.opentour.model.DichVuDaSuDung" %>
<%@ page import="com.example.opentour.model.HoaDonDoiTac" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: profe
  Date: 12/7/2022
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        HoaDonDoiTac hoaDonDoiTac = (HoaDonDoiTac) session.getAttribute("hoaDonDoiTac" + request.getParameter("hoaDonId"));
        List<DichVuDaSuDung> listDichVuDaSuDung = hoaDonDoiTac.getListDvDaSuDung();
    %>
    <pre>
        <%= listDichVuDaSuDung %>
    </pre>
</body>
</html>
