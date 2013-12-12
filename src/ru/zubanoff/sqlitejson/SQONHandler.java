package ru.zubanoff.sqlitejson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author developer
 */
public class SQONHandler implements SQON {

    private final String urlConnection;
    private Connection connection;
    private static final Logger logger = Logger.getLogger("SQONHandler");
    private static final String SQL_LAST_INSERT_ID = "SELECT MAX(rowid) AS lastInsertId FROM main";

    public SQONHandler(String urlConnection) {
        this.urlConnection = urlConnection;
        init();
    }

    private void init() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(urlConnection);
            logger.log(Level.INFO, "Connection established");

        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.SEVERE, urlConnection, e);
        }
    }

    @Override
    public int insert(String key, String value) {
        try {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO main VALUES(?, ?, ?, ?)")) {
                statement.setString(1, key);
                statement.setString(2, value);
                statement.setInt(3, (int) (new Date().getTime() / 1000));
                statement.setInt(4, (int) (new Date().getTime() / 1000));
                statement.executeUpdate();
            }

            return getLastInsertId();

        } catch (SQLException e) {
            logger.log(Level.WARNING, null, e);
            return -1;
        }
    }

    @Override
    public int insert(String key, String value, String... indexedValueKeys) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int rowid, String value) {
        try {
            JSONObject jsonObject = new JSONObject(value);
            if(jsonObject.has("jsonDBInfo")){
                jsonObject.remove("jsonDBInfo");
            }
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE main SET value = ?, modify = ? WHERE rowid = ?")) {
                statement.setString(1, jsonObject.toString());
                statement.setInt(2, (int) (new Date().getTime() / 1000));
                statement.setInt(3, rowid);
                statement.executeUpdate();
            }

            return true;

        } catch (JSONException | SQLException e) {
            logger.log(Level.WARNING, null, e);
            return false;
        }
    }

    @Override
    public boolean update(String key, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(String key, String value, int... rowids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int rowid) {
        try {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM main WHERE rowid = ?")) {
                statement.setInt(1, rowid);
                statement.executeUpdate();
            }

            return true;

        } catch (SQLException e) {
            logger.log(Level.WARNING, null, e);
            return false;
        }
    }

    @Override
    public boolean delete(int... rowids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String key, int... rowids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getLastInsertId() {
        try {
            int rowid;
            try (Statement statement = connection.createStatement()) {
                rowid = -1;
                try (ResultSet rs = statement.executeQuery(SQL_LAST_INSERT_ID)) {
                    while (rs.next()) {
                        rowid = rs.getInt(1);
                    }
                }
            }

            return rowid;

        } catch (SQLException e) {
            logger.log(Level.WARNING, null, e);
            return -1;
        }
    }

    @Override
    public List<SQONItem> getValues(String key) {
        try {
            ArrayList<SQONItem> resultSet = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement("SELECT rowid, value, date, modify FROM main WHERE key = ?")) {
                statement.setString(1, key);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        resultSet.add(new SQONItem(
                                rs.getInt("rowid"),
                                rs.getString("value"),
                                rs.getInt("date"),
                                rs.getInt("modify")));
                    }
                }
            }

            return resultSet;

        } catch (SQLException e) {
            Logger.getGlobal().log(Level.WARNING, null, e);
            return new ArrayList<>();
        }
    }

    @Override
    public String getValue(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SQONItem> getValuesByIndex(String sqlRequest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<SQONItem> getValuesByKeyAndDate(String key, int dateStart, int dateEnd){
        try {
            ArrayList<SQONItem> resultSet = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT rowid, value, date, modify FROM main WHERE key = ? AND (date >= ? AND date <= ?)")) {
                statement.setString(1, key);
                statement.setInt(2, dateStart);
                statement.setInt(3, dateEnd);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        resultSet.add(new SQONItem(
                                rs.getInt("rowid"),
                                rs.getString("value"),
                                rs.getInt("date"),
                                rs.getInt("modify")));
                    }
                }
            }

            return resultSet;

        } catch (SQLException e) {
            Logger.getGlobal().log(Level.WARNING, null, e);
            return new ArrayList<>();
        }
    }

}
