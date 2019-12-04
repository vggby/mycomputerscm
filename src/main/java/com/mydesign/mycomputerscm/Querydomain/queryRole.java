package com.mydesign.mycomputerscm.Querydomain;
import lombok.Data;
import org.springframework.stereotype.Component;


import	java.util.List;
@Component
@Data
public class queryRole {
    private String role_name;
    private List <Integer>status;
    private Integer limit;
    private Integer offset;

}
