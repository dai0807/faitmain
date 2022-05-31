package com.faitmain.domain.order.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class OrderControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testController(){

        String json = "{\"name\" : \"1234\"}";

//        mockMvc.perform();





























    }
}
