import React, { useState } from 'react';
import dayjs from 'dayjs';

import PFeedCalendar from './PFeedCalendar';

export default function FeedCalendar(){
    const [dayObj, setDayObj] = useState(dayjs());

    return <PFeedCalendar dayObj={dayObj} setDayObj={setDayObj}/>
}