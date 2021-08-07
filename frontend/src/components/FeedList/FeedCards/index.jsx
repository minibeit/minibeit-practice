/*eslint-disable*/
import React from "react";
import { useState, useEffect } from "react";
import { useHistory } from 'react-router-dom';
import axios from 'axios';

import PFeedCards from "./PFeedCards";

export default function FeedCards(props) {
  const history = useHistory();
  const category = props.category;
  const [feedData, setFeedData] = useState([]);
  useEffect(()=>{
    axios.get('/testData.json')
    .then(result => {
      /* axios로 받은 데이터를 카테고리에 따라 분류 후 feed data set */
      setFeedData(assortData(result.data, category))
    })
  },[])
  return (
    <>
    {
        feedData && feedData.map((a, i)=>{
          return <PFeedCards data={a} category={category} key={i}/>
        })
    }
    </>
  );
}

export function assortData(data, category){
    let arr = []
    arr = data.filter(ele => ele.category == `${category}`)
    return arr
}