var id = document.querySelector('#id');
var pw1 = document.querySelector('#pswd1');
var pwMsg = document.querySelector('#alertTxt');
var pw2 = document.querySelector('#pswd2');
var pwMsgArea = document.querySelector('.int_pass');
var userName = document.querySelector('#name');
var email = document.querySelector('#email');
var mobile = document.querySelector('#mobile');
var error = document.querySelectorAll('.error_next_box');
var joinBtn = document.querySelector('#btnJoin');
var idCheck = false;
var pwCheck = false;
var pw2Check = false;
var nameCheck = false;
var emailCheck = false;



id.addEventListener("focusout", checkId);
pw1.addEventListener("focusout", checkPw);
pw2.addEventListener("focusout", comparePw);
userName.addEventListener("focusout", checkName);
email.addEventListener("focusout", isEmailCorrect);
mobile.addEventListener("focusout", checkPhoneNum);

function btnActive() {
	const target = joinBtn;
	if (idCheck && pwCheck && pw2Check && nameCheck && phoneCheck && emailCheck) {
		target.disabled = false;
		joinBtn.innerHTML = "가입 하기"
		joinBtn.classList.remove('btnJoin_false');
		joinBtn.classList.add('btnJoin_true');
	}
}
function btnDisabled() {
	const target = joinBtn;
	if (!idCheck || !pwCheck || !pw2Check || !nameCheck || !phoneCheck || !emailCheck) {
		target.disabled = true;
		joinBtn.innerHTML = "가입 양식을 확인하세요"
		joinBtn.classList.remove('btnJoin_true');
		joinBtn.classList.add('btnJoin_false');
	}
}


function checkId() {
	var idPattern = /[a-zA-Z0-9_-]{4,20}/;
	if (id.value === "") {
		error[0].innerHTML = "필수 정보입니다.";
		error[0].style.color = "red";
		error[0].style.display = "block";
		idCheck = false;
		btnDisabled();
	} else if (!idPattern.test(id.value)) {
		error[0].innerHTML = "4~20자의 영어만 사용 가능합니다.";
		error[0].style.color = "red";
		error[0].style.display = "block";
		idCheck = false;
		btnDisabled();
	} else {
		error[0].innerHTML = "사용 가능";
		error[0].style.color = "#08A600";
		error[0].style.display = "block";
		idCheck = true;
		btnActive();
	}
}

function checkPw() {
	var pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
	if (pw1.value === "") {
		error[1].innerHTML = "필수 정보입니다.";
		error[1].style.display = "block";
		pwMsg.style.color = "red";
		pwCheck = false;
		btnDisabled();
	} else if (!pwPattern.test(pw1.value)) {
		error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
		pwMsg.innerHTML = "사용불가";
		pwMsgArea.style.paddingRight = "93px";
		error[1].style.display = "block";
		pwMsg.style.display = "block";
		pwMsg.style.color = "red";
		pwCheck = false;
		btnDisabled();
	} else {
		error[1].style.display = "none";
		pwMsg.innerHTML = "사용가능";
		pwMsg.style.display = "block";
		pwMsg.style.color = "#03c75a";
		pwCheck = true;
		btnActive();
	}
}

function comparePw() {
	if (pw2.value === pw1.value && pw2.value != "") {
		error[2].innerHTML = "일치";
		error[2].style.color = "#08A600";
		error[2].style.display = "block";
		pw2Check = true;
		btnActive();
	} else if (pw2.value !== pw1.value) {
		error[2].innerHTML = "비밀번호가 일치하지 않습니다.";
		error[2].style.display = "block";
		pw2Check = false;
		btnDisabled();
	}

	if (pw2.value === "") {
		error[2].innerHTML = "필수 정보입니다.";
		error[2].style.display = "block";
		pw2Check = false;
		btnDisabled();
	}
}

function checkName() {
	var namePattern = /[a-zA-Z가-힣]/;
	if (userName.value === "") {
		error[3].innerHTML = "필수 정보입니다.";
		error[3].style.color = "red";
		error[3].style.display = "block";
		nameCheck = false;
		btnDisabled();
	} else if (!namePattern.test(userName.value) || userName.value.indexOf(" ") > -1) {
		error[3].innerHTML = "이름을 정확히 입력하세요.(공백 사용 불가)";
		error[3].style.color = "red";
		error[3].style.display = "block";
		nameCheck = false;
		btnDisabled();
	} else {
		error[3].innerHTML = "사용 가능";
		error[3].style.display = "block";
		error[3].style.color = "#08A600";
		nameCheck = true;
		btnActive();
	}
}

function checkPhoneNum() {
	var isPhoneNum = /([01]{2})([01679]{1})([0-9]{3,4})([0-9]{4})/;

	if (mobile.value === "") {
		error[5].innerHTML = "필수 정보입니다.";
		error[5].style.display = "block";
		error[5].style.color = "red";
		phoneCheck = false;
		btnDisabled();
	} else if (!isPhoneNum.test(mobile.value)) {
		error[5].innerHTML = "형식에 맞지 않는 번호입니다.";
		error[5].style.color = "red";
		error[5].style.display = "block";
		phoneCheck = false;
		btnDisabled();
	} else {
		error[5].innerHTML = "사용 가능";
		error[5].style.display = "block";
		error[5].style.color = "#08A600";
		phoneCheck = true;
		btnActive();
	}
}

function isEmailCorrect() {
	var emailPattern = /[a-z0-9]{2,}@[a-z0-9-]{2,}\.[a-z0-9]{2,}/;

	if (email.value === "") {
		error[6].innerHTML = "필수 정보입니다.";
		error[6].style.display = "block";
		error[6].style.color = "red";
		emailCheck = false;
		btnDisabled();
	} else if (!emailPattern.test(email.value)) {
		error[6].innerHTML = "양식에 맞게 작성해주세요.";
		error[6].style.display = "block";
		error[6].style.color = "red";
		emailCheck = false;
		btnDisabled();
	} else {
		error[6].innerHTML = "사용가능";
		error[6].style.display = "block";
		error[6].style.color = "#08A600";
		emailCheck = true;
		btnActive();
	}

}


