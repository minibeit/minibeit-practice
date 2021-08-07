import React from "react";
import { useHistory } from "react-router";

import PSignupForm from "./PSignupForm";
import { onSignup } from "../../../api/authAPI";

export default function SignupForm() {
  var history = useHistory();

  function signupHandler(userData){
    const config = {
      method: 'post',
      url: 'http://3.36.95.15:8080/api/user/signup',
      data: userData
    }
    const success = (res)=>{
      alert(`회원가입 성공. 로그인 창으로 이동합니다.`);
      history.push('/login')
    }
    const fail = (err)=>{
      alert(`error`);
    }
    onSignup(config,success,fail)
  }

  return <PSignupForm signupHandler={signupHandler}/>;
}