package com.example.opentour.dao;

import com.example.opentour.model.NhanVien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanVienDAO extends DAO {
    public NhanVienDAO() {
        super();
    }

    public NhanVien checkLogin(NhanVien nv) throws SQLException {
        String sql = "SELECT * FROM tblnhanvien WHERE username = ? and password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nv.getUsername());
        ps.setString(2, nv.getPassword());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String usn = rs.getString("username");
            String psw = rs.getString("password");
            String position = rs.getString("position");
            return new NhanVien(id, usn, psw, position);
        }
        return null;
    }
}
