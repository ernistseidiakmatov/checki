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

    @PostMapping("insert")
    public ResponseEntity<?> insert(@RequestBody Cfacility cfacility) 
    {
      try {
        cfService.insert(cfacility);
        log.info("Insert Operation Successful.");
        return ResponseEntity.ok().body("Insert Operation Successful.");
      } catch (Exception e) {
        log.error("Insert operation failed:" + e.getMessage());
        return ResponseEntity.badRequest().body("Insert Operation Falied");
      }
    }


    @PutMapping("/{idx}")
    public ResponseEntity<?> update(@PathVariable int idx, @RequestBody Cfacility cfacility){
      try{
        cfacility.setIdx(idx);
        cfService.update(cfacility);
        return ResponseEntity.ok().body("Updated Successfully");
      }catch(Exception e){
        log.warn("Update Operation Failed");
        return ResponseEntity.badRequest().body("Update Operation Failed");
      }
      
    } 

    @GetMapping("find/{idx}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable int idx){
        Cfacility result;
        try {
          result = cfService.findById(idx);
          return ResponseEntity.ok().body(result);
        } catch(Exception e) {
          log.warn("Get Operation Failed");
          return ResponseEntity.badRequest().body("Get Opereation Failed");
        }
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteById(@PathVariable int idx) 
    {
      try {
        cfService.deleteById(idx);
        log.info("Delete Operation Successful.");
        return ResponseEntity.ok().body("Delete Operation Successful");
      } catch (Exception e) {
        log.warn("Delete operation Failed");
        return ResponseEntity.badRequest().body("Delete operation Failed");
      }
    }

}
