package dao;

import com.example.opentour.dao.DAO;
import com.example.opentour.dao.DoiTacDAO;
import com.example.opentour.model.DoiTac;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DoiTacDAOTest {
    private static DoiTacDAO doiTacDAO;
    private static Connection con;

    public DoiTacDAOTest() {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        doiTacDAO = new DoiTacDAO();
        con = DAO.con;
    }

    @Test
    public void testSearchDoiTac_testChuan() throws SQLException {
        // Đối tác tồn tại có trên hệ thống
        String key = "n";
        List<DoiTac> result = doiTacDAO.searchDoiTac(key);
        Assert.assertNotNull(result);
        for (DoiTac doiTac : result) {
            Assert.assertTrue(doiTac.getName().toLowerCase().contains(key.toLowerCase()));
        }
    }

    @Test
    public void testSearchDoiTac_testNgoaiLe() throws SQLException {
        // Đối tác không có trên hệ thống
        String key = "fpt";
        List<DoiTac> result = doiTacDAO.searchDoiTac(key);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
