import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom'

import PFeedView from './PFeedView';
import { getDetailFeed } from '../../../api/feedDataAPI'

export default function FeedView(props){
    const history = useHistory();
    const postId = props.postId;
    const [detailFeedData, setDetailFeedData] = useState({});

    useEffect(()=>{
        getDetailFeed(postId).then((res)=>{setDetailFeedData(res)})
    },[])

    return <PFeedView
        data={detailFeedData}
        postId={postId}
    />
}



