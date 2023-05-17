package com.checki.checksubclass.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.checki.checksubclass.domain.CheckSubClass;


@Mapper
public interface CheckSubClassDao {
    
    public List<CheckSubClass> find();

    void insert(CheckSubClass checkSubClass);

    void update(CheckSubClass checkSubClass);

    CheckSubClass findById(int idx);

    void deleteById(int idx);
}
