import React from "react";
import { Link, useHistory } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { userState } from "../../recoil/userState";
import * as S from "./style";

export default function NavBar() {
  const data = useRecoilValue(userState);
  const loginState = data.isLogin;
  const username = data.name;

  const history = useHistory();
  const logout = () => {
    localStorage.clear();
    window.alert("로그아웃이 되었습니다!");
    window.location.replace("/");
  };
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
          {loginState === true ? (
            <S.NavBarAuth onClick={logout}>
              <p>{username} 님 안녕하세요</p>
              <p>로그아웃</p>
            </S.NavBarAuth>
          ) : (
            <S.NavBarAuth>
              <Link to="/signup">
                <p>회원가입</p>
              </Link>
              <Link to="/login">
                <p>로그인</p>
              </Link>
            </S.NavBarAuth>
          )}
        </S.NavBarAuth>
      </S.NavBarMenuContainer>
    </S.NavBarContainer>
  );
}
