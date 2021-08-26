package com.converter.converter;

import org.junit.jupiter.api.Test;

public class test {

    @Test
    public void test(){
        String teste = "abc+def(ghi*jkl)";
        String[] splitted = teste.split("\\+|\\(|\\*|\\)");
        for (int i=0; i<splitted.length; i++){
            var valorVelho = splitted[i];
            var valorNovo = "gogogo";
            teste = teste.replaceAll(valorVelho, valorNovo);
        }
        System.out.println(teste);
    
    }
}