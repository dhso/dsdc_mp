var robotName = 'Abby',
  robotAvatar = 'static/img/robot.jpg',
  userName = 'Andy Li',
  userAvatar = 'static/img/andyli.png',
  photoBrowser;

$(function() {
  // FastClick
  FastClick.attach(document.body);
  // chat images click listener
  $('.aui-chat').on('click', '.aui-chat-content img', function() {
    var items = [],
      item = {};
    item.image = $(this).attr('src');
    item.caption = $(this).attr('alt');
    items.push(item);
    showPic(items);
  });
  // chat link click listener
  $('.aui-chat').on('click', '[data-action="getLinkContent"]', function() {
	  clickLink(1,userName,$(this).text(),$(this).attr('data-id'));
  });
  // send button click listener
  $('.sendBtn').on('click', function() {
    // send message
    sendMsgExt(userAvatar, userName, 'text', $('.editArea').val());
    askRobot(1,userName,$('.editArea').val());
    // empty edit area
    $('.editArea').val('');
  });
  // 1 timestamp
  timeMsg({
    text: new Date().format('yyyy-MM-dd hh:mm:ss')
  });
  // 2 welcome
  getTip(1,userName,'','wx.tip.hello');
  
});

function showPic(items) {
  photoBrowser = $.photoBrowser({
    items: items
  });
  photoBrowser.open();
}

function sendMsg(data) {
  laytpl($('#sendMsg').html()).render(data, function(html) {
    $('.aui-chat').append(html);
    scrollToMsg();
  });
}

function sendMsgExt(_avatar, _userName, _type, _text, _image) {
  var data = {
    avatar: _avatar,
    userName: _userName,
    type: _type,
    text: _text,
    image: _image
  };
  sendMsg(data);
}

function recvTextMsg(data) {
  laytpl($('#recvTextMsg').html()).render(data, function(html) {
    $('.aui-chat').append(html);
    scrollToMsg();
  });
}

function recvTextMsgExt(_avatar, _userName, _answer) {
  var data = {
    avatar: _avatar,
    userName: _userName,
    answer:_answer
  };
  recvTextMsg(data);
}

function recvImageMsg(data) {
	  laytpl($('#recvImageMsg').html()).render(data, function(html) {
	    $('.aui-chat').append(html);
	    scrollToMsg();
	  });
	}

	function recvImageMsgExt(_avatar, _userName, _answer) {
	  var data = {
	    avatar: _avatar,
	    userName: _userName,
	    answer:_answer
	  };
	  recvImageMsg(data);
	}

function recvIframeMsg(data) {
	  laytpl($('#recvIframeMsg').html()).render(data, function(html) {
	    $('.aui-chat').append(html);
	    scrollToMsg();
	  });
	}

function recvIframeMsgExt(_avatar, _userName, _answer) {
	  var data = {
	    avatar: _avatar,
	    userName: _userName,
	    answer:_answer
	  };
	  recvIframeMsg(data);
}
	
function recvChooseMsg(data) {
  laytpl($('#recvChooseMsg').html()).render(data, function(html) {
    $('.aui-chat').append(html);
    scrollToMsg();
  });
}

function recvChooseMsgExt(_avatar, _userName, _answer) {
	  var data = {
	    avatar: _avatar,
	    userName: _userName,
	    answer:_answer
	  };
	  recvChooseMsg(data);
}

function timeMsg(data) {
  laytpl($('#timeMsg').html()).render(data, function(html) {
    $('.aui-chat').append(html);
    scrollToMsg();
  });
}

function scrollToMsg(speed) {
  if (!speed) speed = 300;
  $('.scroll_page').animate({
    scrollTop: $('.scroll_page').scrollHeight()
  }, speed);
}
Date.prototype.format = function(format) {
  var o = {
    "M+": this.getMonth() + 1, // month
    "d+": this.getDate(), // day
    "h+": this.getHours(), // hour
    "m+": this.getMinutes(), // minute
    "s+": this.getSeconds(), // second
    "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
    "S": this.getMilliseconds() // millisecond
  }
  if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(format))
      format = format.replace(RegExp.$1,
        RegExp.$1.length == 1 ? o[k] :
        ("00" + o[k]).substr(("" + o[k]).length));
  return format;
}

function askRobot(_userId,_userName,_question){
	$.post('api/question/',{
    	userId:_userId,
    	userName:_userName,
    	question:_question
    },function(res){
    	if(res && res.answer){
    		identMsgType(robotAvatar, robotName, res.answer);
    	}
    });
}
function getTip(_userId,_userName,_question,_type){
	$.post('api/question/type/'+_type,{
    	userId:_userId,
    	userName:_userName,
    	question:_question
    },function(res){
    	if(res && res.answer){
    		identMsgType(robotAvatar, robotName, res.answer);
    		// 3 first ask
    		  recvChooseMsgExt(robotAvatar,robotName,[{"id":10,"text":"The top 10 valuable segments in past 30 days.","type":"link"},
    		                                          {"id":11,"text":"The top 10 segments with huge volume.","type":"link"},
    		                                          {"id":12,"text":"The top 10 segments with best ROI.","type":"link"},
    		                                          {"id":15,"text":"Recommend segment for you.","type":"link"}]);
    	}
    });
}
function clickLink(_userId,_userName,_question,_id){
	$.post('api/question/id/'+_id,{
		userId:_userId,
    	userName:_userName,
    	question:_question
    },function(res){
    	if(res && res.answer){
    		identMsgType(robotAvatar, robotName, res.answer);
    	}
    });
}
function identMsgType(_avatar, _username, _answer){
	if(_answer.length > 1){
		recvChooseMsgExt(_avatar, _username, _answer);
		return false;
	}
	switch (_answer[0].type){
	case 'chart':
		recvIframeMsgExt(_avatar, _username, _answer[0]);
		break;
	case 'image':
		recvImageMsgExt(_avatar, _username, _answer[0]);
		break;
	default:
		recvTextMsgExt(_avatar, _username, _answer[0]);
		break;
	}
}