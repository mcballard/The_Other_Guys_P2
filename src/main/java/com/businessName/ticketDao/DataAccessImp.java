package com.businessName.ticketDao;

import com.businessName.dataEntity.DatabaseEntity;
import java.sql.*;
import java.util.HashMap;

public class DataAccessImp implements DataAccessInterface {
    @Override
    public DatabaseEntity insertObjectDb(String sql_query) {
        try(Connection connection = ConnectionObject.createConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql_query);
            // get result set from query execution
            ResultSet rs = ps.executeQuery();
            // retrieve column names from result set
            ResultSetMetaData columnMetadata = rs.getMetaData();
            rs.next();
            HashMap<String, String> resultHashMap = new HashMap<>(columnMetadata.getColumnCount());
            // iterate through number of columns in result set
            for (int i = 1; i <= columnMetadata.getColumnCount(); ++i) {
                // put key value pair into hashmap (column name as string,column value converted to string)
                resultHashMap.put(columnMetadata.getColumnName(i), String.valueOf(rs.getObject(columnMetadata.getColumnName(i))));
            }
            // create our data entity using hashmap created from result set
            DatabaseEntity newDatabaseRow = new DatabaseEntity(resultHashMap);
            // return new entity "ticket_request" etc
            return newDatabaseRow;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DatabaseEntity[] selectObjectsDb(String sql_query) {
        try(Connection connection = ConnectionObject.createConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql_query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // get result set from query execution
            //Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();
            // retrieve column names from result set
            ResultSetMetaData columnMetadata = rs.getMetaData();
            rs.last();
            int totalRows = rs.getRow();
            rs.beforeFirst();
            HashMap<String, String> resultHashMap = new HashMap<>(columnMetadata.getColumnCount());
            DatabaseEntity[] results = new DatabaseEntity[totalRows];
            int indexCount=0;
            while(rs.next()) {
                for (int i = 1; i <= columnMetadata.getColumnCount(); ++i) {
                    // put key value pair into hashmap (column name as string,column value converted to string)
                    resultHashMap.put(columnMetadata.getColumnName(i), String.valueOf(rs.getObject(columnMetadata.getColumnName(i))));
                }
                DatabaseEntity viewDatabaseRow = new DatabaseEntity(resultHashMap);
                results[indexCount] = viewDatabaseRow;
                indexCount++;
            }
            return results;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DatabaseEntity updateObjectDb(String sql_query) {
        try(Connection connection = ConnectionObject.createConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql_query);
            // get result set from query execution
            ResultSet rs = ps.executeQuery();
            // retrieve column names from result set
            ResultSetMetaData columnMetadata = rs.getMetaData();
            rs.next();
            HashMap<String, String> resultHashMap = new HashMap<>(columnMetadata.getColumnCount());
            // iterate through number of columns in result set
            for (int i = 1; i <= columnMetadata.getColumnCount(); ++i) {
                // put key value pair into hashmap (column name as string,column value converted to string)
                resultHashMap.put(columnMetadata.getColumnName(i), String.valueOf(rs.getObject(columnMetadata.getColumnName(i))));
            }
            // create our data entity using hashmap created from result set
            DatabaseEntity newDatabaseRow = new DatabaseEntity(resultHashMap);
            // return new entity "ticket_request" etc
            return newDatabaseRow;
    } catch(SQLException e) {
        e.printStackTrace();
        return null;
    }
    }

    @Override
    public int deleteObjectDb(String sql_query) {
        try(Connection connection = ConnectionObject.createConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql_query);
            int rowCount = ps.executeUpdate();
            return rowCount;
        } catch(SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

/*

    public static void main(String[] args) {
        DataAccessImp daoObj = new DataAccessImp();
        daoObj.insertObjectDb("insert into p2_sandbox.ticket_requests " +
                "(status_id,employee_id,description,ticket_requests_id) " +
                "values ('1','2','this is something',default) returning *;");
    }

 */
}
