package com.businessName.dataEntity;

import com.businessName.MalformedObjectException.MalformedObjectException;

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

    public void sanitizeFromApi() {
        if(newRowObject.containsKey("tableName")) {
            for (Map.Entry<String, String> entry : newRowObject.entrySet()) {
                if(entry.getKey()!="tableName") {
                    if(entry.getKey().matches("\\w*Id\\b") || entry.getKey().matches("\\w*_id\\b")) {
                        if(!entry.getValue().matches("\\d+")) {
                            throw new MalformedObjectException("Id cannot be converted to integer!");
                        }
                    }
                    if(entry.getKey()=="description" && (entry.getValue()=="" || entry.getValue()==null)) {
                        throw new MalformedObjectException("Description must have at least 1 character!");
                    }
                }
            }
        } else {
            throw new MalformedObjectException("Object does not contain tableName key!");
        }
    }

    public String returnSqlForInsertOne() {
        /*
            i break the instruction into 4 seperate sections
                action
                columns affected
                column values
                trailer arguments
            insert into table_name + (column, column1, column2) + values (value, value1, value2) + returning *;
            string = "insert into " + schemaPrefix + "." + newRowObject.get("tableName") + " ";
            string = "(";
            string = " values (";
        */
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

        return "select * from " + schemaPrefix + "." + newRowObject.get("tableName") + " where " +
                newRowObject.get("tableName")+"_id"+"="+newRowObject.get(newRowObject.get("tableName")+"_id")+";";
    }

    public String returnSqlForUpdateOne() {
        String sqlQuery = "update "+schemaPrefix+"."+newRowObject.get("tableName")+" ";
        String columnNames = "set ";
        String columnValues = " where ";
        int tooManyCommas = newRowObject.size() - 2;
        int commas=0;
        for (Map.Entry<String, String> entry : newRowObject.entrySet()) {
            commas++;


            if(entry.getKey() != "tableName" && entry.getKey() != "employees_id") {
                columnNames +=  entry.getKey() + " = '" + entry.getValue() + "'";
//            } else if(entry.getKey()=="tableName") {
//                columnValues += entry.getValue() + "_id = " + "default" + ";";
            }


            if(commas<tooManyCommas){
                columnNames += ",";
//                columnValues += ",";
            }
        }
//        columnNames += ")";
//        columnValues += ")";
        columnValues += "employees_id = "+newRowObject.get("employees_id")+";";
        sqlQuery += columnNames + columnValues;
        return sqlQuery;
    }
//        String sqlQuery;
//        sqlQuery = "";
//        return sqlQuery;
//    }

    public String returnSqlForDeleteOne() {
        String sqlQuery = "delete from " +schemaPrefix+ "." +newRowObject.get("tableName")+" where request_id="+ newRowObject.get("requestID")+ ";";
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
