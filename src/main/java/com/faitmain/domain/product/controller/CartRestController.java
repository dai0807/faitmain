package com.faitmain.domain.product.controller;

import com.faitmain.domain.product.domain.Cart;
import com.faitmain.domain.product.service.CartService;
import com.faitmain.domain.product.service.ProductService;
import com.faitmain.domain.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping( "/cart/*" )
@Slf4j
public class CartRestController{

    @Autowired
    @Qualifier( "cartServiceImpl" )
    private CartService cartService;

    @Autowired
    @Qualifier( "productServiceImpl" )
    private ProductService productService;


    public CartRestController(){
        log.info( "Controller = {} " , CartRestController.class );
    }

    @PostMapping( "json/addCart" )
    public Map<String, Boolean> addCart( @RequestBody Cart cart ) throws Exception{

        log.info( "/cart/json/addCart" );

        boolean result = false;

        Cart prevCart = cartService.getCart( cart );

        if ( prevCart == null ) {
            cartService.addCart( cart );
            result = true;
        }

        return Collections.singletonMap( "success" , result );
    }

    @PostMapping( "json/updateCart" )
    public Map<String, Boolean> updateCart( @RequestBody Cart cart ) throws Exception{

        log.info( "/cart/json/updateCart" );

        boolean result = false;

        int quantity = productService.getProductQuantity( cart.getCartProduct().getProductNumber() );

        if ( cart.getCartQuantity() <= quantity ) {
            cartService.updateCart( cart );
            result = true;
        }

        return Collections.singletonMap( "success" , result );
    }

    @GetMapping( "json/deleteCart/{cartNumber}" )
    public Map<String, Boolean> delete( @PathVariable int cartNumber ) throws Exception{

        log.info( "/cart/json/deleteCart" );

        cartService.deleteCart( cartNumber );

        return Collections.singletonMap( "success" , true );
    }

    @PostMapping( "/cart" )
    public int cart( HttpSession session , HttpServletRequest request , HttpServletResponse response , Cart cart ) throws Exception{
        log.info( " productNumber = {} " , cart.getCartProduct() );
        Cookie cookie = WebUtils.getCookie( request , "cartCookie" );

        //비회원장바구니 첫 클릭시 쿠키생성
        if ( cookie == null && session.getAttribute( "member" ) == null ) {
            String ckid = RandomStringUtils.random( 6 , true , true );
            Cookie cartCookie = new Cookie( "cartCookie" , ckid );
            cartCookie.setPath( "/" );
            cartCookie.setMaxAge( 60 * 60 * 24 );
            response.addCookie( cartCookie );
            cart.setCartCookieId( ckid );
            this.cartService.addCart( cart );

            //비회원 장바구니 쿠키생성 후 상품추가
        } else if ( cookie != null && session.getAttribute( "member" ) == null ) {

            String ckValue = cookie.getValue();
            cart.setCartCookieId( ckValue );

            //장바구니 중복제한
            //쿠키시간 재설정해주기
            cookie.setPath( "/" );
            cookie.setMaxAge( 60 * 60 * 24 );
            response.addCookie( cookie );
            cartService.addCart( cart );

            //회원 장바구니 상품추가
        } else if ( session.getAttribute( "member" ) != null ) {
            User user = ( User ) session.getAttribute( "member" );
        }
        cartService.addCart( cart );

        return 1;
    }

    // 로그인 POST
    @PostMapping( "/cartOnLogin" )
    private String cartOnLogin( User user, HttpSession session, HttpServletRequest request, HttpServletResponse response,
    RedirectAttributes rttr, Model model ) throws Exception{



        return "/main/index";
    }
}






















































































