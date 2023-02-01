// 아이디의 유효성 여부를 저장할 변수를 만들고 초기값 false 부여
let isIdValid=false;
// 패스워드의 유효성 여부를 저장할 변수를 만들고 초기값 false 부여
let isPasswardValid=false;
// 패스워드 확인의 유효성 여부를 저장할 변수를 만들고 초기값 false 부여
let isPasswordConfirmValid=false;
// 이름의 유효성 여부를 저장할 변수를 만들고 초기값 false 부여
let isNameValid=false;

// id 가 id 인 input 요소에 input 이벤트가 일어났을때 실행할 함수 등록
$("#id").on("input", function(){
    // 1. 입력한 value 값을 읽어온다.
    let inputId=this.value;
    // 2. 유효성(4글자이상 50글자 이하)을 검증한다.
    isIdValid = inputId.length >= 4 && inputId.length <= 50;
    // 3. 유효하다면 input 요소에 is-valid 클래스 추가, 아니라면 is-invalid 클래스 추가
    if(isIdValid){
        this.classList.remove("is-invalid");
        this.classList.add("is-valid");
    }else{
        this.classList.remove("is-valid");
        this.classList.add("is-invalid");
    }
});

// id 가 password 인 input 요소에 input 이벤트가 일어났을때 실행할 함수 등록
$("#password").on("input", function(){
    // 영문 대문자 + 소문자 + 숫자 조합
    var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$/;

    // 1. 입력한 value 값을 읽어온다.
    let inputPassword=this.value;
    // 2. 정규표현식(1자리의 영문 대문자 + 영문 소문자 + 숫자 조합)을 통해 유효성을 검사
    isPasswardValid = reg.test(inputPassword);
    // 3. 유효하다면 input 요소에 is-valid 클래스 추가, 아니라면 is-invalid 클래스 추가
    if(isPasswardValid){
        this.classList.remove("is-invalid");
        this.classList.add("is-valid");
    }else{
        this.classList.remove("is-valid");
        this.classList.add("is-invalid");
    }
    // 패스워드를 변경하면 패스워드 확인에 입력한 값과 차이가 발생하기 때문에
    // 패스워드 확인의 유효성 검사를 실시
    passwordConfirmCheck();
});

// id 가 passwordConfirm 인 input 요소에 input 이벤트가 일어났을때 실행할 함수 등록
$("#passwordConfirm").on("input", function(){
    // 패스워드 확인의 유효성 검사를 실시
    passwordConfirmCheck();
});

// 패스워드 확인의 유효성 검사
function passwordConfirmCheck() {
    let passwordConfirm = $("#passwordConfirm");

    // 1. 입력한 value 값을 읽어온다.
    let inputPasswordConfirm=passwordConfirm.val();
    // 2. 유효성(패스워드의 유효성이 true이며 패스워드와 패스워드 확인의 입력값이 일치)을 검증한다.
    isPasswordConfirmValid = isPasswardValid == true && $("#password").val() == inputPasswordConfirm;
    // 3. 유효하다면 input 요소에 is-valid 클래스 추가, 아니라면 is-invalid 클래스 추가
    if(isPasswordConfirmValid){
        passwordConfirm.removeClass("is-invalid");
        passwordConfirm.addClass("is-valid");
    }else{
        passwordConfirm.removeClass("is-valid");
        passwordConfirm.addClass("is-invalid");
    }
}

// id 가 name 인 input 요소에 input 이벤트가 일어났을때 실행할 함수 등록
$("#name").on("input", function(){
    // 1. 입력한 value 값을 읽어온다.
    let inputName=this.value;
    // 2. 유효성(글자수가 0이 아니며 30글자 이하)을 검증한다.
    isNameValid = inputName.length != 0 && inputName.length <= 30;
    // 3. 유효하다면 input 요소에 is-valid 클래스 추가, 아니라면 is-invalid 클래스 추가
    if(isNameValid){
        this.classList.remove("is-invalid");
        this.classList.add("is-valid");
    }else{
        this.classList.remove("is-valid");
        this.classList.add("is-invalid");
    }
});