


function cartAlert(result) {
    if (result == '0') {
        alert("장바구니에 추가하지 못했습니다.");
    } else if (result =='1'){
        alert("장바구니에 추가되었습니다.")
    }else if (result == '2') {
        alert("장바구니에 이미 추가되어있습니다.")
    }else if (result == '5') {
        alert("로그인이 필요합니다.")
    }
}