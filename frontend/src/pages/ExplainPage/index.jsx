import React from "react";
import { ExplainContent, ExplainTitle } from "../../components/ExplainPage";

export default function ExplainPage({ match }) {
  const { category } = match.params;
  console.log(category);

  return (
    <>
      <ExplainTitle category={category} />
      <ExplainContent category={category} />
    </>
  );
}
