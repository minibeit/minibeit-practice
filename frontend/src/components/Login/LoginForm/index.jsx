import React from "react";
import { useHistory } from "react-router";

import PLoginForm from "./PLoginForm";
import { onLogin } from "../../../api/authAPI";

export default function LoginForm() {
  const history = useHistory()
  function loginHandler(userData){
    const config = {
      method: 'post',
      url: 'http://3.36.95.15:8080/api/user/login',
      data: userData
    }
    const success = (res)=>{
      console.log(res.data)
      /* 받아온 엑세스 토큰을 로컬에 저장시킴. */
      localStorage.setItem("accessToken", res.data.accessToken)
      localStorage.setItem("accessTokenExpiredAt", res.data.accessTokenExpiredAt)
      /* 리프레쉬 토큰 로컬에 저장시킴 */
      /* 메인페이지로 이동시킴 */
      history.push('/')
    }
    const fail = (err)=>{
      alert(err)
    }
    onLogin(config, success, fail);
  }
  return <PLoginForm loginHandler={loginHandler}/>;
}
