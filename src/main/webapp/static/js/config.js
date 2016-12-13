$.parser.onComplete = function(){
	$("#loading").fadeOut("slow",function(){
		$(this).remove();
	});
}
$(function(){
	$(window).on('beforeunload', function() {
		return '关闭或刷新页面可能导致操作丢失，请确认！';
	});
	initMenu();
});
//初始化menus
function initMenu(){
	$.get(baseUrl + '/config/menu_get',function(arry){
		$.each(arry,function(index,element){
			addAccordion('#menu_accordion',element.url_type_name,element.url_type_icon,'<div id="menu_accordion_'+element.url_type_id+'" class="easyui-menu" data-options="inline:true,fit:true,itemHeight:30" style="width:100%"></div>');
			addMenu('#menu_accordion_'+element.url_type_id,element.text,element.icon,element.url,element.is_iframe);
		});
		$('#menu_accordion').accordion({
			selected:0
		});
	});
	
}
//添加accordion
function addAccordion(divId, title,iconCls, content) {
	//取出重复accordion
	if($(divId).accordion('getPanel',title)){
		return false;
	}
	$(divId).accordion('add', {
		title : title,
		iconCls:iconCls,
		content : content
	});
}
//添加menu
function addMenu(divId, text,iconCls, url,is_iframe) {
	$(divId).menu('appendItem', {
		text: text,
		iconCls: iconCls,
		onclick: function(){
			addTabPanel('#main_tab',text,url,is_iframe);
		}
	});
}
//展示加载/处理提示条
function showProgress(title, text) {
	parent.$.messager.progress({
		title : title,
		text : text
	});
}
//关闭加载/处理提示条
function closeProgress(){
	parent.$.messager.progress('close');
}
//添加tabPanel
function addTabPanel(divId,title,href,is_iframe){
	showProgress('加载中','正在加载中，请耐心等待...');
	var existTabPanel = $(divId).tabs('exists',title);
	if (existTabPanel) {
		$(divId).tabs('close', title);
	}
	if(is_iframe == '1' || is_iframe == true){
		if (href.indexOf('http') == -1) {
			href = baseUrl + href;
		}
		$(divId).tabs('add',{
	        title: title,
	        content: '<iframe src="' + href + '" frameborder="0" style="border:0;width:100%;height:99%;" seamless="seamless" sandbox="allow-forms allow-same-origin allow-scripts allow-top-navigation" onload="closeProgress()"></iframe>',
	        closable: true
	    });
	} else {
		$(divId).tabs('add', {
			title : title,
			href : baseUrl + href,
			closable : true,
			onLoad : function() {
				closeProgress();
			}
		});
	}
}
// 删除tabPanel
function removeTabPanel(divId){
    var tab = $(divId).tabs('getSelected');
    if (tab){
        var index = $(divId).tabs('getTabIndex', tab);
        $(divId).tabs('close', index);
    }
}
