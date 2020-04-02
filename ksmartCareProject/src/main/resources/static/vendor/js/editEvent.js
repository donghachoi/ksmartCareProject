/* ****************
 *  일정 편집
 * ************** */
var editEvent = function (event, element, view) {

    $('.popover.fade.top').remove();
	$('#employeeId').find('option').remove();
    $(element).popover("hide");

    if (event.allDay === true) {
        editAllDay.prop('checked', true);
    } else {
        editAllDay.prop('checked', false);
    }

    if (event.end === null) {
        event.end = event.start;
    }

    modalTitle.html('일정 수정');
    $('.serviceCategory').css('display', 'block');
    console.log("servicestr12222222"+ event.title);
    var time =  (event.time).split('~');

    var servicestr = (event.title).substring(0,2);
    console.log("servicestr11111111"+ servicestr);
    var serviceCategory = $('#serviceCategory');
    serviceCategory.val(servicestr).attr("selected", "selected");
    editStart.val(event.visitPlanDate);
    editTime1.val(time[0]);
    editTime2.val(time[1]);
    var visitCode = event.visitCode;
    console.log("dfdfdfdf"+event.serviceTime);
    $('#visitCode').val(visitCode);
    
    //직원 목록
    for(var i=0; i< event.empList.length; i++){
    	if(event.employeeNam == event.empList[i].employeeId){
    		employeeId.val(event.employeeName).attr("selected", "selected");
    	}else{
        	employeeId.append("<option value=\'"+ event.empList[i].employeeId + "\'>"
    				+ event.empList[i].employeeName +"</option>"); 
    	}
    }
    var test = "<option value='"+event.serviceTime+"'>"+event.serviceTime+"</option>";
    //상세 서비스 내용
	if(serviceCategory.val() =='요양'){
		test += "<option value='인지활동'>인지활동</option>";
		test += "<option value='가족케어'>가족케어</option>";
		test += "<option value='치매가족휴가'>치매가족휴가</option>";
		
		$('#serviceCategoryDetail').append(test);
	}
	if(serviceCategory.val()  == '목욕'){
		test += "<option value='차량이용(차량 내)'>차량이용(차량 내)</option>";
		test += "<option value='차량이용(가정 내)'>차량이용(가정 내)</option>";
		test += "<option value='차량미이용'>차량미이용</option>";
			
		$('#serviceCategoryDetail').append(test);
	 }
	if(serviceCategory.val() == '간호'){
		$('#serviceCategoryDetail').parent().hide();
	}				
   
    description.val(event.description);
    
    editStart.val(event.start.format('YYYY-MM-DD'));

    addBtnContainer.hide();
    modifyBtnContainer.show();
    eventModal.modal('show');
    
   
    var test1 = ($('#editTime1').val()).split(':');
    var test2 = ($('#editTime2').val()).split(':');
    var startTime = new Date(0, 0, 0, test1[0], test1[1]);
    var endTime = new Date(0, 0, 0, test2[0], test2[1]);
    var tmp = (endTime.getTime() - startTime.getTime()) / 60000; 

	if($('#serviceCategory').val() == '요양'){
		bcolor = '#28a745';
	}else if($('#serviceCategory').val() == '목욕'){
		bcolor = '#ffc107';
	}else{
		bcolor = '#ffa94d';
	}	
    	var eventData = {

			title: $('#serviceCategory').val() + "("+$('#editTime1').val()+"~"+ $('#editTime2').val() +")", 
			start: $('#visitPlanDate').val(),
			end: $('#visitPlanDate').val(),
			time : $('#editTime1').val()+"~"+ $('#editTime2').val(),
			backgroundColor: bcolor,
			borderColor: bcolor,
            //***** 입력값 ********
			id : visitCode,
            visitCode : visitCode,
			visitServiceCategory : $('#serviceCategory').val(),  //서비스 종류
			visitPlanDate: $('#visitPlanDate').val(),                     //날짜
			employeeId: $('#employeeId').val(),              //직원아이디
			employeeName: $('#employeeId option:checked').text(),          //직원 이름
			visitPlanTime : $('#editTime1').val()+"~"+ $('#editTime2').val(),        //시간
			description : $('#editDesc').val(),            //설명
			serviceCategoryDetail : $('#serviceCategoryDetail').val(),  //인지활동 등 상세 서비스
			monthlyClaimGroupCode: ($('#visitPlanDate').val()).substring(0, 7),  //날짜별 그룹
			yoyangBathNonBenefit : $('#yoyangBathNonBenefit').val(),  //비급여 여부
			visitServiceTime : tmp,
			allDay: false   
        };
        
        if (eventData.employeeId == '') {
            alert('직원 선택은 필수입니다.');
            return false;
        }
        
        if (eventData.serviceCategoryDetail == '' &&  eventData.visitServiceCategory == '요양' &&  eventData.visitServiceCategory == '목욕') {
            alert('서비스 선택은 필수입니다.');
            return false;
        }
        
        if (!editTime1.val() || !editTime2.val()) {
            alert('시간 입력은 필수 입니다');
            return false;
        }

        //시간 계산
        if(0 <= eventData.visitServiceTime && eventData.visitServiceTime <60){
        	eventData.visitServiceTime = 30;
        }else if(60 <= eventData.visitServiceTime &&  eventData.visitServiceTime <90 ){
        	eventData.visitServiceTime = 60;
        }else if(90 <= eventData.visitServiceTime && eventData.visitServiceTime  <120 || eventData.serviceCategoryDetail == '가족케어'){
        	eventData.visitServiceTime = 90;
        }else if(120 <= eventData.visitServiceTime && eventData.visitServiceTime <150){
        	eventData.visitServiceTime = 120;
        }else if(150 <= eventData.visitServiceTime && eventData.visitServiceTime <210){
        	eventData.visitServiceTime = 150;
        }else if(210 <= eventData.visitServiceTime && eventData.visitServiceTime  <240){
        	eventData.visitServiceTime = 210;
        }else if(240 == eventData.visitServiceTime){
        	eventData.visitServiceTime = 240;
        }else if(240 < eventData.visitServiceTime){
        	alert("최대시간을 넘겼습니다");
        	return false;
        }else{
        	alert("최대시간을 넘겼습니다");
        	return false;
        }
      
        if(eventData.visitServiceCategory == '요양'){
        	eventData.visitServiceTime = eventData.visitServiceTime + eventData.serviceCategoryDetail;
        }

        if(eventData.visitServiceCategory == '간호' && 60<eventData.visitServiceTime){
        	alert("60분이 최고 시간입니다");
        	eventData.visitServiceTime = 60;
            return false;
        }

        if(eventData.visitServiceCategory == '목욕' ){
        	eventData.visitServiceTime = serviceCategoryDetail.val();
        }    
    

    //업데이트 버튼 클릭시
    $('#updateEvent').unbind();
    $('#updateEvent').on('click', function () {

        var statusAllDay;
        var startDate;
        var endDate;
        var displayDate;

        if (editAllDay.is(':checked')) {
            statusAllDay = true;
            startDate = moment(editStart.val()).format('YYYY-MM-DD');
            endDate = moment(editEnd.val()).format('YYYY-MM-DD');
            displayDate = moment(editEnd.val()).add(1, 'days').format('YYYY-MM-DD');
        } else {
            statusAllDay = false;
            startDate = editStart.val();
            endDate = editEnd.val();
            displayDate = endDate;
        }

        eventModal.modal('hide');

//        event.allDay = statusAllDay;
        
        event.title = editTitle.val();
        event.start = startDate;
        event.end = displayDate;
        event.type = editType.val();
        event.backgroundColor = editColor.val();
        event.description = editDesc.val();
            
//        $("#calendar").fullCalendar('updateEvent', eventData);

        //일정 업데이트
        $.ajax({
            type: "post",
            url: "/employee/visitUpdate",
            data: eventData,
            success: function (response) {
            	if(response != 0){
            	    alert('수정되었습니다.');
            	}else{
            		alert('수정되지 않았습니다');
            		return false;
            	}
//    			calendar.fullCalendar('removeEvents');
//    			calendar.fullCalendar('rerenderEvents');
            },
    		error : function(error){
    			console.log("error", error);
    		}            
        });

    });

    // 삭제버튼
    $('#deleteEvent').on('click', function () {
        $('#deleteEvent').unbind();
       // $("#calendar").fullCalendar('removeEvents', visitCode);
        eventModal.modal('hide');
        visitCode = $('#visitCode').val();

        //삭제시
        $.ajax({
            type: "get",
            url: "/employee/visitDelete",
            data: {visitCode : visitCode},
            success: function (response) {
            	if(response != 0){
                    alert('삭제되었습니다.');
            	}else {
                    alert('삭제되지 않앗습니다.');
                    return false;
            	}
//    			calendar.fullCalendar('removeEvents');
//    			calendar.fullCalendar('rerenderEvents');
            },
    		error : function(error){
    			console.log("error", error);
    		}                
        });
    });
};