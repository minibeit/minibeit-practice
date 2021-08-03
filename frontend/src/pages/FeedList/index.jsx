import React from "react";

export default function FeedList({ match }) {
  const { category } = match.params;
  return (
    <>
      <div>{category}</div>
    </>
  );
}
