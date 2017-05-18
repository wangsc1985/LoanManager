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

    //region Borrower
    public void addBorrower(Borrower model) {
        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //使用insert方法向表中插入数据
            ContentValues values = new ContentValues();
            values.put("id", model.getId().toString());
            values.put("identity", model.getIdentity());
            values.put("name", model.getName());
            values.put("phone", model.getPhone());
            values.put("addressId", model.getAddressId().toString());

            //调用方法插入数据
            db.insert("borrower", "id", values);
            //关闭SQLiteDatabase对象
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
    }

    //
    public Borrower getBorrower(UUID id) {

        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //查询获得游标
            Cursor cursor = db.query("borrower", null, "id=?", new String[]{id.toString()}, null, null, null);
            //判断游标是否为空
            if (cursor.moveToNext()) {
                Borrower model = new Borrower(id);
                model.setIdentity(cursor.getString(1));
                model.setName(cursor.getString(2));
                model.setPhone(cursor.getString(3));
                model.setAddressId(UUID.fromString(cursor.getString(4)));
                return model;
            }
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
        return null;
    }

    public List<Borrower> getBorrowers(String likeWhat) {
        List<Borrower> result = new ArrayList<Borrower>();
        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //查询获得游标
            Cursor cursor = db.query("borrower", null, "name like ?", new String[]{"%"+likeWhat+"%"}, null, null, null);
            //判断游标是否为空
            while (cursor.moveToNext()) {
                Borrower model = new Borrower(UUID.fromString(cursor.getString(0)));
                model.setIdentity(cursor.getString(1));
                model.setName(cursor.getString(2));
                model.setPhone(cursor.getString(3));
                model.setAddressId(UUID.fromString(cursor.getString(4)));
                result.add(model);
            }
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
        return result;
    }
    //endregion

    //region Address
    public void addAddress(Address model) {
        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //使用insert方法向表中插入数据
            ContentValues values = new ContentValues();
            values.put("id", model.getId().toString());
            values.put("provinceId", model.getProvinceId().toString());
            values.put("cityId", model.getCityId().toString());
            values.put("countyId", model.getCountyId().toString());
            values.put("townId", model.getTownId().toString());
            values.put("villageId", model.getVillageId().toString());

            //调用方法插入数据
            db.insert("address", "id", values);
            //关闭SQLiteDatabase对象
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
    }

    public Address getAddress(UUID id) {
        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //查询获得游标
            Cursor cursor = db.query("address", null, "id==?", new String[]{id.toString()}, null, null, null);
            //判断游标是否为空
            while (cursor.moveToNext()) {
                Address model = new Address(UUID.fromString(cursor.getString(0)));
                model.setProvinceId(UUID.fromString(cursor.getString(1)));
                model.setCityId(UUID.fromString(cursor.getString(2)));
                model.setCountyId(UUID.fromString(cursor.getString(3)));
                model.setTownId(UUID.fromString(cursor.getString(4)));
                model.setVillageId(UUID.fromString(cursor.getString(5)));
                return model;
            }
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
        return null;
    }
    //endregion

    //region AddressItem
    public void addAddressItem(AddressItem model) {
        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //使用insert方法向表中插入数据
            ContentValues values = new ContentValues();
            values.put("id", model.getId().toString());
            values.put("value", model.getValue());
            values.put("level", model.getLevel());
            values.put("parentId", model.getParentId().toString());

            //调用方法插入数据
            db.insert("addressItem", "id", values);
            //关闭SQLiteDatabase对象
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
    }

    public AddressItem getAddressItem(UUID id) {
        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //查询获得游标
            Cursor cursor = db.query("addressItem", null, "id==?", new String[]{id.toString()}, null, null, null);
            //判断游标是否为空
            while (cursor.moveToNext()) {
                AddressItem model = new AddressItem(UUID.fromString(cursor.getString(0)));
                model.setValue(cursor.getString(1));
                model.setLevel(cursor.getInt(2));
                model.setParentId(UUID.fromString(cursor.getString(3)));
                return model;
            }
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
        return null;
    }
    public List<AddressItem> getAddressItems(UUID parentId) {
        List<AddressItem> result = new ArrayList<AddressItem>();
        try {
            //获取数据库对象
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //查询获得游标
            Cursor cursor = db.query("addressItem", null, "parentId==?", new String[]{parentId.toString()}, null, null, null);
            //判断游标是否为空
            while (cursor.moveToNext()) {
                AddressItem model = new AddressItem(UUID.fromString(cursor.getString(0)));
                model.setValue(cursor.getString(1));
                model.setLevel(cursor.getInt(2));
                model.setParentId(UUID.fromString(cursor.getString(3)));
                result.add(model);
            }
            db.close();
        } catch (Exception e) {
            _Helper.printException(context, e);
        }
        return result;
    }
    //endregion



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

    public Setting getSetting(Setting.KEYS key, Object defaultValue) {
        Setting setting = getSetting(key);
        if (setting == null) {
            this.addSetting(key, defaultValue);
            setting = new Setting(key.toString(), defaultValue.toString());
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
            Setting setting = new Setting(cursor.getString(0), cursor.getString(1));
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
