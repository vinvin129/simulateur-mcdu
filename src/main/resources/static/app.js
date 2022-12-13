let stompClient = null;

function connect() {
    let socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/lskClick', function (greeting) {
            console.log(JSON.parse(greeting.body).content);
        });
        stompClient.send("/app/lskClick", {}, JSON.stringify({
            'lskTouch': 'LSK1L',
            'input': 'ABCD'
        }))
    });
}

$(function () {
    connect();
});