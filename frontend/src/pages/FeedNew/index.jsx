import React from "react";

/* Components */
import { NavBar } from "../../components/Common";

export default function FeedNew({ match }) {
  return (
      <>
        <NavBar/>
        <div>{ match.params.category } feednew</div>
      </>
    );
}
