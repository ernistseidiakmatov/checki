package com.checki.cf.domain;

import org.springframework.objenesis.strategy.StdInstantiatorStrategy;

import com.checki.checkitem.domain.CheckItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
public class Cfacility {

    private int idx;

    private String check_group;

    private String del_yn;

    private String create_by;

    private String create_dt;

    private String update_by;

    private String update_dt;

    private String file_name;

    private String file_host;

    private String file_path;

    private String file_size;

    private String check_item_idx;

    private CheckItem checkItem;

    private String tag_id;

   
}
