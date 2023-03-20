class Ecran {
    dom = document.getElementsByClassName('ecran-ligne');
    inputTextElt = document.getElementById('inputText');

    data = [
        [
            {
                direction: 'L',
                value: {
                    text: 'Test 1',
                    color: 'white'
                }
            },
            {
                direction: 'C',
                value: {
                    text: 'Test 1',
                    color: 'white'
                }
            },
            {
                direction: 'R',
                value: {
                    text: 'Test 1',
                    color: 'white'
                }
            }
        ],
        [
            {
                direction: 'L',
                value: {
                text: 'Test 2',
                color: 'white'
                }
            },
            {
                direction: 'C',
                value: {
                    text: 'Test 2',
                    color: 'white'
                }
            },
            {
                direction: 'R',
                value: {
                    text: 'Test 2',
                    color: 'white'
                }
            }
        ],
        [
            {
                direction: 'L',
                value: {
                    text: 'Test 3',
                    color: 'white'
                }
            },
            {
                direction: 'C',
                value: {
                    text: 'Test 3',
                    color: 'white'
                }
            },
            {
                direction: 'R',
                value: {
                    text: 'Test 3',
                    color: 'white'
                }
            }
        ],
        [
            {
                direction: 'L',
                value: {
                    text: 'Test 4',
                    color: 'white'
                }
            },
            {
                direction: 'C',
                value: {
                    text: 'Test 4',
                    color: 'white'
                }
            },
            {
                direction: 'R',
                value: {
                    text: 'Test 4',
                    color: 'white'
                }
            }
        ],
        [
            {
                direction: 'L',
                value: {
                    text: 'Test 5',
                    color: 'white'
                }
            },
            {
                direction: 'C',
                value: {
                    text: 'Test 5',
                    color: 'white'
                }
            },
            {
                direction: 'R',
                value: {
                    text: 'Test 5',
                    color: 'white'
                }
            }
        ],
        [
            {
                direction: 'L',
                value: {
                    text: 'Test 6',
                    color: 'white'
                }
            },
            {
                direction: 'C',
                value: {
                    text: 'Test 6',
                    color: 'white'
                }
            },
            {
                direction: 'R',
                value: {
                    text: 'Test 6',
                    color: 'white'
                }
            }
        ]
    ];

    getlskValueElt(lskNum, direction) {
        let directionNum = 0;
        switch (direction) {
            case "C":
                directionNum = 1;
                break;
            case "L":
                directionNum = 0;
                break;
            case "R":
                directionNum = 2;
        }
        return this.dom[lskNum - 1].getElementsByTagName('div')[directionNum];
    }

    setvalue(lskKey) {
        let lskValueElt = this.getlskValueElt(lskKey.num, lskKey.direction);
        lskValueElt.innerHTML = lskKey.value.text;
        lskValueElt.style.color = lskKey.value.couleur;
    }

    initializationKeyboard() {
        const lettresElt = document.getElementsByClassName('clavier_lettres')[0].getElementsByTagName('button');
        const chiffresElt = document.getElementsByClassName('clavier_chiffres')[0].getElementsByTagName('button');

        for (let lettreElt of lettresElt) {
            lettreElt.addEventListener("click",() => this.keyPressed(lettreElt.id, 'L'));
        }

        for (let chiffreElt of chiffresElt) {
            chiffreElt.addEventListener("click",() => this.keyPressed(chiffreElt.id, 'C'));
        }

        document.getElementById('CLEAR').addEventListener("click", () => this.clearInputText())
    }

    listenControlKey(callback) {
        const controls1Elt = document.getElementsByClassName('clavier_commandes')[0].getElementsByTagName('button');
        const controls2Elt = document.getElementsByClassName('clavier_fleches')[0].getElementsByTagName('button');
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