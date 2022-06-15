$(function () {

    let productPrice = Number('[[${ product.productPrice }]]');
    let productQuantity = Number('[[${ product.productQuantity }]]');

    // 수량 버튼 조작
    let quantity = $(".quantity_input").val();
    $(".plus_btn").on("click", function () {
        if (quantity < productQuantity) {
            $(".quantity_input").val(++quantity);
            $("#totalPrice").text(Number($("#totalPrice").text()) + productPrice);
        } else {
            alert("최대 수량입니다!");
        }
    });

    $(".minus_btn").on("click", function () {
        if (quantity > 1) {
            $(".quantity_input").val(--quantity);
            $("#totalPrice").text(Number($("#totalPrice").text()) - productPrice);
        }
    });

    $("#addCart").on("click", function () {

        console.log("product : " + $("#getProductForm").serialize());

        let user = '[[${ session.user }]]';
        console.log("session : " + user);

        if (user == null) { // 로그인 안했을 때
            self.location = "/user/login";
        } else { // 로그인 했을 때

            if ($("#totalPrice").text() == '0') {
                alert("옵션을 선택해주세요");
                return;
            }

            $.ajax({
                url: "/cart/add", // 주소
                //data: JSON.stringify(jsonData), // 전송 데이터
                data: $("#getProductForm").serialize(), type: "POST", // 전송 타입
                dataType: "JSON", // 응답 받을 데이터 타입
                //contentType: "application/json; charset=utf-8",
                success: function (result) {
                    console.log("result : " + result);
                    if (result == 2) {
                        alert("중복된 상품을 제외하고 장바구니에 추가되었습니다.");
                    } else {
                        alert("장바구니에 추가되었습니다.");
                    }
                    self.location = "/cart/user01@naver.com";
                }, //요청 실패 시 이벤트
                //응답 status code가 2xx가 아닌 경우
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);  //응답 메시지
                    console.log(textStatus); //"error"로 고정인듯함
                    console.log(errorThrown);
                }
            });
        }
    });

    $("#addOrder").on("click", function () {

        let orderLink;

        if("${session.user} != null")
        orderLink = '[[@{/order/{buyerId}(buyerId=${session.user.id})}]]';


        if (orderLink) {
            console.log(orderLink);

            if ($("#totalPrice").text() == '0') {
                alert("옵션을 선택해주세요");
            }

            $("#getProductForm").attr("action", orderLink);
            $("#getProductForm").submit();

        } else {
            self.location = "/user/login";
        }

    });


});


/* 옵션 */
$("select[name=options]").change(function () {


    let productNumber = $(this).val();
    let optionQuantity = Number($("select[name=options] option:selected").attr("value1"));
    let productName = $("select[name=options] option:selected").text();
    let productPrice = Number('[[${ product.productPrice }]]');
    let productMainImage = '[[${ product.productMainImage }]]';
    var optionIndex = 0;

    console.log($(this).val()); //value값 가져오기
    console.log($("select[name=options] option:selected").attr("value1")); //value값 가져오기
    console.log($("select[name=options] option:selected").text()); //text값 가져오기
    //$("select[name=options] option:selected").attr('disabled', 'true');

    console.log("optionQuantity : " + optionQuantity);
    var list = [];
    $(".selectName").each(function (index, item) {
        list.push($(item).text());
    });

    console.log("selectNames : " + list[0]);

    if (list.includes(productName)) {

        alert("이미 선택된 상품입니다");

    } else {

        $("#totalPrice").text(Number($("#totalPrice").text()) + productPrice);

        $(".optionArea").append("<div>" + "<span class='selectName'>" + productName + "</span>" + "<button type='button' class='optionQuantity_minus_btn'>-</button>"

            + "<input type='text' class='quantity_input' style='width:100px;' value='1' readonly name='orderPageProductList[" + optionIndex + "].productOrderCount' />" + "<button type='button' class='optionQuantity_plus_btn'>+</button>" + "<button type='button' style='float:right;' id='optionDelBtn' class='btn-btn dark'>" + "삭제" + "</button>" + "<input type='hidden' value='" + productNumber + "' name='orderPageProductList[" + optionIndex + "].productNumber' />" + "<input type='hidden' value='" + productName + "' name='orderPageProductList[" + optionIndex + "].productName'/>" + "<input type='hidden' value='" + productPrice + "' name='orderPageProductList[" + optionIndex + "].productPrice'/>" + "<input type='hidden' value='" + productMainImage + "' name='orderPageProductList[" + optionIndex + "].productMainImage'/>"

            + "<input type='hidden' value='" + optionQuantity + "' class='optionQuantity' />" + "</div>");
        optionIndex++;
    }

});

/* 수량버튼 */
$(document).on("click", ".optionQuantity_plus_btn", function () {
    let quantity = Number($(this).parent("div").find(".quantity_input").val());
    let maxQuantity = Number($(this).parent("div").find(".optionQuantity").val());
    console.log("quantity : " + quantity);
    console.log("maxQuantity : " + maxQuantity);
    console.log("result : " + quantity < maxQuantity);
    if (quantity < maxQuantity) {
        $(this).parent("div").find(".quantity_input").val(++quantity);
        $("#totalPrice").text(Number($("#totalPrice").text()) + productPrice);
    } else {
        alert("수량 초과");
    }
});

$(document).on("click", ".optionQuantity_minus_btn", function () {
    let quantity = $(this).parent("div").find(".quantity_input").val();
    if (quantity > 1) {
        $(this).parent("div").find(".quantity_input").val(--quantity);
        $("#totalPrice").text(Number($("#totalPrice").text()) - productPrice);
    }
});

$(document).on("click", "#optionDelBtn", function () {
    let quantity = Number($(this).parent("div").find(".quantity_input").val());
    let totalPrice = $("#totalPrice").text();
    totalPrice = totalPrice - (productPrice * quantity);
    if (totalPrice < 0) {
        totalPrice = 0;
    }
    console.log("totalPrice : " + totalPrice);

    $(this).parent().remove();
    $("#totalPrice").text(totalPrice);
    optionIndex--;
});
