package com.wangsc.loanmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wangsc.loanmanager.helper.DateTime;
import com.wangsc.loanmanager.helper._Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 阿弥陀佛 on 2015/11/18.
 */
public class DataContext {

    private DatabaseHelper dbHelper;
    private Context context;

    public DataContext(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    //region TallyRecord

//    /**
//     * 增加一条记录
//     *
//     * @param tallyRecord 记录对象
//     */
//    public void addRecord(TallyRecord tallyRecord) {
//        try {
//            //获取数据库对象
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//            //使用insert方法向表中插入数据
//            ContentValues values = new ContentValues();
//            values.put("id", tallyRecord.getId().toString());
//            values.put("start", tallyRecord.getStart().getTimeInMillis());
//            values.put("interval", tallyRecord.getInterval());
//            values.put("item", tallyRecord.getItem());
//            values.put("summary", tallyRecord.getSummary());
//
//            //调用方法插入数据
//            db.insert("record", "id", values);
//            //关闭SQLiteDatabase对象
//            db.close();
//        } catch (Exception e) {
//            _Helper.printException(context, e);
//        }
//    }
//
//    /**
//     * 得到一条record
//     *
//     * @param id 记录ID
//     * @return
//     */
//    public TallyRecord getRecord(UUID id) {
//
//        try {
//            //获取数据库对象
//            SQLiteDatabase db = dbHelper.getReadableDatabase();
//            //查询获得游标
//            Cursor cursor = db.query("record", null, "id=?", new String[]{id.toString()}, null, null, null);
//            //判断游标是否为空
//            if (cursor.moveToNext()) {
//                TallyRecord model = new TallyRecord(id);
//                model.setStart(new DateTime(cursor.getLong(1)));
//                model.setInterval(cursor.getInt(2));
//                model.setItem(cursor.getString(3));
//                model.setSummary(cursor.getString(4));
//                return model;
//            }
//            db.close();
//        } catch (Exception e) {
//            _Helper.printException(context, e);
//        }
//        return null;
//    }
//
//    /**
//     * 获取某一天的record。
//     *
//     * @param date
//     * @return
//     */
//    public List<TallyRecord> getRecords(DateTime date) {
//
//        List<TallyRecord> result = new ArrayList<TallyRecord>();
//        try {
//            //获取数据库对象
//            SQLiteDatabase db = dbHelper.getReadableDatabase();
//            DateTime startDate = new DateTime(date.getYear(), date.getMonth(), date.getDay());
//            DateTime endDate = (DateTime) (startDate.clone());
//            endDate = endDate.addDays(1);
//            //查询获得游标
//            Cursor cursor = db.query("record", null, "start>=? and start<?", new String[]{startDate.getTimeInMillis() + "",endDate.getTimeInMillis()+""}, null, null, null);
//            //判断游标是否为空
//            while (cursor.moveToNext()) {
//                TallyRecord model = new TallyRecord(UUID.fromString(cursor.getString(0)));
//                model.setStart(new DateTime(cursor.getLong(1)));
//                model.setInterval(cursor.getInt(2));
//                model.setItem(cursor.getString(3));
//                model.setSummary(cursor.getString(4));
//                result.add(model);
//            }
//            db.close();
//        } catch (Exception e) {
//            _Helper.printException(context, e);
//        }
//        return result;
//    }
//
//    /**
//     * 获取某一时刻的record。
//     *
//     * @param timeInmillis
//     * @return
//     */
//    public TallyRecord getRecords(long timeInmillis) {
//
//        TallyRecord result = null;
//        try {
//            //获取数据库对象
//            SQLiteDatabase db = dbHelper.getReadableDatabase();
//            //查询获得游标
//            Cursor cursor = db.query("record", null, "start=?", new String[]{timeInmillis + ""}, null, null, null);
//            //判断游标是否为空
//            while (cursor.moveToNext()) {
//                result = new TallyRecord(UUID.fromString(cursor.getString(0)));
//                result.setStart(new DateTime(cursor.getLong(1)));
//                result.setInterval(cursor.getInt(2));
//                result.setItem(cursor.getString(3));
//                result.setSummary(cursor.getString(4));
//            }
//            db.close();
//        } catch (Exception e) {
//            _Helper.printException(context, e);
//        }
//        return result;
//    }
//
//    /**
//     * 得到所有record
//     *
//     * @return
//     */
//    public List<TallyRecord> getRecords(int year) {
//
//        List<TallyRecord> result = new ArrayList<TallyRecord>();
//        try {
//            //获取数据库对象
//            SQLiteDatabase db = dbHelper.getReadableDatabase();
//            DateTime startDate = new DateTime(year,0,1);
//            DateTime endDate = new DateTime(year+1,0,1);
//            //查询获得游标
//            Cursor cursor = db.query("record", null, "start>=? AND start<?", new String[]{startDate.getTimeInMillis()+"",endDate.getTimeInMillis()+""}, null, null, "start desc");
//            //判断游标是否为空
//            while (cursor.moveToNext()) {
//                TallyRecord model = new TallyRecord(UUID.fromString(cursor.getString(0)));
//                model.setStart(new DateTime(cursor.getLong(1)));
//                model.setInterval(cursor.getInt(2));
//                model.setItem(cursor.getString(3));
//                model.setSummary(cursor.getString(4));
//                result.add(model);
//            }
//            db.close();
//        } catch (Exception e) {
//            _Helper.printException(context, e);
//        }
//        return result;
//    }
//
//    public void updateRecord(TallyRecord tallyRecord) {
//
//        try {
//            //获取数据库对象
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//            //使用update方法更新表中的数据
//            ContentValues values = new ContentValues();
//            values.put("start", tallyRecord.getStart().getTimeInMillis());
//            values.put("interval", tallyRecord.getInterval());
//            values.put("item", tallyRecord.getItem());
//            values.put("summary", tallyRecord.getSummary());
//
//            db.update("record", values, "id=?", new String[]{tallyRecord.getId().toString()});
//            db.close();
//        } catch (Exception e) {
//            _Helper.printException(context, e);
//        }
//    }
//
//    /**
//     * 删除指定的record
//     *
//     * @param id
//     */
//    public void deleteRecord(UUID id) {
//        try {
//            //获取数据库对象
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//            db.delete("record", "id=?", new String[]{id.toString()});
//            //关闭SQLiteDatabase对象
//            db.close();
//        } catch (Exception e) {
//            _Helper.printException(context, e);
//        }
//    }
    //endregion

    //region RunLog
    public List<RunLog> getRunLogs() {
        List<RunLog> result = new ArrayList<>();
        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //查询获得游标
            Cursor cursor = db.query("runLog", null, null, null, null, null, "runTime DESC");
            //判断游标是否为空
            while (cursor.moveToNext()) {
                RunLog model = new RunLog(UUID.fromString(cursor.getString(0)));
                model.setRunTime(new DateTime(cursor.getLong(1)));
                model.setTag(cursor.getString(2));
                model.setItem(cursor.getString(3));
                model.setMessage(cursor.getString(4));
                result.add(model);
            }
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
        return result;
    }

    public void addRunLog(RunLog runLog) {
        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //使用insert方法向表中插入数据
            ContentValues values = new ContentValues();
            values.put("id", runLog.getId().toString());
            values.put("runTime", runLog.getRunTime().getTimeInMillis());
            values.put("tag", runLog.getTag());
            values.put("item", runLog.getItem());
            values.put("message", runLog.getMessage());
            //调用方法插入数据
            db.insert("runLog", "id", values);
            //关闭SQLiteDatabase对象
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
    }

    public void updateRunLog(RunLog runLog) {

        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //使用update方法更新表中的数据
            ContentValues values = new ContentValues();
            values.put("runTime", runLog.getRunTime().getTimeInMillis());
            values.put("tag", runLog.getTag());
            values.put("item", runLog.getItem());
            values.put("message", runLog.getMessage());

            db.update("runLog", values, "id=?", new String[]{runLog.getId().toString()});
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
    }

    public void deleteRunLog() {
        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete("runLog", null, null);
            //关闭SQLiteDatabase对象
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
    }
    //endregion

    //region Setting
    public Setting getSetting(Setting.KEYS key) {

        //获取数据库对象
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //查询获得游标
        Cursor cursor = db.query("setting", null, "key=?", new String[]{key.toString()}, null, null, null);
        //判断游标是否为空
        while (cursor.moveToNext()) {
            Setting setting = new Setting(key.toString(), cursor.getString(1));
            return setting;
        }
        return null;
    }

    public Setting getSetting(Setting.KEYS key, Object defaultValue){
        Setting setting = getSetting(key);
        if (setting == null) {
            this.addSetting(key, defaultValue);
            setting = new Setting(key.toString(),defaultValue.toString());
            return setting;
        }
        return setting;
    }

    /**
     * 修改制定key配置，如果不存在则创建。
     *
     * @param key
     * @param value
     */
    public void editSetting(Setting.KEYS key, Object value) {
        //获取数据库对象
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //使用update方法更新表中的数据
        ContentValues values = new ContentValues();
        values.put("value", value.toString());
        if (db.update("setting", values, "key=?", new String[]{key.toString()}) == 0) {
            this.addSetting(key, value.toString());
        }
        db.close();
    }

    public void deleteSetting(Setting.KEYS key) {
        //获取数据库对象
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("setting", "key=?", new String[]{key.toString()});
//        String sql = "DELETE FROM setting WHERE userId="+userId.toString()+" AND key="+key;
//        addLog(new Log(sql,userId),db);
        //关闭SQLiteDatabase对象
        db.close();
    }

    public void addSetting(Setting.KEYS key, Object value) {
        //获取数据库对象
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //使用insert方法向表中插入数据
        ContentValues values = new ContentValues();
        values.put("key", key.toString());
        values.put("value", value.toString());
        //调用方法插入数据
        db.insert("setting", "key", values);
        //关闭SQLiteDatabase对象
        db.close();
    }

    public List<Setting> getSettings() {
        List<Setting> result = new ArrayList<>();
        //获取数据库对象
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //查询获得游标
        Cursor cursor = db.query("setting", null, null, null, null, null, null);
        //判断游标是否为空
        while (cursor.moveToNext()) {
            Setting setting = new Setting( cursor.getString(0), cursor.getString(1));
            result.add(setting);
        }
        return result;
    }

    public void deleteSetting() {
        //获取数据库对象
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("setting", null, null);
        //        String sql = "DELETE FROM setting WHERE userId="+userId.toString()+" AND key="+key;
//        addLog(new Log(sql,userId),db);
        //关闭SQLiteDatabase对象
        db.close();
    }
    //endregion
}
