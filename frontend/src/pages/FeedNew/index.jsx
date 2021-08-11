import React from "react";
import { FNContainer } from "../../components/FeedNew";

export default function FeedNew({ match }) {
  const { category } = match.params;

  return (
    <>
      <FNContainer category={category} />
    </>
  );
}
