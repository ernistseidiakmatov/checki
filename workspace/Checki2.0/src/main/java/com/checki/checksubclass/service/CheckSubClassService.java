package com.checki.checksubclass.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checki.checksubclass.domain.CheckSubClass;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CheckSubClassService {
    
    @Autowired
    CheckSubClassDao checkSubClassDao;

    public List<CheckSubClass> find() throws Exception {
        List<CheckSubClass> result = new ArrayList<>();
        result = checkSubClassDao.find();
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
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void insert(CheckSubClass checkSubClass) {
        try {
            checkSubClassDao.insert(checkSubClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CheckSubClass findById(int idx){
        CheckSubClass result = checkSubClassDao.findById(idx);

        try {
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
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public void update(CheckSubClass checkSubClass){
        try{
            checkSubClassDao.update(checkSubClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int idx){
        try {
            checkSubClassDao.deleteById(idx); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
