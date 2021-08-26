package com.converter.controller;

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

    @GetMapping("/unitString")
    @ResponseBody
    public ResponseEntity convertUnits(@RequestParam String unit){
        if (unit != null && !unit.isEmpty()){
            String[] splitted = unit.split("\\+|\\(|\\*|\\)|\\-|\\/");
            for (int i=0; i<splitted.length; i++){
                var valorVelho = splitted[i];
                var valorNovo = "gogogo";
                unit = unit.replaceAll(valorVelho, valorNovo);
            }
            return ResponseEntity.ok(unit);
        }
        return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);

    }
    
}
