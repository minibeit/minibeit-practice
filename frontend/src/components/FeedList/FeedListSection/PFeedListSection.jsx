import React from "react";
import { useHistory } from "react-router-dom";
import * as S from "../style";

export default function PFeedListSection({ sectioncate, dummycontent }) {
  const { id, title, explain, category, author, date } = dummycontent;
  const history = useHistory();
  const detail = () => {
    history.push(`/feedList/${sectioncate}/${id}`);
  };
  return (
    <S.FLSectionWrapper onClick={detail}>
      <S.FLSectionTopWrapper>
        <S.FLSectionAuthor>
          <p>{author}</p>
        </S.FLSectionAuthor>
        <S.FLSectionDate>
          <p>{date}</p>
        </S.FLSectionDate>
      </S.FLSectionTopWrapper>
      <S.FLSectionBottomWrapper>
        <S.FLSectionTitle>
          <p>{title}</p>
        </S.FLSectionTitle>
        <S.FLSectionExplain>
          <p>{explain}</p>
        </S.FLSectionExplain>
      </S.FLSectionBottomWrapper>
    </S.FLSectionWrapper>
  );
}
