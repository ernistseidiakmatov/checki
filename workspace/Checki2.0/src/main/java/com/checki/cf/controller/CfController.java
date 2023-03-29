package com.checki.cf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.checki.cf.domain.Cfacility;
import com.checki.cf.service.CfService;

import lombok.extern.slf4j.Slf4j;



@RestController 
@RequestMapping("/cf/*")
@Slf4j 
public class CfController {

    @Autowired 
    private CfService cfService;

    /*
    @GetMapping("save")
    public String save()
    {
        
    	return "모든요청 권한 부여";
    }
    */

    @GetMapping("find")
    @ResponseBody 
    public ResponseEntity<?> findAll()
    {
    	List<Cfacility> result;
		try {
			result = cfService.find();
			return ResponseEntity.ok().body(result);
			
		} catch (Exception e) {
			
			log.warn("조회된 결과 NULL");
			return ResponseEntity.badRequest().body("조회된 결과 NULL");
		}
        
      
    }



}
