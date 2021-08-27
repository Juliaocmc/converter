package com.converter.controller;

import com.converter.service.UnitConverterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/units")
public class UnitConverterController {

    @Autowired
    UnitConverterService ucs;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity convertUnits(@RequestParam String unitString){
        try {
            if (unitString != null && !unitString.isEmpty()) throw new Exception("Unit string is empty!");
                return ResponseEntity.ok(ucs.mountEquations(unitString));
            
            
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage().toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
