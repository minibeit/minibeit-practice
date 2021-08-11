import React from 'react';

import * as S from '../style';

export default function PFeedView(props){
    const isMine = props.data.isMine;
    const deleteFuc = ()=>{
        var result = window.confirm('삭제하시겠습니까?')
        if(result == true){
            props.deleteFeed(props.postId, props.accessToken)
        }
    }
    
    return (
        <S.FeedContainer>
            <S.FeedTitle>제목 - {props.data.title}</S.FeedTitle>
            <S.FeedAuthor>작성자 - {props.data.author}</S.FeedAuthor>
            {
                isMine === true
                ? <button onClick={deleteFuc}>삭제</button>
                : null
            }
            <p>본문 - {props.data.content}</p>
            <p>타입 - {props.data.category}</p>
            <p>날짜 - {props.data.doDate}</p>
            <p>마감기한 - {props.data.dueDate}</p>
            <p>페이 - {props.data.pay}</p>
            <p>장소 - {props.data.place}</p>
            <p>예상 소요시간 - {props.data.time}분</p>
        </S.FeedContainer>
    )
}