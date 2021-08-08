import React from "react";

import { NavBar } from '../../components/Common'
import { FeedView } from "../../components/FeedDetail";

export default function FeedDetail({ match }) {
  const postId = match.params.postPk;
  return (
    <>
    <NavBar/>
      <FeedView postId={postId}/>
    </>
  );
}
