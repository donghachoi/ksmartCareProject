/**
 * 스케줄 테스트
 */
//var modalTitle = $('.modal-title');
//var cname =  $('#ycname');
//var yoyangBathNonBenefit = $('#yoyangBathNonBenefit');
//var employeeId = $('#employeeId');
//var editStart = $('#visitPlanDate');
//var editTime1 = $('#editTime1');
//var editTime2 = $('#editTime2');
//var serviceCategoryDetail = $('#serviceCategoryDetail');
//var backgroundColor = $('#backgroundColor');
//var description = $('#editDesc');
//
//var addBtnContainer = $('.modalBtnContainer-addEvent');
//var modifyBtnContainer = $('.modalBtnContainer-modifyEvent');
//
//var monthlyClaimGroupCode = editStart.substr(0,7);
//var visitPlanTime = editTime1 +" ~ " +editTime2;

$('#calendar').fullCalendar({
  header: {
    left: '',
    center: 'title',
    right: 'today prev,next'
  },
  defaultView: 'month',
  defaultDate: date.yyyymmdd(),
  eventLimit: 3,
      eventLimitText: "더보기",
      eventLimitClick: "popover",
      editable: false,
      droppable: false,
      height: "parent",
      eventSources: getTodayDate()
});
$(".fc-prev-button").on("click", function() {
  //alert("!!!");
  getViewDate();
});
$(".fc-next-button").on("click", function() {
  //alert("next!!!");
  getViewDate();
});
$(".fc-today-button").on("click", function() {
  //alert("next!!!");
  getViewDate();
});
 
function renderCalcEvent(data) {
  for (var i = 0; i < data.length; i++) {
    var locIdx = data[i].locationIdx;
    var eventIdx = data[i].eventIdx;
    var sdate = new Date(data[i].regDt).toString();
    var edate = new Date(data[i].regDt).toString();
    var col = "blue";
    var date = data[i].regDt;
 
    var event = {
      id: data[i].eventIdx,
      title: data[i].locationName,
      url: '/',
      start: moment(data[i].regDt),
      allDay: false,
      color: col
    };
 
    $('#calendar').fullCalendar('renderEvent', event);
  }
}

