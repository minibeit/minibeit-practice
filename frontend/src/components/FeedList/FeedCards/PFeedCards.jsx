/*eslint-disable*/
import React from "react";
import { useHistory } from 'react-router-dom';

/* style */
import * as S from '../style';

export default function PFeedCards(props) {
  const history = useHistory();
  
  const onClick = (e)=>{
    history.push(`/feedList/${props.category}/${props.data.id}`)
  }
  return (
      <S.FeedCard onClick={onClick}>
        <S.FeedInfo>
          <h3>제목 : {props.data.title}</h3>
          <p>작성자 : {props.data.author}</p>
          <p>날짜 : {props.data.dueDate}</p>
        </S.FeedInfo>
      </S.FeedCard>
  );
}
