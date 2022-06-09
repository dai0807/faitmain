
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			plugins:['dayGrid', 'timeGrid', 'list', 'interaction'],
			header:{
					left: 'prev, next today',
					center: 'title',
					right: 'dayGridMonth, timeGridWeek, timeGridDay, listWeek'
					},
			defaultView: 'timeGridWeek',
			locale: 'ko',
			navLink: true, //can click day/week names to navigate views
			editable: true,
			allDaySlot: false,
			eventLimit: true, // allow "more" link when too many events
			minTime: '10:00:00',
			maxTime: '24:00:00',
			contentHeight: auto,
			eventSources: [{
							events: function(info, successCallback, failureCallback){
								$.ajax({
										url: '/live/json/getLiveReservationCal',
										type: 'POST',
										dataType: 'json',
										data: {
												start : moment(info.startStr).format('YYYY-MM-DD'),
												end : moment(info.endStr).format('YYYY-MM-DD')
											},
											success: function(data){
													successCallback(data);
											}
							});
						}
				}]
						
		});
		calendar.render();
	});
	
	