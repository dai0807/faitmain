package com.faitmain.domain.example.service;

import com.faitmain.domain.example.domain.Member;
import com.faitmain.domain.example.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MemberService{
    MemberMapper memberMapper;

    public MemberService( MemberMapper memberMapper ){
        this.memberMapper = memberMapper;
    }

    public ArrayList< HashMap< String, Object > > findAll(){
        return memberMapper.findAll();
    }

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
