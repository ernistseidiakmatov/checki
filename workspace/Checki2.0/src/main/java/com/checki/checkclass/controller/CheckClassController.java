package com.checki.checkclass.controller;

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

import com.checki.checkclass.domain.CheckClass;
import com.checki.checkclass.service.CheckClassService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/check-class/*")
@Slf4j
public class CheckClassController {
    
    @Autowired
    private CheckClassService checkClassService;

    @GetMapping("find")
    @ResponseBody
    public ResponseEntity<?> findAll(){
        List<CheckClass> result;
        try{
            result = checkClassService.find();
            return ResponseEntity.ok().body(result);
        } catch ( Exception e) {
            log.warn("Get Opereation Failed");
            return ResponseEntity.badRequest().body("Get Opereation Failed");
        }
    }

    @GetMapping("find/{idx}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable int idx){
        CheckClass result;
        try {
          result = checkClassService.findById(idx);
          return ResponseEntity.ok().body(result);
        } catch(Exception e) {
          log.warn("Get Opereation Failed");
          return ResponseEntity.badRequest().body("Get Opereation Failed");
        }
    }

    @PostMapping("insert")
    public ResponseEntity<?> insert(@RequestBody CheckClass checkClass) 
    {
      try {
        checkClassService.insert(checkClass);
        log.info("Insert Operation Successful.");
        return ResponseEntity.ok().body("Insert Operation Successful");
      } catch (Exception e) {
        log.error("Insert Opereation Failed: " + e.getMessage());
        return ResponseEntity.badRequest().body("Insert Operation Failed");
      }
    }

    @PutMapping("/{idx}")
    public ResponseEntity<String> update(@PathVariable int idx, @RequestBody CheckClass checkClass){
      try{
        checkClass.setIdx(idx);
        checkClassService.update(checkClass);
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
        checkClassService.deleteById(idx);
        log.info("Delete operation Successful.");
        return ResponseEntity.ok().body("Delete Operation Successful");
      } catch (Exception e) {
        log.warn("Delete Opereation Failed");
        return ResponseEntity.badRequest().body("Delete Opereation Failed");
      }
    }

    
}
