/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.zubanoff.sqlitejson.test;

import java.util.List;
import java.util.Map;
import ru.zubanoff.sqlitejson.SQONHandler;
import ru.zubanoff.sqlitejson.SQONItem;

/**
 *
 * @author developer
 */
public class Test {
    
    SQONHandler handler;
    
    public Test(){
        //handler = new SQONHandler("jdbc:sqlite:/home/developer/bin/sqon/json.db");
        handler = new SQONHandler("jdbc:sqlite:d:\\Projects\\NetBeansProjects\\SQON\\db\\json.db");
    }
    
    public static void main(String args[]){
        Test t = new Test();
        
        //t.testConnect();
        t.testInsert();
        t.testGetValuesByKey();
        t.testGetValuesByKeyAndDate();
        //t.testUpdate();
        //t.testDelete();
    }
    
    private void testDelete(){
        boolean success = handler.delete(2);
        System.out.println("testDelete() " + success);
    }
    
    private void testUpdate(){
        boolean success = handler.update(3, "{lname:\"Зубанов\", fname:\"Дмитрий\", mname:\"Дмитриевич\"}");
        System.out.println("testUpdate() " + success);
    }
    
    private void testConnect(){
        SQONHandler handler = new SQONHandler("jdbc:sqlite:/home/developer/bin/sqon/json.db");
    }
    
    private void testInsert(){
        int id = handler.insert("employee", "{lname:\"Зубанов\", fname:\"Дмитрий\", mname:\"Владимирович\"}");
        System.out.println("testInsert id = " + id);
    }
    
    private void testGetValuesByKey(){
        List<SQONItem> values = handler.getValues("employee");
        System.out.println(values);
    }
    
    private void testGetValuesByKeyAndDate(){
        List<SQONItem> values = handler.getValuesByKeyAndDate("employee", 0, 1386852650);
        System.out.println(values);
    }
    
}
