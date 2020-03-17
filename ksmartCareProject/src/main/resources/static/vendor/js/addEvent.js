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
var cname =  $('input[name=cname]');
var yoyangBathNonBenefit = $('#yoyangBathNonBenefit');
var employeeId = $('#employeeId');
var editStart = $('#edit-start');
var editEnd = $('#edit-end');
var serviceCategoryDetail = $('#serviceCategoryDetail');
var backgroundColor = $('#edit-color');
var description = $('#edit-desc');

var addBtnContainer = $('.modalBtnContainer-addEvent');
var modifyBtnContainer = $('.modalBtnContainer-modifyEvent');


/* ****************
 *  새로운 일정 생성
 * ************** */
var newEvent = function (start, end, eventType) {

    $("#contextMenu").hide(); //메뉴 숨김

    modalTitle.html('방문일정');
    editStart.val(start);
    editEnd.val(end);
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

    	
        var eventData = {
        	visitServiceCategory : modalTitle.text(),
        	start: editStart.val(),
        	end: editEnd.val(),
        	elderName : cname.val(),
        	description : description.val(),
        	serviceCategoryDetail : serviceCategoryDetail.val(),
        	backgroundColor : backgroundColor.val(),
        	textColor: '#ffffff',
        	type: serviceCategoryDetail.val(),
        	allDay : false
        	/**
            //_id: eventId.val(),
        	cname: modalTitle.text(),
            title: editTitle.val(),
            start: editStart.val(),
            end: editEnd.val(),
            description: editDesc.val(),
            type: editType.val(),
            username: cname.text() ,
            backgroundColor: editColor.val(),
            textColor: '#ffffff',
            allDay: false
            */	
        };

        if (eventData.start > eventData.end) {
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }

        if (eventData.visitServiceCategory === '') {
            alert('일정명은 필수입니다.');
            return false;
        }

        var realEndDay;

        /***
        if (yoyangBathNonBenefit.is(':checked')) {
            eventData.start = moment(eventData.start).format('YYYY-MM-DD');
            //render시 날짜표기수정
            eventData.end = moment(eventData.end).add(1, 'days').format('YYYY-MM-DD');
            //DB에 넣을때(선택)
            realEndDay = moment(eventData.end).format('YYYY-MM-DD');

            eventData.allDay = true;
        }
        */

        $("#calendar").fullCalendar('renderEvent', eventData, true);
        eventModal.find('input, textarea').val('');
        yoyangBathNonBenefit.prop('checked', false);
        eventModal.modal('hide');

        //새로운 일정 저장
        $.ajax({
            type: 'post',
            url: '/employee/visitInsert',
            data: eventData,
            dataType: 'json',
            success: function (response) {
                //DB연동시 중복이벤트 방지를 위한
                $('#calendar').fullCalendar('removeEvents');
                $('#calendar').fullCalendar('refetchEvents');
            }
        });
    });
};