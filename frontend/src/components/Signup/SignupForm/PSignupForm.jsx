import React, { useState } from "react";



/* Components */
import * as S from '../style';

export default function PSignupForm(props) {
  const [userData, setUserData] = useState({
    email: '',
    name: '',
    password: ''
  })

  const onChange = (e)=>{
    const userData_cp = userData;
    const type = e.target.name;

    userData_cp[`${type}`] = e.target.value;
    setUserData(userData_cp)
  }

  const onSubmit = (e)=>{
    e.preventDefault();
    props.signupHandler(userData);
  }

  return (
    <S.SignupForm
      onSubmit={onSubmit}
    >
        <label for='email'>이메일</label>
        <S.EmailInput
            id='email'
            name='email'
            required='required'
            onChange={onChange}
        />
        <label for='name'>이름</label>
        <S.NameInput
            id='name'
            name='name'
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
        <input type='submit'/>
    </S.SignupForm>
  );
}
