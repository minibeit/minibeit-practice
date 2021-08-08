import React from "react";
import { Link } from "react-router-dom";
import NavBar from "../../components/Common/NavBar";

export default function ApplyHome() {
  return (
    <>
      <NavBar />
      <Link to="/feedList/survey">
        <p>설문조사 하러가기</p>
      </Link>
      <Link to="/feedList/experiment">
        <p>대면실험 하러가기</p>
      </Link>
      <Link to="/feedList/survey/feedNew">
        <p>설문조사 모집하기</p>
      </Link>
      <Link to="/feedList/experiment/feedNew">
        <p>대면실험 모집하기</p>
      </Link>
    </>
  );
}
