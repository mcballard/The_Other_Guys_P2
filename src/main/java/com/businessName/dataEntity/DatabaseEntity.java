package com.businessName.dataEntity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DatabaseEntity {

    public HashMap<String, String> newRowObject;
    public String schemaPrefix;

    public DatabaseEntity(HashMap<String, String> newDatabaseObject) {
        this.newRowObject = newDatabaseObject;
        this.schemaPrefix = "p2_sandbox";
    }

    public HashMap<String, String> sanitizeFromApi() {
        return newRowObject;
    }

    public String returnSqlForInsertOne() {
        String sqlQuery = "insert into "+schemaPrefix+"."+newRowObject.get("tableName")+" ";
        String columnNames = "(";
        String columnValues = " values (";
        int tooManyCommas = newRowObject.size();
        int commas=0;
        for (Map.Entry<String, String> entry : newRowObject.entrySet()) {
            commas++;
            if(entry.getKey() != "tableName") {
                columnNames += entry.getKey();
                columnValues += "'" + entry.getValue() + "'";
            } else if(entry.getKey()=="tableName") {
                columnNames+=entry.getValue()+"_id";
                columnValues+="default";
            }
            if(commas<tooManyCommas){
                columnNames += ",";
                columnValues += ",";
            }
        }
        columnNames += ")";
        columnValues += ")";
        sqlQuery += columnNames + columnValues + " returning *;";
        return sqlQuery;
    }

    public String returnSqlForSelectOne() {
        String sqlQuery = "";
        return sqlQuery;
    }

    public String returnSqlForUpdateOne() {
        String sqlQuery;
        sqlQuery = "";
        return sqlQuery;
    }

    public String returnSqlForDeleteOne() {
        String sqlQuery = "";
        return sqlQuery;
    }
/*
    public static void main(String[] args) {
        HashMap<String, String> test = new HashMap<>();
        test.put("tableName", "ticket_requests");
        test.put("employee_id", "5");
        test.put("description", "this is something");
        test.put("status_id", "0");
        DatabaseEntity testRow = new DatabaseEntity(test);
        String resultSql = testRow.returnSqlForInsertOne();
        System.out.println(resultSql);

        resultSql = testRow.returnSqlForDeleteOne();
        System.out.println(resultSql);
        resultSql = testRow.returnSqlForSelectOne();
        System.out.println(resultSql);
    }*/
}
