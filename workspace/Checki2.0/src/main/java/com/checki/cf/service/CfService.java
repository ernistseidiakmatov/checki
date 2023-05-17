package com.checki.cf.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.checki.cf.domain.Cfacility;

import lombok.extern.slf4j.Slf4j;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j 
public class CfService{
    @Autowired
    CfDao cfDao;

    public List<Cfacility> find() throws Exception {

        List<Cfacility> result = new ArrayList<>();
         result=cfDao.find();
         
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
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public void insert(Cfacility cfacility) {
        try {
            cfDao.insert(cfacility);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(Cfacility cfacility) {
        try {
            cfDao.update(cfacility);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cfacility findById(int idx){
        Cfacility result = cfDao.findById(idx);
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

    public void deleteById(int idx){
        try{
            cfDao.deleteById(idx); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
