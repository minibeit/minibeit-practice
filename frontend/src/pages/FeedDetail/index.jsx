import React from "react";

export default function FeedDetail({ match }) {
  const { category, postPK } = match.params;
  return (
    <>
      <div>{category}</div>
      <div>{postPK}</div>
    </>
  );
}
