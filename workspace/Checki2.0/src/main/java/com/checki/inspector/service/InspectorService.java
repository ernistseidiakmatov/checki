package com.checki.inspector.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checki.inspector.domain.Inspector;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InspectorService {
    
    @Autowired
    InspectorDao inspectorDao;

    public List<Inspector> find() throws Exception {
        List<Inspector> result = new ArrayList<>();
        result = inspectorDao.find();

        try {
            String pattern="yyyy-MM-dd HH:mm:ss";
        	SimpleDateFormat formatter=new SimpleDateFormat(pattern);

            for(int i=0;i<result.size();i++) {

                String dt=result.get(i).getUpdate_dt();
                long unixTimestamp = Long.parseLong(dt);

                Date  date = new Date(unixTimestamp);
                String formatedDate = formatter.format(date);
        		result.get(i).setUpdate_dt(formatedDate.toString());

                String c_dt=result.get(i).getCreate_dt();
                long unixTimestamp2 = Long.parseLong(c_dt);
 
                Date  date2 = new Date(unixTimestamp2);
                String formatedDate2 = formatter.format(date2);
        		result.get(i).setCreate_dt(formatedDate2.toString());   
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return result;
    }

    public void insert(Inspector inspector) {
        try {
            inspectorDao.insert(inspector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Inspector findById(int idx){
        Inspector result = inspectorDao.findById(idx);

        try{
            String pattern="yyyy-MM-dd HH:mm:ss";
        	SimpleDateFormat formatter=new SimpleDateFormat(pattern);

            String dt=result.getUpdate_dt();
            long unixTimestamp = Long.parseLong(dt);

            Date  date = new Date(unixTimestamp);
            String formatedDate = formatter.format(date);
            result.setUpdate_dt(formatedDate.toString());

            String c_dt=result.getCreate_dt();
            long unixTimestamp2 = Long.parseLong(c_dt);

            Date  date2 = new Date(unixTimestamp2);
            String formatedDate2 = formatter.format(date2);
            result.setCreate_dt(formatedDate2.toString());

        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public void update(Inspector inspector){
        try {
            inspectorDao.update(inspector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int idx){
        try {
            inspectorDao.deleteById(idx); 
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
