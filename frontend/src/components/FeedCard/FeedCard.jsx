/*eslint-disable*/
import React from "react";
import { Link, useHistory } from 'react-router-dom';

/* style */
import * as S from './style';

export default function FeedCard(props) {
  const history = useHistory();
  return (
      <S.FeedCard onClick={()=>{
        history.push(`/feedList/${props.category}/1`)
      }}>
        <h3>작성자</h3>
        <S.FeedImgBox/>
        <S.FeedInfo>
          <h3>제목</h3>
          <p>시간</p>
          <p>보수</p>
        </S.FeedInfo>
      </S.FeedCard>
  );
}
