package com.checki.cf.service;

import org.apache.ibatis.annotations.Mapper;

import com.checki.cf.domain.Cfacility;

import java.util.List;

@Mapper
public interface CfDao {
    public List<Cfacility> find();
    
    void insert(Cfacility cfacility);

    void update(Cfacility cfacility);

    Cfacility findById(int idx);

    void deleteById(int idx);
}
