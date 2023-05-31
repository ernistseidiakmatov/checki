package com.checki.inspector.domain;

import lombok.Data;

@Data
public class Inspector {
    private int idx;

    private String inspector_name;
    
    private String inspector_tel;
    
    private String inspector_email;
    
    private String inspector_description;
    
    private String inspector_image;
    
    private String del_yn;
    
    private String create_by;
    
    private String create_dt;
    
    private String update_by;
    
    private String update_dt;
}
