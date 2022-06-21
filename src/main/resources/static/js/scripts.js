function openProductView(productNumber){
	var win = window.open('/product/getProduct?productNumber='+productNumber, '_self');
	win.focus();
}

$(function() {
			
			var channelKey = $('#channelKey').val();
			var storeId = $('#storeId').val();
			
		    function videoInit() {
					
		    	channel.on("rtcRemoteStreamRemove", function(event){
		    		console.log("rtcRemoteStreamRemove 발동");
		    		let html = $('#remote_cam > video');
		    		
		    		html.remove();
		    	});
		    	
		    	  // 로컬 접속 시
		    	channel.on("rtcRemoteStreamAppend", function (event) {
		    			 console.log(event.clientKey);
		    			 storeId = $('#storeId').val();
		    			if(event.clientKey == storeId){
				    	   	remoteVideo = document.createElement("video");
				    	    let stream = event.target;
				    	    remoteVideo.srcObject = stream;
				    	    remoteVideo.setAttribute("autoplay", true);
				    	    remoteCam.append(remoteVideo);
				    	    channel.setRTCRemoteMedia(remoteVideo);
				    		    
				    	    console.log('videoInit clear');
			    	    }
		    	 });
		    }

		    	// 공통 코드 =====================================
				vChatCloud = new VChatCloud();
				
		    	let channel, // joinRoom() 내부에서 채널 객체를 저장할 곳
		    	  userNick = 'xxxxxxxx'.replace(/[xy]/g, function(a, b) { return (b = Math.random() * 16, (a == 'y' ? b & 3 | 8 : b | 0).toString(16)) }); // 접속자의 닉네임, 사용자에게 입력받은 값을 사용해도 된다.
		    	  // 접속자 고유 키
		    	 


		    	// rtc
		    	let remoteCam;

		    	window.addEventListener("load", function (){
		    	  	// 접속자의 미디어 소스를 보여줄 위치
		    	  
		    	  	console.log('addEventListener start');
		    	  	
		    	  	remoteCam = $('#remote_cam');
		    	  	remoteCam.attr('name', 'remote_cam');

					console.log(channelKey);
					console.log(storeId);
					
		    	    joinRoom(channelKey,  userNick, userNick, function(err, history){
		    	    	if(err){
		    	    		vChatCloud.disconnect();
		    	    		var src = '/img/intro/99C121435B4F72C327.gif';
		    	    		remoteCam.append('<img src="'+src+'" style="width:904px; height:659px;"/>')
		    	    			
		    	    		console.log('joinRoom error');
		    	    		
		    	    	}else{
		    	    		console.log('joinRoom clear');
		    	    		videoInit();
		    	    		//채팅영역에 글쓰기가 활성화될시 활성화(최신공지 한개만 남기기)
		    	    		let flag = undefined;
		    	    		if(typeof write == 'function') history && history.forEach(function(m){
		    	    			if(m.messageType == 'notice'){
		    	    				if(flag == undefined){
		    	    					flag = true;
		    	    					write(m, 'notice', 'history');
		    	    				}
		    	    			}else{
		    	    				write(m, '', 'history');
		    	    			}
		    	    		});
		    	    		
		    	    		//이벤트 바인딩 시작
		    	    		
		    	    		getRoomInfo();
		    		
		    	    	};
		    	   		
		    		});
		    		
		    	});

		    	function joinRoom(roomId, clientKey, nickName, callback) {
		    	  // vchatcloud 객체
		    	  channel = vChatCloud.joinChannel(
		    	    {
		    	      roomId: roomId,
		    	      clientKey: clientKey,
		    	      nickName: nickName
		    	    },
		    	    function (error, history) {
		    	      $('div#content1 p').remove();
		    	      if (error) {
		    	        if (callback) return callback(error, null);
		    	        return error;
		    	      }
		    	      if (callback) callback(null, history);
		    	      // 채팅영역에 글쓰기가 활성화될시 활성화
		    	    }
		    	  );
		    	}


		    	function getRoomInfo() {
		    	  const api_url = "https://vchatcloud.com/api/openapi/getChatRoomInfo";
		    	  let param = {
		    	    room_id: channelKey
		    	  };
		    	  $.post(
		    	    api_url,
		    	    param,
		    	    function (data) {
		    	      if (data.result_cd == 1) {
		    	        console.log(data);
		    	        $("#roomNm").append(data.param.room_nm);
		    	      } else {
		    	        console.log("조회 실패");
		    	        oastPopup("조회 실패");
		    	      }
		    	    },
		    	    "json"
		    	  );
		    	}
		    	
		
});