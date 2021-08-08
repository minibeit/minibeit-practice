import React, { useEffect, useState } from "react";
import { feedlistApi } from "../../../utils";
import PFeedListSection from "./PFeedListSection";

export default function FeedListSection({ category }) {
  //더미데이터는 category에서 받아온 엔드포인트로 하나의 카테코리에 헤당되는 데이터만 받아올것
  const [posts, setPosts] = useState([]);
  const dummycontents = [
    {
      id: 1,
      title: "제목1",
      explain: "설명1",
      category: "survey",
      author: "user1",
      date: "2020-01-01",
    },
    {
      id: 2,
      title: "제목2",
      explain: "설명2",
      category: "survey",
      author: "user1",
      date: "2020-01-01",
    },
    {
      id: 3,
      title: "제목3",
      explain: "설명3",
      category: "survey",
      author: "user1",
      date: "2020-01-01",
    },
    {
      id: 4,
      title: "제목4",
      explain: "설명1",
      category: "experi",
      author: "user1",
      date: "2020-01-01",
    },
    {
      id: 5,
      title: "제목5",
      explain: "설명2",
      category: "experi",
      author: "user1",
      date: "2020-01-01",
    },
    {
      id: 6,
      title: "제목6",
      explain: "설명3",
      category: "experi",
      author: "user1",
      date: "2020-01-01",
    },
  ];
  const getFeedList = async () => {
    try {
      const posts = await feedlistApi(category);
      if (posts) {
        setPosts(posts);
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
