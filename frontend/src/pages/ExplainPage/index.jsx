import React from "react";
import { Link } from 'react-router-dom';

/* Components */
import { NavBar } from "../../components/Common";import { Explain } from "../../components/ExplainPage";

export default function ExplainPage({match}) {
  let category = match.params.category;
  return (
      <>
        <NavBar/>
        <Explain category={ category }/>
        <Link to='/applyHome'><button>minibeit 시작하기</button></Link>
      </>
    );
}
