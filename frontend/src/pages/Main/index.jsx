/*eslint-disable*/
import React from "react";
import { Link } from 'react-router-dom';

/* Components */
import { NavBar } from "../../components/Common";
import { GreetingMsgBox } from "../../components/Main";

export default function Main() {
  return (
    <>
      <NavBar/>
      <GreetingMsgBox/>
    </>
  );
}
