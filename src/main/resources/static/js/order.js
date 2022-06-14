/** 결제 **/


// 카드 결제
function paymentCard(data) {
    // 모바일로 결제시 이동페이지
    const pathName = location.pathname;
    const href = location.href;
    const m_redirect = href.replaceAll(pathName, "");

    IMP.init("imp99151903");

    IMP.request_pay({ // param
            pg: "html5_inicis",
            pay_method: data.payMethod,
            merchant_uid: data.orderNum,
            name: data.name,
            amount: data.amount,
            buyer_email: "",
            buyer_name: "",
            buyer_tel: data.phone,
            buyer_addr: data.deleveryAddress2 + " " + data.deleveryAddress3,
            buyer_postcode: data.deleveryAddress1,
            m_redirect_url: m_redirect,
        },
        function (rsp) { // callback
            if (rsp.success) {
                // 결제 성공 시 로직,
                data.impUid = rsp.imp_uid;
                data.merchant_uid = rsp.merchant_uid;
                paymentComplete(data);

            } else {
                // 결제 실패 시 로직,
            }
        });
}

// 계산 완료
function paymentComplete(data) {

    $.ajax({
        url: "/order/payment/complete",
        method: "POST",
        data: data,
    })
        .done(function (result) {
            messageSend();
            swal({
                text: result,
                closeOnClickOutside: false
            })
                .then(function () {
                    location.replace("/orderList");
                })
        }) // done
        .fail(function () {
            alert("에러");
            location.replace("/");
        })
}


/* 주소입력란 버튼 동작(숨김, 등장) */
function showAdress(className) {
    /* 컨텐츠 동작 */
    /* 모두 숨기기 */
    $(".addressInfo_input_div").css('display', 'none');
    /* 컨텐츠 보이기 */
    $(".addressInfo_input_div_" + className).css('display', 'block');

    /* 버튼 색상 변경 */
    /* 모든 색상 동일 */
    $(".address_btn").css('backgroundColor', '#555');
    /* 지정 색상 변경 */
    $(".address_btn_" + className).css('backgroundColor', '#3c3838');
    /* selectAddress T/F */
    /* 모든 selectAddress F만들기 */
    $(".addressInfo_input_div").each(function (i, obj) {
        $(obj).find(".selectAddress").val("F");
    });
    /* 선택한 selectAdress T만들기 */
    $(".addressInfo_input_div_" + className).find(".selectAddress").val("T");

}
