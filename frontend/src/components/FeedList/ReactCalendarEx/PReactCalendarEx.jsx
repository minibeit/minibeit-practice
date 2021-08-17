import React, { useState } from 'react';
import Calendar from 'react-calendar';  //라이브러리 설치
import 'react-calendar/dist/Calendar.css'; //캘린더에 css적용하는 css파일

import * as S from '../style';

export default function PReactCalendarEx(){
    const [value, onChange] = useState(new Date()); // 날짜 설정위한 state
    console.log(value)
    return (
        <>
            <Calendar
                onChange={onChange}
                value={value}
                calendarType="US"
                locale="en-US"  //언어 설정
                minDetail="year"
            />
        </>
    )
}