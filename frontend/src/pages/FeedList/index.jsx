import React, { useEffect, useState } from "react";
import axios from 'axios';

/* Components */
import NavBar from '../../components/NavBar/NavBar';
import FeedCard from '../../components/FeedCard/FeedCard';

/* Function */
import { assortData } from "./assortData";

export default function FeedList({match}) {
  const category = match.params.category;
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
      <NavBar/>
      <h1>{category} list</h1>
      {
        feedData && feedData.map((a, i)=>{
          return <FeedCard data={a} category={category} key={i}/>
        })
      }
    </>
  );
}