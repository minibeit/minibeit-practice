/*eslint-disable*/
import React from "react";
import { Link } from 'react-router-dom';

import * as S from "../style";

export default function PGreetingMsgBox() {
  return (
    <>
      <S.GreetingMsgBox>
        <p>안녕하세요!</p>
        <p>저희 미니바이트는 실험자 모집 중개 플랫폼입니다.</p>
        <p>미니바이트를를 통해서</p>
        <p>실험자를 모집하거나 실험에 참여해보세요.</p>
        <Link to='/applyHome'><S.StartBtn>시작하기</S.StartBtn></Link>
      </S.GreetingMsgBox>
    </>
  );
}
