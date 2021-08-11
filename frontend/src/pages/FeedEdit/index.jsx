import React from "react";
import { FEContainer } from "../../components/FeedEdit";

export default function FeedEdit({ match }) {
  const { category, postId } = match.params;

  return (
    <>
      <FEContainer category={category} postId={postId} />
    </>
  );
}
