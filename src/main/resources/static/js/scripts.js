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


		var w, container, carousel, item, radius, itemLength, rY, ticker, fps; 
		var mouseX = 0;
		var mouseY = 0;
		var mouseZ = 0;
		var addX = 0;
		
		
		// fps counter created by: https://gist.github.com/sharkbrainguy/1156092,
		// no need to create my own :)
		var fps_counter = {
			
			tick: function () 
			{
				// this has to clone the array every tick so that
				// separate instances won't share state 
				this.times = this.times.concat(+new Date());
				var seconds, times = this.times;
        
				if (times.length > this.span + 1) 
				{
					times.shift(); // ditch the oldest time
					seconds = (times[times.length - 1] - times[0]) / 1000;
					return Math.round(this.span / seconds);
				} 
				else return null;
			},
 
			times: [],
			span: 20
		};
		var counter = Object.create(fps_counter);
		
		
		
		$(document).ready( init )
		
		function init()
		{
			w = $(window);
			container = $( '#contentContainer' );
			carousel = $( '#carouselContainer' );
			item = $( '.carouselItem' );
			itemLength = $( '.carouselItem' ).length;
			fps = $('#fps');
			rY = 360 / itemLength;
			radius = Math.round( (250) / Math.tan( Math.PI / itemLength ) );
			
			// set container 3d props
			TweenMax.set(container, {perspective:600})
			TweenMax.set(carousel, {z:-(radius)})
			
			// create carousel item props
			
			for ( var i = 0; i < itemLength; i++ )
			{
				var $item = item.eq(i);
				var $block = $item.find('.carouselItemInner');
				
        //thanks @chrisgannon!        
        TweenMax.set($item, {rotationY:rY * i, z:radius, transformOrigin:"50% 50% " + -radius + "px"});
				
				animateIn( $item, $block )						
			}
			
			// set mouse x and y props and looper ticker
			window.addEventListener( "mousemove", onMouseMove, false );
			ticker = setInterval( looper, 1000/60 );			
		}
		
		function animateIn( $item, $block )
		{
			var $nrX = 360 * getRandomInt(2);
			var $nrY = 360 * getRandomInt(2);
				
			var $nx = -(2000) + getRandomInt( 4000 )
			var $ny = -(2000) + getRandomInt( 4000 )
			var $nz = -4000 +  getRandomInt( 4000 )
				
			var $s = 1.5 + (getRandomInt( 10 ) * .1)
			var $d = 1 - (getRandomInt( 8 ) * .1)
			
			TweenMax.set( $item, { autoAlpha:1, delay:$d } )	
			TweenMax.set( $block, { z:$nz, rotationY:$nrY, rotationX:$nrX, x:$nx, y:$ny, autoAlpha:0} )
			TweenMax.to( $block, $s, { delay:$d, rotationY:0, rotationX:0, z:0,  ease:Expo.easeInOut} )
			TweenMax.to( $block, $s-.5, { delay:$d, x:0, y:0, autoAlpha:1, ease:Expo.easeInOut} )
		}
		
		function onMouseMove(event)
		{
			mouseX = -(-(window.innerWidth * .5) + event.pageX) * .0025;
			mouseY = -(-(window.innerHeight * .5) + event.pageY ) * .01;
			mouseZ = -(radius) - (Math.abs(-(window.innerHeight * .5) + event.pageY ) - 200);
		}
		
		// loops and sets the carousel 3d properties
		function looper()
		{
			addX += mouseX
			TweenMax.to( carousel, 1, { rotationY:addX, rotationX:mouseY, ease:Quint.easeOut } )
			TweenMax.set( carousel, {z:mouseZ } )
			fps.text( 'Framerate: ' + counter.tick() + '/60 FPS' )	
		}
		
		function getRandomInt( $n )
		{
			return Math.floor((Math.random()*$n)+1);	
		}