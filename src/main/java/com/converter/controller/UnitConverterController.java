package com.converter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/units")
public class UnitConverterController {

    @GetMapping("/{unitString}")
    public ResponseEntity convertUnits(@PathVariable String unitString){
        return null;
    }
    
}
