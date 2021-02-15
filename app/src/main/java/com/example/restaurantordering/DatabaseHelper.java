package com.example.restaurantordering;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;


public class DatabaseHelper extends SQLiteOpenHelper {
    //Session
    private Session session;
    // Database Name
    private static final String DATABASE_NAME = "deviceDB.db";
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Table Names
    public static final String TABLE_SERVER = "SERVER";
    public static final String TABLE_APP = "APP";
    public static final String TABLE_MENU = "MENU";
    public static final String TABLE_SUBMENU = "SUBMENU";
    public static final String TABLE_ORDER = "ORDER";
    public static final String TABLE_USERS = "USERS";
    public static final String TABLE_DINETable = "DINETable";



    //Column Names - App
    public static final String COLUMN_AppName = "AppName";
    public static final String COLUMN_AppImg = "AppImg";

    //ColumnNames - Users
    public static final String COLUMN_EMPID = "EMPID";
    public static final String COLUMN_EMPNAME = "EMPNAME";
    public static final String COLUMN_EMPUSERID = "EMPUSERID";
    public static final String COLUMN_EMPROLE = "EMPROLE";
    public static final String COLUMN_EMPPASSWORD = "EMPPASSWORD";

    //ColumnNames - SERVER
    public static final String COLUMN_SVRIp = "SVRIp";
    public static final String COLUMN_SVRUser = "SVRUser";
    public static final String COLUMN_SVRPassword = "SVRPassword";
    public static final String COLUMN_SVRPort = "SVRPort";
    public static final String COLUMN_SVRDBName = "SVRDBName";
    public static final String COLUMN_SVRStatus = "SVRStatus";

    //ColumnNames - MENU
    public static final String COLUMN_MENUID = "MENUID";
    public static final String COLUMN_MENUCode = "MENUCode";
    public static final String COLUMN_MENUTITLE = "MENUTITLE";
    public static final String COLUMN_MENUDescription = "MENUDescription";
    public static final String COLUMN_MENUCreatedBy = "MENUCreatedBy";
    public static final String COLUMN_MENUCreatedOn = "MENUCreatedOn";
    public static final String COLUMN_MENUUpdatedBy = "MENUUpdateBy";
    public static final String COLUMN_MENUUpdatedOn = "MENUUpdatedOn";
    public static final String COLUMN_MENUStatus = "MENUStatus";

    //ColumnNames - SUBMENU
    public static final String COLUMN_SUBMENUID = "SUBMENUID";
    public static final String COLUMN_SUBMENUCode = "SUBMENUCode";
    public static final String COLUMN_SUBMENUTITLE = "SUBMENUTITLE";
    public static final String COLUMN_SUBMENUDescription = "SUBMENUDescription";
    public static final String COLUMN_SUBMENUCreatedBy = "SUBMENUCreatedBy";
    public static final String COLUMN_SUBMENUCreatedOn = "SUBMENUCreatedOn";
    public static final String COLUMN_SUBMENUUpdatedBy = "SUBMENUUpdateBy";
    public static final String COLUMN_SUBMENUUpdatedOn = "SUBMENUUpdatedOn";
    public static final String COLUMN_SUBMENUStatus = "SUBMENUStatus";

    // Create Table Users Statement
    private static final String createTableStatement_Users = "CREATE TABLE "
            + TABLE_USERS + " ("
            + COLUMN_EMPID + " TEXT PRIMARY KEY, "
            + COLUMN_EMPNAME + " TEXT, "
            + COLUMN_EMPROLE + " TEXT, "
            + COLUMN_EMPUSERID + " TEXT, "
            + COLUMN_EMPPASSWORD + " TEXT "
            + ")";

    // Create Table Server Statement
    private static final String createTableStatement_SERVER = "CREATE TABLE "
            + TABLE_SERVER + " ("
            + COLUMN_SVRIp + " TEXT PRIMARY KEY, "
            + COLUMN_SVRPort + " TEXT, "
            + COLUMN_SVRDBName + " TEXT, "
            + COLUMN_SVRUser + " TEXT, "
            + COLUMN_SVRPassword + " TEXT, "
            + COLUMN_SVRStatus + " TEXT "
            + ")";

    // Create Table Menu Statement
    private static final String createTableStatement_Menu = "CREATE TABLE "
            + TABLE_MENU + " ("
            + COLUMN_MENUID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MENUCode + " TEXT, "
            + COLUMN_MENUTITLE + " TEXT,"
            + COLUMN_MENUDescription + " TEXT,"
            + COLUMN_MENUCreatedOn + " TEXT,"
            + COLUMN_MENUCreatedBy + " TEXT,"
            + COLUMN_MENUUpdatedBy + " TEXT,"
            + COLUMN_MENUUpdatedOn + " TEXT,"
            + COLUMN_MENUStatus + " TEXT"
            + ")";

    // Create Table SUBMenu Statement
    private static final String createTableStatement_SubMenu = "CREATE TABLE "
            + TABLE_SUBMENU + " ("
            + COLUMN_SUBMENUID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MENUID + " INTEGER, "
            + COLUMN_SUBMENUCode + " TEXT, "
            + COLUMN_SUBMENUTITLE + " TEXT,"
            + COLUMN_SUBMENUDescription + " TEXT,"
            + COLUMN_SUBMENUCreatedOn + " TEXT,"
            + COLUMN_SUBMENUCreatedBy + " TEXT,"
            + COLUMN_SUBMENUUpdatedBy + " TEXT,"
            + COLUMN_SUBMENUUpdatedOn + " TEXT,"
            + COLUMN_SUBMENUStatus + " TEXT"
            + ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTableStatement_Users);
        sqLiteDatabase.execSQL(createTableStatement_Menu);
        sqLiteDatabase.execSQL(createTableStatement_SubMenu);
        sqLiteDatabase.execSQL(createTableStatement_SERVER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
// on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBMENU);
        // create new tables
        onCreate(sqLiteDatabase);
    }

    public String authentication(String UserId, String EmpPassword) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = " select count(*) from " + TABLE_USERS + " where "+COLUMN_EMPUSERID+" ='" + UserId + "' and EmpPassword='" + EmpPassword + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        String count = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));
        return count;
    }
}
