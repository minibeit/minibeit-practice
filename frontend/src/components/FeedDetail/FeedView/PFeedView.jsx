import React from 'react';
import { useHistory } from 'react-router-dom';

import * as S from '../style';

export default function PFeedView(props){
    const history = useHistory();
    const postId = props.postId
    const accessToken = props.accessToken
    const isMine = props.data.isMine;
    const deleteFuc = ()=>{
        var result = window.confirm('삭제하시겠습니까?')
        if(result == true){
            props.deleteFeed(postId, accessToken)
        }
    }
    
    return (
        <S.FeedContainer>
            <S.FeedTitle>제목 - {props.data.title}</S.FeedTitle>
            <S.FeedAuthor>작성자 - {props.data.author}</S.FeedAuthor>
            {
                isMine === true
                ? <button onClick={(e)=>{
                    history.push(`/feedList/${props.data.category}/${postId}/feedEdit`)
                }}>수정</button>
                : null
            }
            {
                isMine === true
                ? <button onClick={deleteFuc}>삭제</button>
                : null
            }
            {
                props.data.images && props.data.images !== 0
                ? viewImages(props.data.images)
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

function viewImages(images){
    for(var i=0;i<images.length;i++){
        return <S.FeedImg src={`${images[i].url}`}/>
    }
}