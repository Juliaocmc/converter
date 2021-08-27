package com.converter.service;

import java.math.BigDecimal;

import com.converter.dto.ConversationObject;
import com.converter.repository.UnitConverterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Service
public class UnitConverterService {

    @Autowired
    UnitConverterRepository ucd;
    
    public ConversationObject mountEquations(String unitString){
        var co = new ConversationObject();
        String[] splitted = unitString.split("\\+|\\(|\\*|\\)|\\-|\\/");
        var unitName = unitString;
        var multiplicationFactor = unitString;
        for (int i=0; i<splitted.length; i++){
            if (!splitted[i].isEmpty()){
                var si = ucd.getSi(splitted[i]);
                var siConversion = ucd.getSiConverter(splitted[i]);
                unitName = unitName.replaceAll(splitted[i], si);
                multiplicationFactor = multiplicationFactor.replaceAll(splitted[i], String.valueOf(siConversion));

            }
        }        
        co.setMultiplication_factor(getMultiplicationFactor(multiplicationFactor));
        co.setUnit_name(unitName);
        return co;

    }

    public Double getMultiplicationFactor(String multiplicationFactor){

        Expression e = new ExpressionBuilder(multiplicationFactor).build();   
        System.out.println(e.evaluate());
        return e.evaluate();

    }

    

}
