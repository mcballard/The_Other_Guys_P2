package com.businessName.ticketDao;

import com.businessName.dataEntity.DatabaseEntity;

import java.util.List;

public interface DataAccessInterface {
    DatabaseEntity insertObjectDb(String sql_query);
    List<DatabaseEntity> selectObjectsDb(String sql_query);
    DatabaseEntity updateObjectDb(String sql_query);
    boolean deleteObjectDb(String sql_query);
}
