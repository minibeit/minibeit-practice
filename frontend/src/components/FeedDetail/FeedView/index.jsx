import React, { useEffect, useState } from 'react';

import PFeedView from './PFeedView';
import { getFeedData } from '../../../api/feedDataAPI'

export default function FeedView(props){
    const accessToken = localStorage.getItem('accessToken');
    const [detailFeedData, setDetailFeedData] = useState({});
    const config = {
        method: 'get',
        url: `http://3.36.95.15:8080/api/board/${props.postId}`,
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
    useEffect(()=>{
        getFeedData(config,success,fail)
    },[])
    return <PFeedView data={detailFeedData}/>
}