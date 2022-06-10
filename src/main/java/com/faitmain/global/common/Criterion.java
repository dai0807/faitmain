package com.faitmain.global.common;

import lombok.ToString;

@ToString
public class Criterion{

    /* 현재 페이지 번호 */
    private int pageNumber;

    /* 페이지 표시 개수 */
    private int pageAmount;

    /* 페이지 스킵 */
    private int skip;

    /* 검색 타입 */
    private String type;

    /* 검색 키워드 */
    private String keyword;

    /* 카테고리 코드 */
    private String categoryCode;

    /* 상품 번호 */
    private int productNumber;

    /* CRITERION 생성자 */
    public Criterion( int pageNumber , int pageAmount ){
        this.pageNumber = pageNumber;
        this.pageAmount = pageAmount;
        this.skip = ( pageNumber - 1 ) * pageAmount;
    }

    /* CRITERION 기본 생성자*/
    public Criterion(){
        this( 1 , 10 );
    }

    /* 검색 타입 데이터 배열 변환 */
    public String[] getTypeArray(){
        return type == null ? new String[] {} : type.split( "" );
    }

    public int getPageNumber(){
        return pageNumber;
    }

    public void setPageNumber( int pageNumber ){
        this.pageNumber = pageNumber;
        this.skip = ( pageNumber - 1 ) * this.pageAmount;
    }

    public int getPageAmount(){
        return pageAmount;
    }

    public void setPageAmount( int pageAmount ){
        this.pageAmount = pageAmount;
        this.skip = ( pageNumber - 1 ) * this.pageAmount;
    }

    public int getSkip(){
        return skip;
    }

    public void setSkip( int skip ){
        this.skip = skip;
    }

    public String getType(){
        return type;
    }

    public void setType( String type ){
        this.type = type;
    }

    public String getKeyword(){
        return keyword;
    }

    public void setKeyword( String keyword ){
        this.keyword = keyword;
    }

    public String getCategoryCode(){
        return categoryCode;
    }

    public void setCategoryCode( String categoryCode ){
        this.categoryCode = categoryCode;
    }

    public int getProductNumber(){
        return productNumber;
    }

    public void setProductNumber( int productNumber ){
        this.productNumber = productNumber;
    }
}
