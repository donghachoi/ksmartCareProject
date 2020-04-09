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
			eventLimit: true, // for all non-TimeGrid views
			 views: {
			   timeGrid: {
			     eventLimit: 6
			   }
			 },	
			eventLimitText : "보기",
			eventClick:  function (events, jsEvent, view) {
				
				var stime = events.start.format('YYYY.MM.DD');
				var xpos = jsEvent.pageX;
				var ypos = jsEvent.pageY;
				
				if(events.eventname == 'elder'){
					$(".eventDate").text(stime);
					$(".eventTitle").text(events.title);
					$(".eventEmployeeName").text(events.employeeName);	
					
				}else if(events.eventname == 'employee'){
					$(".eventDate").text(stime);
					$(".eventTitle").text(events.visitServiceTime);
					$(".eventTime").text(events.time);
					$(".eventelderName").text(events.elderName);
				}
			
					$("#test").modal("show");
					$("#test").css('left', '60%');
					$("#test").css('top', '40%');
					return false;	
			}
			
		});
	}else{	
		
		calendar.fullCalendar('removeEvents');
		calendar.fullCalendar('addEventSource', events);         
		calendar.fullCalendar('rerenderEvents' );
		
	}
	
}

	$('.close').on('click', function(){
		$(".eventContent").css('display', 'none');
	});



$(document).ready(function(){
	
	var firstEvent = true;
	
    $("#dataTable").DataTable({
    	  displayLength: 50
    });
      
    
    // 수급자 이름 검색 
    $('#dataTable .elderId').on('click', function(){

    	$('.col-lg-12').attr('class', 'col-lg-5');	
		$('.col-lg-7').css('display', 'block');
		var id = $(this).attr('data-elder-id');
		var name =$(this).attr('data-elder-name');
		$('#elderNametitle').text("\( "+ name + " \)님의 일정");
		$('#exinput').attr('href','/elder/excelDown?id='+id+'&name='+name);

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
						bcolor = '#ffa94d';
					}					
					
					events.push({ 
						eventname: "elder",
						title: data[i].visitServiceCategory + "("+data[i].visitPlanTime +")", 
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
		
/*		$(".eventContent").click(function() {
		      $(".eventContent").css('display', 'none');
		});*/

    });
    // 수급자 이름 검색 끝
    
    //직원 이름 검색
    
    $('#dataTable .employeeId').on('click', function(){
    	
    	$('.col-lg-12').attr('class', 'col-lg-5');	
		$('.col-lg-7').css('display', 'block');
		var id = $(this).attr('data-employee-id');
		var name =$(this).attr('data-employee-name');
		$('#employeeNametitle').text("\( "+ name + " \)님의 일정");
		$('#exinput').attr('href','/employee/excelDown?id='+id+'&name='+name);

		$.ajax({
			type: 'post',
			url: '/employee/employeeCalenderSearch',
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
						bcolor = '#ffa94d';
					}					
					
					events.push({ 
						eventname: "employee",
						title: data[i].visitPlanTime + "\n("+data[i].elderName+")", 
						start: data[i].visitPlanDate,
						time : data[i].visitPlanTime,
						elderName : data[i].elderName,
						visitServiceTime : data[i].visitServiceTime,
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
    
    //직원 이름 검색 끝
   // Metis.dashboard();
    });
    
 
 
})
