package com.example.opentour.dao;

import com.example.opentour.model.DoiTac;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoiTacDAO extends DAO {
    public DoiTacDAO() {
        super();
    }

    public List<DoiTac> searchDoiTac(String key) throws SQLException {
        List<DoiTac> listDoiTac = new ArrayList<>();
        String sql = "SELECT * FROM tbldoitac WHERE name LIKE ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + key + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String tel = rs.getString("tel");
            String description = rs.getString("description");
            listDoiTac.add(new DoiTac(id, name, email, tel, description));
        }
        return listDoiTac;
    }
}
