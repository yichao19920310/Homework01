<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 登录</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
</head>
<body>
<%@ include file="index_top.jsp"  %>
<div id="login" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div id="login">
			<h1>欢迎回到亚马逊</h1>
			<!-- onsubmit="return loginCheck()" -->
			<form id="loginForm" method="post" >
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input id="userName" class="text" type="text" name="userName" onfocus="FocusItem(this)" onblur="Checkexist();" /><span id ="uName"></span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" id="passWord" name="passWord" onfocus="FocusItem(this)" onblur="CheckPwd(this);" /><span id="Pwd"></span><a href="retrieve_password.jsp">忘记密码</a></td>
		
					</tr>
					<tr>           
						<td class="field">验证码：</td>
						<td><input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)" onblur="checkValidateCode();" maxlength="4"/><img id="veryCode" src="code.jsp" /><span id="Code"></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="button" name="submit" value="立即登录"  onmousedown="return loginCheck()"onclick="myFormPost()"/></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2016  上海海文 All Rights Reserved
</div>
<script type="text/javascript">



	function myFormPost(){
		//获取id为regform的表单
		var Cfm= $("#loginForm");
			//验证表单数据		
			if(loginCheck){
		//如果表单验证成功,发送一个ajax请求给后台
		$.ajax({
			type:"post",
			url:"doAction?action=login",
			data:$("form").serialize(), 
			success:function (msg){
				if(msg==1){//登录成功
					alert("登录成功");
					//页面跳转
					window.location.href="index.jsp";
				}else{
					alert("登陆失败");
				}
			}
		});
		}
	}

</script>
</body>
</html>
