
package ru.zubanoff.sqlitejson;

import java.util.List;
import java.util.Map;

/**
 *
 * @author developer
 */
public interface SQON {
    
    /**
     * Add record without indexing
     * @param key
     * @param value
     * @return rowid
     */
    public int insert(String key, String value);
    
    /**
     * 
     * @param key
     * @param value
     * @param timestamp
     * @return 
     */
    public int insert(String key, String value, int timestamp);
    
    /**
     * Add record without indexing
     * @param key
     * @param value
     * @param indexedValueKeys
     * @return rowid
     */
    public int insert(String key, String value, String ... indexedValueKeys);
    
    /**
     * Update row
     * @param rowid
     * @param value
     * @return 
     */
    public boolean update(int rowid, String value);
    
    /**
     * Update all keys to value
     * @param key
     * @param value
     * @return 
     */
    public boolean update(String key, String value);
    
    /**
     * Update all values for all keys only for rowid's
     * @param key
     * @param value
     * @param rowids
     * @return 
     */
    public boolean update(String key, String value, int ... rowids);
    
    /**
     * Delete row
     * @param rowid
     * @return 
     */
    public boolean delete(int rowid);
    
    /**
     * Delete rows
     * @param rowids
     * @return 
     */
    public boolean delete(int ... rowids);
    
    /**
     * Delete all keys
     * @param key
     * @return 
     */
    public boolean delete(String key);
    
    /**
     * Delete all keys only for rowids
     * @param key
     * @param rowids
     * @return 
     */
    public boolean delete(String key, int ... rowids);
    
    /**
     * Get all values by key
     * @param key
     * @return 
     */
    public List<SQONItem> getValues(String key);
    
    /**
     * Get value by id
     * @param id
     * @return 
     */
    public String getValue(int id);
    
    /**
     * Get indexed values
     * @param sqlRequest
     * @return 
     */
    public List<SQONItem> getValuesByIndex(String sqlRequest);
    
    /**
     * 
     * @param key
     * @param dateStart
     * @param dateEnd
     * @return 
     */
    public List<SQONItem> getValuesByKeyAndDateInterval(String key, int dateStart, int dateEnd);
    
    /**
     * 
     * @param key
     * @param date
     * @return 
     */
    public SQONItem getValuesByKeyAndDate(String key, int date);
    
    /**
     * Add or modify single value. If value by key not present new value will be added, otherwise value will be modified.
     * @param key
     * @param value 
     * @return  
     */
    public boolean addOrModify(String key, String value);
    
}
