<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/Views/Admin/css/common.css">
<link rel="stylesheet" href="/Views/Admin/css/style.css">
<link rel="stylesheet" href="/Views/Admin/css/href.css">
<script type="text/javascript" src="/Views/Admin/js/jquery.min.js"></script>
<script type="text/javascript"
	src="/Views/Admin/js/jquery.SuperSlide.js"></script>
<script type="text/javascript">
			$(document).ready(function(){
   				alert($('.on').length);        
					$('li').click(function(){
						$('.on').removeClass();
						$(this).addClass('on');
					});
				});
		</script>
<script type="text/javascript">
			$(function(){
			      $(".sideMenu").slide({
			         titCell:"h3", 
			         targetCell:"ul",
			         defaultIndex:0, 
			         effect:'slideDown', 
			         delayTime:'500' , 
			         trigger:'click', 
			         triggerTime:'150', 
			         defaultPlay:true, 
			         returnDefault:false,
			         easing:'easeInQuint',
			         endFun:function(){
			              scrollWW();
			         }
			       });
			      $(window).resize(function() {
			          scrollWW();
			      });
			  });
			  function scrollWW(){
			    if($(".side").height()<$(".sideMenu").height()){
			       $(".scroll").show();
			       var pos = $(".sideMenu ul:visible").position().top-38;
			       $('.sideMenu').animate({top:-pos});
			    }else{
			       $(".scroll").hide();
			       $('.sideMenu').animate({top:0});
			       n=1;
			    }
			  } 
			
			var n=1;
			function menuScroll(num){
			  var Scroll = $('.sideMenu');
			  var ScrollP = $('.sideMenu').position();
			  /*alert(n);
			  alert(ScrollP.top);*/
			  if(num==1){
			     Scroll.animate({top:ScrollP.top-38});
			     n = n+1;
			  }else{
			    if (ScrollP.top > -38 && ScrollP.top != 0) { ScrollP.top = -38; }
			    if (ScrollP.top<0) {
			      Scroll.animate({top:38+ScrollP.top});
			    }else{
			      n=1;
			    }
			    if(n>1){
			      n = n-1;
			    }
			  }
			}
		</script>
<title>后台首页</title>
</head>
<body>
	<div class="top">
		<div id="top_t">
			<div id="logo" class="fl"></div>
			<div id="photo_info" class="fr">
				<div id="photo" class="fl">
					<a href="#"><img src="/Views/Admin/images/a.png" alt=""
						width="60" height="60"></a>
				</div>
				<div id="base_info" class="fr">
					<div class="help_info">
						<a href="help.html" id="hp">&nbsp;</a> <a href="2" id="gy">&nbsp;</a>
						<a href="3" id="out">&nbsp;</a>
					</div>
					<div class="info_center">
						${sessionScope.userName} <span id="nt">通知</span><span><a
							href="#" id="notice">3</a></span>
					</div>
				</div>
			</div>
		</div>
		<div id="side_here">
			<div id="side_here_l" class="fl"></div>
			<div id="here_area" class="fl">欢迎您使用！如若有问题，请联系 +86
				136-0704-2810</div>
		</div>
	</div>
	<div class="side">
		<div class="sideMenu" style="margin: 0 auto">
			<h3>考生信息</h3>
			<ul>
				<li><a class="dh" href="/Views/Admin/addExamer.html"
					target="right">考生信息录入</a></li>
				<li><a class="dh" href="/Views/Admin/updateExamer.html"
					target="right">考生信息修改</a></li>
				<li><a class="dh" href="/Views/Admin/selectExamer.html"
					target="right">查询考生信息</a></li>
			</ul>
			<h3>试题管理</h3>
			<ul>
				<li><a class="dh" href="/Views/Exam/stdl.html" target="right">试题大类设置</a></li>
				<li><a class="dh" href="/Views/Exam/stlb.html" target="right">试题类别设置</a></li>
				<li><a class="dh" href="/Views/Exam/stlx.html" target="right">试题类型设置</a></li>
				<li><a class="dh" href="/Views/Exam/addst.html" target="right">添加试题</a></li>
				<li><a class="dh" href="/Views/Exam/selectST.html"
					target="right">试题查询</a></li>
			</ul>
			<h3>成绩管理</h3>
			<ul>
				<li><a class="dh" href="/Views/Admin/selectMark.html"
					target="right">成绩查询</a></li>
				<li><a class="dh" href="/Views/Admin/system.html"
					target="right">成绩单预览</a></li>
				<li><a class="dh" href="/Views/Admin/system.html"
					target="right">未完待续</a></li>
				<li><a class="dh" href="/Views/Admin/system.html"
					target="right">未完待续</a></li>
			</ul>
			<h3>统计信息</h3>
			<ul>
				<li><a class="dh" href="/Views/Exam/err-info.html"
					target="right">Echart入门</a></li>
				<li><a class="dh" href="/Views/Admin/system.html"
					target="right">错题排行</a></li>
				<li><a class="dh" href="/Views/Admin/system.html"
					target="right">未完待续</a></li>
				<li><a class="dh" href="/Views/Admin/system.html"
					target="right">未完待续</a></li>
			</ul>
			<h3>系统初始化</h3>
			<ul>
				<li><a class="dh" href="/Views/Admin/addExamer.html"
					target="right">初始化题库</a></li>
				<li><a class="dh" href="/Views/Admin/updateExamer.html"
					target="right">初始化考场</a></li>
				<li><a class="dh" href="/Views/Admin/system.html"
					target="right">未完待续</a></li>
				<li><a class="dh" href="/Views/Admin/system.html"
					target="right">未完待续</a></li>
			</ul>
			<h3>系统测试模块</h3>
			<ul>
				<li><a class="dh" href="/Views/Test/test1.html" target="right">测试1</a></li>
				<li><a class="dh" href="/Views/Test/test2.html" target="right">测试2</a></li>
				<li><a class="dh" href="/Views/Test/test3.html" target="right">测试题库信息</a></li>
			</ul>

		</div>
	</div>
	<div class="main">
		<iframe name="right" id="rightMain" src="/Views/Admin/system.html"
			frameborder="no" scrolling="auto" width="100%" height="auto"
			allowtransparency="true"></iframe>
	</div>
	<div class="bottom">
		<div id="bottom_bg">
			<b>姓名:胡欣欣©学号:20152051</b>
		</div>

	</div>
	<div class="scroll">
		<a href="javascript:;" class="per" title="使用鼠标滚轴滚动侧栏"
			onclick="menuScroll(1);"></a> <a href="javascript:;" class="next"
			title="使用鼠标滚轴滚动侧栏" onclick="menuScroll(2);"></a>
	</div>


</body>

</body>
</html>