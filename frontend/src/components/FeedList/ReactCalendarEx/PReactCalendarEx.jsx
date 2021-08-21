import React, { useEffect, useState } from 'react';
import Calendar from 'react-calendar';  //라이브러리 설치

import './PReactCalendar.css'; //캘린더에 css적용하는 css파일

export default function PReactCalendarEx(props){
    const {date, setDate} = props;

    return (
            <Calendar
                onChange={setDate}
                value={date}
                calendarType="US"
                locale="ko"  //언어 설정
                minDetail="year"
                tileClassName="day-tile"
            />
    )
}