// <script src = "https://cdn.bootpay.co.kr/js/bootpay-3.3.1.min.js" type = "application/javascript"></script>

var order = [[${insertOrder}]] // 서버의 주문 값
var product = [[${product}]]  // 주문한 상품

$('#charge_kakao').on('click',function (){
    //실제 복사하여 사용시에는 모든 주석을 지운 후 사용하세요
    BootPay.request({
        price: '135315', //실제 결제되는 가격
        // 관리자로그인 -> 결제설치 -> 인증키 및 보안 -> WEB Application ID
        application_id: "62a0dc64e38c3000235ae079",
        name: '테스트', //결제창에서 보여질 이름
        pg: 'kakao',
        method: 'easy', //결제수단, 입력하지 않으면 결제수단 선택부터 화면이 시작합니다.
        show_agree_window: 0, // 부트페이 정보 동의 창 보이기 여부
        items: [
            {
                item_name: product.name, //상품명
                qty: 1, //수량
                unique: product.productNumber.toString(), //해당 상품을 구분짓는 primary key
                price: product.price, //상품 단가
            }
        ],
        user_info: [
            {
                username: '사용자 이름',
                email: '사용자 이메일',
                addr: '사용자 주소',
                phone: '010-1234-4567'
            }
        ],
        order_id: order.orderNumber, //고유 주문번호로, 생성하신 값을 보내주셔야 합니다.
    }).error(function (data) {
        //결제 진행시 에러가 발생하면 수행됩니다.
        console.log(data);
        location.replace("pay/delete?id="+order.id)
    }).cancel(function (data) {
        //결제가 취소되면 수행됩니다.
        console.log(data);
        location.replace("pay/delete?id"+order.id)
    }).close(function (data) {
        // 결제창이 닫힐때 수행됩니다. (성공,실패,취소에 상관없이 모두 수행됨)
        console.log(data);
    }).done(function (data) {
        //결제가 정상적으로 완료되면 수행됩니다
        //비즈니스 로직을 수행하기 전에 결제 유효성 검증을 하시길 추천합니다.
        location.replace("pay/confirm?receipt_id=" + data.receipt_id);
        console.log(data);
    });
});










$('#charge_kakao').click(function () {
    // getter
    var IMP = window.IMP;
    IMP.init('imp76668016');
    // var money = $('input[name="cp_item"]:checked').val();
    console.log(money);

    IMP.request_pay({
        pg: 'kakao',
        merchant_uid: 'merchant_' + new Date().getTime(),
        name: '주문명 : 주문명 설정',
        amount: '9876',
        buyer_email: 'iamport@siot.do',
        buyer_name: '구매자이름',
        buyer_tel: '010-1234-5678',
        buyer_addr: '인천광역시 부평구',
        buyer_postcode: '123-456'
    }, function (rsp) {
        console.log(rsp);
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '고유ID : ' + rsp.imp_uid;
            msg += '상점 거래ID : ' + rsp.merchant_uid;
            msg += '결제 금액 : ' + rsp.paid_amount;
            msg += '카드 승인번호 : ' + rsp.apply_num;
            $.ajax({
                type: "GET",
                url: "/user/mypage/charge/point", //충전 금액값을 보낼 url 설정
                data: {
                    "amount" : money
                },
            });
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
        document.location.href="/user/mypage/home"; //alert창 확인 후 이동할 url 설정
    });
});


function requestPay() {

    // 결제 금액, 구매자의 이름, 이메일
    const buyerName = $('input[name="buyerName"]').val();
    const receiverAddress1 = $('input[name="receiverAddress1"]').val();
    const receiverAddress2 = $('input[name="receiverAddress2"]').val();
    const receiverAddress3 = $('input[name="receiverAddress3"]').val();
    const amonut = $('input[name="amount"]').val();

    const IMP = window.IMP;
    IMP.init('imp76668016');
    IMP.request_pay({ // param
        pg: "kakaopay.TC0ONETIME",
        // pay_method: "card",
        merchant_uid: 'cart_' + new Date().getTime(),
        amount: amonut,
        buyer_name: buyerName,
        buyer_addr: receiverAddress1 +" "+ receiverAddress2,
        buyer_postcode: receiverAddress3,
    }, function (rsp) { // callback

        $.ajax({
            type: 'POST',
            url: '/verifyIamport/' + rsp.imp_uid,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            }
        }).done(function (result) {

            // rsp.paid_amount와 result.response.amount(서버 검증) 비교 후 로직 실행
            if (rsp.paid_amount === result.response.amount) {
                alert("결제가 완료되었습니다.");
                $.ajax({
                    type: 'POST',
                    url: '/lecture/payment',
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(header, token);
                    }
                }).done(function () {
                    window.location.reload();
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                })
            } else {
                alert("결제에 실패했습니다." + "에러코드 : " + rsp.error_code + "에러 메시지 : " + rsp.error_message);

            }
        })
    });
}

