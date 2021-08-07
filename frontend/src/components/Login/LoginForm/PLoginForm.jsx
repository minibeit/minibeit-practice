import React, { useState } from "react";
import { Link } from 'react-router-dom';

/* Components */
import * as S from '../style';

export default function PLoginForm(props) {
  const [userData, setUserData] = useState({
    email: '',
    password: ''
  })

  const onChange=(e)=>{
    const userData_cp = userData;
    userData_cp[`${e.target.name}`] = e.target.value;
    setUserData(userData_cp);
  }

  const onSubmit = (e)=>{
    e.preventDefault();
    props.loginHandler(userData);
  }

  return (
    <S.LoginForm
      onSubmit={onSubmit}
    > {/* 액션, 메소드 추가 */}
      <label for='email'>이메일</label>
      <S.EmailInput
        id='email'
        name='email'
        required='required'
        onChange={onChange}
      />
      <label for='pw'>비밀번호</label>
      <S.PwInput
        id='pw'
        type='password'
        name='password'
        required='required'
        onChange={onChange}
      />
      <input type='submit'/> {/* default prevent 받은데이터 formdata 만든 후 전송 */}
      <Link to='/signup'><p>회원가입</p></Link>
    </S.LoginForm>
  );
}
