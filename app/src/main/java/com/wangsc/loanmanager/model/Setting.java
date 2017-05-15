package com.wangsc.loanmanager.model;

import com.wangsc.loanmanager.helper.DateTime;

/**
 * Created by 阿弥陀佛 on 2015/6/30.
 */
public class Setting {

    private String key;
    private String value;

    public Setting(String key, String value){
        this.key=key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getBoolean(){
        return Boolean.parseBoolean(value);
    }
    public int getInt(){
        return Integer.parseInt(value);
    }
    public long getLong(){
        return Long.parseLong(value);
    }
    public DateTime getDateTime(){
        return new DateTime(getLong());
    }
    public enum KEYS{
        start_page_index,
        nianfo_intervalInMillis, nianfo_endInMillis, nianfo_palyId, nianfo_isReading,
        tally_dayTargetInMillis, tally_manualOverTimeInMillis,tally_sectionStartInMillis, tally_endInMillis, tally_intervalInMillis,
        web_index,
        music_current_list_id,music_isplaying,music_waiting,
        screen_light,
        setting_isShowDialog;


    }
}
