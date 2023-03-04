import {ecranSystem} from "./ecran_system.js";

let stompClient = null;


function connect() {
    let socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/mcdu/receive/newView', function (greeting) {
            console.log(JSON.parse(greeting.body));
        });
        stompClient.send("/mcdu/emit/click", {}, JSON.stringify({
            lskKey: 'LSK1L',
            input: 'ABCD'
        }));
    });
}

function onLSKClick(lskKey) {
    stompClient.send("/mcdu/emit/click", {}, JSON.stringify({
        lskKey: lskKey,
        input: ecranSystem.getInput()
    }));
    ecranSystem.clearInputText();
}

window.onLSKClick = onLSKClick;

$(function () {
    connect();
    const lsk = {
        num: 1,
        direction: "L",
        value: {
            text: 'Toto !!!',
            color: 'black'
        }
    };
    ecranSystem.setvalue(lsk);

    ecranSystem.initializationKeyboard();
});