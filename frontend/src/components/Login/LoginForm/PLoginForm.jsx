import React from "react";
import { Link } from 'react-router-dom';

/* Components */
import * as S from '../style';

export default function PLoginForm() {
  return (
    <S.LoginForm> {/* 액션, 메소드 추가 */}
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
      <input type='submit'/> {/* default prevent 받은데이터 formdata 만든 후 전송 */}
      <Link to='/signup'><p>회원가입</p></Link>
    </S.LoginForm>
  );
}
