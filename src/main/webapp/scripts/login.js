
var SUCCESS = 0;
$(function(){

});
$(function(){
	
	//console.log("Hello!!");
	
	//1. 绑定 登录 按钮
	//2. 在按键被点击时候获取 用户名和密码
	//3. 检查用户名和密码 是否符合规则 ??
	//4. 如何符合规则就向服务器发送
	//5. 处理服务器返回的结果(成功/失败)
	
	//绑定登录界面的事件
	$('#login').click(loginAction); 
	$('#count').blur(checkName);
	$('#password').blur(checkPassword);
	
	//绑定注册界面的事件
	$('#regist_username').blur(checkRegistName);
	$('#regist_password').blur(checkRegistPassword);
	$('#final_password').blur(checkConfirmPassword);
	$('#regist_button').click(registAction);
	
});

function checkName(){
	var name = $('#count').val();
	var reg = /^\w{3,10}$/;
	$('#count-msg').empty();
	if(! reg.test(name)){
		$('#count-msg').append("3~10个字符");
		return false;
	}
	return true;
}
function checkPassword(){
	var pwd = $('#password').val();
	var reg = /^\w{3,10}$/;
	$('#password-msg').empty();
	if(! reg.test(pwd)){
		$('#password-msg').append("3~10个字符");
		return false;
	}
	return true;



function loginAction(){
	//console.log("Login Action!");
	var name = $('#count').val();
	var password = $('#password').val();
	//检查用户名和密码的规则
	var n = checkName() + checkPassword();
	if(n!=2){
		return;
	}
	//向服务器发送用户名和密码
	var url="user/login.do";
	//data中的key与服务端控制器参数名一致
	//data中的值来源于页面表单中的数据
	var data={'name':name,'password':password};
	//var data={name:name,password:password};
	$.post(url, data, function(result){
		//服务器返回result 是JsonResult序列化的
		//结果. result: {state:0,data:...,message:...}
		//console.log(result);
		if(result.state==SUCCESS){
			//登录成功
			//跳转到 edit.html
			console.log('成功');
			
			//登录成功以后将ID保存到Cookies中
			var userId=result.data.id;
			setCookie("userId", userId);
			
			location.href='edit.html';
		}else{
			//显示错误消息
			var msg=result.message;
			console.log(msg);
			$('#count-msg').html(msg);
		}
	});
}


function  checkRegistName(){
	console.log("checkRegistName");
	var name=$('#regist_username').val();
	var reg=/^\w{3,10}$/;
	$('#warning_1 span').empty();
	$('#warning_1').hide();
	if(reg.test(name)){
		return true;
	}
	$('#warning_1 span').html('3~10字符');
	$('#warning_1').show();
	return false;
}

function  checkRegistPassword(){
	console.log("checkRegistPassword");
	var pwd=$('#regist_password').val();
	var reg=/^\w{3,10}$/;
	$('#warning_2 span').empty();
	$('#warning_2').hide();
	if(reg.test(pwd)){
		return true;
	}
	$('#warning_2 span').html('3~10字符');
	$('#warning_2').show();
	return false;
}

function  checkConfirmPassword(){
	console.log("checkConfirmPassword");
	var pwd=$('#regist_password').val();
	var pwd2=$('#final_password').val();
	
	$('#warning_3 span').empty();
	$('#warning_3').hide();
	if(pwd != "" && pwd==pwd2){
		return true;
	}
	$('#warning_3 span').html('密码不一致');
	$('#warning_3').show();
	return false;

}

function registAction(){
	var n = checkRegistName()+
			checkRegistPassword()+
			checkConfirmPassword();
	if(n!=3){
		return;
	}
	var name=$('#regist_username').val();
	var pwd=$('#regist_password').val();
	var pwd2=$('#final_password').val();
	var nick=$('#nickname').val();
	
	var url='user/regist.do';
	var data = {name:name,password:pwd,
			confirm:pwd2,nick:nick};
	console.log(data);
	
	$.post(url, data, function(result){
		console.log(result);
		if(result.state==SUCCESS){
			var n = result.data.name;
			
	
			$('#count').val(n);
			$('#count').focus();
			$('#back').click();
		}else{
			var msg=result.message;
			$('#warning_1 span').html(msg);
			$('#warning_1').show();
		}
	});
}









