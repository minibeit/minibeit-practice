import React, { useEffect, useState } from "react";
import { feedDetailApi } from "../../../utils";
import PFDBottomContainer from "./PFDBottomContainer";

function FDBottomContainer({ postId }) {
  const [post, setPost] = useState([]);
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

  const getFeedDetail = async () => {
    try {
      const result = await feedDetailApi(postId);
      if (result) {
        console.log(result);
        setPost(result);
      }
    } catch (e) {
      console.log(e.response.data.error.msg);
      alert(e.response.data.error.msg);
    }
  };

  useEffect(() => {
    getFeedDetail();
  }, []);
  return <PFDBottomContainer post={post} />;
}
export default FDBottomContainer;
