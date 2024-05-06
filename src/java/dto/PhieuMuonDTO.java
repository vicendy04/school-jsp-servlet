package dto;

import entity.PhieuMuon;
import entity.PhieuMuonChiTiet;
import entity.Sach;
import entity.PhieuTra;

public class PhieuMuonDTO {
    public PhieuMuon phieuMuon;
    public Sach sach;
    public PhieuTra phieuTra;
    public PhieuMuonChiTiet phieuMuonChiTiet;

    public PhieuMuonDTO() {
    }

    public PhieuMuonDTO(PhieuMuon phieuMuon, Sach sach, PhieuTra phieuTra, PhieuMuonChiTiet phieuMuonChiTiet) {
        this.phieuMuon = phieuMuon;
        this.sach = sach;
        this.phieuTra = phieuTra;
        this.phieuMuonChiTiet = phieuMuonChiTiet;
    }

    public PhieuMuon getPhieuMuon() {
        return phieuMuon;
    }

    public void setPhieuMuon(PhieuMuon phieuMuon) {
        this.phieuMuon = phieuMuon;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public PhieuTra getPhieuTra() {
        return phieuTra;
    }

    public void setPhieuTra(PhieuTra phieuTra) {
        this.phieuTra = phieuTra;
    }

    public PhieuMuonChiTiet getPhieuMuonChiTiet() {
        return phieuMuonChiTiet;
    }

    public void setPhieuMuonChiTiet(PhieuMuonChiTiet phieuMuonChiTiet) {
        this.phieuMuonChiTiet = phieuMuonChiTiet;
    }
}
