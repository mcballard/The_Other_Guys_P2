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
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData columnMetadata = rs.getMetaData();
            // retrieve info from result set
            rs.next();
            HashMap<String, String> resultHashMap = new HashMap<>(columnMetadata.getColumnCount());
            for (int i = 1; i <= columnMetadata.getColumnCount(); ++i) {
                resultHashMap.put(columnMetadata.getColumnName(i), String.valueOf(rs.getObject(columnMetadata.getColumnName(i))));
            }
            /*
            while (rs.next()) {
                for (int i = 1; i <= columnMetadata.getColumnCount(); ++i) {
                    resultHashMap.get(columnMetadata.getColumnName(i)).add(rs.getObject(i));
                }
            }*/
            DatabaseEntity newDatabaseRow = new DatabaseEntity(resultHashMap);
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

    public static void main(String[] args) {
        DataAccessImp daoObj = new DataAccessImp();
        daoObj.insertObjectDb("insert into p2_sandbox.ticket_requests " +
                "(status_id,employee_id,description,ticket_requests_id) " +
                "values ('1','2','this is something',default) returning *;");
    }
}
