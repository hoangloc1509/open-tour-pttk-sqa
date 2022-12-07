<%@ page import="com.example.opentour.model.NhanVien" %>
<%@ page import="com.example.opentour.model.HoaDonDoiTac" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.example.opentour.model.DichVuDaSuDung" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: profe
  Date: 12/7/2022
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sửa hóa đơn</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .details-container {
            width: 70%;
            display: flex;
            flex-direction: column;
            padding: 6.4rem 8rem;
            background-color: white;
            gap: 3rem;
        }

        .list-dichvu {
            border-collapse: collapse;
            width: 100%;
            font-size: 1.8rem;
        }

        .list-dichvu td, .list-dichvu th {
            color: #555;
            text-align: center;
            border: 0.2rem solid #ddd;
            padding: 1rem;
        }

        .list-dichvu th {
            padding: 1.2rem;
            background-color: white;
        }

        .total-amount {
            font-size: 1.8rem;
        }

        .action {
            display: flex;
            align-items: center;
            justify-content: flex-end;
            gap: 2.4rem;
        }

        .number-input {

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

        HoaDonDoiTac hoaDonDoiTac = (HoaDonDoiTac) session.getAttribute("hoaDonDoiTac" + request.getParameter("hoaDonId"));
        List<DichVuDaSuDung> listDichVuDaSuDung = hoaDonDoiTac.getListDvDaSuDung();

        // Handle currency format
        Locale vn = new Locale("vi", "VN");
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(vn);
    %>
    <div class="container">
        <h1>Sửa hóa đơn</h1>
        <main class="details-container">
            <h2 class="title">Đối tác: <%= session.getAttribute("doiTacName") %></h2>
            <h2 class="title">Hóa đơn: <%= hoaDonDoiTac.getName() %></h2>
            <h2 class="title">Sửa thông tin dịch vụ đã sử dụng</h2>
            <table class="list-dichvu">
                <tr>
                    <th>Mã</th>
                    <th>Tên dịch vụ</th>
                    <th>Ngày sử dụng</th>
                    <th>Đơn giá (VNĐ)</th>
                    <th>Số lượng</th>
                    <th>Thành tiền (VNĐ)</th>
                    <th>Hành động</th>
                </tr>
                <%
                    for (DichVuDaSuDung dichVuDaSuDung : listDichVuDaSuDung) {
                %>
                <tr>
                    <td><%= dichVuDaSuDung.getId() %></td>
                    <td><%= dichVuDaSuDung.getDvCungCap().getDvDoiTac().getDichVu().getName() %></td>
                    <td><%= dichVuDaSuDung.getStartDate() %></td>
                    <td><%= vndFormat.format(dichVuDaSuDung.getDvCungCap().getDvDoiTac().getDichVu().getUnitPrice()) %></td>
                    <td><input class="number-input" type="number" value="<%= dichVuDaSuDung.getQuantity() %>" /></td>
                    <td><%= vndFormat.format(dichVuDaSuDung.getAmount()) %></td>
                    <td><button class="btn">Sửa</button></td>
                </tr>
                <%
                    }
                %>
            </table>
            <h3 class="total-amount">Tổng tiền: <%= vndFormat.format(hoaDonDoiTac.getTotalAmount()) %></h3>
            <div class="action">
                <button class="btn" onclick="window.history.back()">Hủy</button>
                <form action="doCapNhatHoaDon.jsp?hoaDonId=<%= hoaDonDoiTac.getId() %>" method="post">
                    <button class="btn btn-submit" type="submit">Lưu</button>
                </form>
            </div>
        </main>
    </div>
</body>
</html>
