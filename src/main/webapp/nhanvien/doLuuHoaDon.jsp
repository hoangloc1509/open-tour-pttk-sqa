<%@ page import="com.example.opentour.model.HoaDonDoiTac" %>
<%@ page import="com.example.opentour.dao.HoaDonDoiTacDAOTest" %>
<%--
  Created by IntelliJ IDEA.
  User: profe
  Date: 11/6/2022
  Time: 9:14 PM
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

        HoaDonDoiTacDAO hoaDonDoiTacDAO = new HoaDonDoiTacDAO();
        boolean isSave = hoaDonDoiTacDAO.saveHoaDonDoiTac(hoaDonDoiTac);
        if (isSave) response.sendRedirect("gdDanhSachHoaDonDoiTac.jsp?doiTacId=" + session.getAttribute("doiTacId") + "&doiTacName=" + session.getAttribute("doiTacName") + "&status=success");
        else response.sendRedirect("gdDanhSachHoaDonDoiTac.jsp?doiTacId=" + session.getAttribute("doiTacId") + "&doiTacName=" + session.getAttribute("doiTacName") + "&status=fail");
    %>
</body>
</html>
