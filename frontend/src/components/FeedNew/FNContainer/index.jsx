import React from "react";
import { useHistory } from "react-router";
import { feedCreateApi } from "../../../utils";
import PFNContainer from "./PFNContainer";

function FNContainer() {
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
    files,
    school
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
        school
      );
      console.log(result);
      if (result.id) {
        window.alert("게시물 생성에 성공!");
        history.push(`/feedList/${result.id}`);
      }
    } catch (e) {
      console.log(e);
      alert(e);
    }
  };
  return <PFNContainer FNHandler={FNHandler} />;
}
export default FNContainer;
