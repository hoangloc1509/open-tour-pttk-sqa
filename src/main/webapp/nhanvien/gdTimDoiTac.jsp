<%@ page import="com.example.opentour.model.NhanVien" %>
<%--
  Created by IntelliJ IDEA.
  User: profe
  Date: 11/3/2022
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách đối tác</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .search-container {
            width: 70%;
            display: flex;
            flex-direction: column;
            padding: 6.4rem 8rem;
            background-color: white;
            gap: 4rem;
            align-items: center;
        }

        .search-form {
            display: flex;
            gap: 2.4rem;
            align-items: center;
            width: 100%;
        }

        .search-input {
            width: 30%;
            font-size: 1.8rem;
            padding: 1rem;
            border-radius: 0.5rem;
            border: none;
            border: .1rem solid #555555;
            color: inherit;
        }

        .results {
            display: flex;
            flex-direction: column;
            gap: 1.8rem;
            width: 100%;
        }

        .doitac {
            border-collapse: collapse;
            width: 100%;
            font-size: 1.8rem;
        }

        .doitac td, .doitac th {
            color: #555;
            text-align: center;
            border: 0.2rem solid #ddd;
            padding: 1rem;
        }

        .doitac th {
            padding: 1.2rem;
            background-color: white;
        }
    </style>
</head>
<body>
    <%
        NhanVien nhanVien = (NhanVien) session.getAttribute("staff");
        if (nhanVien == null) response.sendRedirect("../gdDangNhap.jsp");
    %>
    <div class="container">
        <h1>Tìm đối tác</h1>
        <main class="search-container">
            <div class="search-form">
                <input class="search-input" name="searchDoiTac" id="search" type="text" placeholder="Nhập tên đối tác">
                <button class="btn btn-submit" type="submit" onclick="handleOnClick()">Tìm kiếm</button>
            </div>
            <div class="results"></div>
        </main>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        const tblDOiTacElement = document.getElementById("tbl-doitac");
        function handleOnClick() {
            $.ajax({
                url: "/OpenTour_war_exploded/nhanvien/doTimDoiTac.jsp",
                type: "get",
                data: {
                    searchDoiTac: document.getElementById("search").value,
                },
                success: function (data) {
                    const row = document.querySelector(".results");
                    row.innerHTML = data;
                },
                error: function (err) {
                    alert(err);
                },
            });
        }
    </script>
</body>
</html>
