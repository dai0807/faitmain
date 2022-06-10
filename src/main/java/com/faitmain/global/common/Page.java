package com.faitmain.global.common;


import lombok.Data;

@Data
public class Page{

    /* 페이지 시작 번호 */
    private int pageStart;

    /* 페이지 끝 번호 */
    private int pageEnd;

    /* 이전, 다음 버튼 존재 유무 */
    private boolean next, prev;

    /* 행 전체 개수 */
    private int total;

    /* 현재페이지 번호(pageNumber), 행 표시 수(pageAmount), 검색 키워드(keyword), 검색 종류(type) */
    private Criterion criterion;



    /* 생성자(클래스 호출 시 각 변수 값 초기화) */
    public Page( Criterion criterion , int total ){

        /* cri, total 초기화 */
        this.criterion = criterion;
        this.total = total;

        /* 페이지 끝 번호 */
        this.pageEnd = ( int ) ( Math.ceil( criterion.getPageNumber() / 10.0 ) ) * 10;

        /* 페이지 시작 번호 */
        this.pageStart = this.pageEnd - 9;

        /* 전체 마지막 페이지 번호 */
        int realEnd = ( int ) ( Math.ceil( total * 1.0 / criterion.getPageAmount() ) );

        /* 페이지 끝 번호 유효성 체크 */
        if ( realEnd < pageEnd ) {
            this.pageEnd = realEnd;
        }

        /* 이전 버튼 값 초기화 */
        this.prev = this.pageStart > 1;

        /* 다음 버튼 값 초기화 */
        this.next = this.pageEnd < realEnd;

    }

    public int getPageStart(){
        return pageStart;
    }

    public void setPageStart( int pageStart ){
        this.pageStart = pageStart;
    }

    public int getPageEnd(){
        return pageEnd;
    }

    public void setPageEnd( int pageEnd ){
        this.pageEnd = pageEnd;
    }

    public boolean isNext(){
        return next;
    }

    public void setNext( boolean next ){
        this.next = next;
    }

    public boolean isPrev(){
        return prev;
    }

    public void setPrev( boolean prev ){
        this.prev = prev;
    }

    public int getTotal(){
        return total;
    }

    public void setTotal( int total ){
        this.total = total;
    }

    public Criterion getCri(){
        return criterion;
    }

    public void setCri( Criterion criterion ){
        this.criterion = criterion;
    }



}