package com.converter.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) 
@SpringBootTest
@AutoConfigureMockMvc
public class ConverterTest {
    String PATH = "/units";

    @Autowired
    MockMvc mockMvc;


    @Test
    public void findOneParameter() throws Exception{
        try {
            String units = "minute";
            // mockMvc.perform(get(PATH).param("unitString", units)).andExpect(status().isOk());

            // units = "degree";
            // mockMvc.perform(get(PATH).param("unitString", units)).andExpect(status().isOk());

            // units = "litre";
            // mockMvc.perform(get(PATH).param("unitString", units)).andExpect(status().isOk());

            units = "false";
            mockMvc.perform(get(PATH).param("unitString", units)).andExpect(status().isBadRequest());
            
        } catch (Exception e) {
         throw new Exception(e.getMessage());
        }
            


    }
   
}