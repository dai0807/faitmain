 
$(document).ready(function () {

	let form = $("#infoForm");
	let mForm = $("#modifyForm"); 
   
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