package com.example.authorizationserver.web.form.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberSignUpForm {


    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String id;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String pass;

    @NotEmpty(message = "이름은 필수항목입니다.")
    private String name;
}
