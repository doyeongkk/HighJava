/**
 * 
 */

currentPage=1;
var boardSearchServer = function() {
	//게시글 가져오기
	$.ajax({
		url : '/webBoard/WebSearchList.do',
		type : 'get',
//		type : 'post',
		data : {"board_title" : input},
		dataType : 'json',
		success : function(res) {
			
			code = '<div class="panel-group" id="accordion">';
			$.each(res, function(i, v) {
			   code +='<div class="panel panel-default">';
			   code +='   <div class="panel-heading">';
			   code +='     <h4 class="panel-title">';
			   code +='       <a name="list" class="action" idx="' + v.no + '" data-toggle="collapse" data-parent="#accordion" href="#collapse' + v.no + '">'+ v.title +'</a>';
			   code +='     </h4>';
			   code +='   </div>';
			   code +='   <div id="collapse' + v.no + '" class="panel-collapse collapse">';
			   code +='     <div class="panel-body">';
			   code +='     	<p class="p1">';
			   code +='     		작성자 : ' + v.name + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			   code +='     		조회수 : ' + v.hit + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			   code +='     		작성날짜 : ' + v.date + '&nbsp;&nbsp;&nbsp;';
			   code +='     	</p>';
			   code +='     	<p class="p2">';
			   code +='     		<button type="button" idx="' + v.no + '" name="modify" class="action">수정</button>';
			   code +='     		<button type="button" idx="' + v.no + '" name="delete" class="action">삭제</button>';
			   code +='     	</p>';
			   code +='     	<hr>';
			   code +='     	<p>' + v.cont + '</span></p>';
			   code +='     </div>';
			   code +='   </div>';
			   code +=' </div>';
			})

			code +='</div>';
			$('.box').html(code);
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	})
	
	

}
var setCountIncrementServer = function(but) {	//but : 제목이 있는 a태그 - list
	$.ajax({
		url : '/webBoard/WebBoardCount.do',
		type : 'get',
		data : {"board_no" : vidx },		//date : "board_no=" + vidx,
		success : function(res) {
			alert(res.sw);
			//화면의 조회수 수정
			hit = $(but).parents('.panel').find('.hspan').text();
			hit = parseInt(hit) + 1;
			$(but).parents('.panel').find('.hspan').text(hit);
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status);
		},
		dataType : 'json'
	})
}

var boardUpdateServer = function() {
	
	$.ajax ({
		url : '/webBoard/WebBoardUpdate.do',
		type : 'post',
		data : jdbcboard,	//seq, writer, subject, content, password, mail
		success : function(res) {
			alert(res.sw);
			//화면 수정 - 수정모달창에 있는 값들을 다시 가져와서(board객체) 화면에 출력.
			$(pbody).find('.nspan').text(jdbcboard.board_writer);
			
			content = jdbcboard.board_content;
			content = content.replace(/\n/g, "<br>");
			
			$(pbody).find('.cspan').html(content);
			$(pbody).find('a').text(jdbcboard.board_title);
			
			today = new Date();
			today = today.toLocaleString();
			$(pbody).find('.dspan').text(today);
			
			
			
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status);
		},
		dataType : 'json'
	})
}

var boardDeleteServer = function(but) { 	//but : 삭제버튼
	
	$.get(
			'/webBoard/WebBoardDelete.do',
			{"board_no" : vidx},
			function(res) {
				alert(res.sw);
				//화면에서 지우기
				$(but).parents('.panel').remove();
			},
			'json'
	)
	
}
var boardInsertServer = function() {
	
	$.ajax({
		url : '/webBoard/WebBoardInsert.do',
		data : $('#wform').serializeJSON(),
		type : 'post',
		dataType : 'json',
		success : function(res) {
//			alert(res.sw)
			getAllBoardListServer(1);
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	})
}


//페이지별 리스트 - html에서 getAllBoardListServer(1) 호출
//cpage변수는 페이지 번호이고 controller로 전송한다

var getAllBoardListServer = function(cpage){
	$.ajax ({
		url : '/webBoard/WebBoardList.do',
		type : 'post',
		data : {"page" : cpage},
		dataType : 'json',
		success : function(res){
			code = '<div class="panel-group" id="accordion">';
			$.each(res.datas, function(i, v) {
			   code +='<div class="panel panel-default">';
			   code +='   <div class="panel-heading">';
			   code +='     <h4 class="panel-title">';
			   code +='       <a name="list" class="action" idx="' + v.no + '" data-toggle="collapse" data-parent="#accordion" href="#collapse' + v.no + '">'+ v.title +'</a>';
			   code +='     </h4>';
			   code +='   </div>';
			   code +='   <div id="collapse' + v.no + '" class="panel-collapse collapse">';
			   code +='     <div class="panel-body pbody">';
			   code +='     	<p class="p1">';
			   code +='     		작성자 : <span class="nspan">' + v.name + '</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			   code +='     		조회수 : <span class="hspan">' + v.hit + '</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			   code +='     		작성날짜 : <span class="dspan">' + v.date + '</span> &nbsp;&nbsp;&nbsp;';
			   code +='     	</p>';
			   code +='     	<p class="p2">';
			   code +='     		<button type="button" idx="' + v.no + '" name="modify" class="action">수정</button>';
			   code +='     		<button type="button" idx="' + v.no + '" name="delete" class="action">삭제</button>';
			   code +='     	</p>';
			   code +='     	<hr>';
			   code +='     	<p><span class="cspan">' + v.cont + '</span></p>';
//			   code +=			'<p>';
//			   code +='    			<textarea class="area" cols="60"></textarea>';
//			   code +='    			<button type="button" idx="' + v.no + '" class="action repb" name="reply">댓글등록</button>';
//			   code +='     	</p>';
			   code +='     </div>';
			   code +='   </div>';
			   code +=' </div>';
			})

			code +='</div>';
			$('.box').html(code);
			
			//pagelist에 append를 이용해서 출력
			$('#pagelist').empty();
			//이전버튼 출력
			if(res.sp > 1){
				pager = '<ul class="pager">';
				pager += '<li><a class="prev" href="#">Previous</a></li>';
				pager +='</ul>';
				$('#pagelist').append(pager);
			}
			
			//페이지번호 출력
			pager = '<ul class="pagination pager">';
			for(i=res.sp; i<=res.ep; i++){
				if(currentPage == i){
					pager += '<li class="active"><a class="paging" href="#">' + i + '</a></li>';
				}else{
					pager += '<li><a class="paging" href="#">' + i + '</a></li>';
				}
			}
			pager += '</ul>';
			$('#pagelist').append(pager);
			  
			//다음 버튼 출력
			if(res.ep < res.tp){
				pager = '<ul class="pager">';
				pager += '<li><a class="next" href="#">Next</a></li>';
				pager +='</ul>';
				$('#pagelist').append(pager);
			}
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		}
	})
}