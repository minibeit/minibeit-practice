import React from "react";
import { Link } from 'react-router-dom';

/* Components */
import NavBar from '../../components/NavBar/NavBar';

export default function ApplyHome() {
  return (
    <>
      <NavBar/>
      <Link to='/feedList/survey'><p>설문 실험 지원하기</p></Link>
      <Link to='/feedList/experi'><p>대면 실험 지원하기</p></Link>
      <Link to='/feedList/survey/feedNew'><p>설문 실험자 모집하기</p></Link>
      <Link to='/feedList/experi/feedNew'><p>대면 실험자 모집하기</p></Link>
    </>
  );
}
