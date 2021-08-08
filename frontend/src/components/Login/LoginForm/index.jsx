import React from "react";
import { useHistory } from "react-router-dom";
import PLoginForm from "./PLoginForm";
import { obtainToken } from "../../../utils";

export default function LoginForm() {
  const history = useHistory();
  const loginHandler = async (useremail, password) => {
    try {
      const data = await obtainToken(useremail, password);
      console.log(data);
      if (data) {
        window.alert("로그인에 성공!");
        history.push("/");
      }
    } catch (e) {
      console.log(e.response.data.error.msg);
      alert(e.response.data.error.msg);
    }
  };
  return <PLoginForm loginHandler={loginHandler} />;
}
