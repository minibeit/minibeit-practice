import React from "react";
import * as S from "../style";

function PFDBottomContainer({ dummydetail }) {
  const {
    title,
    category,
    dueDate,
    doDate,
    pay,
    time,
    place,
    contact,
    detailInfo,
  } = dummydetail;

  return (
    <S.FDBottomWrapper>
      <S.FDBTitle>
        <p>{title}</p>
      </S.FDBTitle>
      <S.FDType>
        <p>{category}</p>
      </S.FDType>
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
      <S.FDcontact>
        <p>{contact}</p>
      </S.FDcontact>
      <S.FDdetailInfo>
        <p>{detailInfo}</p>
      </S.FDdetailInfo>
    </S.FDBottomWrapper>
  );
}
export default PFDBottomContainer;
