import React from "react";
import * as S from "../style";

function PFDBottomContainer({ post }) {
  const {
    author,
    category,
    content,
    doDate,
    dueDate,
    id,
    pay,
    place,
    time,
    title,
  } = post;

  return (
    <S.FDBottomWrapper>
      <S.FDBTitle>
        <p>{title}</p>
      </S.FDBTitle>
      <S.FDType>
        <p>{category}</p>
      </S.FDType>
      <S.FDauthor>
        <p>{author}</p>
      </S.FDauthor>
      <S.FDdueDate>
        <p>{dueDate}</p>
      </S.FDdueDate>
      <S.FDdoDate>
        <p>{doDate}</p>
      </S.FDdoDate>
      <S.FDpay>
        <p>{pay}</p>
      </S.FDpay>
      <S.FDtime>
        <p>{time}</p>
      </S.FDtime>
      <S.FDplace>
        <p>{place}</p>
      </S.FDplace>
      <S.FDcontact></S.FDcontact>
      <S.FDdetailInfo>
        <p>{content}</p>
      </S.FDdetailInfo>
    </S.FDBottomWrapper>
  );
}
export default PFDBottomContainer;
