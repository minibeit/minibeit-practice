/*eslint-disable*/
import React from "react";
import { useState, useEffect } from "react";

import PFeedCards from "./PFeedCards";
import PPageButton from "./PPageButton";
import { getFeedList } from "../../../api/feedDataAPI";

export default function FeedCards(props) {
  const category = props.category;
  const [feedData, setFeedData] = useState([]);
  const [page, setPage] = useState(1);
  const [btnCount, setBtnCount] = useState();

  useEffect(()=>{
      getFeedList(category, page).then((res)=>{
        setFeedData(res.content)
        setBtnCount(res.totalPages)
      })
  },[])

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