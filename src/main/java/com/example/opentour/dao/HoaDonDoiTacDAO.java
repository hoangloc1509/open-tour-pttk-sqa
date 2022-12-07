package com.example.opentour.dao;

import com.example.opentour.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDoiTacDAO extends DAO {
    public HoaDonDoiTacDAO() {
        super();
    }

    public List<HoaDonDoiTac> getHoaDonDoiTac(int idDoiTac) throws SQLException {
        List<HoaDonDoiTac> listHoaDonDoiTac = new ArrayList<>();
        String sql = "SELECT * FROM tblhoadondoitac WHERE tblhoadondoitac.tbldoitacid = ? ORDER BY tblhoadondoitac.month DESC LIMIT 12";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, String.valueOf(idDoiTac));
        ResultSet rs = ps.executeQuery();
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MM/yyyy");
        while (rs.next()) {
            int hoaDonId = rs.getInt("id");
            String hoaDonName = rs.getString("name");
            String hoaDonMonth = sdfMonth.format(rs.getDate("month"));
            String hoaDonStatus = rs.getString("status");
            Double hoaDonTotalAmount = 0.0;


            List<DichVuDaSuDung> listDvDaSuDung = new ArrayList<>();
            String dvsdSql = "SELECT * FROM tbldichvudasudung WHERE tbldichvudasudung.tblhoadondoitacid = ?";
            PreparedStatement dvsdPs = con.prepareStatement(dvsdSql);
            dvsdPs.setInt(1, hoaDonId);
            ResultSet dvsdRs = dvsdPs.executeQuery();
            SimpleDateFormat sdfStartDate = new SimpleDateFormat("dd/MM/yyyy");
            while (dvsdRs.next()) {
                int dvsdId = dvsdRs.getInt("id");
                int dvsdQuantity = dvsdRs.getInt("quantity");
                Double dvsdAmount = dvsdRs.getDouble("amount");
                String dvsdStartDate = sdfStartDate.format(dvsdRs.getDate("start_date"));
                int dvsdDvccid = dvsdRs.getInt("tbldichvucungcapid");


                String dvccSql = "SELECT * FROM tbldichvucungcap WHERE tbldichvucungcap.id = ?";
                PreparedStatement dvccPs = con.prepareStatement(dvccSql);
                dvccPs.setInt(1, dvsdDvccid);
                ResultSet dvccRs = dvccPs.executeQuery();
                DichVuCungCap dvcc = null;
                while (dvccRs.next()) {
                    int dvccId = dvccRs.getInt("id");
                    int dvdtId = dvccRs.getInt("tbldichvudoitacid");


                    String dvdtSql = "SELECT * FROM tbldichvudoitac WHERE tbldichvudoitac.tbldichvuid = ?";
                    PreparedStatement dvdtPs = con.prepareStatement(dvdtSql);
                    dvdtPs.setInt(1, dvdtId);
                    ResultSet dvdtRs = dvdtPs.executeQuery();
                    DichVuDoiTac dvDoiTac = null;
                    while (dvdtRs.next()) {
                        int dichvudoitacId = dvdtRs.getInt("id");
                        int dvId = dvdtRs.getInt("tbldichvuid");


                        String dvSql = "SELECT * FROM tbldichvu WHERE tbldichvu.id = ?";
                        PreparedStatement dvPs = con.prepareStatement(dvSql);
                        dvPs.setInt(1, dvId);
                        ResultSet dvRs = dvPs.executeQuery();
                        DichVu dichVu = null;
                        while (dvRs.next()) {
                            int dichvuId = dvRs.getInt("id");
                            String dichvuName = dvRs.getString("name");
                            Double dichvuUnitPrice = dvRs.getDouble("unit_price");
                            dichVu = new DichVu(dichvuId, dichvuName, dichvuUnitPrice);
                        }
                        dvDoiTac = new DichVuDoiTac(dichvudoitacId, dichVu);
                    }


                    dvcc = new DichVuCungCap(dvccId, dvDoiTac);
                }
                listDvDaSuDung.add(new DichVuDaSuDung(dvsdId, dvsdQuantity, dvsdAmount, dvsdStartDate, dvcc));
            }
            for (DichVuDaSuDung dichVuDaSuDung : listDvDaSuDung) {
                hoaDonTotalAmount += dichVuDaSuDung.getAmount();
            }
            listHoaDonDoiTac.add(new HoaDonDoiTac(hoaDonId, hoaDonName, hoaDonMonth, hoaDonStatus, hoaDonTotalAmount, listDvDaSuDung));
        }
        return listHoaDonDoiTac;
    }

    public boolean saveHoaDonDoiTac(HoaDonDoiTac hoaDonDoiTac) throws SQLException {
        if (hoaDonDoiTac.getStatus() == "Đã thanh toán") return false;

        try {
            String sql = "UPDATE tblhoadondoitac SET status = 'Đã thanh toán' WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, hoaDonDoiTac.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateHoaDonDoiTac(HoaDonDoiTac hoaDonDoiTac) throws SQLException {
        if (hoaDonDoiTac.getStatus() == "Đã thanh toán") return false;

        try {
            List<DichVuDaSuDung> listDvDaSuDung = hoaDonDoiTac.getListDvDaSuDung();
            String sql = "UPDATE tbldichvudasudung SET quantity = ?, amount = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            for (DichVuDaSuDung dvDaSuDung : listDvDaSuDung) {
                ps.setInt(1, dvDaSuDung.getQuantity());
                ps.setDouble(
                        2,
                        dvDaSuDung.getDvCungCap()
                                .getDvDoiTac()
                                .getDichVu()
                                .getUnitPrice() * dvDaSuDung.getQuantity()
                );
                ps.setInt(3, dvDaSuDung.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
