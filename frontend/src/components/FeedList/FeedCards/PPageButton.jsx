/*eslint-disable*/
import React from "react";

/* style */
import * as S from '../style';

export default function PPageButton(props) {
  const onClick = (e)=>{
    props.setPage(e.target.value);
  }

  return (
    <S.ButtonContainer>
      {
        [...Array(props.btnCount)].map((a,i)=>{
          return <S.PageButton key={i} onClick={onClick} value={i+1}>{i+1}</S.PageButton>
        })
      }
    </S.ButtonContainer>
  );
}