package com.checki.checkclass.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.checki.checkclass.domain.CheckClass;

@Mapper
public interface CheckClassDao {

    public List<CheckClass> find();
    void insert(CheckClass checkClass);

    CheckClass findById(int idx);

    void update(CheckClass checkClass);

    void deleteById(int idx);
}
