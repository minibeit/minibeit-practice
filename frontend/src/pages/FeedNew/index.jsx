import React from "react";

/* Components */
import NavBar from '../../components/NavBar/NavBar';

export default function FeedNew({ match }) {
  return (
      <>
        <NavBar/>
        <div>{ match.params.category } feednew</div>
      </>
    );
}
