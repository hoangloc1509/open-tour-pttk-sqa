<%@ page import="com.example.opentour.dao.DoiTacDAO" %>
<%@ page import="com.example.opentour.model.DoiTac" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: profe
  Date: 11/4/2022
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String key = request.getParameter("searchDoiTac");
        DoiTacDAO doiTacDAO = new DoiTacDAO();
        List<DoiTac> listDoiTac = doiTacDAO.searchDoiTac(key);

        PrintWriter pw = response.getWriter();
        pw.println(
                "<h2 class=\"title\">Danh sách đối tác</h2>\n" +
                "<table class=\"doitac\">" +
                    "<tr>\n" +
                    "   <th style=\"width: 20%\">Đối tác</th>\n" +
                    "   <th style=\"width: 60%\">Mô tả</th>\n" +
                    "   <th style=\"width: 20%\">Hành động</th>\n" +
                    "</tr>"
        );
        if (listDoiTac != null || listDoiTac.size() != 0) {
            for (DoiTac doiTac : listDoiTac) {
                pw.println(
                            "<tr>\n" +
                            "   <td>" + doiTac.getName() + "</td>\n" +
                            "   <td>" + doiTac.getDescription() + "</td>\n" +
                            "   <td>\n" +
                            "       <a class=\"btn\" href=\"gdDanhSachHoaDonDoiTac.jsp?doiTacId=" + doiTac.getId() + "&doiTacName=" + doiTac.getName() + "\">Chọn</a>\n" +
                            "   </td>\n" +
                            "</tr>"
                );
            }
        }
        pw.println("</table>");
    %>
</body>
</html>
