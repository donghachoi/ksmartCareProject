var eventModal = $('#eventModal');

var modalTitle = $('.modal-title');
var editAllDay = $('#edit-allDay');
var editTitle = $('#edit-title');

var editEnd = $('#edit-end');
var editType = $('#edit-type');
var editColor = $('#edit-color');
var editDesc = $('#edit-desc');

var addBtnContainer = $('.modalBtnContainer-addEvent');
var modifyBtnContainer = $('.modalBtnContainer-modifyEvent');

var cname =  $('#ycname');
var yoyangBathNonBenefit = $('#yoyangBathNonBenefit');
var editStart = $('#visitPlanDate');
var editTime1 = $('#editTime1');
var editTime2 = $('#editTime2');
var serviceCategoryDetail = $('#serviceCategoryDetail');
var backgroundColor = $('#backgroundColor');
var description = $('#editDesc');
var serviceTitle = $('.serviceMenu');
var employeeId = $('#employeeId');

var addBtnContainer = $('.modalBtnContainer-addEvent');
var modifyBtnContainer = $('.modalBtnContainer-modifyEvent');

/* ****************
 *  새로운 일정 생성
 * ************** */
var newEvent = function (start, end, eventType) {

	
	
	var elerId = $('input[name=elderId]').val();
	
	
    $("#contextMenu").hide(); //메뉴 숨김

    //modalTitle.html('');
    editStart.val(start);
    editEnd.val(end);
    editType.val(eventType).prop("selected", true);

    addBtnContainer.show();
    modifyBtnContainer.hide();
    eventModal.modal('show');

    /******** 임시 RAMDON ID - 실제 DB 연동시 삭제 **********/
   var eventId = 1 + Math.floor(Math.random() * 1000);
    /******** 임시 RAMDON ID - 실제 DB 연동시 삭제 **********/

    //새로운 일정 저장버튼 클릭
    $('#save-event').unbind();
    $('#save-event').on('click', function () {
 
    var test1 = (editTime1.val()).split(':');
    var test2 = (editTime2.val()).split(':');
    var hour = (test2[0] - test1[0])*60;
    var minutes = test2[1] - test1[1];
    var total = hour+ minutes;
    //moment(editTime1.val()).format('HH:mm');
//   var test2 = moment(editTime2.val()).format('HH:mm');
    	var eventData = {
            _id: eventId,
            title: serviceTitle.attr('empservice'),
            start: editStart.val(),
            end: editEnd.val(),
            //description: editDesc.val(),
            type: editType.val(),
            //username: '사나',
            //backgroundColor: editColor.val(),
            textColor: '#ffffff',
            //***** 입력값 ********
			visitServiceCategory : (modalTitle.text()).substring(2,4),  //서비스 종류
			visitPlanDate: editStart.val(),                     //날짜
			employeeId: employeeId.val(),              //직원아이디
			employeeName: $('#employeeId option:checked').text(),          //직원 이름
			visitPlanTime : editTime1.val()+"~"+ editTime2.val(),        //시간
			elderName : cname.text(),                    //수급자 이름
			elderId : elerId,                            //수급자 아이디
			description : description.val(),            //설명
			serviceCategoryDetail : serviceCategoryDetail.val(),  //인지활동 등 상세 서비스
			backgroundColor : backgroundColor.val(),      //배경색 
			monthlyClaimGroupCode: editStart.val().substring(0, 7),  //날짜별 그룹
			yoyangBathNonBenefit : yoyangBathNonBenefit.val(),  //비급여 여부
            
			visitServiceTime : total,

			//.diff(moment(editTime1.val()).format('HH:mm')), 'minutes'),
				
			//moment.duration(test2.diff(test1)).asMinutes(),
			//moment.duration(t2.diff(t1)).asMinutes());
			//visitServiceTime :  moment.duration((editTime2.val()) - (editTime1.val())).asMinutes(),
			//visitServiceTime : moment.duration(editTime2.val() - editTime1.val()).asMinutes(),
			
            allDay: false
            
        };
    	
    	//직원 날짜 시간 중복 체크 하는 부분 잘 모르겟음
//    	$(employeeId).change(function(){
//    		
//    		$.ajax({
//    			type: 'post',
//    			url : '/employee/emplyeeDayCheck'
//    		    data: {employeeId : eventData.employeeId, visitPlanDate : visitPlanDate, visitPlanTime : visitPlanTime},
//    		    dataType :'json',
//    		    success : function(data){
//    		    	
//    		    },
//    		    error : function(error){
//    		    	console.log("error", error);
//    		    }
//    		})
//    		
//    	});

        if (eventData.start > eventData.end) {
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }

      
        if (eventData.title === '') {
            alert('일정명은 필수입니다.');
            return false;
        }

        if(eventData.visitServiceCategory == '간호' && 60<eventData.visitServiceTime){
        	alert("60분이 최고 시간입니다");
        	eventData.visitServiceTime = 60;
            return false;
        }
        
        
        //시간 계산
        if( eventData.visitServiceTime <60){
        	eventData.visitServiceTime = 30;
        }else if(60 <= eventData.visitServiceTime <90 ){
        	eventData.visitServiceTime = 60;
        }else if(90 <= eventData.visitServiceTime <120 || serviceCategoryDetail == '가족케어'){
        	eventData.visitServiceTime = 90;
        }else if(120 <= eventData.visitServiceTime <150){
        	eventData.visitServiceTime = 120;
        }else if(150 <= eventData.visitServiceTime <210){
        	eventData.visitServiceTime = 150;
        }else if(210 <= eventData.visitServiceTime < 240){
        	eventData.visitServiceTime = 210;
        }else if(240 <= eventData.visitServiceTime){
        	eventData.visitServiceTime = 240;
        }
        
        if(eventData.visitServiceCategory == '요양'){
        	eventData.visitServiceTime = eventData.visitServiceTime + serviceCategoryDetail.val();
        }
        var realEndDay;

        if (editAllDay.is(':checked')) {
            eventData.start = moment(eventData.start).format('YYYY-MM-DD');
            //render시 날짜표기수정
            eventData.end = moment(eventData.end).add(1, 'days').format('YYYY-MM-DD');
            //DB에 넣을때(선택)
            realEndDay = moment(eventData.end).format('YYYY-MM-DD');

            eventData.allDay = true;
        }
        

        $("#calendar").fullCalendar('renderEvent', eventData, true);
        eventModal.find('input, textarea').val('');
        editAllDay.prop('checked', false);
        eventModal.modal('hide');

    //새로운 일정 저장
        $.ajax({
            type: 'post',
            url: '/employee/visitInsert',
            data:  eventData,
            success: function (response) {
                //DB연동시 중복이벤트 방지를 위한
                $('#calendar').fullCalendar('removeEvents');
                $('#calendar').fullCalendar('refetchEvents');
            }
        });
    });
};