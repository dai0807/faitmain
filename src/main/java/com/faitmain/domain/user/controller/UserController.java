package com.faitmain.domain.user.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.faitmain.domain.live.service.LiveService;
import com.faitmain.domain.product.domain.Product;
import com.faitmain.domain.product.service.ProductService;
import com.faitmain.domain.user.domain.StoreApplicationDocument;
import com.faitmain.domain.user.domain.User;
import com.faitmain.domain.user.service.ApiService;
 import com.faitmain.global.util.security.SecurityUserService;
import com.faitmain.domain.user.service.UserSerivce;
import com.faitmain.global.common.MiniProjectPage;
import com.faitmain.global.common.Search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping( "/user/*" )
public class UserController{


//    @Autowired
//    private BCryptPasswordEncoder pwdEncoder;


    // Field
    @Autowired
    @Qualifier( "userServiceImpl" )
    private UserSerivce userSerivce;

    @Autowired
    @Qualifier( "productServiceImpl" )
    private ProductService productService;

    @Autowired
    @Qualifier( "liveServiceImpl" )
    private LiveService liveService;

    @Autowired
    @Qualifier( "apiServiceImpl" )
    private ApiService apiServiceImpl;

// 보안을 위해 사용함 
    @Autowired
    private PasswordEncoder pwdEncoder;
    
     @Autowired
    private SecurityUserService securityUserService;
    
    
    public UserController(){
     //   log.info( "Controller = {} " , this.getClass() );
    }

  
     // Authentication 인증  ,  접근 주체(Principal)  , 권한(Authorization) ,인가(Authorize)
  // 1번    
  //3      Authentication 인터페이스에서 getPrincipal 해서  securityUser에 담기 
     @GetMapping("test")
     public String test( Authentication authentication , Model model ){
         System.out.println("====test======");
         
         //Authentication 클래스 안에  getPrincipal 를 사용해서 그 안에 있는  SecurityUserService 를 가져옵니다.
         //SecurityUserService 안에 각종 인증 정보가 들어 있음
         
         SecurityUserService securityUserService = ( SecurityUserService )authentication.getPrincipal() ;
         
         System.out.println("==securityUser="+ securityUserService ); //
        
         System.out.println("==securityUser.getUser()="+ securityUserService.getUser());
                 
         System.out.println("==User="+ securityUserService.getUser() );
        
         model.addAttribute("user" , securityUserService.getUser() ) ;
         
         System.out.println("====test==끝====");

          return "/user/test" ;
          
          
       }
  
     @GetMapping("testrun")
     public String test(){
    	 log.info(" testRun --- ");         
          return "/user/test" ;
       }    
     
     
     
  //    Context Holder 에 들어가서 인증 정보를 가져옴  , User 정보에 접근 
  // SecurityContextHolder를 통해 가져오는 방법 
      @GetMapping("/getMyInfo1")
   
      public String getMyInfo(Model model){
          System.out.println("====getMyInfo1=시작=====");

//          Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();   //principal 에 사용자 인증 정보 담음
// 
//          //Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
//          System.out.println("====getMyInfo1==== "+ principal);
//          System.out.println("====getMyInfo1==== "+ principal.toString());
//          SecurityUserService securityUser = (SecurityUserService)principal ;
//          
//          System.out.println(   "securityUser "+ securityUser  );
//          System.out.println(   " securityUser.getUser()   "+  securityUser.getUser()  );
//       
//           
//          return  securityUser.getUser().toString() ;

      
  		SecurityUserService securityUserService = ( SecurityUserService )SecurityContextHolder.getContext().getAuthentication().getPrincipal();   //principal 에 사용자 인증 정보 담음
      	 User  user = securityUserService.getUser();
      	 model.addAttribute("user" , user) ;
      	 
      return "/user/test";
      
      }   
     
 //
     @GetMapping("test2")
     public String test2( @AuthenticationPrincipal SecurityUserService securityUserService , Model model ){
         System.out.println("====test======");

         //Authentication 클래스 안에  getPrincipal 를 사용해서 그 안에 있는  SecurityUserService 를 가져옵니다.
         //SecurityUserService 안에 각종 인증 정보가 들어 있음
         System.out.println("==securityUser="+ securityUserService.getUser() ); // Security 용 Usr 임
         model.addAttribute("user" , securityUserService.getUser() ) ;

          return "/user/test" ;


       }
  //  @AuthenticationPrincipal 은 인증이후 편의적으로 현재 인증된 세션유저를 가져오기 위해
  //  @AuthenticationPrincipal 어노테이션을 통해 UserDetails 인터페이스를 구현한 유저 객체를 SecurityUser를 주입할때 사용






     // 권한이 맞지 않을때 감
     @GetMapping("accessDenied")
     public String accessDenied(){

    	 log.info("  accessDenied:: 페이지 ::: 권한이 맞지 않아서 접근 할 수 없습니다.  " );
   
          return "/user/accessDenied" ;
       }
     
     
      
     
    
    //1번 Controller 의 메서드에서 매개변수 입력 바기
    

  
    //2번///////
    
    //principal 은 getName 만 지원 name 만 뽑아옴 
    // Principal 객체에 접근해 정보를 가져온다. principal은 getName 만 가져 올 수 있다.   용하면 getName으로 id찾음 
    @GetMapping("/getMyInfo2") 
    @ResponseBody 
    public String currentUserName(Principal principal) { 
    	System.out.println("==========currentUserName시작 ===========");
    	System.out.println("==========currentUserName끝 ==========="+principal);
 
    	System.out.println("==========currentUserName끝 ==========="+principal.getName());
	
        return principal.getName(); 
    }  
  
    
 
    
    
  //admin 페이지 리스트 보이기    , 스토어 신청서 리스트  
	   @GetMapping("getStoreApplicationDocumentList")
	   public String getStoreApplicationDocumentList(Model model ,@ModelAttribute Search search   )  throws Exception {
		 
		   if(search.getCurrentPage() == 0) {
				search.setCurrentPage(1);
			}
			search.setPageSize(100);
			
			Map<String, Object> searchMap = new HashMap<>();
			searchMap.put("searchKeyword", search.getSearchKeyword());
			searchMap.put("endRowNum",  search.getEndRowNum());
			searchMap.put("startRowNum",  search.getStartRowNum());
			searchMap.put("searchStatus", search.getSearchStatus());
			searchMap.put("searchCategory", search.getSearchCategory());
			searchMap.put("searchOrderName", search.getOrderName());
			
			System.out.println("Search : " + search);
			
			Map<String, Object> map = userSerivce.getStoreApplicationDocumentList(searchMap);
					
			MiniProjectPage resultPage = new MiniProjectPage( search.getCurrentPage(), ( Integer ) map.get( "totalCount" ) , 4, 10);
			
			log.info("resultPage : " + resultPage);
			
			log.info("list : " + ((List<Product>)map.get("list")).get(0));
			
			
			model.addAttribute("list", map.get("list"));
			model.addAttribute("resultPage", resultPage);
			model.addAttribute("search", search);	

			System.out.println("Search : " + search);
		 
		 
		   
			log.info("get :: addStore " );
	      
	   return "/admin/getStoreApplicationDocumentList";
	   }
	   
	  
	   
	   
	   @GetMapping( "login" )
	   public String longin( ){
	      
		   
		   log.info( " 컨트롤러 탐 login Page로 이동"  );
		   
	      return "/user/login";
	   }
	    //반드시 바꿀 것 
	   @PostMapping( "login" )
	   public String RESTlongin(  User loginuser,  HttpSession session ,Model model) throws Exception {
	      
		   log.info("LostController 탔어용 login Page 도착");
		   log.info("받은 유저 user 출력  :: {}" , loginuser);
//		   
		   String encPwd = pwdEncoder.encode(loginuser.getPassword());
		   loginuser.setPassword(encPwd);
		   log.info("암호화한 user PW :: {}" , loginuser.getPassword());
//		   
		   
 		   String result = "";
 		   result = userSerivce.getLogin(loginuser)+"" ; // id/ pw 값 있으면 1 없으면 0 ,,
		   log.info("받은 유저 result 출력  :: {}" , result);
 
 		   if(result.equals("1")) { //1 이면 로그인 된거임 
 			   User user = userSerivce.getUser(loginuser.getId()) ;   // 로그인 된 사람 정보 가져와서 회월탈퇴 값 있는지 검증 
 			   
			 			  System.out.println("너의 값은 무엇이냐" +user.getWithdrawalStatus()) ;
				 			   if( user.getWithdrawalStatus() ) { // true 는 회원 탈퇴
				 				   result="withdraw" ; //
				 				   
				 			      return result;
				    
				 			   }
				 			   
 			   
 			   
 			   log.info("{}의 로그인이 완료 되었습니다  " , user.getId());
 			   session.setAttribute("user", user) ; // user 정보 로그인
 			   
 			   
 		        Map<String, Object> map = new HashMap<>();

 		        map.put( "orderName" , "product_name DESC" );
 		        map.put( "startRowNum" , 1 );
 		        map.put( "endRowNum" , 5 );

 		        map = productService.getProductList( map );
 		        log.info( "after getProductList" );

 		        System.out.println( map );

 		        map.put( "liveList" , liveService.getLiveList().get( "liveList" ) );
 		        System.out.println( map );

 		        log.info( "after getLiveList" );

 		        model.addAttribute( "map" , map );

 		      
 		        return "/";

 			   
 			   
 		   }else {
 			   log.info("로그인 실패");
 			   
 		   }
 		   
 		   
		 
		   
 
 		   
	      return "/user/login";
	   }
	      	   
	   
	   //userList
	   @GetMapping("getUserlist")
	   public String getUserList  (Model model  ,@RequestParam (value="searchCondition", required = false)String searchCondition )throws Exception {
		
		   
		   log.info("getUserList  도착 !! ");
		 
		   Map<String, Object> searchMap = new HashMap<>();
		   
		   if( searchCondition != null) {
			   log.info("getUserList searchCondition ={}", searchCondition);

			   searchMap.put("searchCondition", searchCondition) ;
		   }
		   List<User> userList = userSerivce.getlist(searchMap) ;
		   for(User user : userList) {
			   System.out.println("getUserlist : 유저 출력"+user);
		   }
		   
		   
		   
		   model.addAttribute("userList" , userList) ;
		   
		   return "/admin/getUserList";
		   
	   }
	   

//로그인 ajax로 바꾸면서 RestController 탐	   
/*	   
	   @PostMapping( "login" )
	   public RedirectView longin( RedirectAttributes model , @ModelAttribute("user") User loginuser,  HttpSession session) throws Exception {
	      
		   log.info("Post login Page 도착");
		   log.info("user 출력  :: {}" , loginuser);
		   
 		   int result = 0;
 		  result = userSerivce.getLogin(loginuser) ; // id/ pw 값 있으면 1 없으면 0 ,,
 		   
 		   if(result == 1) {
 			   User user = userSerivce.getUser(loginuser.getId()) ;  
 			   log.info("{}의 로그인이 완료 되었습니다  " , user.getId());
 			   session.setAttribute("user", user) ; // user 정보 u로그인 
 		   }else {
 			   log.info("로그인 실패");
 			   
 		   }
		   log.info("Post login 끝 ");
		   
	        Map<String, Object> map = new HashMap<String, Object>();
	        
	        map.put("orderName", "product_name DESC");
			map.put("startRowNum", 1);
			map.put("endRowNum", 5);
			
			map = productService.getProductList(map);
	        log.info("after getProductList");

			map.put("liveList", liveService.getLiveList().get("liveList"));
			log.info("after getLiveList");
			
	        model.addFlashAttribute("map", map);
 		   
	      return new RedirectView("/");
	   }
	      
*/


    @GetMapping( "logout" )
    public RedirectView logout( HttpSession session , HttpServletRequest request ){


        log.info( "get :: logout    " );
        log.info( "  {} 의 로그아웃  " , ( ( User ) request.getSession( true ).getAttribute( "user" ) ).getId() );

        session.invalidate();

        return new RedirectView( "/" );
    }


    @GetMapping( "selectRegisterType" )
    public String selectRegisterType( ){

        log.info( "get :: selectRegisterType    " );

        return "/user/selectRegisterType";
    }


    @GetMapping( "addUser" )
    public String addUser( ){

        log.info( "get :: addUser " );

        return "/user/addUser";
    }


    @GetMapping( "addStore" )
    public String addStore( ){

        log.info( "get :: addStore " );

        return "/user/addStore";
    }


    @PostMapping( "addUser" )
    public RedirectView addUser( @ModelAttribute( "user" ) User user ) throws Exception{

        log.info( "addUser::들어온 user 결과  ::{}" , user );

//        String encPwd = pwdEncoder.encode( user.getPassword() );
//        user.setPassword( encPwd );
        user.setRole( "user" );
        
        String encPwd =pwdEncoder.encode(user.getPassword());
        user.setPassword(encPwd);
        log.info( "addUser::비밀번호 바꾼후 결과  ::{}" , user );
    
        int result = userSerivce.addUser( user );

        log.info( "restut {} = 1일때 회원가입 완료" , result );

        return new RedirectView( "/" );
    }


    // 최대한 ,,, 써머노트 구현해보자
    //MultipartHttpServletRequest 는 서버에서 오는 파일 받음
    @PostMapping( "addStore" )
    public RedirectView addStore( @ModelAttribute( "user" ) User user , MultipartHttpServletRequest mRequest ,
                                  @ModelAttribute( "storeApplicationDocument" ) StoreApplicationDocument storeApplicatDoc ) throws Exception{
        log.info( "::시작 :::addStore:: " );
        String encPwd =pwdEncoder.encode(user.getPassword());  //PW 암호화
        user.setPassword(encPwd);
        log.info( "addStore::들어온 user 결과  ::{}" , user );
        log.info( "addStore::들어온 storeApplicationDocument 결과  ::{}" , storeApplicatDoc );

        int addStoreresult = userSerivce.addStore( user , mRequest );
        log.info( "addStoreresult {}" , addStoreresult );

        int storeApplicatDocresult = userSerivce.AddStoreApplicationDocument( storeApplicatDoc );
        log.info( "addStoreresult {}" , storeApplicatDocresult );
//		   
//	
        log.info( "::끝 :::addStore:: " );

        return new RedirectView( "/" );
    }


    @GetMapping( "kakaoLogin" )
    //	public RedirectView kakaoLogin(@RequestParam(value = "code", required = false) String code , RedirectAttributes model , HttpSession session) throws Exception {
    public String kakaoLogin( @RequestParam( value = "code", required = false ) String code , Model model , HttpSession session ) throws Exception{

        // 사용자 로그인 및  동의 후 , 인가 코드를 발급받아 302 redirect를 통해  ,  이 메소드 도착함

        log.info( "##kakaoLogin## 페이지 도착 " );

        log.info( "##code {} ##" , code ); // 코드 출력

        // 사용자 정보를 가져오기 위하여 code로  access 토큰 가져오기 !
        String access_Token = apiServiceImpl.getKaKaoAccessToken( code );
        log.info( "##access_Token 가져옴 ::  {} ##" , access_Token );


        // 카카오 getUserInfo 에서 access_Token를 통하여  userInfo 가져오기
        Map<String, Object> userInfo = apiServiceImpl.getKakoUserInfo( access_Token );
        log.info( "##access_Token {} ##" , access_Token );
        log.info( "##email {} ##" , userInfo.get( "email" ) );

        String kakaouserId = ( String ) userInfo.get( "email" );
        User user = new User();
        user.setId( kakaouserId );
        user.setPassword( "12345" ); // 카카오 로그인시 비밀번호
        if ( userSerivce.getLogin( user ) == 0 ) {  // 카카로 로그인 ID가 우리 사이트에 존재 x
            log.info( "로그인한 카카오 아이디가 존재 하지 않습니다. " );

            if ( userSerivce.getUser( user.getId() ) != null ) {
                log.info( "이미 가입된 아이디가 있습니다. " );


            }


            model.addAttribute( "kakaouserId" , kakaouserId );

            return ( "/user/kakaoAdd" );


        } else {
            // 카카오 로그인시 ID가 우리 사이트에 존재 할때

            // Model 과 View 연결
            user = userSerivce.getUser( user.getId() );
            session.setAttribute( "user" , user );

        } // 존재 할때


        Map<String, Object> map = new HashMap<>();

        map.put( "orderName" , "product_name DESC" );
        map.put( "startRowNum" , 1 );
        map.put( "endRowNum" , 5 );

        map = productService.getProductList( map );
        log.info( "after getProductList" );

        System.out.println( map );

        map.put( "liveList" , liveService.getLiveList().get( "liveList" ) );
        System.out.println( map );

        log.info( "after getLiveList" );

        model.addAttribute( "map" , map );

        //  return new RedirectView("/");
        return "/";


    }


    // RedirectView longin( RedirectAttributes model ,
    //kakao회원 추가 가입
    @PostMapping( "kakaoaddUser" )
    public RedirectView kakaoaddUser( @RequestParam( value = "code", required = false ) String code , RedirectAttributes model ,
                                      @ModelAttribute( "user" ) User kuser , HttpSession session ) throws Exception{
        log.info( "##code {} ##" , code );
        log.info( "##kuser {} ##" , kuser );

        kuser.setRole( "user" );

        kuser.setPassword( "12345" ); //  패스워드 고정
        kuser.setJoinPath( "KAKAO" ); // 카카오는 K로 고정 , 자사는 H 로 고정

        log.info( "##kuser {} ##" , kuser );
        log.info( "회원가입이 완료 되었습니다. " );

        int result = userSerivce.addUser( kuser );
        log.info( "##kakaoUser 결과  {} ##" , result );

        session.setAttribute( "user" , kuser );


        Map<String, Object> map = new HashMap<>();

        map.put( "orderName" , "product_name DESC" );
        map.put( "startRowNum" , 1 );
        map.put( "endRowNum" , 5 );

        map = productService.getProductList( map );
        log.info( "after getProductList" );

        map.put( "liveList" , liveService.getLiveList().get( "liveList" ) );
        log.info( "after getLiveList" );

        model.addFlashAttribute( "map" , map );

        return new RedirectView( "/" );


    }

    //이것은 네이버 로그인 하고 구현하기
    @PostMapping( "naverUser" )
    public String naverUser( @RequestParam( value = "code", required = false ) String code , Model model , @ModelAttribute( "user" ) User kuser , HttpSession session ) throws Exception{

        /* TODO : 구현할 것 */
        return ( "redirect:/live/main.jsp" );


    }


    //UpdatePassword
    @GetMapping( "updatePassword" )
    public String updatePassword( @RequestParam( value = "id", required = false ) String id , Model model ){
        log.info( "##updatePassword {} ##" );


        if ( id == null ) {
      		SecurityUserService securityUserService = ( SecurityUserService )SecurityContextHolder.getContext().getAuthentication().getPrincipal();   //principal 에 사용자 인증 정보 담음
            id =  securityUserService.getUser().getId() ;
        
        }
        log.info( "updatePassword id :: {}  " + id );


        model.addAttribute( "id" , id );
        return ( "/user/updatePassword" );


    }


    // find Id Rest Control로 갈 운명
    @GetMapping( "findId" )
    public String findId( @ModelAttribute( "user" ) User user ){

        log.info( "###Stat###findId ={} ##" , user );


        return ( "/user/findUserId" );


    }

    //find PW Rest Control로 갈 운명
    @PostMapping( "findPw" )
    public String findPw( @ModelAttribute( "user" ) User user , Model model ) throws Exception{

        log.info( "##findPw {} ##" , user );


        Map<String, Object> map = new HashMap<>();
        map.put( "phoneNumber" , user.getPhoneNumber() );
        map.put( "id" , user.getId() );


        log.info( "findPW {}" , userSerivce.findUser( map ) );

        if ( userSerivce.findUser( map ) == 1 ) {

            model.addAttribute( "user" , user );


            return ( "forward:/user/updatePassword.jsp" );


        } else {
            return ( "forward:/main" );

        }


    }

    //유저 상세 정보
    //			//id가 있으면 list에서 온거 , 아니면 내 정보 조회에서 온 것
//getUser 유저 상세    // 변자기돈 getUSer시 아이디 날라가기 
    @GetMapping( "getUser" )
    public String getUser( Model model , @RequestParam( value = "id", required = false ) String id 
    		// , @AuthenticationPrincipal SecurityUserService securityUserService 
    		 ) throws Exception{

        log.info( " GestUSer에 옴 출력하라 id  {}" , id );
        User user = null;

        if ( id == null ) {
        	
    	  securityUserService = ( SecurityUserService )SecurityContextHolder.getContext().getAuthentication().getPrincipal();   //principal 에 사용자 인증 정보 담음
           user = securityUserService.getUser();
         
        	log.info(" 로그인한 유저   ={} ", securityUserService.getUser() );
        	
 
        } else {
            user = userSerivce.getUser( id );

        }

        if ( user.getRole().equals( "store" ) || user.getRole().equals( "storeX" ) ) {
            //롤이 스토어면 스토어 넘버 가져와서 user에 넣어라
            int storeNumber = userSerivce.getStoreApplicationDocumenNumber( user.getId() );
            user.setStoreApplicationDocumentNumber( storeNumber );
        }

        
 
        
        
        
        
        
        log.info( " 출력하라 getUSer {}" , user );


        model.addAttribute( "getuser" , user );
        return "/user/getUser";
    }

    //스토어 신청서 상세 보기
    @GetMapping( "getStoreApplicationDocument" )
    public String getStoreApplicationDocument( Model model , @RequestParam( "storeApplicationDocumentNumber" ) int storeApplicationDocumentNumber ) throws Exception{
        log.info( " getStoreApplicationDocument 에 옴 출력하라   {}" , storeApplicationDocumentNumber );

        StoreApplicationDocument storeApplicationDocument = userSerivce.getStoreApplicationDocument( storeApplicationDocumentNumber );
        log.info( "StoreApplicationDocument {}" , storeApplicationDocument );

        model.addAttribute( "StoreApplicationDocument" , storeApplicationDocument );

        return "/user/getStoreApplicationDocument";
    }


    //스토어 재 신청 Add
    @GetMapping( "addStoreApplication" )
    public String addStoreApplicationDocument( ){
        log.info( " start !!  addStoreApplicationDocument " );


        return "/user/addStoreApplicationDocument";
    }


    //스토어 재 신청 Add
    @PostMapping( "addStoreApplicationDocument" )
    public String addStoreApplicationDocument( Model model , @ModelAttribute( "storeApplicationDocument" ) StoreApplicationDocument storeApplicationDocument ) throws Exception{
        log.info( " addStoreApplicationDocument 에 옴 출력하라   {}" , storeApplicationDocument );

        int result = userSerivce.AddStoreApplicationDocument( storeApplicationDocument );
        log.info( "결과 에  출력하라   {}" , result );

        if ( result == 1 ) {

            int addStoreApplicationNumber = userSerivce.getStoreApplicationDocumenNumber( storeApplicationDocument.getId() );
            storeApplicationDocument.setStoreApplicationDocumentNumber( addStoreApplicationNumber );


        }


        model.addAttribute( "StoreApplicationDocument" , storeApplicationDocument );


        return "/user/getStoreApplicationDocument";
    }


}