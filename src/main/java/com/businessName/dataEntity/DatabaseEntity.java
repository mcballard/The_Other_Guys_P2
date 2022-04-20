package com.businessName.dataEntity;

import com.businessName.CustomerExceptions.MalformedObjectException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            if(!Objects.equals(entry.getKey(), "tableName")) {
                columnNames += entry.getKey();
                columnValues += "'" + entry.getValue() + "'";
            } else if(entry.getKey().equals("tableName")) {
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

    public String returnSqlForSelectAll() {
        return "select * from " + schemaPrefix + "." + newRowObject.get("tableName") + ";";
    }

    public String returnSqlForSelectByEmployeeId() {
        return "select * from " + schemaPrefix + "." + newRowObject.get("tableName") + " where (employee_id=" +
                newRowObject.get("employee_id") + ") and (status_id=1);";
    }

    public String returnSqlForUpdateOne() {
        String sqlQuery = "update "+schemaPrefix+"."+newRowObject.get("tableName")+" ";
        String columnNames = "set ";
        String columnValues = " where ";
        int tooManyCommas = newRowObject.size() - 1;
        int commas=0;
        for (Map.Entry<String, String> entry : newRowObject.entrySet()) {
            commas++;
            if(!Objects.equals(entry.getKey(), "tableName")
                    && (
                            !Objects.equals(entry.getKey(), "employees_id") && !Objects.equals(entry.getKey(), "ticket_requests_id")
                    )
                ) {
                columnNames +=  entry.getKey() + " = '" + entry.getValue() + "'";
            } else { commas++; }
            if(commas<tooManyCommas){
                columnNames += ",";
            }
        }
        if(!newRowObject.containsKey("employees_id")) {
            columnValues += "ticket_requests_id = "+newRowObject.get("ticket_requests_id");
        } else {
            columnValues += "employees_id = " + newRowObject.get("employees_id");
        }
        sqlQuery += columnNames + columnValues + " returning *;";
        return sqlQuery;
    }

    public String returnSqlForDeleteOne() {
        String sqlQuery = "delete from " +schemaPrefix+ "." +newRowObject.get("tableName")+
                " where "+newRowObject.get("tableName")+"_id"+"="+ newRowObject.get(newRowObject.get("tableName")+"_id")+ ";";
        return sqlQuery;
    }

    public String selectDoLogin() {
        return "select * from " + schemaPrefix + "." + newRowObject.get("tableName") + " where username='" +
                newRowObject.get("username")+"';";
    }
}
