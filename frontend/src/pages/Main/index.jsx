/*eslint-disable*/
import React from "react";
import { Link } from 'react-router-dom';

/* Components */
import NavBar from '../../components/NavBar/NavBar';
import * as S from "./style";

export default function Main() {
  return (
    <>
      <NavBar/>
      <S.GreetingMsgBox>
        <S.GreetingMsg>안녕하세요!</S.GreetingMsg>
        <S.GreetingMsg>저희 미니바이트는 실험자 모집 중개 플랫폼입니다.</S.GreetingMsg>
        <S.GreetingMsg>미니바이트를를 통해서</S.GreetingMsg>
        <S.GreetingMsg>실험자를 모집하거나 실험에 참여해보세요.</S.GreetingMsg>
        <Link to='/applyHome'><S.StartBtn>시작하기</S.StartBtn></Link>
      </S.GreetingMsgBox>
    </>
  );
}
