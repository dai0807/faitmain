package com.faitmain.domain.example.domain;

public class Response{

    private String resultCode;
    private Object res;

    public String getResultCode(){
        return resultCode;
    }

    public void setResultCode( String resultCode ){
        this.resultCode = resultCode;
    }

    public Object getRes(){
        return res;
    }

    public void setRes( Object res ){
        this.res = res;
    }
}
