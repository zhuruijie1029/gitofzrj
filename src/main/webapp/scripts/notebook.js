/* scripts/notebook.js */

/**
 * 用于加载笔记本列表信息
 * 
 * 在页面加载以后立即执行
 */
function loadNotebooksAction(){
	console.log('loadNotebooksAction');
	//发起Ajax请求, 获取笔记本列表数据
	var url='notebook/list.do';
	var data={userId:getCookie('userId')};
	console.log(data);
	$.getJSON(url, data, function(result){
		console.log(result);
		if(result.state==SUCCESS){
			var list=result.data;
			console.log(list);
			//将笔记本列表显示到页面上
			showNotebooks(list);
		}else{
			var msg=result.message;
			alert(msg);
		}
	});
}
//笔记本列表区域的显示模板
//替换[name]为真正笔记本名
var notebookTemplate=
	'<li class="online">' +
		'<a >' + // class="checked"
			'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+
			'[name]'+
		'</a>'+
	'</li>';

function showNotebooks(notebooks){
	//1. 清空 ul 
	//2. 遍历 notebooks 
	//3. 为每个notebook创建一个li
	//     将模板[name]替换为笔记本名即得到 li 
	//4. 将li添加到ul
	$('#notebooks').empty();
	for(var i=0; i<notebooks.length; i++){
		var notebook=notebooks[i];
		var li = notebookTemplate.replace(
				'[name]', notebook.name);
		console.log(li);
		
		//绑定每个笔记本的ID到li元素上
		li = $(li).data(
				'notebookId', notebook.id);
		
		$('#notebooks').append(li);
	}
}

function openAddNotebookDialog(){
	var url="alert/alert_notebook.jsp";
	$('#can').load(url);
	$('.opacity_bg').show();
}

function addNotebookAction(){
	var name=$('#input_notebook').val();
	if(! name || name.replace(' ','')==''){
		return;
	}
	
	var url='notebook/save.do';
	var data = {userId:getCookie('userId'),
				name:name};
	console.log(data);
	
	$.post(url, data, function(result){
		console.log(result);
		//将新的笔记本信息插入到笔记本列表中第一个
		//关闭添加对话框
		if(result.state==SUCCESS){
			var notebook = result.data;
			var li = notebookTemplate.replace(
					'[name]', notebook.name);
			$('#notebooks').prepend(li);
			closeDialog();
		}else{
			alert(result.message);
		}
	});
}







