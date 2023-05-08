class Ecran {
    datasLeftElt = document.getElementsByClassName('data_left');
    datasRightElt = document.getElementsByClassName('data_right');
    datasCenterElt = document.getElementsByClassName('data_center');
    inputTextElt = document.getElementById('display_input');

    getDataLabelElt(lskNum, direction) {
        switch (direction) {
            case "C":
                return this.datasCenterElt[lskNum-1].getElementsByClassName('data_title')[0];
            case "L":
                return this.datasLeftElt[lskNum-1].getElementsByClassName('data_title')[0];
            case "R":
                return this.datasRightElt[lskNum-1].getElementsByClassName('data_title')[0];
        }
    }

    getDataValueElt(lskNum, direction) {
        switch (direction) {
            case "C":
                return this.datasCenterElt[lskNum-1].getElementsByClassName('data_value')[0];
            case "L":
                return this.datasLeftElt[lskNum-1].getElementsByClassName('data_value')[0];
            case "R":
                return this.datasRightElt[lskNum-1].getElementsByClassName('data_value')[0];
        }
    }

    setData(data) {
        const dataValueElt = this.getDataValueElt(data.num, data.direction);
        const dataLabelElt = this.getDataLabelElt(data.num, data.direction);

        dataValueElt.innerHTML = data.value.text;
        dataValueElt.style.color = data.value.couleur;

        dataLabelElt.innerHTML = data.value.label;
    }

    initializationKeyboard() {
        const lettresElt = document.getElementById('clavier_lettre').getElementsByTagName('button');
        const chiffresElt = document.getElementById('clavier_chiffre').getElementsByTagName('button');

        for (let lettreElt of lettresElt) {
            lettreElt.addEventListener("click",() => this.keyPressed(lettreElt.id, 'L'));
        }

        for (let chiffreElt of chiffresElt) {
            chiffreElt.addEventListener("click",() => this.keyPressed(chiffreElt.id, 'C'));
        }

        document.getElementById('CLEAR').addEventListener("click", () => this.clearInputText())
    }

    listenControlKey(callback) {
        const controls1Elt = document.getElementById('controls_top').getElementsByTagName('button');
        const controls2Elt = document.getElementById('controls_bottom').getElementsByTagName('button');
        for (let keyElt of controls1Elt) {
            keyElt.addEventListener("click", () => callback(keyElt.id));
        }

        for (let keyElt of controls2Elt) {
            keyElt.addEventListener("click", () => callback(keyElt.id));
        }
    }

    keyPressed(letter, type) {
        console.log(`lettre : ${letter}; type : ${type}`);
        this.inputTextElt.innerText += letter.toUpperCase();
    }

    clearInputText() {
        this.inputTextElt.innerText = "";
    }

    getInput() {
        return this.inputTextElt.innerText;
    }
}

export const ecranSystem = new Ecran();