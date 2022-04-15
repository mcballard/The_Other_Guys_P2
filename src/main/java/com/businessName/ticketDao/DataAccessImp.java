package com.businessName.ticketDao;

import com.businessName.dataEntity.DatabaseEntity;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<DatabaseEntity> selectObjectsDb(String sql_query) {
        return null;
    }

    @Override
    public DatabaseEntity updateObjectDb(String sql_query) {
        return null;
    }

    @Override
    public boolean deleteObjectDb(String sql_query) {
        return false;
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
