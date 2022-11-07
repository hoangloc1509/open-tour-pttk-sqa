package dao;

import com.example.opentour.dao.DAO;
import com.example.opentour.dao.HoaDonDoiTacDAO;
import com.example.opentour.model.HoaDonDoiTac;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HoaDonDoiTacDAOTest {
    private static HoaDonDoiTacDAO hoaDonDoiTacDAO;
    private static Connection con;

    public HoaDonDoiTacDAOTest() {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        hoaDonDoiTacDAO = new HoaDonDoiTacDAO();
        con = DAO.con;
    }

    @Test
    public void testGetHoaDonDoiTac_testChuan() throws SQLException {
        // Tất cả hóa đơn tồn tại và danh sách dịch vụ đã sử dụng cũng tồn tại trong mỗi hóa đơn
        int idDoiTac = 1;
        List<HoaDonDoiTac> result = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTac);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(0, result.size());
        int soHoaDon1Nam = 12;
        Assert.assertTrue(result.size() <= soHoaDon1Nam);
        for (HoaDonDoiTac hoaDonDoiTac : result) {
            Assert.assertNotNull(hoaDonDoiTac.getListDvDaSuDung());
            Assert.assertTrue(hoaDonDoiTac.getListDvDaSuDung().size() > 0);
        }
    }

    @Test
    public void testGetHoaDonDoiTac_testNgoaiLe1() throws SQLException {
        // Có một số hoá đơn mà không có bất kỳ dịch vụ đã sử dụng nào trong hóa đơn đó
        int idDoiTac = 1;
        List<HoaDonDoiTac> result = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTac);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(0, result.size());
        int soHoaDon1Nam = 12;
        Assert.assertTrue(result.size() <= soHoaDon1Nam);
        for (HoaDonDoiTac hoaDonDoiTac : result) {
            Assert.assertNotNull(hoaDonDoiTac.getListDvDaSuDung());
        }
        Assert.assertTrue(result.stream().anyMatch(hoaDon -> hoaDon.getListDvDaSuDung().size() == 0));
    }

    @Test
    public void testGetHoaDonDoiTac_testNgoaiLe2() throws SQLException {
        // Đối tác tồn tại trên hệ thống nhưng không có bất kỳ hóa đơn nào
        int idDoiTac = 2;
        List<HoaDonDoiTac> result = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTac);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testGetHoaDonDoiTac_testNgoaiLe3() throws SQLException {
        // Đối tác không tồn tại trên hệ thống
        int idDoiTac = 100;
        List<HoaDonDoiTac> result = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTac);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testSaveHoaDonDoiTac_testChuan() {
        // Hóa đơn ở trạng thái "Chưa thanh toán"
        try {
            con.setAutoCommit(false);
            HoaDonDoiTac hoaDonDoiTac = new HoaDonDoiTac();
            hoaDonDoiTac.setId(1);
            boolean result = hoaDonDoiTacDAO.saveHoaDonDoiTac(hoaDonDoiTac);
            Assert.assertTrue(result);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (!con.getAutoCommit()) {
                    con.rollback();
                    con.setAutoCommit(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
