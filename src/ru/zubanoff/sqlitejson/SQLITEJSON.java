

package ru.zubanoff.sqlitejson;

import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author developer
 */
public class SQLITEJSON {
    
    public SQLITEJSON(){
        //
    }
    
    public int add(JSONObject jsonObject){
        // TODO
        return 0;
    }
    
    public boolean update(int rowid, JSONObject jsonObject){
        // TODO
        return true;
    }
    
    public List<Map<Integer, JSONObject>> get(String key){
        // TODO
        return null;
    }
    
    public Map<Integer, JSONObject> get(int rowid){
        // TODO
        return null;
    }
    
    public boolean remove(int rowid){
        //
        return true;
    }
    
}
