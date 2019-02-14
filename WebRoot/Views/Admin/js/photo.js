var video = document.querySelector('video');
var canvas = document.querySelector('canvas');
var ctx = canvas.getContext('2d');
var localMediaStream = null;
var img = document.querySelector('img');

//实现自动拍照
// var li_cnt = 0;
// var lo_time1 = window.setInterval(function(){
// alert("拍照"+li_cnt);
// snapshot();
// alert(li_cnt);
// if((++li_cnt)>2) 
// lo_time1=clearInterval(lo_time1);},10000)


var snapshot = function () {
    if (localMediaStream) {

        ctx.drawImage(video, 0, 0);
        img.src = canvas.toDataURL('image/jpeg');//webp
        //document.querySelector('img').src = canvas.toDataURL('image/webp');
    }
};
// var sizeCanvas = function () {
// 	   canvas.width = '250px';
// 	   canvas.height ='250px';
// 	   img.width='250px';
// 	   img.height='250px';
// 	}
var sizeCanvas = function () {
    setTimeout(function () {
        canvas.width = video.videoWidth;
        canvas.height = video.videoHeight;
        img.width = video.videoWidth;
        img.height = video.videoHeight;
    }, 2000);
};

//实现管理员单击按钮提交照片
var btnCapture;
btnCapture = document.getElementById('capture');
btnCapture.addEventListener('click', snapshot, false);


navigator.mozGetUserMedia(
    {video: true},
    function (stream) {
        video.src = window.URL.createObjectURL(stream);
        localMediaStream = stream;
        sizeCanvas();
    },
    function () {
        alert('your browser does not support getUserMedia');
    }
);