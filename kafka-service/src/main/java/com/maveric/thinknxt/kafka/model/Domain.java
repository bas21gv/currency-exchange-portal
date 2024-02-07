package com.maveric.thinknxt.kafka.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Domain implements Serializable {
    private String domain;
    private String create_date;
    private String update_date;
    private String country;
    private boolean isDead;
    private String A;
    private String NS;
    private String CNAME;
    private String MX;
    private String TXT;
}
