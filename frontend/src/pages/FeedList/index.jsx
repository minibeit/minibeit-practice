import React from "react";
import NavBar from "../../components/Common/NavBar";
import { FLCalendar } from "../../components/FeedList";
import FeedListSection from "../../components/FeedList/FeedListSection";
import FeedListTopContainer from "../../components/FeedList/FeedListTopContainer";

export default function FeedList({ match }) {
  const { category } = match.params;
  return (
    <>
      <NavBar />
      <FLCalendar />
      <FeedListTopContainer category={category} />
      <FeedListSection category={category} />
    </>
  );
}
