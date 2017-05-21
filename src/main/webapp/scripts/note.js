/* note.js 用于存储于笔记操作有关的js脚本 */

function showNotesAction(){
	var li = $(this);
	
	//设置视觉效果
	li.parent().find('a').removeClass('checked');
	li.children('a').addClass('checked');
	
	var id=li.data('notebookId');
	
	//在笔记的UL上绑定 notebookId
	var ul = $('#pc_part_2 ul');
	ul.data('notebookId', id);
	
	var url='note/list.do';
	var data={notebookId:id};
	$.getJSON(url, data, function(result){
		console.log(result);
		if(result.state==SUCCESS){
			var list = result.data;
			//显示全部的笔记
			showNotes(list);
		}else{
			alert(result.message);
		}
	});
} 

var noteTemplate='<li class="online note">'+
	'<a>'+
	'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+
	'[title]<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+
	'</a>'+
	'<div class="note_menu" tabindex="-1">'+
	'<dl>'+
	'	<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+
	'	<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+
	'	<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+
	'</dl>'+
	'</div>'+
	'</li>';

function showNotes(notes, page){
	//找到UL元素
 	var ul = $('#pc_part_2 ul');
 	if(page==0){
 		ul.empty();
 	}else{
 		ul.find('.more').remove();
 	}
 	//console.log(ul);
 	for(var i=0; i<notes.length; i++){
 		var note=notes[i];
 		li = noteTemplate.replace(
 			'[title]', note.title);
 		//console.log(li);
 		
 		//将noteId绑定到li元素
 		li = $(li).data('noteId', note.id);
 		
 		ul.append(li);
 	}
 	ul.append('<li class="online more"><a>More...</a></li>');
}

function loadNoteAction(){
	
	var li = $(this);
	var id = li.data('noteId');
	
	//设置选定笔记列表元素效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	
	var url = 'note/load.do';
	var data = {noteId:id};
	
	$.getJSON(url, data, function(result){
		if(result.state==SUCCESS){
			var note=result.data;
			//绑定 note 到标题区域
			$('#input_note_title')
				.data('note', note);
			
			$('#input_note_title').val(note.title);
			um.setContent(note.body);
		}else{
			alert(result.message);
		}
	});
}

//note.js
function updateNoteAction(){
	//获取用户输入title
	//获取用户输入body
	var note = $('#input_note_title')
		.data('note');
	//提交到服务器
	var title = $('#input_note_title').val();
	var body = um.getContent();
	var data = {};
	if(title!="" && title != note.title){
		data.title=title;
	}
	if(body != note.body){
		data.body = body;
	}
	data.noteId=note.id;
	var url='note/update.do';
	$.post(url, data, function(result){
		if(result.state==SUCCESS){
			console.log("Update Success!");
			//修改客户端保存的笔记信息属性
			note.title = title;
			note.body = body;
			//找到笔记本列表中的全部笔记信息,修改其title
			//list: 包含笔记标题的全部li元素
			var list = $('#pc_part_2 ul li');
			//遍历每个li元素
			list.each(function(){
				//li 是 dom 对象, 是每个li元素
				var li = $(this);
				//console.log(li);
				//取出li元素上绑定的 noteId
				var id=li.data('noteId');
				//如果当前的笔记ID与li上的笔记ID一致
				//找到当正在选定的 笔记
				if(id==note.id){
					//替换笔记的标题
					var newLi = noteTemplate
						.replace('[title]', note.title);
					newLi = $(newLi);
					//html() 用于读取或者替换li元素的内容
					li.html(newLi.html());
					//增加新选定效果
					li.find('a').addClass('checked');
				}
			});
			
		}else{
			alert(result.message);
		}
	});
}

function openAddNoteDialog(){
	//获取当前笔记本的ID, 如果没有, 则不能打开对话框
	var ul = $('#pc_part_2 ul');
	var id = ul.data('notebookId');
	if(! id){
		alert("先选定笔记本!");
		return;
	}
	
	var url="alert/alert_note.jsp";
	$('#can').load(url);
	$('.opacity_bg').show();
}

function addNoteAction(){
	var title = $('#input_note').val();
	if(title=='' || title.replace(/\s/g,'')==''){
		return;
	}
	//获取当前笔记本的ID, 如果没有, 则不能向服务器发送数据
	var ul = $('#pc_part_2 ul');
	var id = ul.data('notebookId');
	if(! id){
		return;
	}
		
	var url='note/add.do';
	var data = {userId:getCookie('userId'),
			notebookId:id, title:title};
	$.post(url, data, function(result){
		console.log(result);
		if(result.state==SUCCESS){
			var note = result.data;
			//绑定note对象
			//绑定 note 到标题区域
			$('#input_note_title')
				.data('note', note);
			
			$('#input_note_title').val(note.title);
			um.setContent(note.body);
			//更新笔记列表区域
		 	var ul = $('#pc_part_2 ul');
	 		li = noteTemplate.replace(
	 	 			'[title]', note.title);
	 		li = $(li);
	 		ul.prepend(li);
	 		//更新选定效果
	 		ul.find('a').removeClass('checked');
	 		li.find('a').addClass('checked');
	 		//关闭对话框
	 		closeDialog();
		}else{
			alert(result.message);
		}
	});
	
}

//note.js 

function showPagedNotesAction(){
	var li = $(this);
	var id=li.data('notebookId');
	$('#pc_part_2 ul').data('notebookId',id);
	
	nextPageNotesAction(true);
}

function nextPageNotesAction(firstPage){
	console.log('More...');
	
	//获取当前UL上绑定的笔记本ID
	var notebookId=$('#pc_part_2 ul')
		.data('notebookId');
	var page;
	if(firstPage===true){ //true
		page=0;
	}else{
		//当前页号
		page=$('#pc_part_2 ul').data('page');
	}
	//绑定下个页号
	$('#pc_part_2 ul').data('page',page+1);
	console.log(notebookId);
	//ajax 	notebookId	
	var url='note/list2.do';
	var data={notebookId:notebookId,
			page:page};
	console.log(data)
	$.getJSON(url, data, function(result){
		if(result.state==SUCCESS){
			var list=result.data;
			showNotes(list, page);
		}else{
			alert(result.message);
		}
	});
}


function test(){
	//在JS中，函数先分配全部的变量，然后在使用
	console.log(a); //未定义
	var a = 5;
	var b = 6;
	var c = a+b;
	console.log(c); 
}




