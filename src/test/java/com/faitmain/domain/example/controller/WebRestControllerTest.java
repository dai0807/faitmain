package com.faitmain.domain.example.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.POST;


@ExtendWith( SpringExtension.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.MOCK )
@AutoConfigureMockMvc
class WebRestControllerTest{

    @Autowired
    private MockMvc mvc;

    @Test
    void mybatis_Test() throws Exception{

        mvc.perform( ( RequestBuilder ) POST( "/api/v1/app/findAll" ) )
                .andExpect( status().isOk() )
                .andDo( print() );

    }
}