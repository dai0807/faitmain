$(function () {
	
	/****************** 필요 data *****************************/
	var productGroupNumber = $("#productGroupNumber").val();
	var productGroupName = $("#productGroupName").val();
	var productPrice = Number($("#productPrice").val());
	var productMainImage = $("#productMainImage").val();
	var productQuantity = Number($("#productQuantity").val());
	//console.log("productGroupNumber : " + productGroupNumber)
    /*********************************************************/
    
    let quantity = $(".quantity_input").val(); // 수량 input 값 저장

	/* 수량 +  */
    $(".plus_btn").on("click", function () { 
        if (quantity < productQuantity) {
            $(".quantity_input").val(++quantity);
            $("#totalPrice").text(Number($("#totalPrice").text()) + productPrice);
        } else {
            alert("최대 수량입니다!");
        }
    });

	/* 수량 - */
    $(".minus_btn").on("click", function () { 
        if (quantity > 1) {
            $(".quantity_input").val(--quantity);
            $("#totalPrice").text(Number($("#totalPrice").text()) - productPrice);
        }
    });

	/* 장바구니 담기 */
    $("#addCart").on("click", function () { 

        console.log("product : " + $("#getProductForm").serialize());
		
		let buyerId = $("input[name=buyerId]").val();
		console.log("buyerId : " + buyerId);
		
		if(buyerId == null){ // 로그인 안했을 때
			
			console.log("buyerId null");
			self.location = "/user/login";
			
		}else{ // 로그인 했을 때
			
			console.log("buyerId exist");
			
			if ($("#totalPrice").text() == '0') { // 옵션 선택 안했을 때
				alert("옵션을 선택해주세요");
				return;
			}

			$.ajax({
				url: "/cart/add", // 주소
				//data: JSON.stringify(jsonData), // 전송 데이터
				data: $("#getProductForm").serialize(),
				type: "POST", // 전송 타입
				dataType: "JSON", // 응답 받을 데이터 타입
				//contentType: "application/json; charset=utf-8",
				success: function (result) {
					
					console.log("result : " + result);
					
					let confirmText;
					
					if (result == 2) {
						
						confirmText = "중복된 상품을 제외하고 장바구니에 추가되었습니다.";
						
					} else {
						
						confirmText = "장바구니에 추가되었습니다.";
						
					}
					
					var selectRes = confirm(confirmText + "\n" + "장바구니로 이동하시겠습니까?");
					
					if(selectRes){ // 장바구니 이동
					
						let buyerId = $("input[name=buyerId]").val();
						
						console.log("buyerId : " + buyerId);
									
						self.location = "/cart/" + buyerId;
						
					}else{ }// 장바구니 이동 취소
					
				},
				//요청 실패 시 이벤트
				//응답 status code가 2xx가 아닌 경우 
				error: function (jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);  //응답 메시지
					console.log(textStatus); //"error"로 고정인듯함
					console.log(errorThrown);
				}
			});
		}
    });

	/* 주문하기 */
    $("#addOrder").on("click", function () {

		let orderLink = $("input[name=buyerId]").val();
		
		if (orderLink) { //로그인 했을 때
			
			console.log(orderLink);
			
			if ($("#totalPrice").text() == '0') { //옵션 선택 안했을 때
				alert("옵션을 선택해주세요");
			}
			
			$("#getProductForm").attr("action", "/order/" + orderLink); // 주소 세팅
			$("#getProductForm").submit(); // 주문 페이지로 이동
			
		} else { //로그인 안했을 때
			self.location = "/user/login";
		}
	});
	
	/* kakao 공유하기 */
	console.log("product : " + productMainImage);
	Kakao.init('9e7bfe9bda76f4fc82c74df2aa9c4c98');
	Kakao.isInitialized();
	console.log(Kakao.isInitialized());

	Kakao.Link.createDefaultButton({
		container: '#create-kakao-link-btn',
		objectType: 'commerce',
		content: {
			title: 'Fait Main',
			imageUrl:
				'http://192.168.0.90:8080/img/upload/' + productMainImage,
			link: {
				mobileWebUrl: 'http://192.168.0.90:8080/product/getProduct?productNumber=' + productGroupNumber,
				webUrl: 'http://192.168.0.90:8080/product/getProduct?productNumber=' + productGroupNumber,
			},
		},
		commerce: {
			productName: productGroupName,
			regularPrice: productPrice
		},
		buttons: [
			{
				title: '구매하기',
				link: {
					mobileWebUrl: 'http://192.168.0.90:8080/product/getProduct?productNumber=' + productGroupNumber,
					webUrl: 'http://192.168.0.90:8080/product/getProduct?productNumber=' + productGroupNumber,
				},
			},
			{
				title: '공유하기',
				link: {
					mobileWebUrl: 'http://192.168.0.90:8080/product/getProduct?productNumber=' + productGroupNumber,
					webUrl: 'http://192.168.0.90:8080/product/getProduct?productNumber=' + productGroupNumber,
				},
			},
		],
	})
	
	var optionIndex = 0;
	$("select[name=options]").change(function() {
		console.log($(this).val()); //value값 가져오기
		console.log($("select[name=options] option:selected").attr("value1")); //value값 가져오기
		console.log($("select[name=options] option:selected").text()); //text값 가져오기
		//$("select[name=options] option:selected").attr('disabled', 'true');


		let productNumber = $(this).val();
		let optionQuantity = Number($("select[name=options] option:selected").attr("value1"));
		let productName = $("select[name=options] option:selected").text();
		console.log("optionQuantity : " + optionQuantity);
		var list = [];
		$(".selectName").each(function(index, item) {
			list.push($(item).text());
		});

		console.log("selectNames : " + list[0]);

		if (list.includes(productName)) {

			alert("이미 선택된 상품입니다");

		} else {

			$("#totalPrice").text(Number($("#totalPrice").text()) + productPrice);

			$(".optionArea").append(
				"<div>"
				+ "<span class='selectName'>" + productName + "</span>"
				+ "<button type='button' class='optionQuantity_minus_btn'>-</button>"

				+ "<input type='text' class='quantity_input' style='width:100px;' value='1' readonly name='orderPageProductList[" + optionIndex + "].productOrderCount' />"
				+ "<button type='button' class='optionQuantity_plus_btn'>+</button>"
				+ "<button type='button' style='float:right;' id='optionDelBtn' class='btn-btn dark'>" + "삭제" + "</button>"
				+ "<input type='hidden' value='" + productNumber + "' name='orderPageProductList[" + optionIndex + "].productNumber' />"
				+ "<input type='hidden' value='" + productName + "' name='orderPageProductList[" + optionIndex + "].productName'/>"
				+ "<input type='hidden' value='" + productPrice + "' name='orderPageProductList[" + optionIndex + "].productPrice'/>"
				+ "<input type='hidden' value='" + productMainImage + "' name='orderPageProductList[" + optionIndex + "].productMainImage'/>"

				+ "<input type='hidden' value='" + optionQuantity + "' class='optionQuantity' />"
				+ "</div>"
			);
			optionIndex++;
		}

	});

	/* 수량버튼 */
	$(document).on("click", ".optionQuantity_plus_btn", function() {
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

	$(document).on("click", ".optionQuantity_minus_btn", function() {
		let quantity = $(this).parent("div").find(".quantity_input").val();
		if (quantity > 1) {
			$(this).parent("div").find(".quantity_input").val(--quantity);
			$("#totalPrice").text(Number($("#totalPrice").text()) - productPrice);
		}
	});

	$(document).on("click", "#optionDelBtn", function() {
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
});