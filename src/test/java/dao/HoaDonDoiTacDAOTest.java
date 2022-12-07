package dao;

import com.example.opentour.dao.DAO;
import com.example.opentour.dao.HoaDonDoiTacDAO;
import com.example.opentour.model.*;
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
        int idDoiTacTest = 1;
        int soHoaDon1Nam = 12;

        List<HoaDonDoiTac> result = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTacTest);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(0, result.size());
        Assert.assertTrue(result.size() <= soHoaDon1Nam);
        for (HoaDonDoiTac hoaDonDoiTac : result) {
            Assert.assertNotNull(hoaDonDoiTac.getListDvDaSuDung());
            Assert.assertTrue(hoaDonDoiTac.getListDvDaSuDung().size() > 0);
        }
    }

    @Test
    public void testGetHoaDonDoiTac_testNgoaiLe1() throws SQLException {
        // Có một số hoá đơn mà không có bất kỳ dịch vụ đã sử dụng nào trong hóa đơn đó
        int idDoiTacTest = 2;
        List<HoaDonDoiTac> result = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTacTest);
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
        int idDoiTacTest = 3;
        List<HoaDonDoiTac> result = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTacTest);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testGetHoaDonDoiTac_testNgoaiLe3() throws SQLException {
        // Đối tác không tồn tại trên hệ thống
        int idDoiTacTest = 100;
        List<HoaDonDoiTac> result = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTacTest);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testSaveHoaDonDoiTac_testChuan() {
        // Hóa đơn ở trạng thái "Chưa thanh toán"
        int idDoiTacTest = 1;
        int idHoaDonDoiTacTest = 1;

        try {
            con.setAutoCommit(false);
            List<HoaDonDoiTac> listHoaDonDoiTac = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTacTest);
            HoaDonDoiTac hoaDonDoiTacTest = listHoaDonDoiTac.stream()
                    .filter(hoaDonDoiTac -> hoaDonDoiTac.getId() == idHoaDonDoiTacTest)
                    .findAny() // trả về phần tử đầu tiên thỏa mãn điều kiện trong filter
                    .orElse(null); // mặc định là null nếu không tìm thấy kết quả

            boolean result = hoaDonDoiTacDAO.saveHoaDonDoiTac(hoaDonDoiTacTest);
            Assert.assertTrue(result);

            // Test new updated row
            listHoaDonDoiTac.clear();
            listHoaDonDoiTac = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTacTest);
            hoaDonDoiTacTest = listHoaDonDoiTac.stream()
                    .filter(hoaDonDoiTac -> hoaDonDoiTac.getId() == idHoaDonDoiTacTest)
                    .findAny()
                    .orElse(null);

            Assert.assertEquals("Đã thanh toán", hoaDonDoiTacTest.getStatus());
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

    @Test
    public void testSaveHoaDonDoiTac_testNgoaiLe() {
        // Hóa đơn ở trạng thái "Đã thanh toán"
        try {
            con.setAutoCommit(false);
            HoaDonDoiTac hoaDonDoiTacTest = new HoaDonDoiTac();
            hoaDonDoiTacTest.setId(1);
            hoaDonDoiTacTest.setStatus("Đã thanh toán");
            boolean result = hoaDonDoiTacDAO.saveHoaDonDoiTac(hoaDonDoiTacTest);
            Assert.assertFalse(result);
        } catch (SQLException e) {
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

    @Test
    public void testUpdateHoaDonDoiTac_testChuan() {
        // Hóa đơn ở trạng thái "Chưa thanh toán"
        int idDoiTacTest = 1;
        int idHoaDonDoiTacTest = 1;
        int idDichVuDaSuDungTest1 = 1;
        int idDichVuDaSuDungTest2 = 2;
        int newQuantity1 = 5;
        int newQuantity2 = 100;

        try {
            con.setAutoCommit(false);
            List<HoaDonDoiTac> listHoaDonDoiTac = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTacTest);
            HoaDonDoiTac hoaDonDoiTacTest = listHoaDonDoiTac.stream()
                    .filter(hoaDonDoiTac -> hoaDonDoiTac.getId() == idHoaDonDoiTacTest)
                    .findAny() // trả về phần tử đầu tiên thỏa mãn điều kiện trong filter
                    .orElse(null); // mặc định là null nếu không tìm thấy kết quả

            if (hoaDonDoiTacTest != null) {
                List<DichVuDaSuDung> listDvDaSuDung = hoaDonDoiTacTest.getListDvDaSuDung();
                DichVuDaSuDung dichVuDaSuDungTest1 = listDvDaSuDung.stream()
                        .filter(dichVuDaSuDung -> dichVuDaSuDung.getId() == idDichVuDaSuDungTest1)
                        .findAny()
                        .orElse(null);
                DichVuDaSuDung dichVuDaSuDungTest2 = listDvDaSuDung.stream()
                        .filter(dichVuDaSuDung -> dichVuDaSuDung.getId() == idDichVuDaSuDungTest2)
                        .findAny()
                        .orElse(null);
                if (dichVuDaSuDungTest1 != null) {
                    dichVuDaSuDungTest1.setQuantity(newQuantity1);
                }
                if (dichVuDaSuDungTest2 != null) {
                    dichVuDaSuDungTest2.setQuantity(newQuantity2);
                }
            }

            boolean result = hoaDonDoiTacDAO.updateHoaDonDoiTac(hoaDonDoiTacTest);
            Assert.assertTrue(result);

            // Test new updated row
            listHoaDonDoiTac.clear();
            listHoaDonDoiTac = hoaDonDoiTacDAO.getHoaDonDoiTac(idDoiTacTest);
            hoaDonDoiTacTest = listHoaDonDoiTac.stream()
                    .filter(hoaDonDoiTac -> hoaDonDoiTac.getId() == idHoaDonDoiTacTest)
                    .findAny()
                    .orElse(null);
            DichVuDaSuDung dichVuDaSuDungUpdated1 = hoaDonDoiTacTest.getListDvDaSuDung()
                    .stream()
                    .filter(dichVuDaSuDung -> dichVuDaSuDung.getId() == idDichVuDaSuDungTest1)
                    .findAny()
                    .orElse(null);
            DichVuDaSuDung dichVuDaSuDungUpdated2 = hoaDonDoiTacTest.getListDvDaSuDung()
                    .stream()
                    .filter(dichVuDaSuDung -> dichVuDaSuDung.getId() == idDichVuDaSuDungTest2)
                    .findAny()
                    .orElse(null);

            Assert.assertEquals(newQuantity1, dichVuDaSuDungUpdated1.getQuantity());
            Assert.assertEquals(
                    newQuantity1 * dichVuDaSuDungUpdated1.getDvCungCap()
                            .getDvDoiTac()
                            .getDichVu()
                            .getUnitPrice(),
                    dichVuDaSuDungUpdated1.getAmount(),
                    0.000001f
            );

            Assert.assertEquals(newQuantity2, dichVuDaSuDungUpdated2.getQuantity());
            Assert.assertEquals(
                    newQuantity2 * dichVuDaSuDungUpdated2.getDvCungCap()
                            .getDvDoiTac()
                            .getDichVu()
                            .getUnitPrice(),
                    dichVuDaSuDungUpdated2.getAmount(),
                    0.000001f
            );
        } catch (SQLException e) {
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

    @Test
    public void testUpdateHoaDonDoiTac_testNgoaiLe() {
        // Hóa đơn ở trạng thái "Đã thanh toán"
        try {
            con.setAutoCommit(false);
            HoaDonDoiTac hoaDonDoiTacTest = new HoaDonDoiTac();
            hoaDonDoiTacTest.setId(1);
            hoaDonDoiTacTest.setStatus("Đã thanh toán");
            boolean result = hoaDonDoiTacDAO.updateHoaDonDoiTac(hoaDonDoiTacTest);
            Assert.assertFalse(result);
        } catch (SQLException e) {
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
