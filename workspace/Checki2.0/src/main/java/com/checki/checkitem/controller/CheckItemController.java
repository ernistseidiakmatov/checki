package com.checki.checkitem.controller;

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

import com.checki.checkitem.domain.CheckItem;
import com.checki.checkitem.service.CheckItemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/check-item/*")
@Slf4j
public class CheckItemController {

    @Autowired
    private CheckItemService checkItemService;

    @GetMapping("find")
    @ResponseBody
    public ResponseEntity<?> findAll() {
        List<CheckItem> results;
        try{
           results = checkItemService.find();
           return ResponseEntity.ok().body(results);
        } catch(Exception e){
            log.warn("Get Opereation Failed");
            return ResponseEntity.badRequest().body("Get Opereation Failed");
        }
    }

    @GetMapping("find/{idx}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable int idx){
        CheckItem result;
        try {
          result = checkItemService.findById(idx);
          return ResponseEntity.ok().body(result);
        } catch(Exception e) {
          log.warn("Get Opereation Failed");
          return ResponseEntity.badRequest().body("Get Opereation Failed");
        }
    }

    @PostMapping("insert")
    public ResponseEntity<?> insert(@RequestBody CheckItem checkItem) 
    {
      try {
        checkItemService.insert(checkItem);
        log.info("Insert Operation Successful.");
        return ResponseEntity.ok().body("Insert Operation Successful");
      } catch (Exception e) {
        log.error("Insert Opereation Failed: " + e.getMessage());
        return ResponseEntity.badRequest().body("Insert Operation Failed");
      }
    }

    @PutMapping("/{idx}")
    public ResponseEntity<?> update(@PathVariable int idx, @RequestBody CheckItem checkItem){
      try{
        checkItem.setIdx(idx);
        checkItemService.update(checkItem);
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
        checkItemService.deleteById(idx);
        log.info("Delete operation Successful.");
        return ResponseEntity.ok().body("Delete Operation Successful");
      } catch (Exception e) {
        log.warn("Delete Opereation Failed");
        return ResponseEntity.badRequest().body("Delete Opereation Failed");
      }
    }
}
