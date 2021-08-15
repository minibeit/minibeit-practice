import React, { useState } from "react";
import dayjs from "dayjs";
import range from "lodash-es/range";
import * as S from "../style";

const weekDays = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

const todayObj = dayjs();

const FLCalendar = () => {
  const [dayObj, setDayObj] = useState(dayjs());
  const [checked, setChecked] = useState(false);
  const [prevnode, setprevnode] = useState("");

  const thisYear = dayObj.year();
  const thisMonth = dayObj.month(); // (January as 0, December as 11)
  const daysInMonth = dayObj.daysInMonth();

  const dayObjOf1 = dayjs(`${thisYear}-${thisMonth + 1}-1`);
  const weekDayOf1 = dayObjOf1.day(); // (Sunday as 0, Saturday as 6)

  const dayObjOfLast = dayjs(`${thisYear}-${thisMonth + 1}-${daysInMonth}`);
  const weekDayOfLast = dayObjOfLast.day();

  const handlePrev = () => {
    setDayObj(dayObj.subtract(1, "month"));
  };

  const handleNext = () => {
    setDayObj(dayObj.add(1, "month"));
  };
  const filterList = (e) => {
    e.target.className = "day-cell day-cell--in-month checked";
    if (prevnode != "" && prevnode != e.target) {
      prevnode.className = "day-cell day-cell--in-month";
    }
    setprevnode(e.target);
  };

  return (
    <S.CalendarWrapper>
      <S.CalendarHead>
        <S.NavBtn onClick={handlePrev}>&lt;</S.NavBtn>
        <S.DateTime>{dayObj.format("MMM DD YYYY")}</S.DateTime>
        <S.NavBtn onClick={handleNext}>&gt;</S.NavBtn>
      </S.CalendarHead>
      <S.DateContainer>
        {weekDays.map((d) => (
          <S.DateCell key={d}>{d}</S.DateCell>
        ))}
      </S.DateContainer>
      <S.DateContainer>
        {range(weekDayOf1).map((i) => (
          <div className="day-cell day-cell--faded" key={i}>
            {dayObjOf1.subtract(weekDayOf1 - i, "day").date()}
          </div>
        ))}

        {range(daysInMonth).map((i) => (
          <div
            className={`day-cell day-cell--in-month${
              i + 1 === todayObj.date() &&
              thisMonth === todayObj.month() &&
              thisYear === todayObj.year()
                ? " day-cell--today"
                : ""
            }`}
            key={i}
            onClick={filterList}
          >
            {i + 1}
          </div>
        ))}

        {range(6 - weekDayOfLast).map((i) => (
          <div className="day-cell day-cell--faded" key={i}>
            {dayObjOfLast.add(i + 1, "day").date()}
          </div>
        ))}
      </S.DateContainer>
    </S.CalendarWrapper>
  );
};

export default FLCalendar;
