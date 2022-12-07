<%@ page import="com.example.opentour.model.NhanVien" %>
<%@ page import="com.example.opentour.model.HoaDonDoiTac" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.opentour.dao.HoaDonDoiTacDAO" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: profe
  Date: 11/4/2022
  Time: 1:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách hóa đơn đối tác</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .list-container {
            width: 70%;
            display: flex;
            flex-direction: column;
            padding: 6.4rem 8rem;
            background-color: white;
            gap: 4rem;
        }

        .list-hoadon {
            border-collapse: collapse;
            width: 100%;
            font-size: 1.8rem;
        }

        .list-hoadon th {
            padding: 1rem;
        }

        .list-hoadon td, .list-hoadon th {
            color: #555;
            text-align: center;
            border: 0.2rem solid #ddd;
        }

        .list-hoadon th {
            padding: 1.2rem;
            background-color: white;
        }

        .list-hoadon tr:not(:first-child) {
            transition: all 0.3s;
        }

        .list-hoadon tr:not(:first-child):hover {
            cursor: pointer;
            background-color: #eee;
        }

        .list-hoadon a {
            display: inline-block;
            text-decoration: none;
            color: inherit;
            width: 100%;
            height: 100%;
            padding: 1rem;
        }
    </style>
</head>
<body>
    <%
        NhanVien nhanVien = (NhanVien) session.getAttribute("staff");
        if (nhanVien == null) {
            response.sendRedirect("../gdDangNhap.jsp");
            return;
        }
        HoaDonDoiTacDAO hoaDonDoiTacDAO = new HoaDonDoiTacDAO();

        List<HoaDonDoiTac> listHoaDonDoiTac = hoaDonDoiTacDAO.getHoaDonDoiTac(Integer.parseInt(request.getParameter("doiTacId")));

        // Handle currency format
        Locale vn = new Locale("vi", "VN");
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(vn);

        // Set session attribute for doiTacId, doiTacName
        session.setAttribute("doiTacId", request.getParameter("doiTacId"));
        session.setAttribute("doiTacName", request.getParameter("doiTacName"));
    %>
    <div class="container">
        <h1>Danh sách hóa đơn 12 tháng gần nhất</h1>
        <main class="list-container">
            <h2 class="title">
                Đối tác: <%= request.getParameter("doiTacName") %>
            </h2>
            <table class="list-hoadon">
                <tr>
                    <th>Tháng</th>
                    <th>Hóa đơn</th>
                    <th>Tổng tiền (VNĐ)</th>
                    <th>Trạng thái</th>
                </tr>
                <%
                    if (listHoaDonDoiTac != null) {
                        for(HoaDonDoiTac hoaDonDoiTac: listHoaDonDoiTac) {
                            session.setAttribute("hoaDonDoiTac" + hoaDonDoiTac.getId(), hoaDonDoiTac);
                %>
                            <tr>
                                <td><a href="gdHoaDonChiTiet.jsp?hoaDonId=<%= hoaDonDoiTac.getId() %>"><%= hoaDonDoiTac.getMonth()%></a></td>
                                <td><a href="gdHoaDonChiTiet.jsp?hoaDonId=<%= hoaDonDoiTac.getId() %>"><%= hoaDonDoiTac.getName() %></a></td>
                                <td><a href="gdHoaDonChiTiet.jsp?hoaDonId=<%= hoaDonDoiTac.getId() %>"><%= vndFormat.format(hoaDonDoiTac.getTotalAmount()) %></a></td>
                                <td><a href="gdHoaDonChiTiet.jsp?hoaDonId=<%= hoaDonDoiTac.getId() %>"><%= hoaDonDoiTac.getStatus() %></a></td>
                            </tr>
                <%
                        }
                    }
                %>
            </table>
        </main>
    </div>
    <script>
        <%
            if (request.getParameter("status") != null && request.getParameter("status").equals("success")) {
        %>
                alert("Lưu hóa đơn thanh công");
        <%
            }
            else if (request.getParameter("status") != null && request.getParameter("status").equals("fail")) {
        %>
                alert("Lưu hóa đơn thất bại!");
        <%
            }
        %>
    </script>
</body>
</html>
