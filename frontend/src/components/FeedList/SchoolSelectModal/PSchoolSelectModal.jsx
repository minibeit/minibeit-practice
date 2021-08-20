import React from 'react';

import * as S from '../style';

export default function PSchoolSelectModal(props){
    const showSelectBox = props.showSelectBox
    const setShowSelectBox = props.setShowSelectBox
    const clickSelectBtn = props.clickSelectBtn;
    const schoolData = props.schoolData
    const setSchoolFilter = props.setSchoolFilter
    return (
        <>
            <button onClick={clickSelectBtn}>학교선택</button>
            {
                showSelectBox === true
                ? <S.SelectModal><S.ListInModal>{
                    schoolData && schoolData.map((a,i)=>{
                        return <S.ListItem key={i}>{a.name}</S.ListItem>
                    })
                    }</S.ListInModal></S.SelectModal>
                : null
            }
        </>
    )
}