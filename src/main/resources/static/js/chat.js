function msgInit() {
    // 공지사항 메시지
    channel.onNotifyNotice = function(event) {
        write(event, 'notice')
    }

    // 유저 입장
    channel.onNotifyJoinUser = function(event) {
        write(event, 'join')
        call_chlid()
        
    }

    // 유저 나감
    channel.onNotifyLeaveUser = function(event) {
        write(event, 'leave')
        setTimeout(function() {
        call_chlid()
		}, 1000)
    }

    // 유저 추방
    channel.onNotifyKickUser = function(event) {
        write("'<font color='blue'><b>" + event.nickName + "</b></font>' 님이 채팅방에서 추방되었습니다.");
        call_chlid()
        console.log("갱신완료")

    }

    // 유저 추방 해제
    channel.onNotifyUnkickUser = function(event) {
        write("'<font color='blue'><b>" + event.nickName + "</b></font>' 님이 채팅방에서 추방 해제되었습니다.");
    }

    // 글쓰기 제한
    channel.onNotifyMuteUser = function(event) {
        write("'<font color='blue'><b>" + event.nickName + "</b></font>' 님의 글쓰기가 제한되었습니다.");
    }

    // 글쓰기 제한 해제
    channel.onNotifyUnmuteUser = function(event) {
        write("'<font color='blue'><b>" + event.nickName + "</b></font>' 님의 글쓰기가 제한 해제되었습니다.");
    }
}