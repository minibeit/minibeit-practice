/*eslint-disable*/
import React from "react";
import { Link } from 'react-router-dom';
import { userState } from "../../recoil/userState";
import { useRecoilValue } from "recoil";

/* style */
import * as S from './style';

export default function NavBar() {
  console.log(useRecoilValue(userState))
  return (
    <S.NavBarContainer>
      <S.BrandLogoContainer>
        <Link to='/'><p>minibeit</p></Link>
      </S.BrandLogoContainer>
      <S.NavItemsContainer>
        <S.NavItem>
          <Link to='/explainPage/recurit'><p>실험자 모집 설명</p></Link>
        </S.NavItem>
        <S.NavItem>
          <Link to='/explainPage/apply'><p>실험자 지원 설명</p></Link>
        </S.NavItem>
        <S.NavItem>
          <Link to='/login'><p>로그인</p></Link>
        </S.NavItem>
      </S.NavItemsContainer>
    </S.NavBarContainer>
  );
}
