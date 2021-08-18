import React, { useState } from "react";
import { filterState } from "../../../recoil/filterState";
import { useRecoilValue } from "recoil";
import { SchoolModal } from "../../Common";
import * as S from "../style";

export default function PFeedListTopContainer({ category }) {
  const [isShowing, setIsShowing] = useState(false);
  const [schoolState, setSchoolState] = useState(useRecoilValue(filterState));
  const openModal = () => {
    setIsShowing(true);
  };
  const closeModal = (name, schoolid) => {
    setIsShowing(false);
    console.log(name, schoolid);
    console.log(schoolState);
    setSchoolState({
      school: name,
      schoolId: schoolid,
    });
  };
  return (
    <S.FLTopWrapper>
      {schoolState.school}
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
