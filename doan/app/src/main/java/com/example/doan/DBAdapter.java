package com.example.doan;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class DBAdapter {
    public static final String KEY_MAKH = "MAKH";
    public static final String KEY_HOTEN = "NAME";
    public static final String KEY_PHONE = "PHONENUMBER";
    public static final String KEY_ADDRESS = "ADDRESS";

    private DatabaseHelper dbHelper;
    private static SQLiteDatabase sqLiteDatabase;
    private static final String DATABASE_NAME = "DOAN";
    private static final String DATABASE_TABLE = "Contacts";
    private static final int DATABASE_VERSION = 3;
    private final Context context;

    public DBAdapter(Context context) {
        this.context = context;
    }
    public DBAdapter open() {
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    public long createContacts(String MAKH ,String name, String address, String phoneNumber) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_MAKH, MAKH);
        initialValues.put(KEY_HOTEN, name);
        initialValues.put(KEY_PHONE, phoneNumber);
        initialValues.put(KEY_ADDRESS, address);
        return sqLiteDatabase.insertWithOnConflict(DATABASE_TABLE, null, initialValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public boolean deleteUser(String maKH) {
        return sqLiteDatabase.delete(DATABASE_TABLE, KEY_MAKH + "=" + maKH,
                null) > 0;
    }
    public static Cursor getAllUsers(String maKH) {
        return sqLiteDatabase.query(DATABASE_TABLE, new String[]{KEY_MAKH,
                KEY_HOTEN, KEY_ADDRESS, KEY_PHONE}, null, null, null, null, null);
    }
    public Cursor getUserByMAKH(String maKH) {
        return sqLiteDatabase.query(DATABASE_TABLE, new String[]{KEY_MAKH,
                KEY_HOTEN, KEY_ADDRESS, KEY_PHONE}, KEY_MAKH + "=?", new String[]{maKH}, null, null, null);
    }
    public boolean deleteAllUsers() {
        return sqLiteDatabase.delete(DATABASE_TABLE, null, null) > 0;
    }

    public boolean updateUser(String maKH, String name, String phone, String address) {
        ContentValues values = new ContentValues();
        values.put(KEY_HOTEN, name);
        values.put(KEY_PHONE, phone);
        values.put(KEY_ADDRESS, address);
        return sqLiteDatabase.update(DATABASE_TABLE, values, KEY_MAKH + "=?", new String[]{maKH}) > 0;
    }
//    public int getCountByFilter(String filter) {
//        String query = "";
//
//        // Tạo điều kiện truy vấn dựa trên bộ lọc
//        switch (filter) {
//            case "Tất cả":
//                query = "SELECT COUNT(*) FROM " + DATABASE_TABLE;
//                break;
//            case "Mới":
//                query = "SELECT COUNT(*) FROM " + DATABASE_TABLE + " WHERE status = 'new'";
//                break;
//            case "Chưa tiếp cận":
//                query = "SELECT COUNT(*) FROM " + DATABASE_TABLE + " WHERE status = 'pending'";
//                break;
//            case "Tiếp cận":
//                query = "SELECT COUNT(*) FROM " + DATABASE_TABLE + " WHERE status = 'approach'";
//                break;
//            case "Nóng":
//                query = "SELECT COUNT(*) FROM " + DATABASE_TABLE + " WHERE status = 'hot'";
//                break;
//            case "Tiềm năng":
//                query = "SELECT COUNT(*) FROM " + DATABASE_TABLE + " WHERE status = 'potential'";
//                break;
//            default:
//                query = "SELECT COUNT(*) FROM " + DATABASE_TABLE;
//                break;
//        }
//
//        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
//        int count = 0;
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                count = cursor.getInt(0);
//            }
//            cursor.close();
//        }
//        return 0;
//    }
}
