/**
 * 방문일정 예상청구 금액
 */

$(document).ready(function(){
	
    $("#dataTable").DataTable({
        lengthChange: false,
    	  displayLength: 10
    });
    
	//년도와 달 별로 검색
	$('#smonth').change(function() {
		$('#dataTable tbody tr').empty();
		$('#sTable').css('display', 'none');
		$('#setitle').text('');
		var syear = $('#syear').val();
		var smonth = $(this).val();
		
		$.ajax({
			type: 'post',
			data:{syear: syear, smonth: smonth},
			dataType: 'json',
			url: '/elder/estimatedExpensesList',
			success: function(data){
				for(var i=0; i<data.length; i++){
					var test = "<tr><td>"+data[i].elderFinalServiceStatus+"</td>";
					test += "<td><a href='javascript:void(0)' class='elderId' data-elder-id ='"+ data[i].elerId +"' data-elder-name='"+data[i].elderName+"'>"+data[i].elderName+"</td>";
					test += "<td>"+data[i].elderBirthDate+"</td>";
					test += "<td>"+data[i].elderGender+"</td>";
					test += "<td>"+data[i].maxCost+"</td>";
					test += "<td>"+data[i].benefitCost+"</td>";
					test += "<td>"+data[i].nonBenefitCost+"</td>";
					test += "<td>"+data[i].totolNum+"</td></tr>";
		
					$('#dataTable tbody').append(test);
				}
			},
			error : function(error){
				console.log("error", error);
			}
		});
	});
	
	//수급자 이름 클릭시 변경 
	$(document).on('click', '.elderId', function(){
		
		$('#sTable tbody tr').remove();
		$('#sTable').css('display', 'block');
		var elderId = $(this).attr('data-elder-id');
		var elderName =$(this).attr('data-elder-name');
		var syear  = $('#syear').val();
		var smonth = $('#smonth').val();
		
		//console.log(elderId, elderName, syear, smonth);
		
		$('#setitle').text(elderName+"님 상세 정보 ");

		$.ajax({
			type: 'post',
			url: '/elder/detailestimatedExpenses',
			data : {elderId: elderId, syear: syear, smonth: smonth},
			dataType: 'json',
			success : function(data){
				var test;
				if(data == "" || data == null || data == undefined){
					test = "<tr><td colspan='5' align='center'>결과 값이 없습니다</td></tr>";	
					$('#sTable tbody').append(test);
			    }
				for(var i=0; i<data.length; i++){
					test = "<tr><td>"+ data[i].data +"</td>";
					test += "<td>"+data[i].serviceCategory+"</td>";
					test += "<td>"+data[i].servicedetail+"</td>";
					test += "<td>"+data[i].benefitCost+"</td>";
					test += "<td>"+data[i].nonBenefitCost+"</td></tr>";
					$('#sTable tbody').append(test);
				}
			},
			error : function(error){
				alert("에러 발생"+error);
				console.log(error);
			}
		});
		
	});
})
