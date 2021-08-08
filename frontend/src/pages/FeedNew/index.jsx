import React from "react";

/* Components */
import { NavBar } from "../../components/Common";
import { CreateFeedForm } from "../../components/FeedNew";

export default function FeedNew({ match }) {
  return (
      <>
        <NavBar/>
        <CreateFeedForm/>
      </>
    );
}
