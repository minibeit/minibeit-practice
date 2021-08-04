import React from "react";
import { Link } from "react-router-dom";
import NavBar from "../../components/Common/NavBar";
import { ExplainContent, ExplainTitle } from "../../components/ExplainPage";
import * as S from "./style";

export default function ExplainPage({ match }) {
  const { category } = match.params;
  console.log(category);

  return (
    <>
      <NavBar />
      <ExplainTitle category={category} />
      <ExplainContent category={category} />
      <S.ExplainBtn>
        <Link to="/applyHome">
          <p>시작하기</p>
        </Link>
      </S.ExplainBtn>
    </>
  );
}
