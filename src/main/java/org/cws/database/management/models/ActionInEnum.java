package org.cws.database.management.models;

public enum ActionInEnum {

    INSERT, DELETE, UPDATE_COLUMN;

    public String getActionIn(){
        return switch (this){
            case INSERT -> "I";
            case DELETE -> "D";
            case UPDATE_COLUMN -> "UC";
        };
    }
}
