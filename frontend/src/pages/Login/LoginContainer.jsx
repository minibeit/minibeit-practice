import React from "react";
import { Link } from 'react-router-dom';

/* Components */
import * as S from './style';

export default function LoginContainer() {
  return (
    <S.LoginForm>
      <label for='email'>이메일</label>
      <S.EmailInput
        id='email'
        name='email'
        required='required'
      />
      <label for='pw'>비밀번호</label>
      <S.PwInput
        id='pw'
        name='password'
        required='required'
      />
      <input type='submit'/>
      <Link to='/signup'><p>회원가입</p></Link>
    </S.LoginForm>
  );
}
