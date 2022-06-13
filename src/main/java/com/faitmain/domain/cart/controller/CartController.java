package com.faitmain.domain.cart.controller;

import com.faitmain.domain.cart.domain.Cart;
import com.faitmain.domain.cart.service.CartService;
import com.faitmain.domain.user.domain.User;
import com.faitmain.global.common.Search;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping( "/cart" )
@Slf4j
public class CartController{

    @Autowired
    private CartService cartService;

    @PostMapping( "/add" )
    @ResponseBody
    public String addCartPOST( Cart cart , HttpServletRequest request ) throws Exception{

        /* 로그인 체크 */
        HttpSession session = request.getSession();
        User user = ( User ) session.getAttribute( "user" );
        if ( user == null ) {
            return "5";
        }
        int result = cartService.addCart( cart );
        return String.valueOf( result );
    }


    @GetMapping( "/{buyerId}" )
    public String cartPageGET( @PathVariable String BuyerId , Model model ) throws Exception{
        model.addAttribute( "cartInfo" , cartService.getCartList( BuyerId ) );
        return "/cart";
    }

    /* 장바구니 수량 수정 */
    @PostMapping( "/update" )
    public String updateCartPOST( Cart cart ) throws Exception{
        cartService.updateProductOrderCount( cart );
        return "redirect:/cart/" + cart.getBuyerId();
    }
}
