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
    
    public ConversationObject mountEquations(String unitString) throws Exception{
        try {
            var co = new ConversationObject();
            String[] splitted = unitString.split("\\+|\\-|\\*|\\/|\\(|\\)");
            var unitName = unitString;
            var multiplicationFactor = unitString;
            for (int i=0; i<splitted.length; i++){
                if (!splitted[i].isEmpty()){
                    var si = getSi(splitted[i]);
                    var siConversion = getSiConverter(splitted[i]);
                    if (si == null || siConversion == null) throw new Exception("Unit not found!");
                    unitName = unitName.replaceAll(splitted[i], si);
                    multiplicationFactor = multiplicationFactor.replaceAll(splitted[i], String.valueOf(siConversion));
                    }
            }        
            co.setMultiplicationFactor(calculateEquation(multiplicationFactor));
            co.setUnitName(unitName);
            return co;
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public Double calculateEquation(String multiplicationFactor) throws Exception{
        try {
            Expression e = new ExpressionBuilder(multiplicationFactor).build();   
            return e.evaluate();
            
        } catch (Exception e) {
            throw new Exception("It was not possible to perform the equation!");
        }

    }

    public String getSi(String unit) throws Exception{
        try {
            return ucd.getSi(unit);            
        } catch (Exception e) {
            throw new Exception("Data not found");
        }
    }

    public Double getSiConverter(String unit) throws Exception{
        try {
            return ucd.getSiConverter(unit);            
        } catch (Exception e) {
            throw new Exception("Data not found");
        }
    }

    

}
