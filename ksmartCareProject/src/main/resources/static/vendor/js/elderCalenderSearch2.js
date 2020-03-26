/**
 * 수급자 캘린더 
 * 
 */

$(document).ready(function(){
	$('#dataTable .elderId').on('click', function(){
		var id = $(this).attr('data-elder-id');
		var name =$(this).attr('data-elder-name');
		$('#elderNametitle').text("\( "+ name + " \)님의 일정");
		
		
	});
	
	
	
    $("#dataTable").DataTable({

  	  displayLength: 50
    });
    
    
//	달력 출력 부분

    $('#calendar').fullCalendar({
  	  header: {
  		   left: "prev,next today",
  		   center: "title",
	right: "month,basicWeek,basicDay"
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
  	  
  	
		events:[
			{
				title: 'Hello Calendar',
				start: '2020-03-25',
				//end: ~~
				// start만 입력하면 그날만 기록된다.
				backgroundColor: 'yellow', // 기타 옵션들
				textColor: 'black',
		        borderColor  : 'yellow' //red
				
			},

		],
		
		dayClick:  function () {
			alert('a day has been clicked!');
		}


    })
    
var  calendar  =  $('#calendar').fullCalendar('getCalendar');
calendar.on('dayClick', function (date, jsEvent, view) {
	$('#calendar').fullCalendar('addEventSource',
		[
			{
				title:  'event1',
				start:  date.format()
			}
		],
	)
})
    Metis.dashboard();
 
})
