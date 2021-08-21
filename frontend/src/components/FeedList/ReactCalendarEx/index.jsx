import React, { useState } from 'react';
import { useRecoilState } from 'recoil';
import { dateFilterState } from '../../../recoil/searchFilter';

import PReactCalendarEx from './PReactCalendarEx';

export default function ReactCalendarEx(){
    const [date, setDate] = useRecoilState(dateFilterState)
    
    return (
            <PReactCalendarEx
            date={date}
            setDate={setDate}
            />
    )
}