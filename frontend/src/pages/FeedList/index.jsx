import React from "react";

/* Components */
import { NavBar } from "../../components/Common";
import { ReactCalendarEx, SchoolSelectModal } from "../../components/FeedList";

export default function FeedList({match}) {
  const category = match.params.category;
  return (
    <>
      <NavBar/>
      <SchoolSelectModal/>
      <ReactCalendarEx/>
    </>
  );
}