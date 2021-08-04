import React from "react";
import PFDBottomContainer from "./PFDBottomContainer";

function FDBottomContainer({ postId }) {
  const dummydetail = {
    title: "제목1",
    category: "survey",
    dueDate: "2020-10-20",
    doDate: "2021-02-02",
    pay: "1000",
    time: "20",
    place: "고려대학교 신공학관",
    contact: "010-0100-0101",
    detailInfo: "설명1",
  };
  return <PFDBottomContainer dummydetail={dummydetail} />;
}
export default FDBottomContainer;
