import React from "react";
import { Link } from 'react-router-dom';

/* Components */
import NavBar from '../../components/NavBar/NavBar';
import { RecruitExplain, ApplyExplain } from './Explain'

export default function ExplainPage({match}) {
  return (
      <>
        <NavBar/>
        {
          match.params.category == 'recurit'
          ? <RecruitExplain/>
          : <ApplyExplain/>
        }
        <Link to='/applyHome'><button>minibeit 시작하기</button></Link>
      </>
    );
}
