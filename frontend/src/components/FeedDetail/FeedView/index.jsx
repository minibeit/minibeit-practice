import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom'

import PFeedView from './PFeedView';
import { sendData } from '../../../api/feedDataAPI'

export default function FeedView(props){
    const history = useHistory();
    const postId = props.postId;
    const accessToken = localStorage.getItem('accessToken');
    const [detailFeedData, setDetailFeedData] = useState({});
    
    function getDetailFeed(postId, accessToken, setDetailFeedData){
        const config = {
            method: 'GET',
            url: `http://3.36.95.15:8080/api/board/${postId}`,
            headers: {
                Authorization: `Bearer ${accessToken}`
            }
        }
        const success = (res)=>{
            setDetailFeedData(res.data)
        }
        const fail = (err)=>{
            alert('불러오기 실패')
        }
        sendData(config,success,fail)
    }

    function deleteFeed(postId, accessToken){
        const config = {
            method: 'DELETE',
            url: `http://3.36.95.15:8080/api/board/${postId}`,
            headers: {
                Authorization: `Bearer ${accessToken}`
            }
        }
        const success = (res)=>{
            alert('삭제 성공!')
            history.goBack();
        }
        const fail = (err)=>{
            alert(err)
        }
        sendData(config,success,fail)
    }

    useEffect(()=>{
        return getDetailFeed(postId, accessToken, setDetailFeedData)
    },[])

    return <PFeedView
        data={detailFeedData}
        accessToken={accessToken}
        postId={postId}
        deleteFeed={deleteFeed}
    />
}



