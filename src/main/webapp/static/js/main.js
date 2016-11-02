var timeout = 1;
$.parser.onComplete = function() {
	$(".welcome-loading").fadeOut("slow", function() {
		$(this).remove();
	});
}
var menuDate = [ {
	'id' : 1,
	'name' : '仓库信息',
	'sub' : [ {
		'id' : 11,
		'name' : '仓库管理',
		'icon' : 'icon-archive',
		'href' : '/ck'
	}, {
		'id' : 12,
		'name' : '仓库增加',
		'icon' : 'icon-archive',
		'href' : '/ck'
	}, {
		'id' : 13,
		'name' : '仓库删除',
		'icon' : 'icon-archive',
		'href' : '/ck'
	} ]
}, {
	'id' : 2,
	'name' : '人力资源',
	'sub' : [ {
		'id' : 21,
		'name' : '人力管理',
		'icon' : 'icon-archive',
		'href' : '/ck'
	}, {
		'id' : 22,
		'name' : '人力增加',
		'icon' : 'icon-archive',
		'href' : '/ck'
	}, {
		'id' : 23,
		'name' : '人力删除',
		'icon' : 'icon-archive',
		'href' : '/ck'
	} ]
}, {
	'id' : 3,
	'name' : '系统设置',
	'sub' : [ {
		'id' : 31,
		'name' : '系统管理',
		'icon' : 'icon-archive',
		'href' : '/ck'
	}, {
		'id' : 32,
		'name' : '系统更新',
		'icon' : 'icon-archive',
		'href' : '/ck'
	}, {
		'id' : 33,
		'name' : '系统报修',
		'icon' : 'icon-archive',
		'href' : '/ck'
	} ]
} ];

$(document).ready(function() {
	initMainMenu(menuDate);
	mainMenuClickListener();
	initSubMenu(menuDate);
	subMenuClickListener();
});
function initMainMenu(data) {
	laytpl($('#main_menu_tpl').html()).render(data, function(html) {
		$('.main-menu').html(html);
	});
}
function mainMenuClickListener() {
	$('.main-menu li').live('click', function() {
	});
}
function initSubMenu(data) {
	$.each(data, function(index, item) {
		laytpl($('#sub_menu_tpl').html()).render(item.sub, function(html) {
			$('.lay-left .easyui-accordion').accordion('add', {
				title : item.name,
				content : '<ul class="sub-menu">' + html + '</ul>'
			});
		});
	});
	$('.lay-left .easyui-accordion').accordion('select', 0);
}
function subMenuClickListener() {
	$('.sub-menu li').live(
			'click',
			function() {
				tab.create($('.lay-tab'), $(this).attr('data-id'), $(this)
						.find('cite').text(), $(this).attr('data-href'));
				$('.sub-menu li').removeClass('active');
				$(this).addClass('active');
			});
}
var tab = {
	create : function(tabDom, id, title, url) {
		if (tabDom.tabs('exists', title)) {
			tabDom.tabs('select', title);
			return;
		}
		progress.show('加载中', '正在加载中，请耐心等待...');
		tabDom.tabs('add', {
			id : 'tab_' + id,
			title : title,
			href : url,
			closable : true,
			cls : 'content-tab-header',
			bodyCls : 'content-tab-content',
			extractor : function(data) {
				var res = JSON.parse(data);
				consoloe.log(res);
			},
			onLoad : function() {
				progress.close();
			}
		});
	}
};
var progress = {
	show : function(title, text) {
		parent.$.messager.progress({
			title : title,
			text : text
		});
		setTimeout(function() {
			progress.close();
		}, timeout * 1000);
	},
	close : function() {
		parent.$.messager.progress('close');
	}
};
// IE8 console.log() 未定义错误
window.console = window.console || (function(){ 
	var c = {};
	c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile 
	= c.clear = c.exception = c.trace = c.assert = function(){}; 
	return c; 
})();