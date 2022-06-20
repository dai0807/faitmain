
		  
		  $( ".carousel" ).swipe({
		    swipe: function ( event, direction, distance, duration, fingerCount ) {
		      if(direction == 'left') $(this).carousel('next');
		      if(direction == 'right') $(this).carousel('prev');
		    },
		    allowPageScroll:"vertical"
		    
		   //tap: function(event, target) {
		   // get the location: in my case the target is my link
		   //  window.location = $(this).find('.carousel-item.active a').attr('href');
		   // },
		  
		  //$('.carousel .carousel-inner').on('dragstart', 'a', function () {
		  //  return false;
		  //});
		  
		  //$('a.btn-danger').on('click', function(){
		  //	
		  //	$getJSON("/live/json/deleteLiveReservation",{
		  //		data: $('#liveReservationNum').val();
		  //	})
		  //	
		  //	.done(function(data){
		  //		console.log(data);
		  //	})
		  //	
		  // });
		});
		  	  
