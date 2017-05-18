package com.wangsc.loanmanager.model;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wangsc.loanmanager.helper.DateTime;
import com.wangsc.loanmanager.helper._Session;

import java.util.UUID;

/**
 * Created by 阿弥陀佛 on 2015/11/18.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "hsbank.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据库后，对数据库的操作
        try {
            createTable(db);
            insertData(db);
        } catch (SQLException e) {
            Log.e("wangsc", e.getMessage());
        }
    }

    private void createTable(SQLiteDatabase db) {
        db.execSQL("create table if not exists borrower("
                + "id TEXT PRIMARY KEY,"
                + "identity TEXT,"
                + "name TEXT,"
                + "phone TEXT,"
                + "addressId TEXT)");

        db.execSQL("create table if not exists loan("
                + "id TEXT PRIMARY KEY,"
                + "type INTEGER,"
                + "date LONG,"
                + "amount REAL,"
                + "life INTEGER,"
                + "account TEXT,"
                + "borrowerId TEXT)");

        db.execSQL("create table if not exists loanGroup("
                + "groupId TEXT PRIMARY KEY,"
                + "loanId TEXT)");

        db.execSQL("create table if not exists addressItem("
                + "id TEXT PRIMARY KEY,"
                + "value TEXT,"
                + "level INTEGER,"
                + "parentId TEXT)");

        db.execSQL("create table if not exists address("
                + "id TEXT PRIMARY KEY,"
                + "provinceId TEXT,"
                + "cityId TEXT,"
                + "countyId TEXT,"
                + "townId TEXT,"
                + "villageId TEXT)");

        db.execSQL("create table if not exists setting("
                + "key TEXT PRIMARY KEY,"
                + "value TEXT)");

        db.execSQL("create table if not exists runLog("
                + "id TEXT PRIMARY KEY,"
                + "runTime LONG,"
                + "tag TEXT,"
                + "item TEXT,"
                + "message TEXT)");
    }

    private void insertData(SQLiteDatabase db) {
        String nxId = UUID.randomUUID().toString();
        String gyId = UUID.randomUUID().toString();
        String szsId = UUID.randomUUID().toString();
        String wzId = UUID.randomUUID().toString();
        String ycId = UUID.randomUUID().toString();
        String zwId = UUID.randomUUID().toString();

        db.execSQL("insert into addressItem values" +
                "('" + nxId + "','宁夏','1','" + _Session.UUID_NULL.toString() + "')");
        db.execSQL("insert into addressItem values" +
                "('" + ycId + "','银川市','2','" + nxId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + szsId + "','石嘴山市','2','" + nxId + "')");
        db.execSQL("insert into addressItem values" +
                "('" +gyId + "','固原市','2','" + nxId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + wzId + "','吴忠市','2','" + nxId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + zwId + "','中卫市','2','" + nxId + "')");


        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','兴庆区','3','" + ycId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','金凤区','3','" + ycId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','西夏区','3','" + ycId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','贺兰县','3','" + ycId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','永宁县','3','" + ycId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','灵武市','3','" + ycId + "')");


        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','大武口区','3','" + szsId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','惠农区','3','" + szsId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','平罗县','3','" + szsId + "')");


        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','泾源县','3','" + gyId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','隆德县','3','" + gyId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','彭阳县','3','" + gyId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','西吉县','3','" + gyId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','原州区','3','" + gyId + "')");


        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','红寺堡区','3','" + wzId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','利通区','3','" + wzId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','青铜峡市','3','" + wzId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','同心县','3','" + wzId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','盐池县','3','" + wzId + "')");


        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','海原县','3','" + zwId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','沙坡头区','3','" + zwId + "')");
        db.execSQL("insert into addressItem values" +
                "('" + UUID.randomUUID().toString() + "','中宁县','3','" + zwId + "')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 更改数据库版本的操作
        try {
            switch (oldVersion) {
                case 1:
                    db.execSQL("ALTER TABLE address RENAME COLUMN city TO cityId");
                    db.execSQL("ALTER TABLE address RENAME COLUMN county TO countyId");
                    db.execSQL("ALTER TABLE address RENAME COLUMN town TO townId");
                    db.execSQL("ALTER TABLE address RENAME COLUMN village TO villageId");
                case 2:
            }
        } catch (SQLException e) {
            Log.e("wangsc", e.getMessage());
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // 每次成功打开数据库后首先被执行
    }


}
