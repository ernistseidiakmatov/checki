package com.checki.checksubclass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.checki.checksubclass.domain.CheckSubClass;
import com.checki.checksubclass.service.CheckSubClassService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/check-sub-class/*")
@Slf4j
public class CheckSubClassController {
    
    @Autowired
    private CheckSubClassService checkSubClassService;

    @GetMapping("find")
    @ResponseBody
    public ResponseEntity<?> findAll(){
        List<CheckSubClass> result;
        try{
           result = checkSubClassService.find();
           return ResponseEntity.ok().body(result);
        }catch(Exception e){
            log.warn("Get Opereation Failed");
            return ResponseEntity.badRequest().body("Get Opereation Failed");
        }
    }

    @GetMapping("find/{idx}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable int idx){
        CheckSubClass result;
        try {
          result = checkSubClassService.findById(idx);
          return ResponseEntity.ok().body(result);
        } catch(Exception e) {
          log.warn("Get Opereation Failed");
          return ResponseEntity.badRequest().body("Get Opereation Failed");
        }
    }

    @PostMapping("insert")
    public ResponseEntity<?> insert(@RequestBody CheckSubClass checkSubClass) 
    {
      try {
        checkSubClassService.insert(checkSubClass);
        log.info("Insert Operation Successful.");
        return ResponseEntity.ok().body("Insert Operation Successful");

      } catch (Exception e) {
        log.error("Insert Operation Failed: " + e.getMessage());
        return ResponseEntity.badRequest().body("Insert Operation Failed");
      }
    }

    @PutMapping("/{idx}")
    public ResponseEntity<String> update(@PathVariable int idx, @RequestBody CheckSubClass checkSubClass){
      try{
        checkSubClass.setIdx(idx);
        checkSubClassService.update(checkSubClass);
        return ResponseEntity.ok().body("Updated Successfully");
      }catch(Exception e){
        log.warn("Update Operation Failed");
        return ResponseEntity.badRequest().body("Update Operation Failed");
      }
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteById(@PathVariable int idx) 
    {
      try {
        checkSubClassService.deleteById(idx);
        log.info("Delete operation Successful.");
        return ResponseEntity.ok().body("Delete Operation Successful");
      } catch (Exception e) {
        log.warn("Delete Opereation Failed");
        return ResponseEntity.badRequest().body("Delete Opereation Failed");
      }
    }
}
