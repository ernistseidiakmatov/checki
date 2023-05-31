package com.checki.inspector.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.checki.inspector.domain.Inspector;

@Mapper
public interface InspectorDao {
    public List<Inspector> find();

    void insert(Inspector inspector);

    Inspector findById(int idx);

    void update(Inspector inspector);

    void deleteById(int idx);
}
