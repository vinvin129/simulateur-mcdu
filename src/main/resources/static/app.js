import {ecranSystem} from "./ecran_system.js";

let stompClient = null;


function connect() {
    let socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/mcdu/receive/newView', function (greeting) {
            console.log(JSON.parse(greeting.body));
            const json = JSON.parse(greeting.body);
            let lskKey = json.lskKey;
            ecranSystem.setvalue({num: parseInt(lskKey[3]), direction: lskKey[4], value: {text: json.donnee.valeur, couleur: json.donnee.couleur}})
        });
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

    ecranSystem.initializationKeyboard();
});