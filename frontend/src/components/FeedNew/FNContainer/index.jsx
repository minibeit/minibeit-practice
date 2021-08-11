import React from "react";
import { useHistory } from "react-router";
import { feedCreateApi } from "../../../utils";
import PFNContainer from "./PFNContainer";

function FNContainer({ category }) {
  const history = useHistory();
  const FNHandler = async (
    title,
    dueDate,
    doDate,
    pay,
    time,
    place,
    content,
    phoneNum,
    files
  ) => {
    try {
      const result = await feedCreateApi(
        title,
        dueDate,
        doDate,
        pay,
        time,
        place,
        content,
        phoneNum,
        files,
        category
      );
      console.log(result.id);
      if (result.id) {
        window.alert("게시물 생성에 성공!");
        history.push(`/feedList/${category}/${result.id}`);
      }
    } catch (e) {
      console.log(e.response.data.error.msg);
      alert(e.response.data.error.msg);
    }
  };
  return <PFNContainer category={category} FNHandler={FNHandler} />;
}
export default FNContainer;
