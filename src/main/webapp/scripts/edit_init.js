/* scripts/edit_init.js */

var SUCCESS = 0;

$(function(){
	//初始化 edit.html 页面
	//var userId=getCookie('userId');
	//console.log(userId);
	
	//在网页加载以后立即加载笔记本列表信息
	loadNotebooksAction();
	//点击#add_notebook时候打开添加笔记本对话框
	$('#add_notebook').click(openAddNotebookDialog);
	//绑定笔记本对话框中的添加笔记本按钮事件
	$('#can').on('click', 
		'.add-notebook', addNotebookAction);
	//利用事件冒泡,在can上绑定关闭按钮
	$('#can').on('click',
		'.close,.cancel',closeDialog);
	
	//绑定笔记本列表点击事件
	//$('#notebooks').on('click','li',showNotesAction);
	
	//绑定“翻页”笔记本列表点击事件
	$('#notebooks').on('click','li',
			showPagedNotesAction);


	//绑定笔记列表点击事件
	$('#pc_part_2 ul').on('click',
			'li.note', loadNoteAction);
	//绑定more事件
	$('#pc_part_2 ul').on('click',
			'li.more', nextPageNotesAction);

	
	//绑定保存笔记事件
	$('#save_note').click(updateNoteAction);
	
	//绑定添加笔记按钮事件
	$('#add_note').click(openAddNoteDialog);
	//绑定添加笔记窗口中的 确定按钮事件
	$('#can').on('click', '.add-note', addNoteAction);
	
	//显示回收站
	$('#rollback_button').click(switchRollback);
	
	//绑定显示笔记子菜单的弹出事件
	$('#pc_part_2')
		.on('click','li .btn_slide_down', 
		showNoteSubMenu);
	//关闭子菜单
	$('body').click(hideNoteSubMenu);
	
	//绑定删除笔记按钮事件
	$('#pc_part_2 ul')
		.on('click', 'li .btn_delete',
		deleteNoteAction);
	
});

function deleteNoteAction(){
	var btn = $(this);
	var id=btn.parents('li').data('noteId');
	//利用ajax，将删除请求发送到服务器，
	//根据服务器返回结果更新界面...
	console.log('删除'+id);
}

function hideNoteSubMenu(){
	$('#pc_part_2 .note_menu').hide(200);
}

function showNoteSubMenu(){
	var btn=$(this);
	btn.parent().next().toggle(200);
	return false;
}

function switchRollback(){
	//检查回收站是否打开，如果打开就关闭，并且
	//开启笔记列表，如果是关闭状态就关闭其他列表
	//回收站
	
	//pc_part_8 //参加活动的笔记列表
	//pc_part_7 //收藏笔记列表 
	//pc_part_6 //搜索笔记列表
	//pc_part_4 //回收站
	//pc_part_2 //笔记列表
	$('#pc_part_8').hide();
	$('#pc_part_7').hide();
	$('#pc_part_6').hide();
	if($('#pc_part_4').css('display')=='block'){
		$('#pc_part_4').hide();
		$('#pc_part_2').show();
	}else{
		$('#pc_part_4').show();
		$('#pc_part_2').hide();
	}
}

function closeDialog(){
	$('#can').empty();
	$('.opacity_bg').hide();
}










