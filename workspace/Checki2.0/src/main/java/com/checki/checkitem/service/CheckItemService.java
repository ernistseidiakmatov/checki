package com.checki.checkitem.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checki.checkclass.domain.CheckClass;
import com.checki.checkclass.service.CheckClassService;
import com.checki.checkitem.domain.CheckItem;
import com.checki.checksubclass.domain.CheckSubClass;
import com.checki.checksubclass.service.CheckSubClassService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CheckItemService {
    
    @Autowired
    CheckItemDao checkItemDao;

    @Autowired
    CheckClassService checkClassService;

    @Autowired
    CheckSubClassService checkSubClassService;

    public List<CheckItem> find() {
        List<CheckItem> result = new ArrayList<>();
        result = checkItemDao.find();

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

                int classId = Integer.parseInt(result.get(i).getCheck_class_idx());
                CheckClass checkClass = checkClassService.findById(classId);
                result.get(i).setCheckClass(checkClass);

                int subClassId = Integer.parseInt(result.get(i).getCheck_sub_class_idx());
                CheckSubClass checkSubClass = checkSubClassService.findById(subClassId);
                result.get(i).setCheckSubClass(checkSubClass);                  
        	 }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return result;
    }

    public void insert(CheckItem checkItem) {
        try{
            int classIdx = Integer.parseInt(checkItem.getCheck_class_idx());
            CheckClass checkClass = checkClassService.findById(classIdx);
            checkItem.setCheckClass(checkClass);

            int subClassIdx = Integer.parseInt(checkItem.getCheck_sub_class_idx());
            CheckSubClass checkSubClass = checkSubClassService.findById(subClassIdx);
            checkItem.setCheckSubClass(checkSubClass);

            checkItemDao.insert(checkItem);
        } catch(Exception e){
            log.error("Insert Opereation Failed: " + e.getMessage());
        }

    }

    public CheckItem findById(int idx){
        CheckItem result = checkItemDao.findById(idx);
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

            int classId = Integer.parseInt(result.getCheck_class_idx());
            CheckClass checkClass = checkClassService.findById(classId);
            result.setCheckClass(checkClass);

            int subClassId = Integer.parseInt(result.getCheck_sub_class_idx());
            CheckSubClass checkSubClass = checkSubClassService.findById(subClassId);
            result.setCheckSubClass(checkSubClass);

        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public void update(CheckItem checkItem) {
        try {
            int classIdx = Integer.parseInt(checkItem.getCheck_class_idx());
            CheckClass checkClass = checkClassService.findById(classIdx);
            checkItem.setCheckClass(checkClass);

            int subClassIdx = Integer.parseInt(checkItem.getCheck_sub_class_idx());
            CheckSubClass checkSubClass = checkSubClassService.findById(subClassIdx);
            checkItem.setCheckSubClass(checkSubClass);
            
            checkItemDao.update(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int idx){
        try {
            checkItemDao.deleteById(idx); 
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
