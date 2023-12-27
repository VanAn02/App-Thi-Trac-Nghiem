package com.example.thitracnghiem.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LichSuLamBai {
    private int id;
    private int idDeThi;
    private String thoiGianLamBai;
    private String dapAnDaChon;
    private String dapAnDung;
    private String cauHoi;
    private String dapAnA;
    private String dapAnB;
    private String dapAnC;
    private String dapAnD;

    public LichSuLamBai() {
    }

    public LichSuLamBai(int id, int idDeThi, String thoiGianLamBai, String dapAnDaChon, String dapAnDung, String cauHoi, String dapAnA, String dapAnB, String dapAnC, String dapAnD) {
        this.id = id;
        this.idDeThi = idDeThi;
        this.thoiGianLamBai = thoiGianLamBai;
        this.dapAnDaChon = dapAnDaChon;
        this.dapAnDung = dapAnDung;
        this.cauHoi = cauHoi;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnD = dapAnD;
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

    public String getThoiGianLamBai() {
        return thoiGianLamBai;
    }

    public void setThoiGianLamBai(String thoiGianLamBai) {
        this.thoiGianLamBai = thoiGianLamBai;
    }

    public String getDapAnDaChon() {
        return dapAnDaChon;
    }

    public void setDapAnDaChon(String dapAnDaChon) {
        this.dapAnDaChon = dapAnDaChon;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getDapAnA() {
        return dapAnA;
    }

    public void setDapAnA(String dapAnA) {
        this.dapAnA = dapAnA;
    }

    public String getDapAnB() {
        return dapAnB;
    }

    public void setDapAnB(String dapAnB) {
        this.dapAnB = dapAnB;
    }

    public String getDapAnC() {
        return dapAnC;
    }

    public void setDapAnC(String dapAnC) {
        this.dapAnC = dapAnC;
    }

    public String getDapAnD() {
        return dapAnD;
    }

    public void setDapAnD(String dapAnD) {
        this.dapAnD = dapAnD;
    }
}
