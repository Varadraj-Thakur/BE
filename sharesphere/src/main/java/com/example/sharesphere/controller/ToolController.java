package com.example.sharesphere.controller;

import com.example.sharesphere.model.Tool;
import com.example.sharesphere.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ToolController {

    @Autowired
    private ToolService service;

    @GetMapping("/tools")
    public ResponseEntity<List<Tool>> getAllTools(){
        return new ResponseEntity<>(service.getAllTools(), HttpStatus.OK);
    }

    @GetMapping("/tool/{id}")
    public ResponseEntity<Tool> getTool(@PathVariable int id){

        Tool tool = service.getToolbyId(id);
        if(tool != null)
            return new ResponseEntity<>(tool, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tool")
    public ResponseEntity<?> addTool(@RequestBody Tool tool){
        try {
            Tool tool1 = service.addTool(tool);
            return new ResponseEntity<>(tool1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tool/{id}")
    public ResponseEntity<String> updateTool(@PathVariable int id,@RequestBody Tool tool){
        Tool tool1 = service.updateTool(id, tool);
        if(tool1 != null){
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/tool/{id}")
    public ResponseEntity<String> deleteTool(@PathVariable int id){
        Tool tool = service.getToolbyId(id);
        if(tool != null){
            service.deletetool(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Tool not Found", HttpStatus.NOT_FOUND);
        }
    }

}
