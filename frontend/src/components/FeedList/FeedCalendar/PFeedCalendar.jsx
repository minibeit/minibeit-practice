import React from 'react';
import dayjs from 'dayjs';

import * as S from '../style';

const weekDays = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"]

export default function PFeedCalendar(props){
    const dayObj = props.dayObj
    const setDayObj = props.setDayObj
    const dayOfFirstDate = dayjs(`${dayObj.year()}-${dayObj.month()+1}-1`).get('day')
    
    const daysInMonthArr = []
    for(var i=1;i<=dayObj.daysInMonth();i++){
        daysInMonthArr.push(i)
    }

    for(var i=0;i<dayOfFirstDate;i++){  //첫 주에 숫자 끼워넣기 위한 로직
        daysInMonthArr.unshift("")
    }
    
    const weeksCount = Math.ceil(daysInMonthArr.length/7) //달의 주

    const weeksOfMonth = []
    for(var i=0;i<weeksCount;i++){
        weeksOfMonth.push(daysInMonthArr.splice(0,7))
    }
    
    
    const controlMonth = (e)=>{
        if(e.target.name === 'preMonth'){
            setDayObj(dayObj.subtract(1, 'month'))
        }else{
            setDayObj(dayObj.add(1, 'month'))
        }
    }



    return (
        <>
        <S.Calendar>
            <S.MonthController>
                <button name='preMonth' onClick={controlMonth}>이전달</button>
                <span>{dayObj.format('YYYY-MM')}</span>
                <button name='nextMonth' onClick={controlMonth}>다음달</button>
            </S.MonthController>
            <table border='1'>
                <thead>
                    {
                        weekDays.map((a)=>{
                            return <th>{a}</th>
                        })
                    }
                </thead>
                <tbody>
                    {
                        weeksOfMonth.map((a,i)=>{
                            return <tr>
                                {
                                    weeksOfMonth[i].map((a)=>{
                                        return <td>{a}</td>
                                    })
                                }
                            </tr>
                        })
                    }
                </tbody>
            </table>
        </S.Calendar>
        </>
    )
}