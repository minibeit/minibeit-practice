import React from "react";
import { FEContainer } from "../../components/FeedEdit";

export default function FeedEdit({ match }) {
  const { postId } = match.params;

  return (
    <>
      <FEContainer postId={postId} />
    </>
  );
}
