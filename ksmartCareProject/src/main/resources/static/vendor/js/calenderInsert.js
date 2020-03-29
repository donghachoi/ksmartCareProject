$(document).ready(function(){
	
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
				dayClick:function (date, jsEvent, view) {
					
				    $(".fc-body").unbind('click');
				    $(".fc-body").on('click', 'td', function (e) {

				      $("#contextMenu")
				        .addClass("contextOpened")
				        .css({
				          display: "block",
				          left: e.pageX - 200,
				          top: e.pageY - 150
				        });
				      return false;
				    });
				    
				    var today = moment();
				    
				    date.set({
				          hours: today.hours(),
				          minute: today.minutes()
				        });
				      
				    date = moment(date).format('YYYY-MM-DD');
				    
				    //날짜 클릭시 카테고리 선택메뉴
				    var $contextMenu = $("#contextMenu");
				    $contextMenu.on("click", "a", function (e) {
				      e.preventDefault();

				      //닫기 버튼이 아닐때
				      if ($(this).data().role !== 'close') {
				        newEvent(startDate, endDate, $(this).html());    
				      }

				      $contextMenu.removeClass("contextOpened");
				      $contextMenu.hide();
				    });			 	
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

	
	
	//테이블 행 출력 갯수 
    $("#dataTable").DataTable({
        lengthChange: false,
    	  displayLength: 5
    });
    
    //금액 오른쪽 정렬 
	$('.trigth').css('text-align', 'right');
	
	var firstEvent = true;
	//수급자 총 금액과 잔액 가져옴 
	$('.eldername').on('click', function(){
		//$('tr').remove('#remove2');
		$('.col-lg-12').css('display', 'block');
		var id = $(this).attr('data-elder-id');
		var cost = $(this).attr('max-cost');
		var cname = $(this).attr('cname');
			$.ajax({
				type : 'POST',
				url : '/employee/velderbenefitcost',
				data: {elder_id : id, maxcost : cost},
				dataType : 'json',
				async:false,
				success : function(data){
				
				    $('#maxCost').text(data.cost.maxcost);
				    $('#mixCost').text(data.cost.subCost);
				    $('#ycname').html("<b>"+cname+"</b>");
				    $('input[name=elderId]').val(id);
				    $('input[name=maxcost]').val(cost);

				    $("#yoyangInt").text(data.cost.yoyang);
				    $("#bathInt").text(data.cost.bath);
				    $("#nurseInt").text(data.cost.nurse);
				    
					$("#bcost1").text(data.cost.getYoyang);
					$("#bcost2").text(data.cost.getbath);
					$("#bcost3").text(data.cost.getnurse);
					
					$("#mcoat1").text(data.cost.mgetYoyang);
					$("#mcoat2").text(data.cost.mgetbath);
					$("#mcoat3").text(data.cost.mgetnurse);
					
					$("#tolnum").text(data.cost.tolnum);
					$("#tolnum2").text(data.cost.BenefitCost);
					$("#tolnum3").text(data.cost.NonBenefitCost);

				    $('#syear').val(String(data.cost.syear)).attr("selected", "selected");
				    $('#smonth').val(String(data.cost.smonth)).attr("selected", "selected");
				    
				    //캘린더 출력 내용
					var events = [];
					
					for(var i=0; i< data.calender.length; i++){
						if(data.calender[i].visitServiceCategory == '요양'){
							bcolor = '#28a745';
						}else if(data.calender[i].visitServiceCategory == '목욕'){
							bcolor = '#ffc107';
						}else{
							bcolor = '#ffa94d';
						}					
						
						events.push({ 
							eventname: "elder",
							title: data.calender[i].visitServiceCategory + "("+data.calender[i].visitPlanTime +")", 
							start: data.calender[i].visitPlanDate,
							time : data.calender[i].visitPlanTime,
							employeeName : data.calender[i].employeeName,
							backgroundColor: bcolor,
							borderColor: bcolor,
							textColor: '#fff'
						});	
						
					}				    
					//켈린더 함수 호출
					fullCalendarReload(events, firstEvent);
					firstEvent = false;
				},
				
				error : function(error){
					console.log("error", error);
				}

		});
	});
	
	//년도와 달로 수가값 가져옴
	$('#yearSearch').on('click', function(){
		//$('tr').remove('#remove2');
		var id = $('input[name=elderId]').val();
		var cost = $('input[name=maxcost]').val();
		var cname = $('#ycname').text();
		if( !cname ){
			alert("수급자를 선택해 주세요");
		}
		var syear  =$("#syear").val();
		var smonth = $('#smonth').val();
			$.ajax({
				type : 'POST',
				url : '/employee/velderbenefitcost',
				data: {elder_id : id, maxcost : cost, syear: syear, smonth: smonth},
				dataType : 'json',
				async:false,
				success : function(data){
				
				    $('#maxCost').text(data.cost.maxcost);
				    $('#mixCost').text(data.cost.subCost);
				    $('#ycname').html("<b>"+cname+"</b>");
				    $('input[name=elderId]').val(id);
				    $('input[name=maxcost]').val(cost);

				    $("#yoyangInt").text(data.cost.yoyang);
				    $("#bathInt").text(data.cost.bath);
				    $("#nurseInt").text(data.cost.nurse);
				    
					$("#bcost1").text(data.cost.getYoyang);
					$("#bcost2").text(data.cost.getbath);
					$("#bcost3").text(data.cost.getnurse);
					
					$("#mcoat1").text(data.cost.mgetYoyang);
					$("#mcoat2").text(data.cost.mgetbath);
					$("#mcoat3").text(data.cost.mgetnurse);
					
					$("#tolnum").text(data.cost.tolnum);
					$("#tolnum2").text(data.cost.BenefitCost);
					$("#tolnum3").text(data.cost.NonBenefitCost);

				    $('#syear').val(String(data.cost.syear)).attr("selected", "selected");
				    $('#smonth').val(String(data.cost.smonth)).attr("selected", "selected");
				},
				error : function(error){
					console.log("error", error);
				}
		});
	});

	//직원 분류해서 보여줌
	$('.serviceMenu').on("click", function(){
		
		var elderId = $('input[name=elderId]').val();
		var cname = $('#ycname').text();
		
		if( !cname ){
			alert("수급자를 선택해 주세요");
			 return false;
		}

		//요양, 목욕, 간호
		var empcategory = $(this).attr('empcategory');
		//인지 활동, 차안목욕 
		var empservice = $(this).attr('empservice');
		
		$.ajax({
			url: "/employee/vemployeecategory",
			data : { empcategory : empcategory},
			dateType: "json",
			type: "GET",
			success : function(data){
				$('#employeeId').find('option').remove();
				$('#serviceCategoryDetail').find('option').remove();
				$('.modal-title').text("방문"+ empservice);
				$('#employeeId').append("<option value=''>직원을 선택해주세요</option>");
				for(var i=0; i<data.length; i++){
					
					$('#employeeId').append("<option value=\'"+ data[i].employeeId + "\'>"
							+ data[i].employeeName +"</option>");
				}
				
				if(empservice =='요양'){
					var test = "<option value=''>서비스를 선택헤주세요</option>";
					test += "<option value='인지활동'>인지활동</option>";
					test += "<option value='가족케어'>가족케어</option>";
					test += "<option value='치매가족휴가'>치매가족휴가</option>";
					
					$('#serviceCategoryDetail').append(test);
				}
				if(empservice == '목욕'){
					var test = "<option value=''>서비스를 선택헤주세요</option>";
						test = "<option value='차량이용(차량 내)'>차량이용(차량 내)</option>";
						test += "<option value='차량이용(가정 내)'>차량이용(가정 내)</option>";
						test += "<option value='차량미이용'>차량미이용</option>";
						
					$('#serviceCategoryDetail').append(test);
				 }
				if(empservice == '간호'){
					$('#serviceCategoryDetail').parent().hide();
				}				
			},
            error:function(){
                alert("ajax통신 실패!!!");
            }
		});
	});
	//--직원 분류해서 보여줌
	
})