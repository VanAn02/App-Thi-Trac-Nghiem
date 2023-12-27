package com.example.thitracnghiem.Models;

public class CauHoiTracNghiem {
    private int id;
    private int idDeThi;
    private String noiDungCauHoi;
    private String phuongAnA;
    private String phuongAnB;
    private String phuongAnC;
    private String phuongAnD;
    private String dapAnDung;

    public CauHoiTracNghiem() {
        // Constructors
    }

    public CauHoiTracNghiem(int id, int idDeThi, String noiDungCauHoi, String phuongAnA, String phuongAnB, String phuongAnC, String phuongAnD, String dapAnDung) {
        this.id = id;
        this.idDeThi = idDeThi;
        this.noiDungCauHoi = noiDungCauHoi;
        this.phuongAnA = phuongAnA;
        this.phuongAnB = phuongAnB;
        this.phuongAnC = phuongAnC;
        this.phuongAnD = phuongAnD;
        this.dapAnDung = dapAnDung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDeThi() {
        return idDeThi;
    }

    public void setIdDeThi(int idDeThi) {
        this.idDeThi = idDeThi;
    }

    public String getNoiDungCauHoi() {
        return noiDungCauHoi;
    }

    public void setNoiDungCauHoi(String noiDungCauHoi) {
        this.noiDungCauHoi = noiDungCauHoi;
    }

    public String getPhuongAnA() {
        return phuongAnA;
    }

    public void setPhuongAnA(String phuongAnA) {
        this.phuongAnA = phuongAnA;
    }

    public String getPhuongAnB() {
        return phuongAnB;
    }

    public void setPhuongAnB(String phuongAnB) {
        this.phuongAnB = phuongAnB;
    }

    public String getPhuongAnC() {
        return phuongAnC;
    }

    public void setPhuongAnC(String phuongAnC) {
        this.phuongAnC = phuongAnC;
    }

    public String getPhuongAnD() {
        return phuongAnD;
    }

    public void setPhuongAnD(String phuongAnD) {
        this.phuongAnD = phuongAnD;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }
}
