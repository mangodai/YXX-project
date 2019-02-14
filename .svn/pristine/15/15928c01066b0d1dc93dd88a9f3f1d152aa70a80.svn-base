<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/Views/Admin/laypage-v1.3/laypage/laypage.js"></script>
<script src="/Views/Admin/jquery1.9/jquery-1.9.1.js"></script>
</head>
<body>
	<ul id="biuuu_city_list"></ul>

	<div id="biuuu_city"></div>

	
	<script>
	$(window).load(function() {
		$.ajax({
			type:"post",
			url:"/admin/pageActionTest",
			async:true,
			success:function(data){
				var nums = 5; //每页出现的数量
				var pages = Math.ceil(data.length/nums); //得到总页数

				var thisDate = function(curr){
				    //此处只是演示，实际场景通常是返回已经当前页已经分组好的数据
				    var str = '', last = curr*nums - 1;
				    last = last >= data.length ? (data.length-1) : last;
				    for(var i = (curr*nums - nums); i <= last; i++){
				        str += '<li>'+ data[i].STTM +'</li>';
				    }
				    return str;
				};

				//调用分页
				laypage({
					skin:'molv',
				    cont: 'biuuu_city',
				    pages: pages,
				    jump: function(obj){
				        document.getElementById('biuuu_city_list').innerHTML = thisDate(obj.curr);
				    }
				})
			},
			error:function(data){
				
			}
		});
	});
	</script>
</body>
</html>