var channelKey = "zrWskFNzrd-41toO2KQAc-20220604114542";

// login.js
const vChatCloud = new VChatCloud();
let channel, userNick, userKey; //, channelKey;

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


$(function() {
    // channelKey = getParameters('channelKey');
    let p = $('div.login').show();

    $('button.popupbtn', p).click(function() {
        let r = { nick: $('input#name', p).val() };
        if (r.nick) {
            joinRoom(channelKey, 'xxxxxxxx'.replace(/[xy]/g, function(a, b) { return (b = Math.random() * 16, (a == 'y' ? b & 3 | 8 : b | 0).toString(16)) }), r.nick, function(err, history) {
                if (err) {
                    console.log(err)
                    res.toastPopup((errMsg[err.code] == undefined) ? err.code : errMsg[err.code].kor);
                    vChatCloud.disconnect();
                } else {
                    p.hide();
                    $("#wrap > section > div > article.contents > div.webcam > div.cam-footer > p.roomtitle").text(channel.roomName);
                    // 이벤트 바인딩 시작
                    videoInit();
                }
            });
        }
    });

    $('.exit.btn_on').click(function() {
        exit(p)
    })

    $('#wrap > section > div > article.contents > div.webcam > div.cam-footer > p.present-btn').click(function() {
        if (channel) {
            channel.toggleRTCMedia('display')
        } else {
            res.toastPopup("로그인을 해주세요");
        }
    })
})

function exit(p) {
    if (channel) {
        var exit_chk = confirm('종료 하시겠습니까?')
        if (!exit_chk)
            return;

        $("#wrap > section > div > article.contents > div.webcam > div.cam-footer > p.roomtitle").text('')
        p.show();
        $('.cam-footer .cam-btn .mic').off("click.rtc")
        $('.cam-footer .cam-btn .cam').off("click.rtc")
        vChatCloud.disconnect();
        $("#likeCounter").text("0");
        channel = undefined;
    } else {
        res.toastPopup("로그인을 해주세요");
    }
}

function joinRoom(roomId, clientKey, nickName, callback) {
    // vchatcloud 객체
    channel = vChatCloud.joinChannel({
        roomId: roomId,
        clientKey: clientKey,
        nickName: nickName
    }, function(error, history) {
        if (error) {
            if (callback) return callback(error, null);
            return error;
        }
        callback(error, history);
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

// rtc.js
let res, myWrap, listWrap;

window.addEventListener('load', function() {
    // 리소스 로드
    if (res === undefined) {
        res = new resources('.toast', 400, 1000, 400);
    }
    myWrap = $('#wrap > section > div > article.contents > div.webcam > div.cam-area');
    listWrap = $('#wrap > section > div > article.contents > div.webcam > div.cam-area > div.cam-list');
});

function mic_on_off(item) {
    if (channel) {
        var chk = $(item).attr('class');
        var img = $(item).children('img')[0];
        var cam_mic = $('div[name=my_cam]').children('img')[0];
        if (chk == 'mic btn_on') {
            $(item).attr('class', 'mic btn_off');
            $(img).attr('src', 'https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/off_mic.png');
            $(cam_mic).show();
            res.toastPopup("마이크 꺼짐.");
        } else {
            $(item).attr('class', 'mic btn_on');
            $(img).attr('src', 'https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/on_mic.png');
            $(cam_mic).hide();
            res.toastPopup("마이크 켜짐.");
        }
    } else {
        res.toastPopup("로그인을 해주세요");
    }
}

function cam_on_off(item) {
    if (channel) {
        var chk = $(item).attr('class');
        var img = $(item).children('img')[0];
        var video = $('div[name=my_cam]').children('div.camvideo')[0];

        if (chk == 'cam btn_on') {
            $(item).attr('class', 'cam btn_off');
            $(img).attr('src', 'https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/off_cam.png');
            res.toastPopup("카메라 꺼짐.");
        } else {
            $(item).attr('class', 'cam btn_on');
            $(img).attr('src', 'https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/on_cam.png');
            res.toastPopup("카메라 켜짐.");
        }
    } else {
        res.toastPopup("로그인을 해주세요");
    }
}

function videoInit() {
    // 채널에 local video or audio 추가시
    channel.on('rtcLocalStreamAppend', function(event) {
        let stream = event.target;
        let html = $('div[name=my_cam]', myWrap);
        if (!html.length) {
            html = $(res.myVideo);
            myWrap.prepend(html);
        }
        let video = $('video', html)[0];
        video.srcObject = stream;
        channel.setRTCLocalMedia(video)
    });
    channel.on('rtcLocalStreamRemove', function(event) {
        let html = $('div[name=my_cam]', myWrap);
        if (html.length) {
            html.remove();
        }
    });
    channel.on('rtcRemoteStreamAppend', function(event) {
        let stream = event.target;
        let html = $(`div[name=${event.clientKey}]`, listWrap);
        if (!html.length) {
            html = $(res.remoteVideo).attr({ name: event.clientKey });
            listWrap.append(html);
            $('.cam-name p', html).html(event.client.nickName);
        }
        let video = $('video', html)[0];
        video.srcObject = stream;

        $('.nocam', html).toggleClass('active', (stream.getVideoTracks().length == 0));
        $('.nomic', html).toggleClass('active', (stream.getAudioTracks().length == 0));

        channel.setRTCRemoteMedia(video, event.clientKey)
    });
    channel.on('rtcRemoteStreamRemove', function(event) {
        let html = $(`div.camvideo-wrap[name=${event.clientKey}]`, listWrap);
        if (html.length) {
            html.remove();
        }
    });

    channel.on('rtcLocalAudioChanged', function(event) {
        console.log("Local audio", event)
        let is_mic = event.enable;
        let html = $('div[name=my_cam]', myWrap);
        $('.nomic', html).toggleClass('active', !is_mic);
        $('.cam-footer .cam-btn .mic').off('.rtc').on('click.rtc', function() {
            channel.toggleRTCAudioControl(!is_mic);
        })
        $('.cam-footer .cam-btn .mic').toggleClass('btn_on', is_mic).toggleClass('btn_off', !is_mic);
        $('.cam-footer .cam-btn .mic img').attr('src', is_mic ? 'https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/on_mic.png' : 'https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/off_mic.png');
    });

    channel.on('rtcLocalVideoChanged', function(event) {
        console.log("Local video", event)
        let is_cam = event.enable;
        let html = $('div[name=my_cam]', myWrap);
        $('.nocam', html).toggleClass('active', !is_cam);
        $('.camvideo video', html).css('display', is_cam ? '' : 'none');
        $('.cam-footer .cam-btn .cam').off('.rtc').on('click.rtc', function() {
            channel.toggleRTCVideoControl(!is_cam);
        })
        $('.cam-footer .cam-btn .cam').toggleClass('btn_on', is_cam).toggleClass('btn_off', !is_cam);
        $('.cam-footer .cam-btn .cam img').attr('src', is_cam ? 'https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/on_cam.png' : 'https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/off_cam.png');
    });

    channel.on('rtcRemoteAudioChanged', function(event) {
        console.log("Remote audio", event)
        let is_mic = event.enable;
        let html = $(`div.camvideo-wrap[name=${event.clientKey}]`, listWrap);
        $('.nomic', html).toggleClass('active', !is_mic);
    });

    channel.on('rtcRemoteVideoChanged', function(event) {
        console.log("Remote video", event)
        let is_cam = event.enable;
        let html = $(`div.camvideo-wrap[name=${event.clientKey}]`, listWrap);
        $('.nocam', html).toggleClass('active', !is_cam);
        $('.camvideo video', html).css({ 'display': is_cam ? '' : 'none' });
    });
}

// video 태그 리소스
class resources {
    constructor(target, in_fi, in_de, in_fo) {
        this.toastLayer = $(target);
        this.fi = in_fi;
        this.de = in_de;
        this.fo = in_fo;
    }
    get myVideo() {
        return '<!-- 내 비디오 --><div class="mycam" name="my_cam"><div class="camvideo"><video autoplay style="position: absolute;left: 0;top: 0;width: 100%;height: 100%;"></video><img src="https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/mycam-nocam.png" class="nocam" alt="영상없음"></div><img src="https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/mycam-nosound.png" class="nomic" alt="소리없음"><div class="cam-name"><p>나</p></div></div>';
    }
    get remoteVideo() {
        return '<div class="camvideo-wrap"><div class="camvideo"><video autoplay style="position: absolute;left: 0;top: 0;width: 100%;height: 100%;"></video><img src="https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/user.png" class="nocam" alt="영상없음"></div><img src="https://www.vchatcloud.com/chat-demo/iframe/iframe_rtc_2/img/webRTC/nosound.png" class="nomic" alt="소리없음"><div class="cam-name"><p>사용자 이름 노출 영역입니다.</p></div></div>';
    }
    toastPopup(msg) {
        this.toastLayer.finish().fadeIn(this.fi).delay(this.de).fadeOut(this.fo).text(msg);
    }
}