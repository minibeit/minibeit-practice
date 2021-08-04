import React from "react";

export default function FeedNew({ match }) {
  const { category } = match.params;

  return (
    <>
      <div>{category}</div>
    </>
  );
}
