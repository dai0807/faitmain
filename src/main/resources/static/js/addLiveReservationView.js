$(function(){
	$("#reservationDate").on("change", function(){
		$.getJSON( "/live/json/addLiveReservation",{
			date: $("#reservationDate").val()
		})
			.done(function(data){
				
				$("#reservationTime").remove();
				
				var resultStr = "<select id='reservationTime' name='reservationTime'>";
				$.each(data, function(index, value){
					resultStr += "<option value='"+value+"'>"+value+"시</option>";
				})
				resultStr += "</select>";
				
				$("#timeDiv").append(resultStr);
			})
	});
});