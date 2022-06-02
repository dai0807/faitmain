package com.faitmain.domain.example.service;

import com.faitmain.domain.example.domain.Member;
import com.faitmain.domain.example.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Primary
public class MemberService{

    @Autowired
    MemberMapper memberMapper;

    public List< Member > getMemberList(){
        return memberMapper.getMemberList();
    }

    public Member getMember( int id ){
        return memberMapper.getMember( id );
    }

    public int createMember( Member member ){
        return memberMapper.createMember( member );
    }

    public int updateMember( Member member ){
        return memberMapper.updateMember( member );
    }

    public int deleteMember( int id ){
        return memberMapper.deleteMember( id );
    }

}
