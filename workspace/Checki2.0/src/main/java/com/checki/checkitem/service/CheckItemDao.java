package com.checki.checkitem.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.checki.checkitem.domain.CheckItem;

@Mapper
public interface CheckItemDao {
    
    public List<CheckItem> find();

    void insert(CheckItem checkItem);

    CheckItem findById(int idx);

    void update(CheckItem checkItem);

    void deleteById(int idx);
}
