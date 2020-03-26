/**
 * 수급자 캘린더 
 * 
 */

var fullCalendarReload = function(events, isFirstEvent){
	
	//달력 출력 부분
	var calendar = $('#calendar');
	
	if(isFirstEvent){
		
		calendar.fullCalendar({
			header: {
				left: "prev,next today",
				center: "title",
				right: "month,basicWeek"
					//right: "today prev,next"
			},
			titleFormat: {
				month: "YYYY년 M월"
			},
			buttonText: {
				today : "오늘",
				month : "월별",
				week : "주별",
				day : "일별",
			},
			lang: "ko",
			//locale: "ko",
			//editable: false,
			events:events,
			eventClick:  function (events, jsEvent, view) {
				var stime = events.start.format('YYYY.MM.DD');
				
				var xpos = jsEvent.pageX;
				var ypos = jsEvent.pageY;
				$(".eventDate").text(stime);
				$(".eventTitle").text(events.title);
				$(".eventEmployeeName").text(events.employeeName);
				$(".eventContent").css('display', 'inline-block');
				$(".eventContent").css('left', '60%');
				$(".eventContent").css('top', '40%');
				return false;
			}
			
		});
	}else{	
		
		calendar.fullCalendar('removeEvents');
		calendar.fullCalendar('addEventSource', events);         
		calendar.fullCalendar('rerenderEvents' );
		
	}
	
}

$(document).ready(function(){
	
	var firstEvent = true;
	
    $("#dataTable").DataTable({
    	  displayLength: 50
    });
      
    $('#dataTable .elderId').on('click', function(){

		var id = $(this).attr('data-elder-id');
		var name =$(this).attr('data-elder-name');
		$('#elderNametitle').text("\( "+ name + " \)님의 일정");
	
		$.ajax({
			type: 'post',
			url: '/elder/elderCalenderSearch',
			data: { id: id },
			dataType: 'json',
			async:false,
			success: function(data){
				var events = [];
				for(var i=0; i< data.length; i++){
					if(data[i].visitServiceCategory == '요양'){
						bcolor = '#28a745';
					}else if(data[i].visitServiceCategory == '목욕'){
						bcolor = '#ffc107';
					}else{
						bcolor = '#17a2b8';
					}					
					
					events.push({ 
						title: data[i].visitServiceCategory + "( "+data[i].visitPlanTime +" )", 
						start: data[i].visitPlanDate,
						time : data[i].visitPlanTime,
						employeeName : data[i].employeeName,
						backgroundColor: bcolor,
						borderColor: bcolor,
						textColor: '#fff'
					});	
					
				}
				//켈린더 함수 호출
				fullCalendarReload(events, firstEvent);
				firstEvent = false;
			},
			error: function(error){
				alert("에러발생"+error);
			}
		
		});	
		
		$(".eventContent").click(function() {
		      $(".eventContent").css('display', 'none');
		});

    });
   // Metis.dashboard();
 
})
