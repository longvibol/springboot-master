package com.eazybytes.eazyschool.model;

import lombok.Data;

@Data
public class Holiday extends BaseEntity{

	/* We remove the final keyword becasue we want to change from backend and for RowMapper it also need to change
    private final String day;
    private final String reason;
    private final Type type;
    
    */
    private  String day;
    private  String reason;
    private  Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
}
