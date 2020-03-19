var eventModal = $('#eventModal');

/**
var modalTitle = $('.modal-title');
var editAllDay = $('#yoyangBathNonBenefit');
var editTitle = $('#employeeId');
var editStart = $('#edit-start');
var editEnd = $('#edit-end');
var editType = $('#serviceCategoryDetail');
var editColor = $('#edit-color');
var editDesc = $('#edit-desc');
var cname =  $('#cname');

 */

var modalTitle = $('.modal-title');
var cname =  $('#ycname');
var yoyangBathNonBenefit = $('#yoyangBathNonBenefit');
var employeeId = $('#employeeId');
var editStart = $('#visitPlanDate');
var editTime1 = $('#editTime1');
var editTime2 = $('#editTime2');
var serviceCategoryDetail = $('#serviceCategoryDetail');
var backgroundColor = $('#backgroundColor');
var description = $('#editDesc');

var addBtnContainer = $('.modalBtnContainer-addEvent');
var modifyBtnContainer = $('.modalBtnContainer-modifyEvent');

var monthlyClaimGroupCode = editStart.val().substr(0, 7);
var visitPlanTime = editTime1 +" ~ " +editTime2;
/* ****************
 *  새로운 일정 생성
 * ************** */
var newEvent = function (start, end, eventType) {

    $("#contextMenu").hide(); //메뉴 숨김

    modalTitle.html('');
    editStart.val(start);
    //editEnd.val(start);
    //editEnd.val(end);
    serviceCategoryDetail.val(eventType).prop("selected", true);

    addBtnContainer.show();
    modifyBtnContainer.hide();
    eventModal.modal('show');

    /******** 임시 RAMDON ID - 실제 DB 연동시 삭제 **********/
    //var eventId = 1 + Math.floor(Math.random() * 1000);
    /******** 임시 RAMDON ID - 실제 DB 연동시 삭제 **********/

    //새로운 일정 저장버튼 클릭
    $('#save-event').unbind();
    $('#save-event').on('click', function () {

	var dateLoad = {
				
		title: modalTitle.text(),
		start: editStart.val(),
		visitServiceCategory : modalTitle.text(),   //서비스 종류
		visitPlanDate: editStart.val(),                     //날짜
		employeeId: employeeId.val(),              //직원아이디
		employeeName: employeeId.text(),            //직원 이름
		visitPlanTime : visitPlanTime ,                 //시간
		elderName : cname.val(),                    //수급자 이름
		description : description.val(),            //설명
		serviceCategoryDetail : serviceCategoryDetail.val(),  //인지활동 등 상세 서비스
		backgroundColor : backgroundColor.val(),      //배경색 
		monthlyClaimGroupCode: monthlyClaimGroupCode   //날짜별 그룹 
	};
    	
        var eventData = {
     		
        _id: employeeId,
    	//cname: modalTitle.text(),
        title: modalTitle.text(),
        start: editStart.val(),
        end: editStart.val(),
        description: description.val(),
        type: serviceCategoryDetail.val(),
        username: cname.text() ,
        backgroundColor: backgroundColor.val(),
        textColor: '#ffffff',
        allDay: false
        
        };

        if (editTime1.val() > editTime2.val()) {
            alert('끝나는 시간이 앞설 수 없습니다.');
            return false;
        }

//        if (eventData.visitServiceCategory === '') {
//            alert('일정명은 필수입니다.');
//            return false;
//        }

        var realEndDay;

//        if (yoyangBathNonBenefit.is(':checked')) {
//            eventData.start = moment(eventData.start).format('YYYY-MM-DD');
//            //render시 날짜표기수정
//            eventData.end = moment(eventData.end).add(1, 'days').format('YYYY-MM-DD');
//            //DB에 넣을때(선택)
//            realEndDay = moment(eventData.end).format('YYYY-MM-DD');
//
//            eventData.allDay = true;
//        }
// 

        
//        $("#calendar").fullCalendar('renderEvent', eventData, true);
//	       eventModal.find('input, textarea').val('');
//	        yoyangBathNonBenefit.prop('checked', false);
//	        eventModal.modal('hide');

        //새로운 일정 저장
        $.ajax({
            type: 'post',
            url: '/employee/visitInsert',
            data:  dateLoad,
            dataType: 'json',
            success: function (response) {
                //DB연동시 중복이벤트 방지를 위한
                $('#calendar').fullCalendar('removeEvents');
                $('#calendar').fullCalendar('refetchEvents');
            }
        );}
    });
};