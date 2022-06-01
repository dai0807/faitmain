package com.faitmain.domain.example.service;

import com.faitmain.domain.example.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MemberService{

    @Autowired
    MemberMapper memberMapper;

    public ArrayList< HashMap< String, Object > > findAll(){
        return memberMapper.findAll();
    }
}
