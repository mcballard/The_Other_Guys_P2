package com.businessName.ticketDao;

import com.businessName.dataEntity.DatabaseEntity;

import java.util.List;

public interface DataAccessInterface {
    DatabaseEntity insertObjectDb(String sql_query);
    DatabaseEntity[] selectObjectsDb(String sql_query);
    DatabaseEntity updateObjectDb(String sql_query);
    int deleteObjectDb(String sql_query);
}
