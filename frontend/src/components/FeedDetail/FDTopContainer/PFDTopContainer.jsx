import React from "react";
import * as S from "../style";

function PFDTopContainer({ category }) {
  return (
    <S.FDTopWrapper>
      {category === "survey" ? (
        <S.FDTitle>설문조사</S.FDTitle>
      ) : (
        <S.FDTitle>대면실험</S.FDTitle>
      )}
    </S.FDTopWrapper>
  );
}
export default PFDTopContainer;
