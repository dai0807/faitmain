const vChatCloud = new VChatCloud();
let channel, userNick, userKey, channelKey = "",
    youtubeId;

var getParameters = function(paramName) {
    // 리턴값을 위한 변수 선언
    var returnValue;

    // 현재 URL 가져오기
    var url = location.href;

    // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');

    // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
    for (var i = 0; i < parameters.length; i++) {
        var varName = parameters[i].split('=')[0];
        if (varName.toUpperCase() == paramName.toUpperCase()) {
            returnValue = parameters[i].split('=')[1];
            return decodeURIComponent(returnValue);
        }
    }
};
channelKey = 'zrWskFNzrd-41toO2KQAc-20220604114542';

$(function() {
    if (getParameters('youtubeId') != undefined) {
        youtubeId = getParameters('youtubeId');
        $("#ytplayer").attr("src", "https://www.youtube.com/embed/" + youtubeId + "?autoplay=1&controls=0&mute=1&modestbranding=1&rel=0&loop=1" + youtubeId + "&loop=1");
    }

    let p = $('div.dim').show(),
        l = $('div.login').show(),
        c = $('div.chat_contents').hide();
    likeInif();
    //        cb = $('div.chat_bottom').hide();
    //        tc = $('article.title .close').hide();
    $('button.popupbtn', p).click(function() {
        console.log("click")
        let r = { nick: $('input#name', p).val() };
        if (r.nick) {
            $('div.bottom div.name').text(r.nick);
            joinRoom(channelKey, 'xxxxxxxx'.replace(/[xy]/g, function(a, b) { return (b = Math.random() * 16, (a == 'y' ? b & 3 | 8 : b | 0).toString(16)) }), r.nick, function(err, history) {
                if (err) {
                    openError(err.code, function() {
                        p.show();
                        l.show();
                        c.hide();
                        //cb.hide();
                        //tc.hide();
                        vChatCloud.disconnect();
                    });
                    p.show();
                    l.hide();
                    c.show();
                    //cb.show();
                    //tc.show();

                } else {

                    // 채팅영역에 글쓰기가 활성화될시 활성화(최신공지 한개만 남기기)
                    let flag = undefined;
                    if (typeof write == 'function') history && history.forEach(function(m) {
                        if (m.messageType == 'notice') {
                            if (flag == undefined) {
                                flag = true;
                                write(m, 'notice', 'history');
                            }
                        } else {
                            write(m, '', 'history');
                        }
                    });

                    p.hide();
                    c.show();
                    //cb.show();
                    //tc.show();

                    // 이벤트 바인딩 시작
                    chatInit();
                    personalInit();
                    msgInit();
                    getRoomInfo();
                    //likeInif();
                }
            });
        }
    });

    /*$('a.closebtn').click(function() {
        p.show();
        c.hide();
        //cb.hide();
        //tc.hide();
        likeEnd();
        vChatCloud.disconnect();
    })*/
})

function joinRoom(roomId, clientKey, nickName, callback) {
    // vchatcloud 객체
    channel = vChatCloud.joinChannel({
        roomId: roomId,
        clientKey: clientKey,
        nickName: nickName
    }, function(error, history) {
        $('div#content1 p').remove();
        if (error) {
            if (callback) return callback(error, null);
            return error;
        }
        if (callback) callback(null, history);
        // 채팅영역에 글쓰기가 활성화될시 활성화
        // setTimeout(function(){write("① 이벤트미션 하나<br>쇼핑 LIVE 채팅창에 응원 메시지 입력(●'◡'●)", "market")}, 5 * 1000);
        // setTimeout(function(){write("② 이벤트미션 두울<br>쇼핑 영상 댓글에 시청소감 또는 응원메시지 입력 :)", "market")}, 30 * 1000);
        // setInterval(function(){write("★라이브 커머스 이벤트! 댓글을 달면 선물이 내 품으로!★", "market")}, 5 * 60 * 1000);
        // if (typeof write == 'function') write("📢채팅 참여하고! ☕커피 한잔까지!<br>추첨을 통해 스타벅스 아메리카노를 드립니다!", 'notice');
    })
}

function openError(code, callback) {
    let p = $('div.errorpopup').hide();
    if (errMsg[code] == undefined) {
        $('p:nth-child(2)', p).text(code);
    } else {
        $('p:nth-child(2)', p).text(errMsg[code].kor);
    }
    $('a', p).off().click(function() { p.hide(); if (typeof callback == 'function') { callback() } });
    p.show();
}

// 채팅방 제목 (채팅방 입장시 제목 변경)
function getRoomInfo() {
    const api_url = "https://vchatcloud.com/api/openapi/getChatRoomInfo";
    let param = {
        "room_id": channelKey
    };
    $.post(api_url, param, function(data) {
        if (data.result_cd == 1) {
            $("#roomNm").append(data.param.room_nm);
        } else {
            console.log("조회 실패")
        }
    }, "json");
}




/* function openLayer(e) {
    let sWidth = window.innerWidth;
    let sHeight = window.innerHeight;
    let oWidth = $('.popupLayer').width();
    let oHeight = $('.popupLayer').height();
    let fWidth = $("#chat").offset().left;
    let fHeight = $("#chat").offset().top;
    let cHeight = $("#content1").height();
    // 레이어가 나타날 위치를 셋팅한다.
    let divLeft = e.clientX - fWidth;
    let divTop = e.clientY - fHeight;
    // 레이어가 화면 크기를 벗어나면 위치를 바꾸어 배치한다.
    if (divLeft + oWidth > sWidth) divLeft -= oWidth;
    if (divTop + oHeight > sHeight) divTop -= oHeight;
    if (divTop > (cHeight - oHeight)) {
        divTop = divTop - oHeight;
    }
    $('.popupLayer').data($(this).data()).css({
        "top": Math.max(0, divTop),
        "left": Math.max(0, divLeft),
        "position": "absolute",
        "z-index": 1
    }).show();
    $("#whisper").show();
} */