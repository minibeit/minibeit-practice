/*eslint-disable*/
import React from "react";
import { Link } from 'react-router-dom';
import { useHistory } from "react-router";
import { userState } from '../../../recoil/userState';
import { useRecoilValue, useResetRecoilState } from "recoil";

/* style */
import * as S from '../style';

export default function PNavBar() {
    const history = useHistory();
    const logoutFuc = (e)=>{
        localStorage.clear();
        window.location.href = '/'
    }

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
          {
            useRecoilValue(userState).isLogin
            ? <p onClick={logoutFuc}>{useRecoilValue(userState).name} 로그아웃</p>
            : <Link to='/login'><p>로그인</p></Link>
          }
        </S.NavItem>
      </S.NavItemsContainer>
    </S.NavBarContainer>
  );
}
