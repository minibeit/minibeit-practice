import React from "react";
import * as S from "../style";

export default function PExplainTitle({ category }) {
  return (
    <S.ExplainTitleWrapper>
      {category === "apply" ? <p>지원하는 방법</p> : <p>모집하는 방법</p>}
    </S.ExplainTitleWrapper>
  );
}
