package com.converter.controller;

import com.converter.service.UnitConverterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        if (unitString != null && !unitString.isEmpty()){
            var co = ucs.mountEquations(unitString);
            
            return ResponseEntity.ok(co);
        }
        return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);

    }
    
}
