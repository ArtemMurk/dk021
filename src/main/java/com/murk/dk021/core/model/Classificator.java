package com.murk.dk021.core.model;

import lombok.Data;

@Data
public class Classificator {
    private int id;
    private Integer parentId;
    private short num;
    private String name;

    public Classificator(int id,short num, Integer parentId,  String name) {
        this.id = id;
        this.parentId = parentId;
        this.num = num;
        this.name = name;
    }
}