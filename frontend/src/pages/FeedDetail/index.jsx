import React from "react";
import { FDBottomContainer, FDTopContainer } from "../../components/FeedDetail";
import NavBar from "../../components/Common/NavBar";

function FeedDetail({ match }) {
  const { postId } = match.params;
  return (
    <>
      <NavBar />
      <FDTopContainer />
      <FDBottomContainer postId={postId} />
    </>
  );
}
export default FeedDetail;
