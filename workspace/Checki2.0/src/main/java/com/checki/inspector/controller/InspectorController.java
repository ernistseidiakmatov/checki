package com.checki.inspector.controller;

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

import com.checki.inspector.domain.Inspector;
import com.checki.inspector.service.InspectorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/inspector/*")
@Slf4j
public class InspectorController {
    
    @Autowired
    private InspectorService inspectorService;

    @GetMapping("find")
    @ResponseBody
    public ResponseEntity<?> findAll(){
        List<Inspector> result;
        try{
            result = inspectorService.find();
            return ResponseEntity.ok().body(result);
        } catch ( Exception e) {
            log.warn("Get Opereation Failed");
            return ResponseEntity.badRequest().body("Get Opereation Failed");
        }
    }

    @GetMapping("find/{idx}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable int idx){
        Inspector result;
        try {
          result = inspectorService.findById(idx);
          return ResponseEntity.ok().body(result);
        } catch(Exception e) {
          log.warn("Get Opereation Failed");
          return ResponseEntity.badRequest().body("Get Opereation Failed");
        }
    }

    @PostMapping("insert")
    public ResponseEntity<?> insert(@RequestBody Inspector inspector) 
    {
        try {
            inspectorService.insert(inspector);
            log.info("Insert Operation Successful.");
            return ResponseEntity.ok().body("Insert Operation Successful");
        } catch (Exception e) {
            log.error("Insert Opereation Failed: " + e.getMessage());
            return ResponseEntity.badRequest().body("Insert Operation Failed");
        }
    }

    @PutMapping("/{idx}")
    public ResponseEntity<String> update(@PathVariable int idx, @RequestBody Inspector inspector){
        try{
            inspector.setIdx(idx);
            inspectorService.update(inspector);
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
            inspectorService.deleteById(idx);
            log.info("Delete operation Successful.");
            return ResponseEntity.ok().body("Delete Operation Successful");
        } catch (Exception e) {
            log.warn("Delete Opereation Failed");
            return ResponseEntity.badRequest().body("Delete Opereation Failed");
        }
    }
}
