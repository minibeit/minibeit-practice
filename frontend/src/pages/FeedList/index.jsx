import React from "react";

/* Components */
import { NavBar } from "../../components/Common";
import { FeedCards } from "../../components/FeedList";

export default function FeedList({match}) {
  const category = match.params.category;
  return (
    <>
      <NavBar/>
      <h1>{category} list</h1>
      <FeedCards category={category}/>
    </>
  );
}