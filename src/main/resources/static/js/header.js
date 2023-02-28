/**
 * header섹션에 적용된 스크립트
 */
 
//로그아웃 csrf 적용시 ajax활용방법
$(function(){
	$("#btn-logout").click(function(){
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
					
		$.ajax({
			beforeSend:function(xhr){xhr.setRequestHeader(header, token);},
			url:"/logout",
			type:"post",
			success:function(){
				location.href="/";
			}
		
		});
		
	});
}); 