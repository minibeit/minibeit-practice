import React from "react";

/* Components */
import * as S from '../style';

export default function PSignupForm() {
  return (
    <S.SignupForm>
        <label for='email'>이메일</label>
        <S.EmailInput
            id='email'
            name='email'
            required='required'
        />
        <label for='name'>이름</label>
        <S.NameInput
            id='name'
            name='name'
            required='required'
        />
        <label for='pw'>비밀번호</label>
        <S.PwInput
            id='pw'
            name='password'
            required='required'
        />
        <input type='submit'/>
    </S.SignupForm>
  );
}
