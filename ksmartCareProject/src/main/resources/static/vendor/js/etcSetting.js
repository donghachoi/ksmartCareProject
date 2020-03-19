//SELECT 색 변경
$('#edit-color').change(function () {
    $(this).css('color', $(this).val());
});

//필터
$('.filter').on('change', function () {
    $('#calendar').fullCalendar('rerenderEvents');
});

$("#type_filter").select2({
    placeholder: "선택..",
    allowClear: true
});

//datetimepicker
$("#visitPlanDate").datetimepicker({
     format: 'YYYY-MM-DD'     
});


$("#editTime1, #editTime2").datetimepicker({
    format: 'HH:mm'     
});

