import React from "react";
import { FDBottomContainer, FDTopContainer } from "../../components/FeedDetail";
import NavBar from "../../components/Common/NavBar";

function FeedDetail({ match }) {
  const { category, postId } = match.params;
  console.log(postId);
  //postId에 따른 엔드포인트 찍어서 보여주기
  return (
    <>
      <NavBar />
      <FDTopContainer category={category} />
      <FDBottomContainer postId={postId} />
    </>
  );
}
export default FeedDetail;
