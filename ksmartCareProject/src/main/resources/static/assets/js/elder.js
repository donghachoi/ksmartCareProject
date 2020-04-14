/**
 * elderList 자바스크립트 파일입니다.
 * 
 */


	/*[검사] 오늘 이상이면 테두리 빨강으로 바꾸기*/
	var putRedLine = function(array){
		var today = new Date();
    	var dd = today.getDate();
    	var mm = today.getMonth()+1;
    	var mmAgo = today.getMonth();
    	var yyyy = today.getFullYear();
    	if(dd<10) {
    	    dd='0'+dd
    	}if(mm<10) {
    	    mm='0'+mm
    	}if(mmAgo<10) {
    		mmAgo='0'+mmAgo
    	}
    	var formatToday = yyyy+"-"+mm+"-"+dd;
    	var formatOneMonthAgo = yyyy+"-"+mmAgo+"-"+dd;
		for(var i=0;i<array.length;i++){
			
			//검사 시행 날짜가 
			if(array[i].text()<formatOneMonthAgo){
				array[i].css('border','solid red')
				array[i].append("</br><span>한달에 한번 시행해 주기 바랍니다.</span>")
			}else if(array[i].text()=="시행전"){
				array[i].css('border','solid green')
				array[i].append("</br><span>검사일정을 잡아주세요.</span>")
			}else if(array[i].text()>formatToday){
				array[i].css('border','solid blue')
				array[i].append("</br><span>검사예정입니다.</span>")
			}else{
				array[i].css('border','')
			}
		}
	}

	/*[공통] 삭제 메서드*/
	var deleteEvent = function(){
		$('.deleteBtn').click(function(){
			var url = "";
			var map = {};
			var value = $(this).parents('tr').find('input').val();
 			var subValue = value.substring(2,3)
			console.log(subValue);
			if(subValue=='c'){
				url = "/employee/statusDelete";
			}else if(subValue=='l'){
				url = "/employee/levelDelete";
			}else if(subValue=='e'){
				url = "/employee/deleteRegularCheck";
			}
			map.code = value;
			$.ajax({
				type 	: 	'POST',
				url		:	url,
				data	:	JSON.stringify(map),
				contentType	:	'application/json',
				success	:	function(data){
					console.log('삭제완료')
					if(subValue=='l'){
						levelListInModal(elderIdInLevel)
					}
					else if(subValue=='c'){
						statusListInModal($('#elderIdInStatus').val());
					}
					else if(subValue=='e'){
						regularCheckList($('#elderIdInCheck').val());
					}
				},
				
				error	:	function(error){
					console.log("error", error)
					alert("삭제실패.")
				}
			})
			
		})
	} 
	
	/* [계약]계약관리 리스트 누르면 아래 수정 화면으로 바뀌고 값 뿌려주기 */
	var UpdateStatus = function(){
		
		$('.elderStatusRow').click(function(){
			var statusCode = $(this).find('input').val();
			var statusSort = $(this).find('td:eq(0)').text();
			var serviceStartDate = $(this).find('td:eq(1)').text();
			var serviceEndDate = $(this).find('td:eq(2)').text();
			$('#elderStatusSelect').val(statusSort);
			elderStatusEvent(statusSort);
			$('input[name="serviceStartDate"]').val(serviceStartDate);
			$('input[name="serviceEndDate"]').val(serviceEndDate);
			$('#serviceStatusCodeInStatus').val(statusCode);
			/* 리스트를 누르면 수정버튼으로 바뀌는 이벤트 */
			var statusUpdate = $('#statusUpdate');
			var statusInsert = $('#statusInsert');
			var statusCancel = $('#statusCancel');
			changeBtn(statusUpdate,statusInsert,statusCancel);
			statusCancel.click(function(){
				$('#elderStatusSelect').val("")
				$('#statusStartDate').val("")
				$('#statusEndDate').val("")
			})
		})
		

	}	

	/* [계약]모달안 에 해당 수급자 계약상태 리스트 뿌리기 */
	var statusListInModal = function(id){
	  	var map = {};
		map.elderId = id;
		$.ajax({
		type 	: 	'POST',
		url		:	'/elderDetailList',
		data	:	JSON.stringify(map),
		contentType	:	'application/json',
		success	:	function(data){
			
			/* 수급자 계약 상태 리스트  */
			var status = data.elderstatusList
			$('#elderStatus').empty();
			var elderStatusRowHtml = '';
			
			for(var i=0;i<status.length;i++){
				var elderName = status[i].elderName
				var elderId = status[i].elderId
				var serviceStatusCode = status[i].serviceStatusCode
				var statusSort = status[i].serviceStatus
				var serviceStartDate = status[i].serviceStartDate
				var serviceEndDate = status[i].serviceEndDate
				elderStatusRowHtml += '<tr class="elderStatusRow">';
				elderStatusRowHtml += '<input type="hidden" name="serviceStatusCode" value="'+serviceStatusCode+'">';
				elderStatusRowHtml += '<td>'+statusSort+'</td><td>'+serviceStartDate+'</td>';
				elderStatusRowHtml += '<td>'+serviceEndDate+'</td>';
				elderStatusRowHtml += '<td><button type="button" style="display: none;" class="deleteBtn btn btn-danger btn-xs">삭제</button></td></tr>';
			}
				
				$('#elderStatus').append(elderStatusRowHtml);
				
				/*계약 리스트에 수정이벤트 걸기*/
				UpdateStatus();
				
				/* 계약 등록 */
				insertStatus();
				
				/*삭제버튼 생기게하는 이벤트*/
				deleteBtnEvent('.elderStatusRow','td:eq(3)');
				/*누르면 삭제 되는 이벤트*/
				deleteEvent();


			}
			,error	:	function(error){
			console.log("error", error)
			}
    	})
	  }
	
	/* [계약]계약관리 등록 함수 */
	var insertStatus = function(){
		
		  $('#statusInsert').click(function(){
			  if(checkEmpty(elderStatusSelect)){
			  }else{
				  return false;
			  }if(checkEmpty(statusStartDate)){
			  }else{
				  return false;
			  }if(checkEmpty(statusEndDate)){
			  }else{
				  return false;
			  }
			  $.ajax({
					type 	: 	'POST',
					url		:	'/employee/insertStatus',
					data	:	$("#status").serialize(),
					traditional :true,
					success	:	function(data){
						alert('등록완료')
						statusListInModal($('#elderIdInStatus').val());
						$('#elderStatusSelect').val('');
						$('#statusStartDate').val('');
						$('#statusEndDate').val('');
							},
	
				error	:	function(error){
					console.log("error", error)
						console.log("등록실패.")
				}
	 		})
		  })
		}

    /* [공통]수정화면 뿌릴때 버튼 수정버튼 생기기 */
    var changeBtn = function(updateBtn,insertBtn,cancelBtn){
		$(insertBtn).css('display','none');
		$(updateBtn).show();
		$(cancelBtn).show();
		$(cancelBtn).click(function(){
			$(insertBtn).show();
			$(updateBtn).hide();
			$(cancelBtn).hide();
			$(this).parent().find('input').each(function(){
				  if($(this).attr('type')=='checkbox'){
					  this.checked = false;
					  this.disabled = false;
				  }else{
					  $(this).val('');
				  }
			})
		})
    }
    
   	/* [공통}오늘 날짜 구하기 */
    var getToday = function(){
	    var today = new Date();
    	var dd = today.getDate();
    	var mm = today.getMonth()+1;
    	var yyyy = today.getFullYear();
    	if(dd<10) {
    	    dd='0'+dd
    	} 

    	if(mm<10) {
    	    mm='0'+mm
    	} 
    	return yyyy+"-"+mm+"-"+dd;
    }
    
	  /*[공통] 체크박스 체크된 년도로 날짜 계산하기 */
	  var changeDate = function(startDate,endDate,inputName){
			var levelStartDate = startDate;
			var levelLastDate = endDate;

  	  inputName.change(function(){
  		  if(levelStartDate.val() == null || levelStartDate.val() ==""){
  				levelStartDate.val(getToday());
				var year = levelStartDate.val().substring(0,4)
				var monthToDay = levelStartDate.val().substring(4)
				var yearInt = parseInt(year)
				var checkedInt = parseInt($(this).val())
				levelLastDate.val((yearInt+checkedInt)+monthToDay)
				}else{
					var year = levelStartDate.val().substring(0,4)
					var monthToDay = levelStartDate.val().substring(4)
					var yearInt = parseInt(year)
					var checkedInt = parseInt($(this).val())
					levelLastDate.val((yearInt+checkedInt)+monthToDay)
				}
		});
	  	startDate.change(function(){
			var levelStartDate = startDate;
			var levelLastDate = endDate;
	  		var year = levelStartDate.val().substring(0,4)
			var monthToDay = levelStartDate.val().substring(4)
			var yearInt = parseInt(year)
			var checkedInt = parseInt($(inputName).val())
			levelLastDate.val((yearInt+checkedInt)+monthToDay)
			console.log(levelLastDate.val())
	  	})
	  }
    
    /* [계약]계약 상태가 바뀔때 input 박스와 버튼이 바뀌는 이벤트 */
    var elderStatusEvent = function(value){
    		
			var supplyHtml = '';
			supplyHtml += '<div><label>계약기간<div style="margin:0px; display:inline; margin-left:50px;">';
			supplyHtml += '<label><input type="radio" name="serviceTermYear" value="1" checked><span>1년</span></label>';
			supplyHtml += '<label><input type="radio" name="serviceTermYear" value="2" ><span>2년</span></label>';
			supplyHtml += '<label><input type="radio" name="serviceTermYear" value="3" ><span>3년</span></label>';
			supplyHtml += '<label><input type="radio" name="serviceTermYear" value="4" ><span>4년</span></label></div>';
			supplyHtml += '<div><input class="date" name="serviceStartDate" id="statusStartDate">~<input class="date" name="serviceEndDate" id="statusEndDate"></div></label>';
			supplyHtml += '<button type="button" id="statusInsert" class="btn btn-primary btn-xs" style="margin: 0 auto;" >등록하기</button>';
			supplyHtml += '<button class="btn btn-metis-3 btn-xs" type="button" style="display: none" id="statusUpdate">수정하기</button>';
			supplyHtml += '<button class="btn btn-metis-1 btn-xs" type="button" style="display: none" id="statusCancel">취소</button></div>';
			
			var cancelHtml = '';
			cancelHtml += '<div><label>시작날짜<input class="date" name="serviceStartDate" id="statusStartDate"></label>';
			cancelHtml += '<button type="button" id="statusInsert" class="btn btn-primary btn-xs" style="margin: 0 auto;" >등록하기</button>';
			cancelHtml += '<button class="btn btn-metis-3 btn-xs" type="button" style="display: none" id="statusUpdate">수정하기</button>';
			cancelHtml += '<button class="btn btn-metis-1 btn-xs" type="button" style="display: none" id="statusCancel">취소</button></div>';
			
			if(value==""){
				return false;
			}
			if(value=='사망'||value=='해지'||value=='타기관'){
				$('#elderStatusDiv').find('div').remove();
				$('#elderStatusDiv').append(cancelHtml)
				
			}else{
				$('#elderStatusDiv').find('div').remove();
				$('#elderStatusDiv').append(supplyHtml);
				changeDate($('input[name="serviceStartDate"]'), $('input[name="serviceEndDate"]'), $('input[name="serviceTermYear"]'));
			}
			 $( ".date" ).datepicker({
			  	});
			/* [계약] 등록 */
		    insertStatus();
		    
	    	/*[계약] 수정*/
	    	$('#statusUpdate').click(function(){
	    		console.log('hi')
	    		$.ajax({
					type 	: 	'POST',
					url		:	'/employee/statusUpdate',
					data	:	$("#status").serialize(),
					traditional :true,
					success	:	function(data){
						statusListInModal($('#elderIdInStatus').val());
						console.log('success')
						
					},
        			error	:	function(error){
        				console.log("error", error)
        			}
         		})
	    	})
    }
	
    
    	/* [공통]삭제 버튼 마우스 on이벤트 */
    	var deleteBtnEvent = function(rowClass,tdNum){
    	
		   	 $(rowClass).find(tdNum).mouseover(function(){
				 $(this).find('button').css('display','block')
				
			})
			$(rowClass).find(tdNum).mouseout(function(){
				 $(this).find('button').css('display','none')
				
			})
	    }
    
    
	  /* [공통]유효성 검사 함수 */
	  var checkEmpty = function(id){
		  $(id).siblings('span').remove();
			if($(id).val()==null || $(id).val()==""){
				$(id).focus();
				$(id).parent().append("<span style=\"color :red;\">입력해주세요.</span>");
				
				return false;
			}else{
				
				return true;
			}
		}
	  
	  /* [등급]수급자 레벨 리스트 뿌리는 함수  */
	  var levelListInModal = function(id){
	  var map = {};
	  var Id = $(id).val()
	  map.elderId = Id;
	  $.ajax({
			type 	: 	'POST',
			url		:	'/elderDetailList',
			data	:	JSON.stringify(map),
			contentType	:	'application/json',
			success	:	function(data){
				$('#elderNameInLevel').val(data.elderOenList.elderName);
				$('#elderIdInLevel').val(data.elderOenList.elderId);
				//등급인정 모달에 리스트 뿌리기.
				$('#elderServiceLevel').empty();
				for(var i = 0;i<data.elderOneLevelHistory.length;i++){
					var elderLevelHistoryCode = data.elderOneLevelHistory[i].elderLevelHistoryCode;
					var elderServiceApprovalLevel = data.elderOneLevelHistory[i].elderServiceApprovalLevel;
					var elderServiceApprovalStartDate = data.elderOneLevelHistory[i].elderServiceApprovalStartDate;
					var elderServiceApprovalEndDate = data.elderOneLevelHistory[i].elderServiceApprovalEndDate;
					var elderServiceApprovalNumber = data.elderOneLevelHistory[i].elderServiceApprovalNumber;
					var elderServiceApprovalCategory = data.elderOneLevelHistory[i].elderServiceApprovalCategory;
					var elderServiceApprovalCategory2 = data.elderOneLevelHistory[i].elderServiceApprovalCategory2;
					$('#elderServiceLevel').append(
							"<tr class=\"elderLevelRow\"><input type=\"hidden\" name=\"levelCode\" value=\""+elderLevelHistoryCode+"\">" +
							"<td>"+elderServiceApprovalLevel+"</td><td>"+elderServiceApprovalStartDate+"</td>" +
							"<td>"+elderServiceApprovalEndDate+"</td><td>"+elderServiceApprovalNumber+"</td>" +
							"<td>"+elderServiceApprovalCategory2+"</td><td>"+elderServiceApprovalCategory+"</td>"+
							"<td style=\"align :center;\">" +
							"<button id=\"levelDeleteBtn\" type=\"button\" style=\"display: none;\" class=\"deleteBtn btn btn-danger btn-xs\">삭제" +
							"</button></td></tr>");						
				}
				
				deleteBtnEvent('.elderLevelRow','td:eq(6)');
				deleteEvent();
				updateLevel();
				var levelUpdate = $('#levelUpdate');
	   			var statusInsert = $('#statusInsert');
	   			var levelCancel = $('#levelCancel');
	   			changeBtn(levelUpdate,levelInsert,levelCancel);
			},
			error	:	function(error){
				console.log("error", error)
			}
		})
  	}
	  
		/*
		* [등급]수급자 등급 및 인정기간 수정 하기 위해 아래 화면에 뿌려주기--------------------------------------------
		* 
		* */
	  var updateLevel = function(){
		  $('.elderLevelRow').click(function(){
			  $("#levelGrade").siblings('span').remove();
			  var levelCode = $(this).find('input[name=levelCode]').val();
			  
			  var elderServiceApprovalLevelDetail = $(this).find('td:eq(0)').text();
			  var elderServiceApprovalStartDateDetail = $(this).find('td:eq(1)').text();
			  var elderServiceApprovalEndDateDetail = $(this).find('td:eq(2)').text();
			  var elderServiceApprovalNumber = $(this).find('td:eq(3)').text();
			  var elderServiceApprovalCategoryDetail = $(this).find('td:eq(4)').text();
			  var elderServiceApprovalCategory2Detail = $(this).find('td:eq(5)').text();
			  $('#elderCodeInLevel').val(levelCode);
			  $('#levelGrade').val(elderServiceApprovalLevelDetail);
			  $('#approvalNumber').val(elderServiceApprovalNumber)
			  $('#elderServiceApprovalStartDate').val(elderServiceApprovalStartDateDetail);
			  $('#elderServiceApprovalEndDate').val(elderServiceApprovalEndDateDetail);
			  $('#approvalCategory').val(elderServiceApprovalCategoryDetail);
			  $('#approvalCategory2').val(elderServiceApprovalCategory2Detail)
			  var levelUpdate = $('#levelUpdate');
			  var statusInsert = $('#statusInsert');
			  var levelCancel = $('#levelCancel');
			  changeBtn(levelUpdate,levelInsert,levelCancel);
			  $('#levelCancel').click(function(){
				  $('#levelGrade').val('');
				  $('#approvalNumber').val('');
				  $('#elderServiceApprovalStartDate').val('');
				  $('#elderServiceApprovalEndDate').val('');
				  $('#approvalCategory').val('진행상태 선택');
				  $('#approvalCategory2').val('진행결과 선택');
				  
			  })
		  })
	  }
		
	  
	  /*[검사] 리스트 함수*/
	  var regularCheckList =function(elderIdVal){
		  $('#regularCheckRow').empty();
		  var map = {};
		  var elderId = elderIdVal;
		  map.elderId = elderId;
		  
		  $.ajax({
			  type			:	'POST',
			  url			:	'/employee/regularCheck',
			  data			:	JSON.stringify(map),
			  contentType	:	'application/json',
			  success		:	function(data){
				  
				  regularCheckHtml = "";
				  for(var i=0;i<data.length;i++){
					  regularCheckHtml += "<tr class=\"regularCheckRow\">";
					  regularCheckHtml += "<input type=\"hidden\" name=\"elderRegularCheckCode\" value=\""+data[i].elderRegularCheckCode+"\"\/>";
					  regularCheckHtml += "<input type=\"hidden\" name=\"elderId\" value=\""+data[i].elderId+"\"\/>";
					  regularCheckHtml += "<input type=\"hidden\" name=\"elderName\" value=\""+data[i].elderName+"\"\/>";
					  regularCheckHtml += "<td>"+(i+1)+"</td>";
					  regularCheckHtml += "<td>"+data[i].elderRegularCheckCategory+"</td>";
					  regularCheckHtml += "<td>"+data[i].elderRegularCheckPlanDate+"</td>";
					  regularCheckHtml += "<td>"+data[i].elderRegularCheckDoingDate+"</td>";
					  regularCheckHtml += "<td>"+data[i].elderRegularCheckRegistrationDate+"</td>";
					  regularCheckHtml += "<td><button type=\"button\" style=\"display: none;\" class= \"deleteBtn btn btn-danger btn-xs\">삭제</button></tr>";
					  regularCheckHtml += "</tr>";
				  }
				  
				  $('#regularCheckRow').append(regularCheckHtml);
				  deleteBtnEvent('.regularCheckRow','td:eq(5)');
				  updateRegular();
				  deleteEvent();
			  },
			  error		:	function(error){
				  console.log("error", error)
			  }
    	  })
	  }
	  /*[검사] 수정을 위해 아래 화면에 뿌리기*/
	  var updateRegular = function(){
		  $('.regularCheckRow').click(function(){//검사 리스트를 눌렀을때 아래 수정 화면으로 값들 넘겨주기.
			  
			  $('#elderCodeInCheck').val($(this).find('input[name=elderRegularCheckCode]').val())
			  $('#elderIdInCheck').val($(this).find('input[name=elderId]').val())
			  $('#elderNameInCheck').val($(this).find('input[name=elderName]').val())
			  $('#elderRegularCheckPlanDate').val($(this).find('td:eq(2)').text())
			  $('#elderRegularCheckDoingDate').val($(this).find('td:eq(3)').text())
			  var listCategory= $(this).find('td:eq(1)').text();
			  
			  
			  changeThecheckdbox(listCategory)
			  var insertRegularCheck = $('#insertRegularCheck');
			  var updateRegularCheck = $('#updateRegularCheck');
			  var cancelRegularCheck = $('#cancelRegularCheck');
			  changeBtn(updateRegularCheck,insertRegularCheck,cancelRegularCheck);
			  /*[검사] 삭제*/
			  
		  })
	  }
	  
	  //[검사]체크박스 하나씩 체크하는 이벤트
	  var changeThecheckdbox = function(listCategory){
		  $('input:checkbox(name=elderRegularCheckCategory)').each(function(){
			  this.disabled = false;
			  this.checked = false; 					//체크박스 초기화
			  var checkBoxCategory = $(this).val();		//체크박스 값
			  if(listCategory==checkBoxCategory){		//체크박스와 클릭한 정보의 카테고리 일치하면 그 해당 체크박스 체크
				  this.checked = true;
			  }
			  this.disabled = true;
		  })
	  }
	  
	  
/***********document ready *************************************************************************************************************************************************/
      $(document).ready(function(){
    	  
    	  
    	  /*[검색] 셀렉트 박스 바뀔때 input 박스 바꾸는 */
    	  
    	  //성별 HTML
    	  var searchGenderRadioBox = "";
    	  searchGenderRadioBox += "<div id=\"genderRadio\">";
    	  searchGenderRadioBox += "<div class=\"col-lg-3\"><label for=\"male\">남</label>";
    	  searchGenderRadioBox += "<input class=\"form-control\" id=\"male\" type=\"radio\" name=\"sv\" value=\"남\"></div>";
    	  searchGenderRadioBox += "<div class=\"col-lg-3\"><label for=\"female\">여</label>";
    	  searchGenderRadioBox += "<input class=\"form-control\" id=\"female\" type=\"radio\" name=\"sv\" value=\"여\"></div></div>";
    	  //InputBox HTML
    	  var searchInput = "";
    	  searchInput = "<input class=\"form-control\" type=\"text\" name=\"sv\" id=\"sv\">";
    	  //생년월일 inputbox
    	  var searchBirthInput = "";
    	  searchBirthInput += "<div class=\"col-lg-6\">";
    	  searchBirthInput += "<input class=\"date form-control\" name=\"elderSearchBeginBirthdate\" id=\"elderSearchBeginBirthdate\" placeholder=\"부터\">";
    	  searchBirthInput += "</div><div class=\"col-lg-6\">";
    	  searchBirthInput += "<input class=\"date form-control\" name=\"elderSearchEndBirthdate\" placeholder=\"까지\"></div>";
    	  
    	  //검색 select box 변경 시 input box 변경되는 js 
    	  $('#sk').change(function(){
    		  $('#searchInputBox').empty();		
    		  
    		  var selectVal = $('#sk option:selected').text();
    		  if(selectVal=='성별'){
    			  $('#searchInputBox').append(searchGenderRadioBox);		  
    		  }else if(selectVal=='생년월일'){
    			  $('#searchInputBox').append(searchBirthInput);
    			  $( ".date" ).datepicker({});
    		  }else{
    			  $('#searchInputBox').append(searchInput);		  
    		  }
    	  })
    	  
    	  
    	  /*[검사] 수정*/
    	  $('#updateRegularCheck').click(function(){
    		  regularCheckFormData = $('#regularCheckForm').serialize(); // get 방식으로 name=value& EX) ↓↓↓↓
    		  															 // elderRegularCheckPlanDate=2020-04-23&elderRegularCheckDoingDate=2020-05-28&
    		  regularCheckFormData += "&elderRegularCheckCategory="+$('input:checkbox[name=elderRegularCheckCategory]:checked').val()+"&";
    		  regularCheckFormData += "elderRegularCheckPlanDate="+$('#elderRegularCheckPlanDate').val()+"&";
    		  $.ajax({
    			  type 	: 	'POST',
    			  url	:	'/employee/updateRegularCheck',
    			  data	:	JSON.stringify(regularCheckFormData),
    			  success	:	function(data){
    				  console.log(data)
    				  regularCheckList($('#elderIdInCheck').val());
    			  },
    			  error	:	function(error){
    				  console.log("error", error)
    			  }
    		  })
    	  })  
    	  
    	  /*[검사] 등록*/
    	  
    	  $('#insertRegularCheck').click(function(){
    		  var elderRegularCheckPlanDate = $('#elderRegularCheckPlanDate');
    		  var elderRegularCheckDoingDate = $('#elderRegularCheckDoingDate');
    		  $('#elderRegularCheckCategory3').siblings('span').remove();
    		  var chk = $('input:checkbox[name=elderRegularCheckCategory]:checked');
    		  if(chk.length==0){
    			  $('#elderRegularCheckCategory3').parent().append("<span style=\"color :red;\">      최소 하나는 체크 해주세요.</span>");;
    			  return false;
    		  }
    		  if(checkEmpty(elderRegularCheckPlanDate)){
    		  }else{
    			  return false;
    		  }
    		  if(checkEmpty(elderRegularCheckDoingDate)){
    		  }else{
    			  
    			  return false;
    		  }
    		  //배열에 값 담기
    		  formDataTest = [];
    		  var elderName = $('input[name=elderName]').val();
    		  var elderId = $('input[name=elderId]').val();
			  var elderRegularCheckDoingDate = $('#elderRegularCheckDoingDate');
			  var elderRegularCheckPlanDate = $('#elderRegularCheckPlanDate');
			  for(var i=0;i<chk.length;i++){
				  formDataTest.push({
					  elderName : elderName,
					  elderId : elderId,
					  elderRegularCheckCategory : chk[i].value,
					  elderRegularCheckPlanDate : elderRegularCheckPlanDate.val(),
					  elderRegularCheckDoingDate : elderRegularCheckDoingDate.val()
				  });
			  }
			  console.log(formDataTest)
			  $.ajax({
    			  type 	: 	'POST',
    			  url	:	'/employee/insertRegularCheck',
    			  data	:	JSON.stringify(formDataTest),
    			  contentType : 'application/json',
    			  success	:	function(data){
    				  
    				  $('input:checkbox[name=elderRegularCheckCategory]').each(function(){
    					  $(this).attr('checked',false)
    				  })
    				  elderRegularCheckPlanDate.val('')
    				  elderRegularCheckDoingDate.val('')
    				  alert('등록되었습니다.')
    				  regularCheckList(elderId);
    			  },
    			  error	:	function(error){
    				  console.log("error", error)
    			  }
    		  })
    	  })//[검사등록]클릭이벤트 끝 
    	  	
    	  
    	  
    	  
    	  
    	  /*[검사] 리스트*/
    	 $('#regularCheck').click(function(){
    		 regularCheckList($(this).parents('div').find('#elderId').text());
    	  })
    	  
    	  
    	  
    	  /* 등급 및 인정기간 수정 */
    	  $('#levelUpdate').click(function(){
    		  $.ajax({
	    			type 	: 	'POST',
	    			url		:	'/employee/levelUpdate',
					data	:	$("#level").serialize(),
					traditional :true,
					success	:	function(data){
						alert("수정완료.")
						levelListInModal(elderIdInLevel)
					},
					error	:	function(error){
						console.log("error", error)
						alert("수정실패.")
					}
    		  })
    	  })
    	  
    	 	/* [등급]등급 및 인정기간 입력 */
        	$('#levelInsert').click(function(){
        		if($('#elderIdInLevel').val()==""){
        			alert('수급자를 선택해주세요.')
        			return false;
        		}
        		if(checkEmpty(levelGrade)){
        		}else{
        			return false;
        		}
        		if(checkEmpty(approvalNumber)){
        		}else{
        			return false;
        		}
        		if(checkEmpty(approvalCategory)){
        		}else{
        			return false;
        		}
        		if(checkEmpty(approvalCategory2)){
        		}else{
        			return false;
        		}
        		if(checkEmpty(elderServiceApprovalStartDate)){
        		}else{
        			return false;
        		}
        		if(checkEmpty(elderServiceApprovalEndDate)){
        		}else{
        			return false;
        		}
        		
        		$.ajax({
					type 	: 	'POST',
					url		:	'/employee/levelInsert',
					data	:	$("#level").serialize(),
					traditional :true,
					success	:	function(data){
						alert("등록되었습니다.")
						levelListInModal(elderIdInLevel);
					},
        			error	:	function(error){
        				console.log("error", error)
						alert("등록 안됬습니다.")
        			}
         		})
        	})
            	
    	
	  	/* [메인] 메인 리스트를 누르면 해당 수급자 상세정보 가져오기 */
		$('#elderRow tr').click(function(){
			$('#defaultTable').css('display','block');
			var map = {};
			var Id = $(this).find('td:eq(1)').text()
			map.elderId = Id;
			$.ajax({
				type 	: 	'POST',
				url		:	'/elderDetailList',
				data	:	JSON.stringify(map),
				contentType	:	'application/json',
				success	:	function(data){
					
					
					/* [계약] 리스트  */
					var status = data.elderstatusList
					$('#elderStatus').empty();
					var elderStatusRowHtml = '';
					for(var i=0;i<status.length;i++){
						var elderName = status[i].elderName
						var elderId = status[i].elderId
						var serviceStatusCode = status[i].serviceStatusCode
						var statusSort = status[i].serviceStatus
						var serviceStartDate = status[i].serviceStartDate
						var serviceEndDate = status[i].serviceEndDate
						elderStatusRowHtml += '<tr class="elderStatusRow">';
						elderStatusRowHtml += '<input type="hidden" name="serviceStatusCode" value='+serviceStatusCode+'>';
						elderStatusRowHtml += '<td>'+statusSort+'</td><td>'+serviceStartDate+'</td>';
						elderStatusRowHtml += '<td>'+serviceEndDate+'</td>';
						elderStatusRowHtml += '<td><button type="button" style="display: none;" class="deleteBtn btn btn-danger btn-xs">삭제</button></td></tr>';
					}
					$('#elderStatus').append(elderStatusRowHtml);
					
					$('#elderIdInStatus').val(status[0].elderId);
					$('#elderNameInStatus').val(status[0].elderName);
						
					/* 계약관리 내에 셀렉박스 바뀔때 인풋 박스 변경 */
					$('#elderStatusSelect').change(function(){
						elderStatusEvent($(this).val());
					})
					
					/* 계약관리 리스트 삭제 부분에 마우스 올리면 삭제 버튼 나타남. */ 
					deleteBtnEvent('.elderStatusRow','td:eq(3)');
					
					/* 계약관리 리스트 누르면 아래 수정 화면으로 바뀌고 값 뿌려주기 */
					UpdateStatus();
					insertStatus();
					
					//등급인정 모달에 리스트 뿌리기.
					$('#elderNameInLevel').val(data.elderOenList.elderName);
					$('#elderIdInLevel').val(data.elderOenList.elderId);
					$('#elderServiceLevel').empty();
					for(var i = 0;i<data.elderOneLevelHistory.length;i++){
						var elderLevelHistoryCode = data.elderOneLevelHistory[i].elderLevelHistoryCode;
						var elderServiceApprovalLevel = data.elderOneLevelHistory[i].elderServiceApprovalLevel;
						var elderServiceApprovalStartDate = data.elderOneLevelHistory[i].elderServiceApprovalStartDate;
						var elderServiceApprovalEndDate = data.elderOneLevelHistory[i].elderServiceApprovalEndDate;
						var elderServiceApprovalNumber = data.elderOneLevelHistory[i].elderServiceApprovalNumber;
						var elderServiceApprovalCategory = data.elderOneLevelHistory[i].elderServiceApprovalCategory;
						var elderServiceApprovalCategory2 = data.elderOneLevelHistory[i].elderServiceApprovalCategory2;
						$('#elderServiceLevel').append("<tr class=\"elderLevelRow\"><input type=\"hidden\" name=\"levelCode\" value=\""+elderLevelHistoryCode+"\"><td>"+elderServiceApprovalLevel+"</td><td>"+elderServiceApprovalStartDate+"</td><td>"+elderServiceApprovalEndDate+"</td><td>"+elderServiceApprovalNumber+"</td><td>"+elderServiceApprovalCategory2+"</td><td>"+elderServiceApprovalCategory+"</td>"
							+"<td style=\"align :center;\"><button id=\"elderLevelDelete\" type=\"button\" style=\"display: none;\" class=\"deleteBtn btn btn-danger btn-xs\">삭제</button></td></tr>");						
					}
					
					updateLevel();
					
					/* 등급인정 관리 리스트 삭제 부분에 마우스 올리면 삭제 버튼 나타남 */
					deleteBtnEvent('.elderLevelRow','td:eq(6)');
					
					//수급자 정규검사
					var	fallDownCheckDate = data.fallDownCheck.elderRegularCheckDoingDate;
					var bedsoreCheckDate = data.bedsoreCheck.elderRegularCheckDoingDate
					var functionCheckDate = data.functionCheck.elderRegularCheckDoingDate
					var needsCheckDate = data.needsCheck.elderRegularCheckDoingDate
					
					//수급자 수급 인정기간 
					var serviceEndDate = data.elderLastStatus.serviceEndDate;
					var serviceStartDate = data.elderFirstStatusDate.serviceStartDate;
					
					//수급자 등급 히스토리
					var elderServiceApprovalEndDate = data.elderLastLevel.elderServiceApprovalEndDate;
					var elderServiceApprovalStartDate = data.elderLastLevel.elderServiceApprovalStartDate;
					//수급자 상세정보 (모달 밖에).
					var elderId = data.elderOenList.elderId
					var elderName = data.elderOenList.elderName
					var elderBirthDate = data.elderOenList.elderBirthDate
					var elderGender = data.elderOenList.elderGender
					var elderFinalServiceApprovalLevel = data.elderOenList.elderFinalServiceApprovalLevel
					var elderFinalServiceStatus = data.elderOenList.elderFinalServiceStatus
					var elderDisease = data.elderOenList.elderDisease
					var elderCoinsurance = data.elderOenList.elderCoinsurance
					var totalVisits = data.elderOenList.totalVisits
					var totalServiceTime = data.elderOenList.totalServiceTime
					var serviceLocalmanageCode = data.elderOenList.serviceLocalmanageCode
					var serviceLocalmanageName = data.elderOenList.serviceLocalmanageName
					var longTermCareNumver = data.elderOenList.longTermCareNumver
					var longTermCareValidity = data.elderOenList.longTermCareValidity
					var elderPhone = data.elderOenList.elderPhone
					var elderAdressForStay = data.elderOenList.elderAdressForStay
					var elderAdressForPost = data.elderOenList.elderAdressForPost
					var elderRemarks = data.elderOenList.elderRemarks
					var elderGroups = data.elderOenList.elderGroups
					$('#elderId').text(elderId);
					$('#elderName').text(elderName);
					$('#elderGender').text(elderGender);
					$('#elderFinalServiceStatus').text(elderFinalServiceStatus);
					$('#elderBirthDate').text(elderBirthDate);
					$('#elderCoinsurance').text(elderCoinsurance+"%");
					$('#elderLevel').text(elderFinalServiceApprovalLevel+' 등급 / '+longTermCareNumver);
					$('#elderServiceApprovalDate').text(elderServiceApprovalStartDate+" ~ "+elderServiceApprovalEndDate);
					$('#serviceEndDate').text(serviceEndDate);
					$('#serviceStartDate').text(serviceStartDate);
					$('#elderPhone').text(elderPhone);
					$('#elderAdressForStay').text(elderAdressForStay);
					$('#elderAdressForPost').text(elderAdressForPost);
					$('#elderDisease').text(elderDisease);
					$('#elderRemarks').text(elderRemarks);
					$('#fallDownCheckDate').text(fallDownCheckDate);
					$('#bedsoreCheckDate').text(bedsoreCheckDate);
					$('#functionCheckDate').text(functionCheckDate);
					$('#needsCheckDate').text(needsCheckDate);

					$( ".date" ).datepicker({});
					
					deleteEvent();
					
					var fallDownCheckDate = $('#fallDownCheckDate')
					var bedsoreCheckDate = $('#bedsoreCheckDate')
					var functionCheckDate = $('#functionCheckDate')
					var needsCheckDate = $('#needsCheckDate')
					var checkTd = [fallDownCheckDate,bedsoreCheckDate,functionCheckDate,needsCheckDate];
					putRedLine(checkTd);
					
				},
				error	:	function(error){
					console.log("error", error)
				}
			})   
		})
    	  
    	  //data table
    	  $('#dataTable').DataTable({
    		  
              "language": 
                 {
                 "sInfo": " 총_TOTAL_건의 자료 중  _START_번부터~_END_번까지 ",
                 "lengthMenu": "_MENU_ 개씩 보기",
                 "search":         "검색:",
                 "paginate": {
                 "first":      "First",
                 "last":       "Last",
                 "next":       "다음",
                 "previous":   "이전"
                      }
              }           
    	  });
    	  
		  	
		  	
		$('#myModal').draggable();
		$('#myModal2').draggable();
		$('#myModal3').draggable();
				
		$('#myModal').on('show.bs.modal', function () {
			  console.log('hi')			  
		})
		$('#myModal').on('shown.bs.modal', function () {
			  console.log('hi')
			  
			})
		$('#myModal').on('hide.bs.modal', function () {
			  console.log('hi')
			  
			})
		$('#myModal').on('hidden.bs.modal', function () {
			  console.log('hi')
			  
			})
		$('#myModal').on('loaded.bs.modal', function () {
			  console.log('hi')
			  
		})
			
		/* datepicker 메서드 */
		$( ".date" ).datepicker({});
    	  
    	  
    	  /* 1~4년 라디오 박스클릭 시 맞는 값으로 들어가기 */
    	  /* 등급관리내 */
    	  changeDate($('input[name="elderServiceApprovalStartDate"]'),$('input[name="elderServiceApprovalEndDate"]'),$('input[name="levelTermYear"]'))
    	  /* 계약관리내 */
    	  changeDate($('input[name="serviceStartDate"]'), $('input[name="serviceEndDate"]'), $('input[name="serviceTermYear"]'))
    	 

      });