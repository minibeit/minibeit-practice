import React from 'react';

import * as S from '../style';

export default function PSchoolSelectModal(props){
    const {showSelectBox, setShowSelectBox, clickSelectBtn, schoolData, schoolFilter, setSchoolFilter} = props
    
    const selectSchool = (e)=>{
        var schoolFilter_cp = {...schoolFilter}
        schoolFilter_cp['school_name'] = e.target.textContent
        schoolFilter_cp['school_id'] = e.target.id
        setSchoolFilter(schoolFilter_cp)
        setShowSelectBox(false);
    }
    
    return (
        <>
            <button onClick={clickSelectBtn}>학교선택</button>
            <span>{schoolFilter['school_name']}</span>
            {
                showSelectBox === true
                ? <S.SelectModal><S.ListInModal>{
                    schoolData && schoolData.map((a)=>{
                        return <S.ListItem id={a.id} key={a.id} onClick={selectSchool}>{a.name}</S.ListItem>
                    })
                    }</S.ListInModal></S.SelectModal>
                : null
            }
        </>
    )
}