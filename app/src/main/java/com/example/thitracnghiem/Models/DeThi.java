package com.example.thitracnghiem.Models;

public class DeThi {
    private int id;
    private int idMonHoc;
    private int soDeThi;

    public DeThi() {
        // Constructors
    }

    public DeThi(int id, int idMonHoc, int soDeThi) {
        this.id = id;
        this.idMonHoc = idMonHoc;
        this.soDeThi = soDeThi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMonHoc() {
        return idMonHoc;
    }

    public void setIdMonHoc(int idMonHoc) {
        this.idMonHoc = idMonHoc;
    }

    public int getSoDeThi() {
        return soDeThi;
    }

    public void setSoDeThi(int soDeThi) {
        this.soDeThi = soDeThi;
    }
}
