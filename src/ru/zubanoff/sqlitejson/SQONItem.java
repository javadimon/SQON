package ru.zubanoff.sqlitejson;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tuganov
 */
public class SQONItem {

    private String value;
    private final int rowid, time, modify;

    public SQONItem(int rowid, String value, int time, int modify) {
        this.rowid = rowid;
        this.value = value;
        this.time = time;
        this.modify = modify;
    }

    public int getModify() {
        return modify;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRowid() {
        return rowid;
    }

    public int getTime() {
        return time;
    }
    
    public String getJSON(){
        try {
            JSONObject jsonObject = new JSONObject(value);
            
            JSONObject jsonDBInfo = new JSONObject();
            jsonDBInfo.put("date", time);
            jsonDBInfo.put("modify", modify);
            jsonDBInfo.put("rowid", rowid);
            
            jsonObject.put("jsonDBInfo", jsonDBInfo);
            
            return jsonObject.toString();
            
        } catch (JSONException e) {
            Logger.getGlobal().log(Level.SEVERE, null, e);
            return "{}";
        }
    }

    @Override
    public String toString() {
        return "SQONItem{" + "value=" + value + ", rowid=" + rowid + ", time=" + time + ", modify=" + modify + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.rowid;
        hash = 71 * hash + this.time;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SQONItem other = (SQONItem) obj;
        if (this.rowid != other.rowid) {
            return false;
        }
        if (this.time != other.time) {
            return false;
        }
        return true;
    }

}
