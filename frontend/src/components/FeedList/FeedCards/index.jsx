/*eslint-disable*/
import React from "react";
import { useState, useEffect } from "react";
import { useHistory } from "react-router";

import PFeedCards from "./PFeedCards";
import PPageButton from "./PPageButton";
import { getFeedData, sendData } from "../../../api/feedDataAPI";

export default function FeedCards(props) {
  const history = useHistory();
  const category = props.category;
  const accessToken = localStorage.getItem('accessToken')
  const [feedData, setFeedData] = useState([]);
  const [page, setPage] = useState(1);
  const [btnCount, setBtnCount] = useState();

  const config = {
    method: 'get',
    url: `http://3.36.95.15:8080/api/board/list?category=${category}&page=${page}&size=5`,
    headers: {
      Authorization: `Bearer ${accessToken}`
    }
  }
  const success = (res)=>{
    setFeedData(res.data.content);
    setBtnCount(res.data.totalPages);
  }
  const fail = (err)=>{
    alert('로그인 후 이용해주세요')
    history.push('/login')
  }

  useEffect(()=>{
      return sendData(config, success, fail)
  },[page])

  return (
    <>
      {
        feedData && feedData.map((a, i)=>{
          return <PFeedCards data={a} category={category} key={i}/>
        })
      }
      <PPageButton setPage={setPage} btnCount={btnCount}/>
    </>
  );
}