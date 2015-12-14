/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Brester
 */
public class MySqlDbStrategy implements DbStrategy {

    private Connection conn;

    @Override
    public final void openConnection(String driverClass, String url, String userName, String password) throws Exception {
        if(driverClass == null || driverClass.equals("") || url == null || url.equals("") || userName == null || userName.equals("") || password == null || password.equals("") ){
            throw new Exception();
        }
           
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);

    }

    @Override
    public final void closeConnection() throws SQLException {
        conn.close();
    }
    @Override
    public final Map<String, Object> findById(String tableName, String pkName,Object pkValue) throws SQLException {
    if(tableName == null || tableName.equals("") || pkName == null || pkName.equals("") || pkValue == null){
        //I dont want to throw this, but Exception is giving me a checked exception and wants me to try catch it.
            throw new SQLException();
        
    }
        String sql = "SELECT * FROM " + tableName + " WHERE " + pkName + " = ?";
        PreparedStatement stmt = null;
        final Map<String, Object> record = new HashMap();

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, pkValue);
            ResultSet rs = stmt.executeQuery();
            final ResultSetMetaData metaData = rs.getMetaData();
            final int fields = metaData.getColumnCount();

            
            if (rs.next()) {
                for (int i = 1; i <= fields; i++) {
                    record.put(metaData.getColumnName(i), rs.getObject(i));
                }
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw e;
            } 
        } 

        return record;
    }

    @Override
    public final List<Map<String, Object>> findAllRecords(String tableName) throws SQLException {
        
        if(tableName == null || tableName.equals("")){
            //Same as above.
            throw new SQLException();
        }
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM " + tableName;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        final int columnCount = metaData.getColumnCount();

        List<Map<String, Object>> recordList = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> record = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                record.put(metaData.getColumnName(i), rs.getObject(i));
            }
            recordList.add(record);
        }
        return recordList;
    }

    @Override
    public final void deleteRecord(String tableName, String pkName, Object pkValue) throws SQLException {
        if(tableName == null || tableName.equals("") || pkName == null || pkName.equals("") || pkValue == null){
        //I dont want to throw this, but Exception is giving me a checked exception and wants me to try catch it.
            throw new SQLException();
        
    }
        PreparedStatement stmt = null;

        String sql = "DELETE FROM " + tableName + " WHERE " + pkName + " =  ?";

        
        stmt = conn.prepareStatement(sql);
        stmt.setObject(1, pkValue);
        System.out.println(stmt);
        stmt.executeUpdate();
    }

    @Override
    public final void createNewRecord(Map<String, Object> record, String tableName) throws Exception {
        if(record == null || tableName == null || tableName.equals("")){
            throw new Exception();
        }
        PreparedStatement stmt = null;

        String sql = "INSERT INTO " + tableName + " (";
        Set temp = record.keySet();
        List<String> colmnNames = new ArrayList<>(temp);
        for (int count = 0; count < colmnNames.size(); count++) {

            try {
                colmnNames.get(count + 1);
                sql += colmnNames.get(count) + ",";
            } catch (IndexOutOfBoundsException e) {
                sql += colmnNames.get(count);

            }
        }
        sql += ") VALUES (";
        for (int count = 0; count < colmnNames.size(); count++) {
            try {
                colmnNames.get(count + 1);
                sql += "?,";
            } catch (IndexOutOfBoundsException e) {
                sql += "?";
            }
        }
        sql += ")";
        
        
        int count = 1;
        stmt = conn.prepareStatement(sql);
        for(;count <= colmnNames.size();count++){
         stmt.setObject(count, record.get(colmnNames.get(count-1)));
        }
        System.out.println(sql);
        stmt.executeUpdate();
    }

    @Override
    public final void updateRecord(Map<String, Object> record, String tableName, String pkName, Object pkValue) throws SQLException {
        if(record == null || tableName == null || tableName.equals("") || pkName == null || pkName.equals("") || pkValue == null){
            throw new SQLException();
        }
        PreparedStatement stmt = null;

        String sql = "UPDATE " + tableName + " SET ";
        Set temp = record.keySet();
        List<String> colmnNames = new ArrayList<>(temp);
        for (int count = 0; count < colmnNames.size(); count++) {
            try {
                colmnNames.get(count + 1);
                sql += colmnNames.get(count) + " = ?, ";
            } catch (IndexOutOfBoundsException e) {
                sql += colmnNames.get(count) + " = ?";

            }

        }
         sql += " WHERE " + pkName + " =  ?";
         
         stmt = conn.prepareStatement(sql);
         int count = 1;
        for(;count <= colmnNames.size();count++){
         stmt.setObject(count, record.get(colmnNames.get(count-1)));
        }
        stmt.setObject(count, pkValue);
        System.out.println(sql);
        
        stmt.executeUpdate();
    }


    @Override
    public final void openConnection(DataSource ds) throws Exception {
      conn =  ds.getConnection();
    }

    public final Connection getConn() {
        return conn;
    }

    public final void setConn(Connection conn) throws Exception {
        if(conn == null){
            throw new Exception();
        }
        this.conn = conn;
    }

    @Override
    public String toString() {
        return "MySqlDbStrategy{" + "conn=" + conn + '}';
    }

    

    

}
