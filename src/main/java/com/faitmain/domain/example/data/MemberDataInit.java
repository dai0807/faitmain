package com.faitmain.domain.example.data;

import com.faitmain.domain.example.domain.Member;
import com.faitmain.domain.example.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@RequiredArgsConstructor
public class MemberDataInit{

    private final MemberMapper memberMapper;

    @PostConstruct
    public void init(){
        memberMapper.createMember( new Member( 1 , "홍길동" ) );
    }
}
