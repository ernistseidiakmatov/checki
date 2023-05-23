package com.checki.cf.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.checki.cf.domain.Cfacility;
import com.checki.checkitem.domain.CheckItem;
import com.checki.checkitem.service.CheckItemService;

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

    @Autowired
    CheckItemService checkItemService;

    public List<Cfacility> find() throws Exception {
        List<Cfacility> result = new ArrayList<>();
        result=cfDao.find();
         
        try {

            String pattern="yyyy-MM-dd HH:mm:ss";
        	SimpleDateFormat formatter=new SimpleDateFormat(pattern);

            for(int i=0;i<result.size(); i++) {

                String dt=result.get(i).getUpdate_dt();
                if(dt instanceof String){
                    long unixTimestamp = Long.parseLong(dt);
                    Date  date = new Date(unixTimestamp);
                    String formatedDate = formatter.format(date);
                    result.get(i).setUpdate_dt(formatedDate.toString());
                }
                
                String c_dt=result.get(i).getCreate_dt();
                if(c_dt instanceof String){
                    long unixTimestamp2 = Long.parseLong(c_dt);
    
                    Date  date2 = new Date(unixTimestamp2);
                    String formatedDate2 = formatter.format(date2);
                    result.get(i).setCreate_dt(formatedDate2.toString());
                }

                if(result.get(i).getCheck_item_idx() != null){
                    int itemId = Integer.parseInt(result.get(i).getCheck_item_idx());
                    CheckItem checkItem = checkItemService.findById(itemId);
                    result.get(i).setCheckItem(checkItem);   
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void insert(Cfacility cfacility) {
        try {
            if(cfacility.getCheck_item_idx() !=null){
                int itemId = Integer.parseInt(cfacility.getCheck_item_idx());
                CheckItem checkItem = checkItemService.findById(itemId);
                cfacility.setCheckItem(checkItem);
            }

            cfDao.insert(cfacility);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(Cfacility cfacility) {
        try {
            if(cfacility.getCheck_item_idx() !=null){
                int itemId = Integer.parseInt(cfacility.getCheck_item_idx());
                CheckItem checkItem = checkItemService.findById(itemId);
                cfacility.setCheckItem(checkItem);
            }
            
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
            if(dt instanceof String){
                long unixTimestamp = Long.parseLong(dt);

                Date  date = new Date(unixTimestamp);
                String formatedDate = formatter.format(date);
                result.setUpdate_dt(formatedDate.toString());
            }

            String c_dt=result.getCreate_dt();
            if(c_dt instanceof String){
                long unixTimestamp2 = Long.parseLong(c_dt);

                Date  date2 = new Date(unixTimestamp2);
                String formatedDate2 = formatter.format(date2);
                result.setCreate_dt(formatedDate2.toString());
            }
            
            if(result.getCheck_item_idx() !=null){
                int itemId = Integer.parseInt(result.getCheck_item_idx());
                CheckItem checkItem = checkItemService.findById(itemId);
                result.setCheckItem(checkItem);
            }
            
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
