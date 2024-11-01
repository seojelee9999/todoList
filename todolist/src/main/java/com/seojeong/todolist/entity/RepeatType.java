package com.seojeong.todolist.entity;

public enum RepeatType {
    NONE, DAILY, WEEKLY, MONTHLY;

    public static RepeatType fromString(String value){
        try{
            return RepeatType.valueOf(value.toUpperCase());
        }
        catch(IllegalArgumentException | NullPointerException e){
            return NONE;
        }
    }
}
