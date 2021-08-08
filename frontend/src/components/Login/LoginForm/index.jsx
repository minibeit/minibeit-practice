import React from "react";
import { useHistory } from "react-router";
import { userState } from "../../../recoil/userState";
import { useRecoilState } from 'recoil';

import PLoginForm from "./PLoginForm";
import { onLogin } from "../../../api/authAPI";

export default function LoginForm() {
  const history = useHistory()
  const [loginedUserState, setLoginedUserState] = useRecoilState(userState)
  function loginHandler(userData){
    const config = {
      method: 'post',
      url: 'http://3.36.95.15:8080/api/user/login',
      data: userData
    }
    const success = (res)=>{
      /* 받아온 엑세스 토큰을 로컬에 저장시킴. */
      localStorage.setItem("accessToken", res.data.accessToken)
      localStorage.setItem("accessTokenExpiredAt", res.data.accessTokenExpiredAt)
      /* 유저 상태 변경 */
      setLoginedUserState({
        isLogin: true,
        name: res.data.name,
      })
      /* 메인페이지로 이동시킴 */
      history.push('/')
    }
    const fail = (err)=>{
      console.log(err)
      /* err == undefined면 유저가 없는것 */
    }
    onLogin(config, success, fail);
  }
  return <PLoginForm loginHandler={loginHandler}/>;
}
