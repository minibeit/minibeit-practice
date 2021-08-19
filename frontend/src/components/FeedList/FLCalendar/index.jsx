import React, { useState } from "react";
import Calendar from "react-calendar";
import { useRecoilState } from "recoil";
import { filterState } from "../../../recoil/filterState";

import "./Calender.scss";

export default function FLCalendar() {
  const [date, setDate] = useRecoilState(filterState);
  console.log(date);
  const [value, setValue] = useState(new Date(date.date) || new Date());

  const onChange = (e) => {
    setValue(e);
    console.log(e);
    const nowDay = e.getDate();
    const nowMonth = e.getMonth() + 1;
    const nowYear = e.getFullYear();
    const nowDate = nowYear + "-" + nowMonth + "-" + nowDay;
    setDate({
      ...date,
      date: new Date(e),
    });
  };
  return (
    <div>
      <Calendar onChange={onChange} value={value} className="react-calendar" />
    </div>
  );
}
