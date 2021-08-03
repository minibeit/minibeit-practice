import React from "react";
import { Link } from "react-router-dom";
import * as S from "./style";

export default function NavBar() {
  return (
    <S.NavBarContainer>
      <S.NavBarLogoContainer>
        <Link to="/">
          <p>MiNiBEiT</p>
        </Link>
      </S.NavBarLogoContainer>
      <S.NavBarMenuContainer>
        <S.NavBarMenu>
          <Link to="/explainPage/recruit">
            <p>모집하기</p>
          </Link>
        </S.NavBarMenu>
        <S.NavBarMenu>
          <Link to="/explainPage/apply">
            <p>지원하기</p>
          </Link>
        </S.NavBarMenu>
        <S.NavBarAuth>
          <Link to="/login">
            <p>로그인</p>
          </Link>
        </S.NavBarAuth>
      </S.NavBarMenuContainer>
    </S.NavBarContainer>
  );
}
