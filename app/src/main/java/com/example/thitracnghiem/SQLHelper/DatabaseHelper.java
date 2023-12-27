package com.example.thitracnghiem.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import com.example.thitracnghiem.Models.CauHoiTracNghiem;
import com.example.thitracnghiem.Models.DeThi;
import com.example.thitracnghiem.Models.LichSuLamBai;
import com.example.thitracnghiem.Models.MonHoc;
import com.example.thitracnghiem.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ThiTracNghiem.db";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và các cột trong bảng MonHoc
    private static final String TABLE_MONHOC = "monhoc";
    private static final String COLUMN_MONHOC_ID = "id";
    private static final String COLUMN_MONHOC_TEN = "tenMonHoc";
    private static final String COLUMN_MONHOC_HINHANH = "hinhAnh";

    // Tên bảng và các cột trong bảng DeThi
    private static final String TABLE_DETHI = "dethi";
    private static final String COLUMN_DETHI_ID = "id";
    private static final String COLUMN_DETHI_MONHOC_ID = "idMonHoc";
    private static final String COLUMN_DETHI_SODETHI = "soDeThi";

    // Tên bảng và các cột trong bảng CauHoiTracNghiem
    private static final String TABLE_CAUHOI = "cauhoitracnghiem";
    private static final String COLUMN_CAUHOI_ID = "id";
    private static final String COLUMN_CAUHOI_DETHI_ID = "idDeThi";
    private static final String COLUMN_CAUHOI_NOIDUNG = "noiDungCauHoi";
    private static final String COLUMN_CAUHOI_PHUONGANA = "phuongAnA";
    private static final String COLUMN_CAUHOI_PHUONGANB = "phuongAnB";
    private static final String COLUMN_CAUHOI_PHUONGANC = "phuongAnC";
    private static final String COLUMN_CAUHOI_PHUONGAND = "phuongAnD";
    private static final String COLUMN_CAUHOI_DAPANDUNG = "dapAnDung";

    //Tên bảng và các cột trong bảng LichSuLamBai
    private static final String TABLE_LICHSULAMBAI = "lichsulambai";
    private static final String COLUMN_LICHSULAMBAI_ID = "id";
    private static final String COLUMN_LICHSULAMBAI_DETHI_ID = "idDeThi";
    private static final String COLUMN_LICHSULAMBAI_THOIGIANLAMBAI = "thoiGianLamBai";
    private static final String COLUMN_LICHSULAMBAI_DAPANDACHON = "dapAnDaChon";
    private static final String COLUMN_LICHSULAMBAI_DAPANDUNG = "dapAnDung";
    private static final String COLUMN_LICHSULAMBAI_CAUHOI = "cauHoi";
    private static final String COLUMN_LICHSULAMBAI_DAPAN_A = "dapAnA";
    private static final String COLUMN_LICHSULAMBAI_DAPAN_B = "dapAnB";
    private static final String COLUMN_LICHSULAMBAI_DAPAN_C = "dapAnC";
    private static final String COLUMN_LICHSULAMBAI_DAPAN_D = "dapAnD";
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng MonHoc
        String createMonHocTable = "CREATE TABLE " + TABLE_MONHOC + "("
                + COLUMN_MONHOC_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_MONHOC_TEN + " TEXT,"
                + COLUMN_MONHOC_HINHANH + " TEXT)";
        db.execSQL(createMonHocTable);

        // Tạo bảng DeThi
        String createDeThiTable = "CREATE TABLE " + TABLE_DETHI + "("
                + COLUMN_DETHI_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_DETHI_MONHOC_ID + " INTEGER,"
                + COLUMN_DETHI_SODETHI + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_DETHI_MONHOC_ID + ") REFERENCES " + TABLE_MONHOC + "(" + COLUMN_MONHOC_ID + "))";
        db.execSQL(createDeThiTable);

        // Tạo bảng CauHoiTracNghiem
        String createCauHoiTable = "CREATE TABLE " + TABLE_CAUHOI + "("
                + COLUMN_CAUHOI_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_CAUHOI_DETHI_ID + " INTEGER,"
                + COLUMN_CAUHOI_NOIDUNG + " TEXT,"
                + COLUMN_CAUHOI_PHUONGANA + " TEXT,"
                + COLUMN_CAUHOI_PHUONGANB + " TEXT,"
                + COLUMN_CAUHOI_PHUONGANC + " TEXT,"
                + COLUMN_CAUHOI_PHUONGAND + " TEXT,"
                + COLUMN_CAUHOI_DAPANDUNG + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_CAUHOI_DETHI_ID + ") REFERENCES " + TABLE_DETHI + "(" + COLUMN_DETHI_ID + "))";
        db.execSQL(createCauHoiTable);

        //Tạo bảng LichSu
        String createLichSuLamBaiTable = "CREATE TABLE " + TABLE_LICHSULAMBAI + "("
                + COLUMN_LICHSULAMBAI_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_LICHSULAMBAI_DETHI_ID + " INTEGER,"
                + COLUMN_LICHSULAMBAI_THOIGIANLAMBAI + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPANDACHON + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPANDUNG + " TEXT,"
                + COLUMN_LICHSULAMBAI_CAUHOI + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPAN_A + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPAN_B + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPAN_C + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPAN_D + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_LICHSULAMBAI_DETHI_ID + ") REFERENCES " + TABLE_DETHI + "(" + COLUMN_DETHI_ID + "))";
        db.execSQL(createLichSuLamBaiTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAUHOI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETHI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONHOC);
        onCreate(db);
    }

    public void AddMonHoc() {
        deleteAllMonHoc();
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MONHOC_TEN, "Toán");
        values.put(COLUMN_MONHOC_HINHANH, R.drawable.montoan);
        database.insert(TABLE_MONHOC, null, values);
        values.put(COLUMN_MONHOC_TEN, "Vật lý");
        values.put(COLUMN_MONHOC_HINHANH,R.drawable.monly);
        database.insert(TABLE_MONHOC, null, values);
        values.put(COLUMN_MONHOC_TEN, "Hóa học");
        values.put(COLUMN_MONHOC_HINHANH,R.drawable.monhoa);
        database.insert(TABLE_MONHOC, null, values);
        values.put(COLUMN_MONHOC_TEN, "Sinh học");
        values.put(COLUMN_MONHOC_HINHANH,R.drawable.monsinh);
        database.insert(TABLE_MONHOC, null, values);
        values.put(COLUMN_MONHOC_TEN, "Tiếng anh");
        values.put(COLUMN_MONHOC_HINHANH,R.drawable.eng);
        database.insert(TABLE_MONHOC, null, values);
        values.put(COLUMN_MONHOC_TEN, "Lịch sử");
        values.put(COLUMN_MONHOC_HINHANH,R.drawable.monsu);
        database.insert(TABLE_MONHOC, null, values);
        values.put(COLUMN_MONHOC_TEN, "Địa lý");
        values.put(COLUMN_MONHOC_HINHANH,R.drawable.mondia);
        database.insert(TABLE_MONHOC, null, values);
        values.put(COLUMN_MONHOC_TEN, "Giáo dục công dân");
        values.put(COLUMN_MONHOC_HINHANH,R.drawable.mongdcd);
        database.insert(TABLE_MONHOC, null, values);
        database.close();
    }

    public List<MonHoc> getListMonHoc() {
        List<MonHoc> monHocList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {COLUMN_MONHOC_ID,COLUMN_MONHOC_TEN, COLUMN_MONHOC_HINHANH};
        Cursor cursor = database.query(TABLE_MONHOC, columns, null, null, null, null, null);
        int columnIndexIdMonHoc=cursor.getColumnIndex(COLUMN_MONHOC_ID);
        int columnIndexTenMonHoc = cursor.getColumnIndex(COLUMN_MONHOC_TEN);
        int columnIndexHinhAnh = cursor.getColumnIndex(COLUMN_MONHOC_HINHANH);
        if (columnIndexTenMonHoc != -1 && columnIndexHinhAnh != -1) {
            if (cursor.moveToFirst()) {
                do {
                    int idmonhoc=cursor.getInt(columnIndexIdMonHoc);
                    String tenMonHoc = cursor.getString(columnIndexTenMonHoc);
                    int hinhAnh = cursor.getInt(columnIndexHinhAnh);
                    MonHoc monHoc = new MonHoc(idmonhoc,tenMonHoc, hinhAnh);
                    monHocList.add(monHoc);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        database.close();
        return monHocList;
    }
    public void AddDeThi(int idMonHoc,int SoDeThi) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DETHI_MONHOC_ID, idMonHoc);
        values.put(COLUMN_DETHI_SODETHI, SoDeThi); // Example: Setting the "soDeThi" to 1
        long deThiId = database.insert(TABLE_DETHI, null, values);
        Log.e("Them thanh cong", "Them thanh cong - DeThi ID: " + deThiId);

        database.close();
    }
    public void AddDeThi() {
        deleteAllDeThi();
        for (int i =1; i <=getListMonHoc().size(); i++) {
            for (int j = 1; j <= 4; j++) {
                SQLiteDatabase database = getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(COLUMN_DETHI_MONHOC_ID, i);
                values.put(COLUMN_DETHI_SODETHI, j);
                database.insert(TABLE_DETHI, null, values);
                database.close();
            }
        }

    }
    public List<DeThi> getDeThiList(int idMonHoc) {
        List<DeThi> deThiList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {COLUMN_DETHI_ID, COLUMN_DETHI_SODETHI};
        String selection = COLUMN_DETHI_MONHOC_ID + " = ?";
        String[] selectionArgs = {String.valueOf(idMonHoc)};
        Cursor cursor = database.query(TABLE_DETHI, columns, selection, selectionArgs, null, null, null);
        int columnIndexDeThiId = cursor.getColumnIndex(COLUMN_DETHI_ID);
        int columnIndexSoDeThi = cursor.getColumnIndex(COLUMN_DETHI_SODETHI);
        if (columnIndexDeThiId != -1 && columnIndexSoDeThi != -1) {
            if (cursor.moveToFirst()) {
                do {
                    int deThiId = cursor.getInt(columnIndexDeThiId);
                    int soDeThi = cursor.getInt(columnIndexSoDeThi);
                    DeThi deThi = new DeThi(deThiId, idMonHoc, soDeThi);
                    deThiList.add(deThi);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        database.close();
        return deThiList;
    }

    public List<DeThi> getDeThiListByIds(List<Integer> deThiIds) {
        List<DeThi> deThiList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        String[] columns = {COLUMN_DETHI_ID, COLUMN_DETHI_MONHOC_ID, COLUMN_DETHI_SODETHI /* other DeThi columns you need */};
        String selection = COLUMN_DETHI_ID + " IN (" + TextUtils.join(",", Collections.nCopies(deThiIds.size(), "?")) + ")";
        String[] selectionArgs = new String[deThiIds.size()];
        for (int i = 0; i < deThiIds.size(); i++) {
            selectionArgs[i] = String.valueOf(deThiIds.get(i));
        }
        Cursor cursor = database.query(TABLE_DETHI, columns, selection, selectionArgs, null, null, null);
        int columnIndexDeThiId = cursor.getColumnIndex(COLUMN_DETHI_ID);
        int columnIndexMonHocId = cursor.getColumnIndex(COLUMN_DETHI_MONHOC_ID);
        int columnIndexSoDeThi = cursor.getColumnIndex(COLUMN_DETHI_SODETHI);
        if (columnIndexDeThiId != -1 && columnIndexMonHocId != -1 && columnIndexSoDeThi != -1) {
            if (cursor.moveToFirst()) {
                do {
                    int deThiId = cursor.getInt(columnIndexDeThiId);
                    int idMonHoc = cursor.getInt(columnIndexMonHocId);
                    int soDeThi = cursor.getInt(columnIndexSoDeThi);
                    DeThi deThi = new DeThi(deThiId, idMonHoc, soDeThi);
                    deThiList.add(deThi);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        database.close();

        return deThiList;
    }

    public void AddCauHoiTracNghiem(int idDeThi, String noiDungCauHoi, String phuongAnA, String phuongAnB, String phuongAnC, String phuongAnD, String dapAnDung) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CAUHOI_DETHI_ID, idDeThi);
        values.put(COLUMN_CAUHOI_NOIDUNG, noiDungCauHoi);
        values.put(COLUMN_CAUHOI_PHUONGANA, phuongAnA);
        values.put(COLUMN_CAUHOI_PHUONGANB, phuongAnB);
        values.put(COLUMN_CAUHOI_PHUONGANC, phuongAnC);
        values.put(COLUMN_CAUHOI_PHUONGAND, phuongAnD);
        values.put(COLUMN_CAUHOI_DAPANDUNG, dapAnDung);

            long cauHoiId = database.insert(TABLE_CAUHOI, null, values);
            Log.e("Thêm thành công", "Thêm thành công - Câu hỏi ID: " + cauHoiId);
            database.close();
        }

    public List<CauHoiTracNghiem> getCauHoiTracNghiemByDeThi(int idDeThi) {
        List<CauHoiTracNghiem> listCauHoi = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_CAUHOI + " WHERE " + COLUMN_CAUHOI_DETHI_ID + " = ?";

    Cursor cursor = database.rawQuery(selectQuery, new String[]{String.valueOf(idDeThi)});

        try {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_ID));
                    String noiDung = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_NOIDUNG));
                    String phuongAnA = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANA));
                    String phuongAnB = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANB));
                    String phuongAnC = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANC));
                    String phuongAnD = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGAND));
                    String dapAnDung = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_DAPANDUNG));

                    CauHoiTracNghiem cauHoi = new CauHoiTracNghiem(id, idDeThi, noiDung, phuongAnA, phuongAnB, phuongAnC, phuongAnD, dapAnDung);
                    listCauHoi.add(cauHoi);
                    System.out.println("ID: " + cauHoi.getId());
                    System.out.println("ID Đề thi: " + cauHoi.getIdDeThi());
                    System.out.println("Nội dung: " + cauHoi.getNoiDungCauHoi());
                    System.out.println("Phương án A: " + cauHoi.getPhuongAnA());
                    System.out.println("Phương án B: " +cauHoi.getPhuongAnB());
                    System.out.println("Phương án C: " + cauHoi.getPhuongAnC());
                    System.out.println("Phương án D: " +cauHoi.getPhuongAnD());
                    System.out.println("Đáp án đúng: " + cauHoi.getDapAnDung());
                    System.out.println("--------------------------------");
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
            database.close();
        }

        return listCauHoi;
    }
    public void addLichSuLamBai(int idDeThi, String thoiGianLamBai, String dapAnDaChon, String dapAnDung, String cauHoi, String dapAnA, String dapAnB, String dapAnC, String dapAnD) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LICHSULAMBAI_DETHI_ID, idDeThi);
        values.put(COLUMN_LICHSULAMBAI_THOIGIANLAMBAI, thoiGianLamBai);
        values.put(COLUMN_LICHSULAMBAI_DAPANDACHON, dapAnDaChon);
        values.put(COLUMN_LICHSULAMBAI_DAPANDUNG, dapAnDung);
        values.put(COLUMN_LICHSULAMBAI_CAUHOI, cauHoi);
        values.put(COLUMN_LICHSULAMBAI_DAPAN_A, dapAnA);
        values.put(COLUMN_LICHSULAMBAI_DAPAN_B, dapAnB);
        values.put(COLUMN_LICHSULAMBAI_DAPAN_C, dapAnC);
        values.put(COLUMN_LICHSULAMBAI_DAPAN_D, dapAnD);

        long lichSuId = database.insert(TABLE_LICHSULAMBAI, null, values);
        Log.e("Thêm lịch sử", "Thêm lịch sử thành công - ID: " + lichSuId);
        database.close();
    }

    public List<LichSuLamBai> getAllLichSuLamBai() {
        List<LichSuLamBai> lichSuList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LICHSULAMBAI;

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                LichSuLamBai lichSu = new LichSuLamBai();
                lichSu.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_ID)));
                lichSu.setIdDeThi(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DETHI_ID)));
                lichSu.setThoiGianLamBai(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_THOIGIANLAMBAI)));
                lichSu.setDapAnDaChon(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPANDACHON)));
                lichSu.setDapAnDung(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPANDUNG)));
                lichSu.setCauHoi(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_CAUHOI)));
                lichSu.setDapAnA(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPAN_A)));
                lichSu.setDapAnB(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPAN_B)));
                lichSu.setDapAnC(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPAN_C)));
                lichSu.setDapAnD(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPAN_D)));
                lichSuList.add(lichSu);
            } while (cursor.moveToNext());
        }
        System.out.println("Lịch sử làm bài Size"+ lichSuList.size());

        cursor.close();
        database.close();
        return lichSuList;
    }


    public List<LichSuLamBai> getListByIdDeThi(int idDeThi) {
        List<LichSuLamBai> lichSuList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LICHSULAMBAI +
                " WHERE " + COLUMN_LICHSULAMBAI_DETHI_ID + " = ?";

        Cursor cursor = database.rawQuery(selectQuery, new String[]{String.valueOf(idDeThi)});
        if (cursor.moveToFirst()) {
            do {
                LichSuLamBai lichSu = new LichSuLamBai();
                // Lấy thông tin từ Cursor và gán vào đối tượng LichSuLamBai
                lichSu.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_ID)));
                lichSu.setIdDeThi(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DETHI_ID)));
                lichSu.setThoiGianLamBai(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_THOIGIANLAMBAI)));
                lichSu.setDapAnDaChon(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPANDACHON)));
                lichSu.setDapAnDung(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPANDUNG)));
                lichSu.setCauHoi(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_CAUHOI)));
                lichSu.setDapAnA(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPAN_A)));
                lichSu.setDapAnB(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPAN_B)));
                lichSu.setDapAnC(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPAN_C)));
                lichSu.setDapAnD(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LICHSULAMBAI_DAPAN_D)));

                lichSuList.add(lichSu);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return lichSuList;
    }
    //Xóa lịch sử làm bài theo đề
    public void deleteLichSuById(int id){
        SQLiteDatabase database = getWritableDatabase();
        String selection = COLUMN_LICHSULAMBAI_DETHI_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        int deletedRows = database.delete(TABLE_LICHSULAMBAI, selection, selectionArgs);
        if (deletedRows > 0) {
            System.out.println("Xóa thành công ");
        } else {
            System.out.println("Lỗi không có dữ liệu ");
        }
    }
    public void CauHoi(){
        deleteAllCauHoi();
        AddCauHoiTracNghiem(1,"Câu 1: Cho hàm số  . Tìm tất cả giá trị của   để hàm số đồng biến trên ?","A. m>0","B. m<=0","C. m>=0","D. m>1","A. m>0");
        AddCauHoiTracNghiem(1,"Câu 2: Trong các câu sau, câu nào không phải là mệnh đề?","A. Băng Cốc là thủ đô của Thái Lan","B. Buồn ngủ quá!","C. 8 là số lẻ.","D. Hình thoi có hai đường chéo vuông góc với nhau.","D. Hình thoi có hai đường chéo vuông góc với nhau.");
        AddCauHoiTracNghiem(1,"Câu 3: Với hai điểm phân biệt A, B ta có được bao nhiêu vectơ khác vectơ không có điểm đầu và điểm cuối là A hoặc B?","A. 1","B. 2","C. 2","D. 4.","D. 4.");
        AddCauHoiTracNghiem(1,"Câu 4: Cho tập hợpA={α;β;γ;λ}. Gọi X là tập hợp con của A và thỏa: β∈Xvà X có 3 phần từ. Số tập X là?","A. 3","B. 8","C. 13","D. Hình thoi có hai đường chéo vuông góc với nhau.","A. 3");

        AddCauHoiTracNghiem(2,"Câu 1: Cho hàm số  . Tìm tất cả giá trị của   để hàm số đồng biến trên ?","A. m>0","B. m<=0","C. m>=0","D. m>1","A. m>0");
        AddCauHoiTracNghiem(2,"Câu 2: Trong các câu sau, câu nào không phải là mệnh đề?","A. Băng Cốc là thủ đô của Thái Lan","B. Buồn ngủ quá!","C. 8 là số lẻ.","D. Hình thoi có hai đường chéo vuông góc với nhau.","D. Hình thoi có hai đường chéo vuông góc với nhau.");
        AddCauHoiTracNghiem(2,"Câu 3: Với hai điểm phân biệt A, B ta có được bao nhiêu vectơ khác vectơ không có điểm đầu và điểm cuối là A hoặc B?","A. 1","B. 2","C. 2","D. 4.","D. 4.");
        AddCauHoiTracNghiem(2,"Câu 4: Cho tập hợpA={α;β;γ;λ}. Gọi X là tập hợp con của A và thỏa: β∈Xvà X có 3 phần từ. Số tập X là?","A. 3","B. 8","C. 13","D. Hình thoi có hai đường chéo vuông góc với nhau.","A. 3");

        AddCauHoiTracNghiem(3,"Câu 1: Cho hàm số  . Tìm tất cả giá trị của   để hàm số đồng biến trên ?","A. m>0","B. m<=0","C. m>=0","D. m>1","A. m>0");
        AddCauHoiTracNghiem(3,"Câu 2: Trong các câu sau, câu nào không phải là mệnh đề?","A. Băng Cốc là thủ đô của Thái Lan","B. Buồn ngủ quá!","C. 8 là số lẻ.","D. Hình thoi có hai đường chéo vuông góc với nhau.","D. Hình thoi có hai đường chéo vuông góc với nhau.");
        AddCauHoiTracNghiem(3,"Câu 3: Với hai điểm phân biệt A, B ta có được bao nhiêu vectơ khác vectơ không có điểm đầu và điểm cuối là A hoặc B?","A. 1","B. 2","C. 2","D. 4.","D. 4.");
        AddCauHoiTracNghiem(3,"Câu 4: Cho tập hợpA={α;β;γ;λ}. Gọi X là tập hợp con của A và thỏa: β∈Xvà X có 3 phần từ. Số tập X là?","A. 3","B. 8","C. 13","D. Hình thoi có hai đường chéo vuông góc với nhau.","A. 3");

        AddCauHoiTracNghiem(4,"Câu 1: Cho hàm số  . Tìm tất cả giá trị của   để hàm số đồng biến trên ?","A. m>0","B. m<=0","C. m>=0","D. m>1","A. m>0");
        AddCauHoiTracNghiem(4,"Câu 2: Trong các câu sau, câu nào không phải là mệnh đề?","A. Băng Cốc là thủ đô của Thái Lan","B. Buồn ngủ quá!","C. 8 là số lẻ.","D. Hình thoi có hai đường chéo vuông góc với nhau.","D. Hình thoi có hai đường chéo vuông góc với nhau.");
        AddCauHoiTracNghiem(4,"Câu 3: Với hai điểm phân biệt A, B ta có được bao nhiêu vectơ khác vectơ không có điểm đầu và điểm cuối là A hoặc B?","A. 1","B. 2","C. 2","D. 4.","D. 4.");
        AddCauHoiTracNghiem(4,"Câu 4: Cho tập hợpA={α;β;γ;λ}. Gọi X là tập hợp con của A và thỏa: β∈Xvà X có 3 phần từ. Số tập X là?","A. 3","B. 8","C. 13","D. Hình thoi có hai đường chéo vuông góc với nhau.","A. 3");

        AddCauHoiTracNghiem(5,"Câu 1: Hãy chọn câu đúng?","A. Hệ quy chiếu bao gồm vật làm mốc, hệ toạ độ, mốc thời gian.","B. Hệ quy chiếu bao gồm hệ toạ độ, mốc thời gian và đồng hồ.","C. Hệ quy chiếu bao gồm vật làm mốc, mốc thời gian và đồng hồ.","D.Hệ quy chiếu bao gồm vật làm mốc, hệ toạ độ, mốc thời gian và đồng hồ.","D.Hệ quy chiếu bao gồm vật làm mốc, hệ toạ độ, mốc thời gian và đồng hồ.");
        AddCauHoiTracNghiem(5,"Câu 2: Hãy chỉ ra câu  sai? Chuyển động tròn đều là chuyển  động có các đặc điểm:","A. Tốc độ dài không đổi.","B. Quỹ đạo là đường tròn.","C. Tốc độ góc không đổi.  ","D. Vectơ gia tốc không đổi. ","D. Vectơ gia tốc không đổi.");
        AddCauHoiTracNghiem(5,"Câu 3: Với hai điểm phân biệt A, B ta có được bao nhiêu vectơ khác vectơ không có điểm đầu và điểm cuối là A hoặc B?","A. 1","B. 2","C. 2","D. 4.","D. 4.");
        AddCauHoiTracNghiem(5,"Câu 4: Tại cùng một vị trí xác định trên mặt đất và ở cùng độ cao thì :","A. Hai vật rơi với cùng vận tốc","B. Vận tốc của  hai vật không đổi.","C. Vận tốc của vật nặng nhỏ hơn vận tốc của vật nhẹ.","D. Vận tốc của vật nặng lớn hơn vận tốc của vật nhẹ.","A. Hai vật rơi với cùng vận tốc.");

        AddCauHoiTracNghiem(6,"Câu 1: Trong các  phát  biểu  dưới  đây, phát  biểu  nào  đúng ? tChuyển động cơ là: ","A. sự thay đổi vị trí của vật này so với vật khác theo thời gian.","B.sự thay đổi chiều của vật này so với vật khác theo thời gian.","C. sự thay đổi hướng của vật này so với vật khác theo thời gian.","D.sự thay đổi phương của vật này so với vật khác theo thời gian .","A. sự thay đổi vị trí của vật này so với vật khác theo thời gian.");
        AddCauHoiTracNghiem(6,"Câu 2: Hãy chỉ ra câu  sai? Chuyển động tròn đều là chuyển  động có các đặc điểm:","A. Tốc độ góc không đổi.  ","B. Quỹ đạo là đường tròn.","C. Vectơ gia tốc không đổi.  ","D. Tốc độ dài không đổi. ","C. Vectơ gia tốc không đổi.");
        AddCauHoiTracNghiem(6,"Câu 3: Đặc điểm nào dưới đây không phải là đặc điểm của vật chuyển động rơi tự do?","A. Chuyển động nhanh dần đều. ","B. Công thức tính vận tốc v = g. t2.","C. Tại một vị trí xác định và ở gần mặt đất, mọi vật rơi tự do như nhau.","D. Chuyển động theo phương thẳng đứng, chiều từ trên xuống dưới.","B. Công thức tính vận tốc v = g. t2.");
        AddCauHoiTracNghiem(6,"Câu 4: Khi ô tô đang chạy với vận tốc 10 m/s trên đoạn đường thẳng thì người lái xe hãm phanh và ô tô chuyển động chậm dần đều. Cho tới khi dừng hẳn lại thì  ô tô đã chạy  thêm được 50m. Nếu chọn chiều dương là chiều chuyển động của ô tô thì gia tốc của ô tô là:","A. a = - 0,5 m/s2. ","B. a = 0,5 m/s2.","C. a = 1 m/s2.","D. a = - 1 m/s2. ","D. a = - 1 m/s2.");

        AddCauHoiTracNghiem(7,"Câu 1:  Phương trình chuyển động của một chất điểm có dạng:x =2t + t ^2 (x; m; t; s).Vận tốc ban đầu của chất điểm: ","A. 1 m/s.","B.   2 m/s.","C.   10 m/s.","D.   12 m/s.","B.   2 m/s.");
        AddCauHoiTracNghiem(7,"Câu 2: Phương trình chuyển động thẳng đều của một chất điểm có dạng: x = 10t – 5. (x: km, t: h). Quãng đường đi được của chất điểm sau 2h là: ","A.  40 km.","B.  15 km.","C.  20 km.","D.  10 km..","C.  20 km.");
        AddCauHoiTracNghiem(7,"Câu 3: Phương trình chuyển động thẳng đều của một chất điểm có dạng: x = 4t – 10" + "(x: km, t: h). Tọa độ ban đầu của chất điểm là?","A. 4 km","B. - 6 km","C. 10 km","D. 14 km.","D. 14 km.");
        AddCauHoiTracNghiem(7,"Câu 4: Một vật nặng rơi từ độ cao 80m xuống mặt đất. Sau bao lâu vật chạm đất? Lấy g = 10 m/s2.","A. t = 1s.","B. t = 2s.","C. t = 3 s.","D. t = 4 s. ","C. t = 3 s.");

        AddCauHoiTracNghiem(8,"Câu 1:  Phương trình chuyển động của một chất điểm có dạng:x =2t + t ^2 (x; m; t; s).Vận tốc ban đầu của chất điểm: ","A. 1 m/s.","B.   2 m/s.","C.   10 m/s.","D.   12 m/s.","B.   2 m/s.");
        AddCauHoiTracNghiem(8,"Câu 2: Phương trình chuyển động thẳng đều của một chất điểm có dạng: x = 10t – 5. (x: km, t: h). Quãng đường đi được của chất điểm sau 2h là: ","A.  40 km.","B.  15 km.","C.  20 km.","D.  10 km..","C.  20 km.");
        AddCauHoiTracNghiem(8,"Câu 3: Phương trình chuyển động thẳng đều của một chất điểm có dạng: x = 4t – 10" + "(x: km, t: h). Tọa độ ban đầu của chất điểm là?","A. 4 km","B. - 6 km","C. 10 km","D. 14 km.","D. 14 km.");
        AddCauHoiTracNghiem(8,"Câu 4: Một vật nặng rơi từ độ cao 80m xuống mặt đất. Sau bao lâu vật chạm đất? Lấy g = 10 m/s2.","A. t = 1s.","B. t = 2s.","C. t = 3 s.","D. t = 4 s. ","C. t = 3 s.");

        AddCauHoiTracNghiem(9,"Câu 1: Chất nào sau đây có nhiệt độ nóng chảy cao nhất? ","A. MgO. ","B. Nước đá.","C. CO2 rắn. ","D. I2.","A. MgO.");
        AddCauHoiTracNghiem(9,"Câu 2:Dãy gồm các phân tử đều có liên kết ion là ","A. Cl2, Br2, I2, HCl.","B. HCl, H2S, NaCl, N2O.","C. Na2O, BaCl2, Al2O3, MgCl2. ","D. Na2SO4, CO2, BF3.","C. Na2O, BaCl2, Al2O3, MgCl2.");
        AddCauHoiTracNghiem(9,"Câu 3: Một orbital p có thể chứa tối đa bao nhiêu electron?","A. 2.","B. 6.","C. 10.","D. 14.","B. 6.");
        AddCauHoiTracNghiem(9,"Câu 4: Đồng vị là những nguyên tử của cùng một nguyên tố hóa học, chúng khác nhau về","A. tính chất hóa học.","B. số proton.","C. số neutron.","D. số electron. ","C. số neutron.");

        AddCauHoiTracNghiem(10,"Câu 1: Chất nào sau đây có nhiệt độ nóng chảy cao nhất? ","A. MgO. ","B. Nước đá.","C. CO2 rắn. ","D. I2.","A. MgO.");
        AddCauHoiTracNghiem(10,"Câu 2:Dãy gồm các phân tử đều có liên kết ion là ","A. Cl2, Br2, I2, HCl.","B. HCl, H2S, NaCl, N2O.","C. Na2O, BaCl2, Al2O3, MgCl2. ","D. Na2SO4, CO2, BF3.","C. Na2O, BaCl2, Al2O3, MgCl2.");
        AddCauHoiTracNghiem(10,"Câu 3: Một orbital p có thể chứa tối đa bao nhiêu electron?","A. 2.","B. 6.","C. 10.","D. 14.","B. 6.");
        AddCauHoiTracNghiem(10,"Câu 4: Đồng vị là những nguyên tử của cùng một nguyên tố hóa học, chúng khác nhau về","A. tính chất hóa học.","B. số proton.","C. số neutron.","D. số electron. ","C. số neutron.");

        AddCauHoiTracNghiem(11,"Câu 1: Chất nào sau đây có nhiệt độ nóng chảy cao nhất? ","A. MgO. ","B. Nước đá.","C. CO2 rắn. ","D. I2.","A. MgO.");
        AddCauHoiTracNghiem(11,"Câu 2:Dãy gồm các phân tử đều có liên kết ion là ","A. Cl2, Br2, I2, HCl.","B. HCl, H2S, NaCl, N2O.","C. Na2O, BaCl2, Al2O3, MgCl2. ","D. Na2SO4, CO2, BF3.","C. Na2O, BaCl2, Al2O3, MgCl2.");
        AddCauHoiTracNghiem(11,"Câu 3: Một orbital p có thể chứa tối đa bao nhiêu electron?","A. 2.","B. 6.","C. 10.","D. 14.","B. 6.");
        AddCauHoiTracNghiem(11,"Câu 4: Đồng vị là những nguyên tử của cùng một nguyên tố hóa học, chúng khác nhau về","A. tính chất hóa học.","B. số proton.","C. số neutron.","D. số electron. ","C. số neutron.");

        AddCauHoiTracNghiem(12,"Câu 1: Chất nào sau đây có nhiệt độ nóng chảy cao nhất? ","A. MgO. ","B. Nước đá.","C. CO2 rắn. ","D. I2.","A. MgO.");
        AddCauHoiTracNghiem(12,"Câu 2:Dãy gồm các phân tử đều có liên kết ion là ","A. Cl2, Br2, I2, HCl.","B. HCl, H2S, NaCl, N2O.","C. Na2O, BaCl2, Al2O3, MgCl2. ","D. Na2SO4, CO2, BF3.","C. Na2O, BaCl2, Al2O3, MgCl2.");
        AddCauHoiTracNghiem(12,"Câu 3: Một orbital p có thể chứa tối đa bao nhiêu electron?","A. 2.","B. 6.","C. 10.","D. 14.","B. 6.");
        AddCauHoiTracNghiem(12,"Câu 4: Đồng vị là những nguyên tử của cùng một nguyên tố hóa học, chúng khác nhau về","A. tính chất hóa học.","B. số proton.","C. số neutron.","D. số electron. ","C. số neutron.");

        AddCauHoiTracNghiem(13,"Câu 1: Bệnh nào sau đây liên quan đến sự thiếu nguyên tố vi lượng? ","A. Bệnh bướu cổ. ","B. Bệnh còi xương.","C. Bệnh cận thị. ","D. Bệnh tự kỉ","A. Bệnh bướu cổ.");
        AddCauHoiTracNghiem(13,"Câu 2:Đặc tính nào sau đây của phân tử nước quy định các đặc tính còn lại?","A. Tính liên kết.","B. Tính điều hòa nhiệt.","C. Tính phân cực. ","D. Tính cách li.","C. Tính phân cực");
        AddCauHoiTracNghiem(13,"Câu 3: Để bảo quản rau quả chúng ta không nên làm điều gì?","A. Giữ rau quả trong ngăn đá của tủ lạnh.","B. Giữ rau quả trong ngăn mát của tủ lạnh.","C. Sấy khô rau quả.","D. Ngâm rau quả trong nước muối hoặc nước đường.","A. Giữ rau quả trong ngăn đá của tủ lạnh.");
        AddCauHoiTracNghiem(13,"Câu 4: Cơ thể người không tiêu hóa được loại đường nào?","A. Lactozo.","B. Mantozo.","C. Xenlulozo.","D. Saccarozo. ","C. Xenlulozo.");

        AddCauHoiTracNghiem(14,"Câu 1: Bệnh nào sau đây liên quan đến sự thiếu nguyên tố vi lượng? ","A. Bệnh bướu cổ. ","B. Bệnh còi xương.","C. Bệnh cận thị. ","D. Bệnh tự kỉ","A. Bệnh bướu cổ.");
        AddCauHoiTracNghiem(14,"Câu 2:Đặc tính nào sau đây của phân tử nước quy định các đặc tính còn lại?","A. Tính liên kết.","B. Tính điều hòa nhiệt.","C. Tính phân cực. ","D. Tính cách li.","C. Tính phân cực");
        AddCauHoiTracNghiem(14,"Câu 3: Để bảo quản rau quả chúng ta không nên làm điều gì?","A. Giữ rau quả trong ngăn đá của tủ lạnh.","B. Giữ rau quả trong ngăn mát của tủ lạnh.","C. Sấy khô rau quả.","D. Ngâm rau quả trong nước muối hoặc nước đường.","A. Giữ rau quả trong ngăn đá của tủ lạnh.");
        AddCauHoiTracNghiem(14,"Câu 4: Cơ thể người không tiêu hóa được loại đường nào?","A. Lactozo.","B. Mantozo.","C. Xenlulozo.","D. Saccarozo. ","C. Xenlulozo.");

        AddCauHoiTracNghiem(15,"Câu 1: Bệnh nào sau đây liên quan đến sự thiếu nguyên tố vi lượng? ","A. Bệnh bướu cổ. ","B. Bệnh còi xương.","C. Bệnh cận thị. ","D. Bệnh tự kỉ","A. Bệnh bướu cổ.");
        AddCauHoiTracNghiem(15,"Câu 2:Đặc tính nào sau đây của phân tử nước quy định các đặc tính còn lại?","A. Tính liên kết.","B. Tính điều hòa nhiệt.","C. Tính phân cực. ","D. Tính cách li.","C. Tính phân cực");
        AddCauHoiTracNghiem(15,"Câu 3: Để bảo quản rau quả chúng ta không nên làm điều gì?","A. Giữ rau quả trong ngăn đá của tủ lạnh.","B. Giữ rau quả trong ngăn mát của tủ lạnh.","C. Sấy khô rau quả.","D. Ngâm rau quả trong nước muối hoặc nước đường.","A. Giữ rau quả trong ngăn đá của tủ lạnh.");
        AddCauHoiTracNghiem(15,"Câu 4: Cơ thể người không tiêu hóa được loại đường nào?","A. Lactozo.","B. Mantozo.","C. Xenlulozo.","D. Saccarozo. ","C. Xenlulozo.");

        AddCauHoiTracNghiem(16,"Câu 1: Bệnh nào sau đây liên quan đến sự thiếu nguyên tố vi lượng? ","A. Bệnh bướu cổ. ","B. Bệnh còi xương.","C. Bệnh cận thị. ","D. Bệnh tự kỉ","A. Bệnh bướu cổ.");
        AddCauHoiTracNghiem(16,"Câu 2:Đặc tính nào sau đây của phân tử nước quy định các đặc tính còn lại?","A. Tính liên kết.","B. Tính điều hòa nhiệt.","C. Tính phân cực. ","D. Tính cách li.","C. Tính phân cực");
        AddCauHoiTracNghiem(16,"Câu 3: Để bảo quản rau quả chúng ta không nên làm điều gì?","A. Giữ rau quả trong ngăn đá của tủ lạnh.","B. Giữ rau quả trong ngăn mát của tủ lạnh.","C. Sấy khô rau quả.","D. Ngâm rau quả trong nước muối hoặc nước đường.","A. Giữ rau quả trong ngăn đá của tủ lạnh.");
        AddCauHoiTracNghiem(16,"Câu 4: Cơ thể người không tiêu hóa được loại đường nào?","A. Lactozo.","B. Mantozo.","C. Xenlulozo.","D. Saccarozo. ","C. Xenlulozo.");

        AddCauHoiTracNghiem(17,"Câu 1:The teacher said that we .......... harder on our pronunciation. ","A. work ","B. working","C. should work ","D. have worked","C. should work");
        AddCauHoiTracNghiem(17,"Câu 2:The train couldn’t run fast .................... snow","A. so","B. if","C. because ","D. because of.","D. because of");
        AddCauHoiTracNghiem(17,"Câu 3: Don’t let children .......... in the kitchen","A. play.","B. playing","C. to play","D. played.","A. play");
        AddCauHoiTracNghiem(17,"Câu 4: Which songs do they often sing ............. Christmas?","A. on","B. at.","C. in.","D. of ","B. at.");

        AddCauHoiTracNghiem(18,"Câu 1:The teacher said that we .......... harder on our pronunciation. ","A. work ","B. working","C. should work ","D. have worked","C. should work");
        AddCauHoiTracNghiem(18,"Câu 2:The train couldn’t run fast .................... snow","A. so","B. if","C. because ","D. because of.","D. because of");
        AddCauHoiTracNghiem(18,"Câu 3: Don’t let children .......... in the kitchen","A. play.","B. playing","C. to play","D. played.","A. play");
        AddCauHoiTracNghiem(18,"Câu 4: Which songs do they often sing ............. Christmas?","A. on","B. at.","C. in.","D. of ","B. at.");

        AddCauHoiTracNghiem(19,"Câu 1:The teacher said that we .......... harder on our pronunciation. ","A. work ","B. working","C. should work ","D. have worked","C. should work");
        AddCauHoiTracNghiem(19,"Câu 2:The train couldn’t run fast .................... snow","A. so","B. if","C. because ","D. because of.","D. because of");
        AddCauHoiTracNghiem(19,"Câu 3: Don’t let children .......... in the kitchen","A. play.","B. playing","C. to play","D. played.","A. play");
        AddCauHoiTracNghiem(19,"Câu 4: Which songs do they often sing ............. Christmas?","A. on","B. at.","C. in.","D. of ","B. at.");

        AddCauHoiTracNghiem(20,"Câu 1:The teacher said that we .......... harder on our pronunciation. ","A. work ","B. working","C. should work ","D. have worked","C. should work");
        AddCauHoiTracNghiem(20,"Câu 2:The train couldn’t run fast .................... snow","A. so","B. if","C. because ","D. because of.","D. because of");
        AddCauHoiTracNghiem(20,"Câu 3: Don’t let children .......... in the kitchen","A. play.","B. playing","C. to play","D. played.","A. play");
        AddCauHoiTracNghiem(20,"Câu 4: Which songs do they often sing ............. Christmas?","A. on","B. at.","C. in.","D. of ","B. at.");

        AddCauHoiTracNghiem(21,"Câu 1:TBiến đổi lớn nhất ở khu vực Đông Nam Á sau Chiến tranh thế giới thứ hai là. ","A. nhiều nước đạt tốc độ tăng trưởng kinh tế cao, trở thành nước công nghiệp ","B. Việt Nam góp phần làm sụp đổ chủ nghĩa thực dân kiểu cũ và kiểu mới.  ","C. từ thân phận là nước thuộc địa, các nước đã trở thành quốc gia độc lập, tự chủ.  ","D. thành lập và mở rộng hiệp hội khu vực - ASEAN","C. từ thân phận là nước thuộc địa, các nước đã trở thành quốc gia độc lập, tự chủ. ");
        AddCauHoiTracNghiem(21,"Câu 2:Điểm chung trong nguyên nhân làm cho kinh tế phát triển giữa Tây Âu  với Mĩ và Nhật Bản là?","A. Tài năng của giới lãnh đạo và kinh doanh.","B. Người lao động có tay nghề cao.","C. Gây chiến tranh xâm lược Việt Nam và Triều Tiên ","D. Áp dụng những thành tựu khoa học - kĩ thuật vào sản xuất.","D. Áp dụng những thành tựu khoa học - kĩ thuật vào sản xuất");
        AddCauHoiTracNghiem(21,"Câu 3: Văn kiện đặt nền tảng cho quan hệ Mĩ - Nhật sau Chiến tranh thế giới thứ hai là","A. Hiếp pháp mới của Nhật Bản (1946).","B. Hiệp ước an ninh Mĩ - Nhật (1951).","C. Hiệp ước Hoà bình Xan Phranxixcô (1951).","D. Học thuyết Phucưđa (1977).","B. Hiệp ước an ninh Mĩ - Nhật (1951).");
        AddCauHoiTracNghiem(21,"Câu 4: Mĩ  là nước khởi đầu cuộc cách mạng nào sau đây trong giai đoạn 1945-1973?","A. Cách mạng khoa học - kĩ thuật hiện đại.","B. Cách mạng công nghiệp.","C. Cách mạng trắng.","D. Cách mạng chất xám ","A. Cách mạng khoa học - kĩ thuật hiện đại.");

        AddCauHoiTracNghiem(22,"Câu 1:TBiến đổi lớn nhất ở khu vực Đông Nam Á sau Chiến tranh thế giới thứ hai là. ","A. nhiều nước đạt tốc độ tăng trưởng kinh tế cao, trở thành nước công nghiệp ","B. Việt Nam góp phần làm sụp đổ chủ nghĩa thực dân kiểu cũ và kiểu mới.  ","C. từ thân phận là nước thuộc địa, các nước đã trở thành quốc gia độc lập, tự chủ.  ","D. thành lập và mở rộng hiệp hội khu vực - ASEAN","C. từ thân phận là nước thuộc địa, các nước đã trở thành quốc gia độc lập, tự chủ. ");
        AddCauHoiTracNghiem(22,"Câu 2:Điểm chung trong nguyên nhân làm cho kinh tế phát triển giữa Tây Âu  với Mĩ và Nhật Bản là?","A. Tài năng của giới lãnh đạo và kinh doanh.","B. Người lao động có tay nghề cao.","C. Gây chiến tranh xâm lược Việt Nam và Triều Tiên ","D. Áp dụng những thành tựu khoa học - kĩ thuật vào sản xuất.","D. Áp dụng những thành tựu khoa học - kĩ thuật vào sản xuất");
        AddCauHoiTracNghiem(22,"Câu 3: Văn kiện đặt nền tảng cho quan hệ Mĩ - Nhật sau Chiến tranh thế giới thứ hai là","A. Hiếp pháp mới của Nhật Bản (1946).","B. Hiệp ước an ninh Mĩ - Nhật (1951).","C. Hiệp ước Hoà bình Xan Phranxixcô (1951).","D. Học thuyết Phucưđa (1977).","B. Hiệp ước an ninh Mĩ - Nhật (1951).");
        AddCauHoiTracNghiem(22,"Câu 4: Mĩ  là nước khởi đầu cuộc cách mạng nào sau đây trong giai đoạn 1945-1973?","A. Cách mạng khoa học - kĩ thuật hiện đại.","B. Cách mạng công nghiệp.","C. Cách mạng trắng.","D. Cách mạng chất xám ","A. Cách mạng khoa học - kĩ thuật hiện đại.");

        AddCauHoiTracNghiem(23,"Câu 1:TBiến đổi lớn nhất ở khu vực Đông Nam Á sau Chiến tranh thế giới thứ hai là. ","A. nhiều nước đạt tốc độ tăng trưởng kinh tế cao, trở thành nước công nghiệp ","B. Việt Nam góp phần làm sụp đổ chủ nghĩa thực dân kiểu cũ và kiểu mới.  ","C. từ thân phận là nước thuộc địa, các nước đã trở thành quốc gia độc lập, tự chủ.  ","D. thành lập và mở rộng hiệp hội khu vực - ASEAN","C. từ thân phận là nước thuộc địa, các nước đã trở thành quốc gia độc lập, tự chủ. ");
        AddCauHoiTracNghiem(23,"Câu 2:Điểm chung trong nguyên nhân làm cho kinh tế phát triển giữa Tây Âu  với Mĩ và Nhật Bản là?","A. Tài năng của giới lãnh đạo và kinh doanh.","B. Người lao động có tay nghề cao.","C. Gây chiến tranh xâm lược Việt Nam và Triều Tiên ","D. Áp dụng những thành tựu khoa học - kĩ thuật vào sản xuất.","D. Áp dụng những thành tựu khoa học - kĩ thuật vào sản xuất");
        AddCauHoiTracNghiem(23,"Câu 3: Văn kiện đặt nền tảng cho quan hệ Mĩ - Nhật sau Chiến tranh thế giới thứ hai là","A. Hiếp pháp mới của Nhật Bản (1946).","B. Hiệp ước an ninh Mĩ - Nhật (1951).","C. Hiệp ước Hoà bình Xan Phranxixcô (1951).","D. Học thuyết Phucưđa (1977).","B. Hiệp ước an ninh Mĩ - Nhật (1951).");
        AddCauHoiTracNghiem(23,"Câu 4: Mĩ  là nước khởi đầu cuộc cách mạng nào sau đây trong giai đoạn 1945-1973?","A. Cách mạng khoa học - kĩ thuật hiện đại.","B. Cách mạng công nghiệp.","C. Cách mạng trắng.","D. Cách mạng chất xám ","A. Cách mạng khoa học - kĩ thuật hiện đại.");

        AddCauHoiTracNghiem(24,"Câu 1:TBiến đổi lớn nhất ở khu vực Đông Nam Á sau Chiến tranh thế giới thứ hai là. ","A. nhiều nước đạt tốc độ tăng trưởng kinh tế cao, trở thành nước công nghiệp ","B. Việt Nam góp phần làm sụp đổ chủ nghĩa thực dân kiểu cũ và kiểu mới.  ","C. từ thân phận là nước thuộc địa, các nước đã trở thành quốc gia độc lập, tự chủ.  ","D. thành lập và mở rộng hiệp hội khu vực - ASEAN","C. từ thân phận là nước thuộc địa, các nước đã trở thành quốc gia độc lập, tự chủ. ");
        AddCauHoiTracNghiem(24,"Câu 2:Điểm chung trong nguyên nhân làm cho kinh tế phát triển giữa Tây Âu  với Mĩ và Nhật Bản là?","A. Tài năng của giới lãnh đạo và kinh doanh.","B. Người lao động có tay nghề cao.","C. Gây chiến tranh xâm lược Việt Nam và Triều Tiên ","D. Áp dụng những thành tựu khoa học - kĩ thuật vào sản xuất.","D. Áp dụng những thành tựu khoa học - kĩ thuật vào sản xuất");
        AddCauHoiTracNghiem(24,"Câu 3: Văn kiện đặt nền tảng cho quan hệ Mĩ - Nhật sau Chiến tranh thế giới thứ hai là","A. Hiếp pháp mới của Nhật Bản (1946).","B. Hiệp ước an ninh Mĩ - Nhật (1951).","C. Hiệp ước Hoà bình Xan Phranxixcô (1951).","D. Học thuyết Phucưđa (1977).","B. Hiệp ước an ninh Mĩ - Nhật (1951).");
        AddCauHoiTracNghiem(24,"Câu 4: Mĩ  là nước khởi đầu cuộc cách mạng nào sau đây trong giai đoạn 1945-1973?","A. Cách mạng khoa học - kĩ thuật hiện đại.","B. Cách mạng công nghiệp.","C. Cách mạng trắng.","D. Cách mạng chất xám ","A. Cách mạng khoa học - kĩ thuật hiện đại.");

        AddCauHoiTracNghiem(25,"Câu 1:Đá mẹ là nguồn cung cấp vật chất vô cơ cho đất, có vai trò quyết định tớí ","A. thành phần tính chất của đất. ","B. khả năng hút nước của đất","C. đặc tính lí, hóa và độ tơi xốp của đất.","D. lượng chất dinh dưỡng trong đất.","A. thành phần tính chất của đất. ");
        AddCauHoiTracNghiem(25,"Câu 2:Đặc điểm nào sau đây đúng với gió Mậu dịch?","A. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là khô.","B. Chủ yếu thổi vào mùa đông, lạnh khô, hướng gió thay đổi theo mùa.","C. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là ẩm ướt.","D. Chủ yếu thổi vào mùa hạ, nóng ẩm, hướng gió thay đổi theo mùa.","A. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là khô.");
        AddCauHoiTracNghiem(25,"Câu 3: Động lực làm tăng dân số thế giới là","A. tỉ suất sinh thô.","B. gia tăng cơ học.","C. tỉ suất tử thô.","D. gia tăng dân số tự nhiên.","D. gia tăng dân số tự nhiên.");
        AddCauHoiTracNghiem(25,"Câu 4: Phạm vi hoạt động của gió Tây ôn đới là","A. thổi từ áp cao cận chí tuyến về áp thấp ôn đới.","B. thổi từ áp cao cực về áp thấp xích đạo.","C. thổi từ áp cao cận chí tuyến về áp thấp xích đạo.","D. thổi từ áp cao cực về áp thấp ôn đới ","A. thổi từ áp cao cận chí tuyến về áp thấp ôn đới.");

        AddCauHoiTracNghiem(26,"Câu 1:Đá mẹ là nguồn cung cấp vật chất vô cơ cho đất, có vai trò quyết định tớí ","A. thành phần tính chất của đất. ","B. khả năng hút nước của đất","C. đặc tính lí, hóa và độ tơi xốp của đất.","D. lượng chất dinh dưỡng trong đất.","A. thành phần tính chất của đất. ");
        AddCauHoiTracNghiem(26,"Câu 2:Đặc điểm nào sau đây đúng với gió Mậu dịch?","A. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là khô.","B. Chủ yếu thổi vào mùa đông, lạnh khô, hướng gió thay đổi theo mùa.","C. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là ẩm ướt.","D. Chủ yếu thổi vào mùa hạ, nóng ẩm, hướng gió thay đổi theo mùa.","A. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là khô.");
        AddCauHoiTracNghiem(26,"Câu 3: Động lực làm tăng dân số thế giới là","A. tỉ suất sinh thô."," B. gia tăng cơ học.","C. tỉ suất tử thô.","D. gia tăng dân số tự nhiên.","D. gia tăng dân số tự nhiên.");
        AddCauHoiTracNghiem(26,"Câu 4: Phạm vi hoạt động của gió Tây ôn đới là","A. thổi từ áp cao cận chí tuyến về áp thấp ôn đới.","B. thổi từ áp cao cực về áp thấp xích đạo.","C. thổi từ áp cao cận chí tuyến về áp thấp xích đạo.","D. thổi từ áp cao cực về áp thấp ôn đới ","A. thổi từ áp cao cận chí tuyến về áp thấp ôn đới.");

        AddCauHoiTracNghiem(27,"Câu 1:Đá mẹ là nguồn cung cấp vật chất vô cơ cho đất, có vai trò quyết định tớí ","A. thành phần tính chất của đất. ","B. khả năng hút nước của đất","C. đặc tính lí, hóa và độ tơi xốp của đất.","D. lượng chất dinh dưỡng trong đất.","A. thành phần tính chất của đất. ");
        AddCauHoiTracNghiem(27,"Câu 2:Đặc điểm nào sau đây đúng với gió Mậu dịch?","A. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là khô.","B. Chủ yếu thổi vào mùa đông, lạnh khô, hướng gió thay đổi theo mùa.","C. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là ẩm ướt.","D. Chủ yếu thổi vào mùa hạ, nóng ẩm, hướng gió thay đổi theo mùa.","A. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là khô.");
        AddCauHoiTracNghiem(27,"Câu 3: Động lực làm tăng dân số thế giới là","A. tỉ suất sinh thô.","B. gia tăng cơ học.","C. tỉ suất tử thô.","D. gia tăng dân số tự nhiên.","D. gia tăng dân số tự nhiên.");
        AddCauHoiTracNghiem(27,"Câu 4: Phạm vi hoạt động của gió Tây ôn đới là","A. thổi từ áp cao cận chí tuyến về áp thấp ôn đới.","B. thổi từ áp cao cực về áp thấp xích đạo.","C. thổi từ áp cao cận chí tuyến về áp thấp xích đạo.","D. thổi từ áp cao cực về áp thấp ôn đới ","A. thổi từ áp cao cận chí tuyến về áp thấp ôn đới.");

        AddCauHoiTracNghiem(28,"Câu 1:Đá mẹ là nguồn cung cấp vật chất vô cơ cho đất, có vai trò quyết định tớí ","A. thành phần tính chất của đất. ","B. khả năng hút nước của đất","C. đặc tính lí, hóa và độ tơi xốp của đất.","D. lượng chất dinh dưỡng trong đất.","A. thành phần tính chất của đất. ");
        AddCauHoiTracNghiem(28,"Câu 2:Đặc điểm nào sau đây đúng với gió Mậu dịch?","A. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là khô.","B. Chủ yếu thổi vào mùa đông, lạnh khô, hướng gió thay đổi theo mùa.","C. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là ẩm ướt.","D. Chủ yếu thổi vào mùa hạ, nóng ẩm, hướng gió thay đổi theo mùa.","A. Thổi quanh năm, hướng gió gần như cố định, tính chất chung là khô.");
        AddCauHoiTracNghiem(28,"Câu 3: Động lực làm tăng dân số thế giới là","A. tỉ suất sinh thô."," B. gia tăng cơ học.","C. tỉ suất tử thô.","D. gia tăng dân số tự nhiên.","D. gia tăng dân số tự nhiên.");
        AddCauHoiTracNghiem(28,"Câu 4: Phạm vi hoạt động của gió Tây ôn đới là","A. thổi từ áp cao cận chí tuyến về áp thấp ôn đới.","B. thổi từ áp cao cực về áp thấp xích đạo.","C. thổi từ áp cao cận chí tuyến về áp thấp xích đạo.","D. thổi từ áp cao cực về áp thấp ôn đới ","A. thổi từ áp cao cận chí tuyến về áp thấp ôn đới.");

        AddCauHoiTracNghiem(29,"Câu 1:Pháp luật XHCN mang bản chất của giai cấp ","A. Nhân dân lao động. ","B. Giai cấp cầm quyền","C. Giai cấp tiến bộ.","D. Giai cấp công nhân.","D. Giai cấp công nhân.");
        AddCauHoiTracNghiem(29,"Câu 2:Pháp luật do nhà nước ta ban hành thể hiện ý chí, nhu cầu lợi ích của ","A. Giai cấp công nhân.","B. Đa số nhân dân lao động","C. Giai cấp vô sản","D. Đảng công sản Việt Nam.","B. Đa số nhân dân lao động.");
        AddCauHoiTracNghiem(29,"Câu 3:Pháp luật là phương tiện để nhà nước quản lý","A. Quản lý XH","B. Quản lý công dân.","C. Bảo vệ giai cấp.","D. Bảo vệ các công dân.","A. Quản lý XH.");
        AddCauHoiTracNghiem(29,"Câu 4:Pháp luật là phương tiện để công dân thực hiện và bảo vệ","A. Lợi ích kinh tế của mình","B. Các quyền của mình","C. Quyền và nghĩa vụ của mình","D. Quyền và lợi ích hợp pháp  ","D. Quyền và lợi ích hợp pháp ");

        AddCauHoiTracNghiem(30,"Câu 1:Pháp luật XHCN mang bản chất của giai cấp ","A. Nhân dân lao động. ","B. Giai cấp cầm quyền","C. Giai cấp tiến bộ.","D. Giai cấp công nhân.","D. Giai cấp công nhân.");
        AddCauHoiTracNghiem(30,"Câu 2:Pháp luật do nhà nước ta ban hành thể hiện ý chí, nhu cầu lợi ích của ","A. Giai cấp công nhân.","B. Đa số nhân dân lao động","C. Giai cấp vô sản","D. Đảng công sản Việt Nam.","B. Đa số nhân dân lao động.");
        AddCauHoiTracNghiem(30,"Câu 3:Pháp luật là phương tiện để nhà nước quản lý","A. Quản lý XH","B. Quản lý công dân.","C. Bảo vệ giai cấp.","D. Bảo vệ các công dân.","A. Quản lý XH.");
        AddCauHoiTracNghiem(30,"Câu 4:Pháp luật là phương tiện để công dân thực hiện và bảo vệ","A. Lợi ích kinh tế của mình","B. Các quyền của mình","C. Quyền và nghĩa vụ của mình","D. Quyền và lợi ích hợp pháp  ","D. Quyền và lợi ích hợp pháp ");

        AddCauHoiTracNghiem(31,"Câu 1:Pháp luật XHCN mang bản chất của giai cấp ","A. Nhân dân lao động. ","B. Giai cấp cầm quyền","C. Giai cấp tiến bộ.","D. Giai cấp công nhân.","D. Giai cấp công nhân.");
        AddCauHoiTracNghiem(31,"Câu 2:Pháp luật do nhà nước ta ban hành thể hiện ý chí, nhu cầu lợi ích của ","A. Giai cấp công nhân.","B. Đa số nhân dân lao động","C. Giai cấp vô sản","D. Đảng công sản Việt Nam.","B. Đa số nhân dân lao động.");
        AddCauHoiTracNghiem(31,"Câu 3:Pháp luật là phương tiện để nhà nước quản lý","A. Quản lý XH","B. Quản lý công dân.","C. Bảo vệ giai cấp.","D. Bảo vệ các công dân.","A. Quản lý XH.");
        AddCauHoiTracNghiem(31,"Câu 4:Pháp luật là phương tiện để công dân thực hiện và bảo vệ","A. Lợi ích kinh tế của mình","B. Các quyền của mình","C. Quyền và nghĩa vụ của mình","D. Quyền và lợi ích hợp pháp  ","D. Quyền và lợi ích hợp pháp ");

        AddCauHoiTracNghiem(32,"Câu 1:Pháp luật XHCN mang bản chất của giai cấp ","A. Nhân dân lao động. ","B. Giai cấp cầm quyền","C. Giai cấp tiến bộ.","D. Giai cấp công nhân.","D. Giai cấp công nhân.");
        AddCauHoiTracNghiem(32,"Câu 2:Pháp luật do nhà nước ta ban hành thể hiện ý chí, nhu cầu lợi ích của ","A. Giai cấp công nhân.","B. Đa số nhân dân lao động","C. Giai cấp vô sản","D. Đảng công sản Việt Nam.","B. Đa số nhân dân lao động.");
        AddCauHoiTracNghiem(32,"Câu 3:Pháp luật là phương tiện để nhà nước quản lý","A. Quản lý XH","B. Quản lý công dân.","C. Bảo vệ giai cấp.","D. Bảo vệ các công dân.","A. Quản lý XH.");
        AddCauHoiTracNghiem(32,"Câu 4:Pháp luật là phương tiện để công dân thực hiện và bảo vệ","A. Lợi ích kinh tế của mình","B. Các quyền của mình","C. Quyền và nghĩa vụ của mình","D. Quyền và lợi ích hợp pháp  ","D. Quyền và lợi ích hợp pháp ");


        getAllCauHoiTracNghiemList();
    }
    public void deleteAllMonHoc() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MONHOC, null, null);
        db.close();
    }
    public void deleteAllDeThi() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DETHI, null, null);
        db.close();
    }
    public void deleteAllCauHoi() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CAUHOI, null, null);
        db.close();
    }
    public void deleteAllLichSuLamBai(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LICHSULAMBAI, null, null);
        db.close();
    }
    public List<CauHoiTracNghiem> getAllCauHoiTracNghiemList() {
        List<CauHoiTracNghiem> listCauHoi = new ArrayList<>();

        try (SQLiteDatabase database = getReadableDatabase()) {
            String selectQuery = "SELECT * FROM " + TABLE_CAUHOI;
            Cursor cursor = database.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_ID));
                    int idDeThi = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_DETHI_ID));
                    String noiDung = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_NOIDUNG));
                    String phuongAnA = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANA));
                    String phuongAnB = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANB));
                    String phuongAnC = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANC));
                    String phuongAnD = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGAND));
                    String dapAnDung = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_DAPANDUNG));

                    CauHoiTracNghiem cauHoi = new CauHoiTracNghiem(id, idDeThi, noiDung, phuongAnA, phuongAnB, phuongAnC, phuongAnD, dapAnDung);
                    listCauHoi.add(cauHoi);

                    System.out.println("ID: " + cauHoi.getId());
                    System.out.println("ID Đề thi: " + cauHoi.getIdDeThi());
                    System.out.println("Nội dung: " + cauHoi.getNoiDungCauHoi());
                    System.out.println("Phương án A: " + cauHoi.getPhuongAnA());
                    System.out.println("Phương án B: " + cauHoi.getPhuongAnB());
                    System.out.println("Phương án C: " + cauHoi.getPhuongAnC());
                    System.out.println("Phương án D: " + cauHoi.getPhuongAnD());
                    System.out.println("Đáp án đúng: " + cauHoi.getDapAnDung());
                    System.out.println("--------------------------------");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listCauHoi;
    }
    public void Reset(){
        AddMonHoc();
        AddDeThi();
        CauHoi();
    }
}