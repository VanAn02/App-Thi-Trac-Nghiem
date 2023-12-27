package com.example.thitracnghiem.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanhSachLichSuCauTraLoi {
    private List<Integer> danhSachCauDaTraLoi;
    private Map<Integer, Long> thoiGianCauTraLoi;
    private Map<Integer, Boolean> ketQuaCauTraLoi; // true if correct, false if incorrect

    public DanhSachLichSuCauTraLoi() {
        danhSachCauDaTraLoi = new ArrayList<>();
        thoiGianCauTraLoi = new HashMap<>();
        ketQuaCauTraLoi = new HashMap<>();
    }

    public void addCauTraLoi(int cauHoiIndex, long thoiGian, boolean ketQua) {
        danhSachCauDaTraLoi.add(cauHoiIndex);
        thoiGianCauTraLoi.put(cauHoiIndex, thoiGian);
        ketQuaCauTraLoi.put(cauHoiIndex, ketQua);
    }

    public List<Integer> getDanhSachCauDaTraLoi() {
        return danhSachCauDaTraLoi;
    }

    public int getSoCauDaTraLoi() {
        return danhSachCauDaTraLoi.size();
    }

    public boolean daTraLoiCauHoi(int cauHoiIndex) {
        return danhSachCauDaTraLoi.contains(cauHoiIndex);
    }

    public long getThoiGianCauTraLoi(int cauHoiIndex) {
        return thoiGianCauTraLoi.getOrDefault(cauHoiIndex, 0L);
    }

    public boolean isCauTraLoiDung(int cauHoiIndex) {
        return ketQuaCauTraLoi.getOrDefault(cauHoiIndex, false);
    }

    @Override
    public String toString() {
        return "DanhSachLichSuCauTraLoi{" +
                "danhSachCauDaTraLoi=" + danhSachCauDaTraLoi +
                ", thoiGianCauTraLoi=" + thoiGianCauTraLoi +
                ", ketQuaCauTraLoi=" + ketQuaCauTraLoi +
                '}';
    }
}
