package dao;

import com.example.opentour.dao.DAO;
import com.example.opentour.dao.NhanVienDAO;
import com.example.opentour.model.NhanVien;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class NhanVienDAOTest {
    private static NhanVienDAO nhanVienDAO;
    private static Connection con;

    public NhanVienDAOTest() {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        nhanVienDAO = new NhanVienDAO();
        con = DAO.con;
    }

    @Test
    public void testCheckLogin_testNgoaiLe() throws SQLException {
        // Nhân viên đăng nhập sai thông tin
        String username = "phuchd";
        String password = "686868";
        NhanVien nhanVien = new NhanVien(username, password);
        Assert.assertNull(nhanVienDAO.checkLogin(nhanVien));
    }

    @Test
    public void testCheckLogin_testChuan() throws SQLException {
        // Nhân viên đăng nhập thành công
        String username = "lochd";
        String password = "123456";
        NhanVien nhanVien = new NhanVien(username, password);
        NhanVien expectedResult = new NhanVien(1, "lochd", "123456", "staff");
        NhanVien actualResult = nhanVienDAO.checkLogin(nhanVien);
        Assert.assertEquals(expectedResult.getId(), actualResult.getId());
        Assert.assertEquals(expectedResult.getUsername(), actualResult.getUsername());
        Assert.assertEquals(expectedResult.getPassword(), actualResult.getPassword());
        Assert.assertEquals(expectedResult.getPosition(), actualResult.getPosition());
    }
}
