import React, { useState } from "react";
import { filterState } from "../../../recoil/filterState";
import { useRecoilValue } from "recoil";
import { SchoolModal } from "../../Common";
import * as S from "../style";

export default function PFeedListTopContainer({ category }) {
  const [isShowing, setIsShowing] = useState(false);
  const [school, setSchool] = useState(useRecoilValue(filterState).school);
  const openModal = () => {
    setIsShowing(true);
  };
  const closeModal = (name) => {
    setIsShowing(false);
    setSchool(name);
  };
  return (
    <S.FLTopWrapper>
      {school}
      <S.SchoolBtn onClick={openModal}>Open</S.SchoolBtn>
      {category === "survey" ? (
        <S.FLTitle>설문조사</S.FLTitle>
      ) : (
        <S.FLTitle>대면실험</S.FLTitle>
      )}
      <div>
        {isShowing && (
          <SchoolModal closeModal={closeModal} message="This is Modal" />
        )}
      </div>
      <S.FLSearchBar />
    </S.FLTopWrapper>
  );
}
