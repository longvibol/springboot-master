package com.eazybytes.eazyschool.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="holidays")
public class Holiday extends BaseEntity{

	/* We remove the final keyword because we want to change from back End and for RowMapper it also need to change
    private final String day;
    private final String reason;
    private final Type type;
    
    */
	
	@Id
    private  String day;
    private  String reason;
    
    @Enumerated(EnumType.STRING)
    private  Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
}
