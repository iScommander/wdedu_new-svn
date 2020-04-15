function showEmptyTimeTable(){
	var data={};
	//buildTable(data);
}

function showTimeTable(urlBase,timeTableTaskId,grade,week,classes,teacherCode,roomId,subject){
	$('table>tbody>tr').remove();
	
	var url=urlBase+"/timetable/getTimeTable3.jspx";
	var data={};
	data.taskId=timeTableTaskId;
	if(grade){
		data.grade=grade;
	}
	if(week){
		data.week=week;
	}
	if(classes){
		data.classes=classes;
	}
	if(teacherCode){
		data.teacher=teacherCode;
	}
	if(roomId){
		data.classroom=roomId;
	}
	if(subject){
		data.subject=subject;
	}
	
	$.ajax({
		url:url,
		data:data,
		dataType:"json",
		type:'post',
		success:function(data){
			buildTable(data);
		}
	});	
}


function showTimeTableStudent(urlBase,timeTableTaskId,stuId){
	$('table>tbody>tr').remove();
	if(!stuId||stuId=='0'){
		alert("请选择学生");
		return;
	}	
	var url=urlBase+"/timetable/getTimeTableStudent.jspx";
	var data={};
	data.taskId=timeTableTaskId;	
	data.student=stuId;
	$.ajax({
		url:url,
		data:data,
		dataType:"json",
		type:'post',
		success:function(data){
			buildTable(data);
		}
	});	
}


function buildTable(data){
	var strWeeks=['星期一','星期二','星期三','星期四','星期五','星期六','星期天'];
	var dl=data["dl"];
	console.log(dl);
	var cntDaysOneWeek=dl[0]["daynumweek"];
	var cntLessonsOneDay=dl[0]["lessonnumday"];
	//build标题head
	var $th_tr=$('table>thead>tr');
	$('table>thead>tr>th').remove();
	$th_tr.append( $('<th>星期</th>') );
	for(var i=1;i<=cntLessonsOneDay;i++){
		$th_tr.append( $('<th>第'+i+'节<br />(S'+i+')</th>') );
	}
	//获取每行的rowspan
	var rowspan=[];
	for(var week=0;week<cntDaysOneWeek;week++){
		var max=1;
		for(var j=0;j<=cntLessonsOneDay;j++){
			var y=""+(week*cntLessonsOneDay+j);
			var v=data[y]; 
			if(!v)
				continue;
			if(max<v.length)
				max=v.length;
		}
		rowspan[week]=max;
	}
	//根据每行(即周几)的rowspan，构建出表格，表格的内容为空
	
	for(var week=0;week<cntDaysOneWeek;week++){
		var $tr=$('<tr></tr>');
		var $tdFirst=$('<td rowspan="1">星期一</td>');
		$tdFirst.attr('rowspan',rowspan[week]);
		$tdFirst.text(strWeeks[week]);
		$tr.append($tdFirst);
		for(var j=0;j<cntLessonsOneDay;j++){
			$tr.append($('<td class="go"></td>'));
		}
		$('table>tbody').append($tr);
		for(var i=1;i<rowspan[week];i++){
			$tr=$('<tr></tr>');
			for(var j=0;j<cntLessonsOneDay;j++){
				$tr.append($('<td class="go"></td>'));
			}
			$('table>tbody').append($tr);
		}
		
	}
	//为每个格子赋值
	var rowBegin=0;
	for(var week=0;week<cntDaysOneWeek;week++){
		var max=0;
		for(var j=0;j<=cntLessonsOneDay;j++){
			var y=""+(week*cntLessonsOneDay+j);
			var v=data[y];
			if(!v)
				continue;
			for(var x=0;x<v.length;x++){
				var item=v[x];
				//span classes
				var $spanClasses=$('<span></span>');
				$spanClasses.text(item.classesName);
				//span classroom
				var $spanClassroom=$('<span></span>');
				$spanClassroom.text(item.classroomName);
				//span teacher
				var $spanTeacher=$('<span></span>');
				$spanTeacher.text(item.teacherName);
				//span subject
				var $spanSubject=$('<span></span>');
				$spanSubject.text(item.subjectName);
				//--span data
				var $spanData=$('<span style="display:none"></span>');
				$spanData.attr('oid',item.id);
				$spanData.attr('classes',item.classesCode);
				$spanData.attr('y',item.y);
				$spanData.attr('week',item.week);
				$spanData.attr('classroom',item.classroom);
				if(item.fixedRoomAndLesson){
					$spanData.attr("fixedRoomAndLesson",item.fixedRoomAndLesson);
				}else if(item.fixedLesson){
					$spanData.attr("fixedLesson",item.fixedLesson);
				}else if(item.continuousLesson){
					$spanData.attr("continuousLesson",item.continuousLesson);
				}else if(item.prohibitionLesson){
					$spanData.attr("prohibitionLesson",item.prohibitionLesson);
				}else if(item.priorityLesson){
					$spanData.attr("priorityLesson",item.priorityLesson);
				}else if(item.fixedRoom){
					$spanData.attr("fixedRoom",item.fixedRoom);
				}
				//td			
				var seltd;
				if(x==0){
					seltd='table>tbody>tr:eq('+(rowBegin+x)+')>td:eq('+(1+j)+')';
				}else{
					seltd='table>tbody>tr:eq('+(rowBegin+x)+')>td:eq('+(0+j)+')';
				}
				var $td=$(seltd);
				//------
				$td.append($spanData);
				$td.append($spanClasses);
				$td.append($spanClassroom);
				$td.append($spanTeacher);
				$td.append($spanSubject);
				if(item.fixedRoom){
					$td.removeClass('go');
					$td.addClass('fixed');
					$td.attr("ondrop","drop(event,this)"); 
					$td.attr("ondragover","allowDrop(event)");
					$td.attr("draggable","true");
					$td.attr("ondragstart","drag(event,this)");
				}
			}
		}
		rowBegin=rowBegin+rowspan[week];
	}
}
