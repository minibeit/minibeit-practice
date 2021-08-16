import React from "react";

/* Components */
import { NavBar } from "../../components/Common";
import { FeedCards, FeedCalendar } from "../../components/FeedList";

export default function FeedList({match}) {
  const category = match.params.category;
  return (
    <>
      <NavBar/>
      <h1>{category} list</h1>
      <FeedCalendar/>
      <FeedCards category={category}/>
    </>
  );
}