// 좋아요 가져오기
function getLike() {

    const url = "https://vchatcloud.com/api/openapi/getLike";
    let param = {
        //"room_id": channel.roomId
        "room_id": channelKey
    };


    $.post(url, param, function(data) {
        if (data.result_cd == 1) {
            $('#likeCounter').html(data.like_cnt);
        } else {
            console.log("조회 실패")
        }
    }, "json");
}

let like_interval;

// 좋아요 동기화
function likeInif() {

    getLike();
    like_interval = setInterval(getLike, 5 * 60 * 1000);

    $('#sendLike').click(function(e) {
        const url = "https://vchatcloud.com/api/openapi/like";
        let param = {
            //"room_id": channel.roomId,
            "room_id": channelKey,
            "log_cnt": 1
        };
        $.post(url, param, function(data) {
            if (data.result_cd == 1) {
                $('#likeCounter').html(data.like_cnt);
            } else {
                console.log("조회 실패")
            }
        }, "json");
    })
}

// 동기화 종료
function likeEnd() {
    clearInterval(like_interval)
}