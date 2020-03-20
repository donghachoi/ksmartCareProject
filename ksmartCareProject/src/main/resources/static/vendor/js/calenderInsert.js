$(document).ready(function(){
	$('.trigth').css('text-align', 'right');
	//수급자 총 금액과 잔액 가져옴 
	$('.eldername').on('click', function(){
		//$('tr').remove('#remove2');
		var id = $(this).attr('data-elder-id');
		var cost = $(this).attr('max-cost');
		var cname = $(this).attr('cname');
			$.ajax({
				type : 'POST',
				url : '/employee/velderbenefitcost',
				data: {elder_id : id, maxcost : cost},
				dataType : 'json',
				success : function(data){
				
				    $('#maxCost').text(data.maxcost);
				    $('#mixCost').text(data.subCost);
				    $('#ycname').html("<b>"+cname+"</b>");
				    $('input[name=elderId]').val(id);
				    $('input[name=maxcost]').val(cost);

				    $("#yoyangInt").text(data.yoyang);
				    $("#bathInt").text(data.bath);
				    $("#nurseInt").text(data.nurse);
				    
					$("#bcost1").text(data.getYoyang);
					$("#bcost2").text(data.getbath);
					$("#bcost3").text(data.getnurse);
					
					$("#mcoat1").text(data.mgetYoyang);
					$("#mcoat2").text(data.mgetbath);
					$("#mcoat3").text(data.mgetnurse);
					
					$("#tolnum").text(data.tolnum);
					$("#tolnum2").text(data.BenefitCost);
					$("#tolnum3").text(data.NonBenefitCost);

				    $('#syear').val(String(data.syear)).attr("selected", "selected");
				    $('#smonth').val(String(data.smonth)).attr("selected", "selected");
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
				success : function(data){
				
				    $('#maxCost').text(data.maxcost);
				    $('#mixCost').text(data.subCost);
				    $('#ycname').html("<b>"+cname+"</b>");
				    $('input[name=elderId]').val(id);
				    $('input[name=maxcost]').val(cost);

				    $("#yoyangInt").text(data.yoyang);
				    $("#bathInt").text(data.bath);
				    $("#nurseInt").text(data.nurse);
				    
					$("#bcost1").text(data.getYoyang);
					$("#bcost2").text(data.getbath);
					$("#bcost3").text(data.getnurse);
					
					$("#mcoat1").text(data.mgetYoyang);
					$("#mcoat2").text(data.mgetbath);
					$("#mcoat3").text(data.mgetnurse);
					
					$("#tolnum").text(data.tolnum);
					$("#tolnum2").text(data.BenefitCost);
					$("#tolnum3").text(data.NonBenefitCost);

				    $('#syear').val(String(data.syear)).attr("selected", "selected");
				    $('#smonth').val(String(data.smonth)).attr("selected", "selected");
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
				
				for(var i=0; i<data.length; i++){
					$('#employeeId').append("<option value=\'"+ data[i].employeeId + "\'>"
							+ data[i].employeeName +"</option>");
				   }
				
				if(empservice =='요양'){
					var test = "<option value=\'인지활동\'>인지활동</option>";
					test += "<option value=\'가족케어\'>가족케어</option>";
					test += "<option value=\'치매가족휴가\'>치매가족휴가</option>";
					
					$('#serviceCategoryDetail').append(test);
				}
				if(empservice == '목욕'){
					var test = "<option value=\'차량이용(차량 내)\'>차량이용(차량 내)</option>";
						test += "<option value=\'차량이용(가정 내)\'>차량이용(가정 내)</option>";
						test += "<option value=\'차량미이용\'>차량미이용</option>";
						
					$('#serviceCategoryDetail').append(test);
				 }
				if(empservice == '간호'){
					$('#serviceCategoryDetail').parent().hide();
				}				
//				$('input[name=visitServiceCategory]').val(empservice);
//				$('input[name=elderId]').val(elderId);
//				$('input[name=cname]').val($('#ycname').text());
			},
            error:function(){
                alert("ajax통신 실패!!!");
            }
		});
	});
	
	//--직원 분류해서 보여줌
})