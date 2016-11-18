var robotName = 'DataGuru',
  robotAvatar = 'static/img/robot.jpg',
  userName = 'Andy Li',
  userAvatar = 'static/img/andyli.png',
  photoBrowser;

$(function() {
  //FastClick
  FastClick.attach(document.body);
  //chat images click listener
  $('.aui-chat').on('click', '.aui-chat-content img', function() {
    var items = [],
      item = {};
    item.image = $(this).attr('src');
    item.caption = $(this).attr('alt');
    items.push(item);
    showPic(items);
  });
  //chat link click listener
  $('.aui-chat').on('click', '[data-action="getLinkContent"]', function() {
	  clickLink(1,userName,$(this).text(),$(this).attr('data-id'));
  });
  //send button click listener
  $('.sendBtn').on('click', function() {
    //send message
    sendMsgExt(userAvatar, userName, 'text', $('.editArea').val());
    askRobot(1,userName,$('.editArea').val());
    //receive message
//    switch ($('.editArea').val()) {
//      case '1':
//        recvMsgExt(robotAvatar, robotName, 'text', 'What do you want to know about the segments?<br>[11] List all the category of your segments.');
//        break;
//      case '11':
//        recvMsgExt(robotAvatar, robotName, 'image', 'developing', 'static/img/developing.jpg');
//        break;
//      case '2':
//        recvMsgExt(robotAvatar, robotName, 'text', 'What the partner info do you want to know?<br>[21] List all the category of your segments.');
//        break;
//      case '21':
//        recvMsgExt(robotAvatar, robotName, 'image', 'developing', 'static/img/developing.jpg');
//        break;
//      default:
//        recvMsgExt(robotAvatar, robotName, 'text', 'Hello, I’m DataGuru. What I can help?<br>[1] Segment Consulting<br>[2] Partner Consulting<br>Reply the number of your question. Like “1”.');
//    }
    //empty edit area
    $('.editArea').val('');
  });
  //1 timestamp
  timeMsg({
    text: new Date().format('yyyy-MM-dd hh:mm:ss')
  });
  //2 welcome
  recvMsgExt(robotAvatar, robotName, [{
	  msgType :  "text",
	  text : "Hello, I’m DataGuru. What I can help?"
  }]);


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

function sendMsgExt(_avatar, _userName, _msgType, _text, _image) {
  var data = {
    avatar: _avatar,
    userName: _userName,
    msgType: _msgType,
    text: _text,
    image: _image
  };
  sendMsg(data);
}

function recvMsg(data) {
  laytpl($('#recvMsg').html()).render(data, function(html) {
    $('.aui-chat').append(html);
    scrollToMsg();
  });
}

function recvMsgExt(_avatar, _userName, _answer) {
  var data = {
    avatar: _avatar,
    userName: _userName,
    answer:_answer
  };
  setTimeout(function(){
    recvMsg(data)
  }, 800);
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
    "M+": this.getMonth() + 1, //month
    "d+": this.getDate(), //day
    "h+": this.getHours(), //hour
    "m+": this.getMinutes(), //minute
    "s+": this.getSeconds(), //second
    "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
    "S": this.getMilliseconds() //millisecond
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
	$.post('api/qas/question',{
    	userId:_userId,
    	userName:_userName,
    	question:_question
    },function(res){
    	if(res && res.answer){
    		recvMsgExt(robotAvatar, robotName, res.answer);
    	}
    });
}
function clickLink(_userId,_userName,_question,_id){
	$.post('api/qas/question/id/'+_id,{
		userId:_userId,
    	userName:_userName,
    	question:_question
    },function(res){
    	if(res && res.answer){
    		recvMsgExt(robotAvatar, robotName, res.answer);
    	}
    });
}