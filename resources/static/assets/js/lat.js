async function getQuest(qId) {
    let response = await fetch("getQuestion?id=" + qId.toString());
    console.log(response);
    if (response.ok) {
        let json = await response.json();
        document.getElementById("qwest").textContent = json.quest;
        document.getElementById("answer").value = json.answer;
        document.getElementById("test-type").value = json.userRole;
        document.getElementById("iddd").value = qId.toString();
    }
}

function cleanQuestForm() {
    document.getElementById("qwest").textContent = "";
    document.getElementById("answer").value = "";
    document.getElementById("test-type").value = "";
    document.getElementById("iddd").value = "";
}

async function getQuestionForTest(role, testId) {
    document.getElementById('questionForTest').innerHTML = '';
    document.getElementById('idTestt').value = testId;
    var block = '';
    var questionForRoleLength;
    let response1 = await fetch("getQuestionForRole?role=" + role.toString());
    let response2 = await fetch("getQuestionForTest?id=" + testId.toString());
    if (response1.ok) {
        let questionForRole = await response1.json();

        questionForRoleLength = questionForRole.length;
        for (let i = 0; i < questionForRole.length; i++) {
            block += '<br><div className="row" id="questCh">' +
                '    ' +
                '        <input name="qwestTest" className="form-check-input" type="checkbox" value="' + questionForRole[i].id + '" id="defaultCheck' + i + '"/>' +
                '    ' +
                '    ' +
                '        <label id="qwestText' + i + '">' + questionForRole[i].quest + '</label>' +
                '    ' + '<div hidden id="idQuest' + i + '">' + questionForRole[i].id + '</div>' +
                '</div>'
        }
    }
    document.getElementById('questionForTest').insertAdjacentHTML('beforeEnd', block);

    if (response2.ok) {
        let test = await response2.json();
        for (let i = 0; i < test.length; i++) {
            for (let j = 0; j < questionForRoleLength; j++) {
                // console.log(document.getElementById('idQuest' + j).textContent.toString());
                // console.log(test[i].id.toString());
                if (document.getElementById('idQuest' + j).textContent.toString() === test[i].id.toString()) {
                    document.getElementById('defaultCheck' + j).checked = true;
                    // console.log('ok');
                }
            }
        }
    }
}

async function postSaveQuestionForTest() {
    let oqw = [];
    oqw.push(document.getElementById('idTestt').value);
    let qw = document.getElementsByName('qwestTest');
    for (let i = 0; i < qw.length; i++) {
        if (qw[i].checked === true) {
            oqw.push(qw[i].value);
        }
    }

    const settings = {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(oqw)
    };
    location.reload();
    try {
        let fetchResponse = await fetch('modifyTestForUser', settings);
        let data = await fetchResponse.json();
        return data;
    } catch (e) {
        return e;
    }
}

//функция для смены типа вопроса
function choiceOfAnswer() {
    if(document.getElementById('choiceOfAnswerSelect').value === 'CHOICE_OF_ANSWER'){
        document.getElementById('choiceOfAnswer').hidden = false;
    }
    else if(document.getElementById('choiceOfAnswerSelect').value === 'OPEN_ANSWER'){
        document.getElementById('choiceOfAnswer').hidden = true;
    }
    else if(document.getElementById('choiceOfAnswerSelect').value === 'CHOICE_OF_ANSWER_MN'){
        document.getElementById('choiceOfAnswer').hidden = false;
    }
}
