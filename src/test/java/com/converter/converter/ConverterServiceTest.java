package com.converter.converter;

import com.converter.service.UnitConverterService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class) 
public class ConverterServiceTest {

    @Autowired
    UnitConverterService ucs;

    @Test
    public void oneUnitConvert(){
        try {
            String unitString = "minute";
            var co = ucs.mountEquations(unitString);

            Assert.assertNotNull("Must return a converted object", co);
            Assert.assertEquals("Must the unit name","s", co.getUnitName());
            Assert.assertEquals("Must the multiplication factor",60D, co.getMultiplicationFactor(), 0);


            unitString = "degree";
            co = ucs.mountEquations(unitString);

            Assert.assertNotNull("Must return a converted object", co);
            Assert.assertEquals("Must the unit name","rad", co.getUnitName());
            Assert.assertEquals("Must the multiplication factor",0.017453292519943295D, co.getMultiplicationFactor(), 0);

            unitString = "litre";
            co = ucs.mountEquations(unitString);

            Assert.assertNotNull("Must return a converted object", co);
            Assert.assertEquals("Must the unit name","m³", co.getUnitName());
            Assert.assertEquals("Must the multiplication factor",0.001D, co.getMultiplicationFactor(), 0);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void oneUnitConvertSpecialCharacters(){
        try {
            String unitString = "°";
            var co = ucs.mountEquations(unitString);

            Assert.assertNotNull("Must return a converted object", co);
            Assert.assertEquals("Must the unit name","rad", co.getUnitName());
            Assert.assertEquals("Must the multiplication factor",0.01745329251994329576923691D, co.getMultiplicationFactor(), 0);

            unitString = "'";
            co = ucs.mountEquations(unitString);

            Assert.assertNotNull("Must return a converted object", co);
            Assert.assertEquals("Must the unit name","rad", co.getUnitName());
            Assert.assertEquals("Must the multiplication factor",0.00029088820866572159615395D, co.getMultiplicationFactor(), 0);

            unitString = "\"";
            co = ucs.mountEquations(unitString);

            Assert.assertNotNull("Must return a converted object", co);
            Assert.assertEquals("Must the unit name","rad", co.getUnitName());
            Assert.assertEquals("Must the multiplication factor",0.0000048481368110953599359D, co.getMultiplicationFactor(), 0);


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void invalidUnit(){
        try {
            var unitString = "miles";
            ucs.mountEquations(unitString);

            Assert.fail("Must not process with null unit string!");
        } catch (Exception e) {
            Assert.assertEquals("Unit not found!", e.getMessage());
        }
    }

    @Test
    public void multipleUnitsWithSigns(){
        try {
            String unitString = "minute+litre-hectare/degree*day";
            var co = ucs.mountEquations(unitString);
            var calculation = (60+0.001-10000.0/0.017453292519943295*86400.0);

            Assert.assertNotNull("Must return a converted object", co);
            Assert.assertEquals("Must the unit name","s+m³-m²/rad*s", co.getUnitName());
            Assert.assertEquals("Must the multiplication factor",calculation, co.getMultiplicationFactor(), 0);


          
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void multipleUnitsWithParentheses(){
        try {
            String unitString = "(minute+litre)-(hectare/degree*day)";
            var co = ucs.mountEquations(unitString);
            var calculation = ((60+0.001)-(10000.0/0.017453292519943295*86400.0));

            Assert.assertNotNull("Must return a converted object", co);
            Assert.assertEquals("Must the unit name","(s+m³)-(m²/rad*s)", co.getUnitName());
            Assert.assertEquals("Must the multiplication factor",calculation, co.getMultiplicationFactor(), 0);


          
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void invalidCalculateEquation(){
        try {
            var equation = "00+invalid";
            ucs.calculateEquation(equation);

            Assert.fail("Shouldn't validate an invalid equation!");
        } catch (Exception e) {
            Assert.assertEquals("It was not possible to perform the equation!", e.getMessage());
        }
    }

    @Test
    public void invalidGetSi(){
        try {
            var unit = "00";
            ucs.getSi(unit);

            Assert.fail("Do not return an invalid unit!");
        } catch (Exception e) {
            Assert.assertEquals("Unit not found!", e.getMessage());
        }
    }

    @Test
    public void invalidGetSiConverter(){
        try {
            var unit = "00";
            ucs.getSiConverter(unit);

            Assert.fail("Do not return an invalid unit!");
        } catch (Exception e) {
            Assert.assertEquals("Unit not found!", e.getMessage());
        }
    }
    
}
