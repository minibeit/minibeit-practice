import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import { getSchoolData } from '../../../api/feedDataAPI';
import { schoolFilterState } from '../../../recoil/searchFilter';

import PSchoolSelectModal from './PSchoolSelectModal';

export default function SchoolSelectModal(){
    const [showSelectBox, setShowSelectBox] = useState(false)
    const [schoolData, setSchoolData] = useState()
    const [schoolFilter, setSchoolFilter] = useRecoilState(schoolFilterState)
    
    function clickSelectBtn(){
        if(showSelectBox === false){
            setShowSelectBox(true)
            getSchoolData()
            .then((res)=>{
                setSchoolData(res)
            })
        } else{
            setShowSelectBox(false)
        }
    }
    return (
        <>
            <PSchoolSelectModal
                showSelectBox={showSelectBox}
                setShowSelectBox={setShowSelectBox}
                clickSelectBtn={clickSelectBtn}
                schoolData={schoolData}
                schoolFilter={schoolFilter}
                setSchoolFilter={setSchoolFilter}
            />
        </>
    )
}