import React from "react";
import PExplainContent from "./PExplainContent";

export default function ExplainContent({ category }) {
  const dummycontents = [
    {
      id: 1,
      explain: "설명1",
      src: "http://placeimg.com/200/200/any",
      category: "apply",
    },
    {
      id: 2,
      explain: "설명2",
      src: "http://placeimg.com/200/200/any",
      category: "apply",
    },
    {
      id: 3,
      explain: "설명3",
      src: "http://placeimg.com/200/200/any",
      category: "apply",
    },
  ];
  return (
    <>
      {dummycontents.map((dummycontent) => (
        <PExplainContent
          key={dummycontent.id}
          category={category}
          dummycontent={dummycontent}
        />
      ))}
    </>
  );
}
