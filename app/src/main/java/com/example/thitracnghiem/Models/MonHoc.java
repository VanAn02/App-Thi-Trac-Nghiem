package com.example.thitracnghiem.Models;

public class MonHoc {
    private int id;
    private String tenMonHoc;
    private int hinhAnh;

    public MonHoc() {
        // Constructors
    }

    public MonHoc(int id, String tenMonHoc, int hinhAnh) {
        this.id = id;
        this.tenMonHoc = tenMonHoc;
        this.hinhAnh = hinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}