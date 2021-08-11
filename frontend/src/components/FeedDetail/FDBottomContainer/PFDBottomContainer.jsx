import React from "react";
import { useHistory } from "react-router";
import { Link } from "react-router-dom";
import { feedDeleteApi } from "../../../utils";
import * as S from "../style";

function PFDBottomContainer({ post }) {
  const history = useHistory();
  const {
    author,
    content,
    doDate,
    dueDate,
    id,
    isMine,
    contact,
    pay,
    place,
    time,
    title,
    images,
  } = post;
  let { category } = post;
  console.log(images);
  if (category === "SURVEY") {
    category = "survey";
  } else {
    category = "experiment";
  }
  const deleteFeed = async () => {
    try {
      const result = await feedDeleteApi(id);
      console.log(result);

      history.push(`/feedList/${category}`);
    } catch (e) {
      console.log(e.response.data.error.msg);
      alert(e.response.data.error.msg);
    }
  };

  return (
    <>
      {isMine ? (
        <>
          <S.FDdeletebutton onClick={deleteFeed}>삭제하기</S.FDdeletebutton>
          <S.FDeditbutton
            onClick={() => {
              history.push({
                pathname: `/feedList/${category}/${id}/feedEdit`,
              });
            }}
          >
            수정하기
          </S.FDeditbutton>
        </>
      ) : null}

      <S.FDBottomWrapper>
        <S.FDBTitle>
          <p>{title}</p>
        </S.FDBTitle>
        <S.FDType>
          <p>{category}</p>
        </S.FDType>
        <S.FDauthor>
          <p>{author}</p>
        </S.FDauthor>
        <S.FDdueDate>
          <p>{dueDate}</p>
        </S.FDdueDate>
        <S.FDdoDate>
          <p>{doDate}</p>
        </S.FDdoDate>
        <S.FDpay>
          <p>{pay}</p>
        </S.FDpay>
        <S.FDtime>
          <p>{time}</p>
        </S.FDtime>
        <S.FDplace>
          <p>{place}</p>
        </S.FDplace>
        <S.FDcontact></S.FDcontact>
        <S.FDdetailInfo>
          <p>{content}</p>
          <p>{contact}</p>
        </S.FDdetailInfo>
      </S.FDBottomWrapper>
    </>
  );
}
export default PFDBottomContainer;
