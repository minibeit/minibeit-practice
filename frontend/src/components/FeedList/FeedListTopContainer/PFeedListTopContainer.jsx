import React from "react";
import * as S from "../style";

export default function PFeedListTopContainer({ category }) {
  return (
    <S.FLTopWrapper>
      {category === "survey" ? (
        <S.FLTitle>설문조사</S.FLTitle>
      ) : (
        <S.FLTitle>대면실험</S.FLTitle>
      )}
      <S.FLSearchBar />
    </S.FLTopWrapper>
  );
}
