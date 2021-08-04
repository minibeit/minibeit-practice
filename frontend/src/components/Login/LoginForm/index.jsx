import React from "react";
import { useHistory } from "react-router-dom";
import PLoginForm from "./PLoginForm";
import { obtainToken } from "../../../utils";

export default function LoginForm() {
  const history = useHistory();
  const loginHandler = async (username, password) => {
    try {
      const data = await obtainToken(username, password);
      if (data.uuid && !data.isUser) {
        window.alert("로그인 정보가 정확하지 않습니다.");
      }
      if (data.isUser) {
        // 이미 존재하는 유저
        history.push("/mypage");
      }
    } catch (e) {
      console.log(e.response.data.error.msg);
      // console.log(e.response.data.error.msg);
      alert(e.response.data.error.msg);
    }
  };
  return <PLoginForm loginHandler={loginHandler} />;
}
