package com.faitmain.domain.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface MemberMapper{

    ArrayList< HashMap<String,Object> > findAll();
}
