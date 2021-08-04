import React from "react";
import NavBar from "../../components/Common/NavBar";
import FeedListSection from "../../components/FeedList/FeedListSection";
import FeedListTopContainer from "../../components/FeedList/FeedListTopContainer";

export default function FeedList({ match }) {
  const { category } = match.params;
  return (
    <>
      <NavBar />
      <FeedListTopContainer category={category} />
      <FeedListSection category={category} />
    </>
  );
}
