function hasUserMedia() {
    //判断是否支持调用设备api，因为浏览器不同所以判断方式不同哦
    return !!(navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);
}

if (hasUserMedia()) {
    //alert(navigator.mozGetUserMedia)
    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
    var video = document.querySelector("video");
    var canvas = document.querySelector("canvas");
    var streaming = false;
    navigator.getUserMedia({
        video: true,//开启视频
        audio: false//先关闭音频，因为会有回响，以后两台电脑通信不会有响声
    }, function (stream) {//将视频流交给video
        video.src = window.URL.createObjectURL(stream);
        streaming = true;
    }, function (err) {
        console.log("capturing", err)
    });
    document.querySelector("#capture").addEventListener("click", function (event) {
        if (streaming) {
            //alert(video.clientHeight)
            //canvas.width = video.clientWidth;
            //canvas.height= video.clientHeight;
            canvas.width = 800;
            canvas.height = 800;
            var context = canvas.getContext('2d');
            context.drawImage(video, 20, 20);
            var img = document.querySelector('img');
            img.src = canvas.toDataURL('image/jpeg');
        }
    })
} else {
    alert("浏览器暂不支持")
}
