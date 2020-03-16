/**
 * 
 */

	//수급자 정보를 가져옴
	$('.eldername').on('click', function(){
		$('tr').remove('#remove2');
		var id = $(this).attr('data-elder-id');
		var cost = $(this).attr('max-cost');
		var cname = $(this).attr('cname');
			$.ajax({
				type : 'POST',
				url : '/employee/velderbenefitcost',
				data: {elder_id : id},
				dataType : 'json',
				success : function(data){
						
				    var jsonStr = JSON.stringify(data);
				    var ali = JSON.parse(jsonStr);
				    $('#maxCost').text(cost);
				    $('#cname').text(cname);
				    for(var i=0; i<Object.keys(ali).length; i++){ 
				    	var str = '<tr id="remove2"><td>'+ ali[i].visit_service_category+'</td>';
				    	str += '<td>'+ ali[i].visit_service_time+'</td>';
				    	str += '<td>'+ ali[i].visit_plan_time+'</td>';
				    	str += '<td></td>';
				    	str += '<td></td>';
				    	str += '<td></td></tr>';
				    	$("#row1").after(str);
				    }
				},
				error : function(error){
					console.log("error", error);
				}
		});
	});
	
	//직원 분류해서 보여줌
	$('.eldername1').on("click", function(){
		
		var empcategory = $(this).attr('empcategory');
		var empservice = $(this).attr('empservice');
		$.ajax({
			url: "/employee/vemployeecategory",
			data : { empcategory : empcategory},
			dateType: "json",
			type: "GET",
			success : function(data){
				$('#empcategory').find('option').remove();
				$('#edit-type').find('option').remove();
				$('.modal-title').text("방문"+empservice);
				for(var i=0; i<data.length; i++){
					$('#empcategory').append("<option value=\'"+ data[i].employee_id + "\'>"
							+ data[i].employee_name +"</option>");
				   }
				
				if(empservice =='요양'){
					var test = "<option value=\'요양\'>인지활동</option>";
					test += "<option value=\'요양\'>가족케어</option>";
					test += "<option value=\'요양\'>치매가족휴가(0회)</option>";
					
					$('#edit-type').append(test);
				}
				if(empservice == '목욕'){
					var test = "<option value=\'차량이용(차량 내)\'>차량이용(차량 내)</option>";
						test += "<option value=\'차량이용(가정 내)\'>차량이용(가정 내)</option>";
						test += "<option value=\'차량미이용\'>차량미이용</option>";
						
					$('#edit-type').append(test);
				 }
				if(empservice == '간호'){
					$('#edit-type').parent().hide();
				}
			},
            error:function(){
                alert("ajax통신 실패!!!");
            }
		});
	}
	

