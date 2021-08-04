/*eslint-disable*/
import React from "react";
import { Link } from 'react-router-dom';

/* Components */
import NavBar from '../../components/NavBar/NavBar';
import { GreetingMsgBox, GreetingMsg, StartBtn } from "./style";

export default function Main() {
  return (
    <>
      <NavBar/>
      <GreetingMsgBox>
        <GreetingMsg>안녕하세요!</GreetingMsg>
        <GreetingMsg>저희 미니바이트는 실험자 모집 중개 플랫폼입니다.</GreetingMsg>
        <GreetingMsg>미니바이트를를 통해서</GreetingMsg>
        <GreetingMsg>실험자를 모집하거나 실험에 참여해보세요.</GreetingMsg>
        <Link to='/applyHome'><StartBtn>시작하기</StartBtn></Link>
      </GreetingMsgBox>
    </>
  );
}
