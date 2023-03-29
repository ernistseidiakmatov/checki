package com.checki.cf.service;

import org.apache.ibatis.annotations.Mapper;

import com.checki.cf.domain.Cfacility;

import java.util.List;

@Mapper
public interface CfDao {

    public List<Cfacility> find();
}
