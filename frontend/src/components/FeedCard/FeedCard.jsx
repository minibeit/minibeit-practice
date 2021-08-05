/*eslint-disable*/
import React from "react";
import { Link, useHistory } from 'react-router-dom';

/* style */
import * as S from './style';

export default function FeedCard(props) {
  const history = useHistory();
  console.log(props.data)
  return (
      <S.FeedCard onClick={()=>{
        history.push(`/feedList/${props.category}/${props.data.id}`)
      }}>
        <h3>작성자 : {props.data.author}</h3>
        <S.FeedImgBox/>
        <S.FeedInfo>
          <h3>제목 : {props.data.title}</h3>
          <p>시간 : {props.data.time}</p>
          <p>유형 : {props.data.category}</p>
        </S.FeedInfo>
      </S.FeedCard>
  );
}
