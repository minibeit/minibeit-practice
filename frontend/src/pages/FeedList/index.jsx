import React from "react";

/* Components */
import NavBar from '../../components/NavBar/NavBar';

export default function FeedList({match}) {
  return (
    <>
      <NavBar/>
      <div>{match.params.category} feedlist</div>
    </>
  );
}
