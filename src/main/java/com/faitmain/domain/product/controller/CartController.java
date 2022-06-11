package com.faitmain.domain.product.controller;

import com.faitmain.domain.product.domain.Cart;
import com.faitmain.domain.product.service.CartService;
import com.faitmain.global.common.Search;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping( "/cart/" )
@Slf4j
public class CartController{

    @Autowired
    private CartService cartService;

    public CartController(){
        log.info( "Controller = {} " , CartController.class );
    }

    @PostMapping( "/cart/add" )
    @ResponseBody
    public String addCartPOST( Cart cart , HttpServletRequest request ){

        /* 로그인 체크 */

    }

    @GetMapping( "getCartList" )
    public String getCartList( @ModelAttribute Search search , @RequestParam( "userId" ) String userId , Model model ) throws Exception{

        log.info( "/cart/getCartList" );

        if ( search.getCurrentPage() == 0 ) {
            search.setCurrentPage( 1 );
        }

        search.setSearchKeyword( userId );
        search.setPageSize( 10 );

        Map<String, Object> map = cartService.getCartList( search );
/*				
		Page resultPage = new Page( search.getCurrentPage(), ( Integer ) map.get( "totalCount" ) , 4, 10);
		
		log.info("resultPage : " + resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
*/
        return "forward:/cart/listCart.jsp";

    }
}
