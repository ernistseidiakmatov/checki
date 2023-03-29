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
public class CfService {
    @Autowired
    CfDao cfDao;

    public List<Cfacility> find() throws Exception {

        List<Cfacility> result = new ArrayList<>();
         result=cfDao.find();
         
         try {
        	 String pattern="yyyyMMdd";
        	 SimpleDateFormat formatter=new SimpleDateFormat(pattern);
        	 
        	 for(int i=0;i<result.size();i++) {
        		 String dt=result.get(i).getUpdate_dt();
        		 Date  date=formatter.parse(dt);
        		 result.get(i).setUpdate_dt(date.toString());
        	 }
    
         }
         catch(Exception e)
         {
        	 e.printStackTrace();
         }


        return result;
    }




}
