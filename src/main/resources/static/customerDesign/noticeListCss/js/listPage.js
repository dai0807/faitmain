 
$(document).ready(function () {

	let form = $("#infoForm");
	let mForm = $("#modifyForm"); 
    let searchForm = $("#searchForm");
    let moveForm = $("#moveForm");	
 	/* 페이지 이동 버튼 */
   	 $(".pageInfo a").on("click", function(e){
 		
 		e.preventDefault();
         moveForm.find("input[name='pageNum']").val($(this).attr("href"));
         moveForm.attr("action", "/customer/listBoard");
         moveForm.submit();
 	});
	
	 /* 작거 검색 버튼 동작 */
    $("#searchForm button").on("click", function (e) {

        e.preventDefault();
        /* 검색 키워드 유효성 검사 */
        if (!searchForm.find("input[name='keyword']").val()) {
            alert("키워드를 입력하십시오");
            return false;
        }
        searchForm.find("input[name='pageNum']").val("1");
        searchForm.submit();

    });
	 
    $(".delete_btn").on("click", function (e) {

        e.preventDefault();

        let id = $(this).data("orderNumber");

        $("#deleteForm").find("input[name='orderNumber']").val(id);
        $("#deleteForm").submit();
    });
/* 목록 페이지 이동 버튼 */  
    $("#list_btn").on("click", function(e){
		form.find("#boardNumber").remove();
		form.attr("action", "/customer/listBoard");
		form.submit();
	});
/* 수정 하기 버튼 */
	$("#modify_btn").on("click", function(e){
		form.attr("action", "/customer/updateBoard");
		form.submit();
	});
/* 취소 버튼 */
    $("#cancel_btn").on("click", function(e){
        form.attr("action", "/customer/detailBoard");
        form.submit();
    });   	
	
/*	$(".move").on("click", function(e){
		e.preventDefault();
		
		moveForm.append("<input type='hidden' name='boardNumber' th:value='"+$(this).attr("th:href")+"'>");
		mveForm.attr("action", "/customer/detailBoard");
		moveForm.submit();
	});*/o
	
	 /* 목록 페이지 이동 버튼 */
    $("#list_btn").on("click", function(e){
        form.find("#bno").remove();
        form.attr("action", "/board/list");
        form.submit();
    });
    
    /* 수정 하기 버튼 */
    $("#modify_btn").on("click", function(e){
        mForm.submit();
    });
    
    /* 취소 버튼 */
    $("#cancel_btn").on("click", function(e){
        form.attr("action", "/board/get");
        form.submit();
    });    
}); // ready