import React from "react";
import { useHistory } from "react-router";
import { signUpfunc } from "../../../utils";
import PSignupForm from "./PSignupForm";

export default function SignupForm() {
  const history = useHistory();
  const signupHandler = async (username, useremail, password) => {
    try {
      const check = await signUpfunc(username, useremail, password);
      if (check) {
        window.alert("회원가입이 완료되었습니다. 로그인을 해주세요.");
        history.push("/login");
      }
    } catch (e) {
      console.log(e.response.data.error.msg);
      alert(e.response.data.error.msg);
    }
  };
  return <PSignupForm signupHandler={signupHandler} />;
}
