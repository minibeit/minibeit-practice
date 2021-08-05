import React from "react";

export default function FeedDetail({ match }) {
  const {category, postPk} = match.params
  return (
    <>
      <div>feedDetail</div>
      <div>category: {category}</div>
      <div>postPk: {postPk}</div>
    </>
  );
}
