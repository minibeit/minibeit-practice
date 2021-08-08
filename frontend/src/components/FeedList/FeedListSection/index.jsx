import React, { useEffect, useState } from "react";
import { feedlistApi } from "../../../utils";
import PFeedListSection from "./PFeedListSection";

export default function FeedListSection({ category }) {
  //더미데이터는 category에서 받아온 엔드포인트로 하나의 카테코리에 헤당되는 데이터만 받아올것
  const [posts, setPosts] = useState([]);

  const getFeedList = async () => {
    try {
      const result = await feedlistApi(category);
      if (result) {
        setPosts(result);
      }
    } catch (e) {
      console.log(e.response.data.error.msg);
      alert(e.response.data.error.msg);
    }
  };

  useEffect(() => {
    getFeedList();
  }, []);

  return (
    <>
      {posts.map((post) => (
        <PFeedListSection key={post.id} sectioncate={category} post={post} />
      ))}
    </>
  );
}
