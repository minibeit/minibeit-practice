import React from "react";

/* Components */
import NavBar from '../../components/NavBar/NavBar';

export default function ExplainPage({match}) {
  return (
      <>
        <NavBar/>
        <div>{match.params.category} explainpage</div>
      </>
    );
}
