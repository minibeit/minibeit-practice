import React from "react";

/* Components */
import NavBar from '../../components/NavBar/NavBar';
import FeedCard from '../../components/FeedCard/FeedCard';

export default function FeedList({match}) {
  /* axios로 데이터 가져와 작성자, 제목 등을 리턴해주는 api 필요 파라미터로 params */
  const category = match.params.category;
  return (
    <>
      <NavBar/>
      <h1>{category} list</h1>
      <FeedCard category={category}/>
      <FeedCard/>
      <FeedCard/>
    </>
  );
}
