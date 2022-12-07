<%@ page import="com.example.opentour.model.NhanVien" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Open Tour</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .form-container {
            width: 30%;
            background-color: white;
            padding: 1.8rem 2.4rem;
            border-radius: 1rem;
            display: flex;
            flex-direction: column;
            gap: 2.4rem;
            align-items: center;
            justify-content: center;
        }

        .input-container {
            display: flex;
            flex-direction: column;
            gap: 0.4rem;
            font-size: 1.8rem;
            font-weight: 500;
            width: 100%;
        }

        .input {
            width: 100%;
            font-size: 1.8rem;
            padding: 1rem 0.5rem;
            border-radius: 0.5rem;
            border: none;
            border: .1rem solid #555555;
            color: inherit;
        }

        .text-error {
            color: #e84118;
            font-size: 1.2rem;
        }
    </style>
</head>
<body>
    <%
        NhanVien nhanVien = (NhanVien) session.getAttribute("staff");
        if (nhanVien != null) response.sendRedirect("./nhanvien/gdChinhNV.jsp");
    %>
    <div class="container">
        <h1>Đăng nhập</h1>
        <form class="form-container" action="doDangNhap.jsp" autocomplete="off" method="post">
            <div class="input-container">
                <label for="username">Username:</label>
                <input class="input" id="username" name="username" type="text">
            </div>
            <div class="input-container">
                <label for="password">Password:</label>
                <input class="input" id="password" name="password" type="password">
            </div>
            <% if (request.getParameter("err") != null && request.getParameter("err").equalsIgnoreCase("fail")){%>
            <p class="text-error">Username hoặc password không đúng!</p>
            <%} %>
            <button class="btn btn-submit" type="submit">Log in</button>
        </form>
    </div>
</body>
</html>
