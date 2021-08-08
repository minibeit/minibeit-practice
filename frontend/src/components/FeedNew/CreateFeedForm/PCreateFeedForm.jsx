import React from 'react';

import * as S from '../style';

export default function PCreateFeedForm(){
    return (
        <S.CreateFeedForm>
            <p>카테고리</p>
            <S.CategoryInput
                id='survey'
                type='radio'
                name='category'
                value='survey'
            />
            <label for='survey'>survey</label>
            <S.CategoryInput
                id='experiment'
                type='radio'
                name='category'
                value='experiment'
            />
            <label for='experiment'>experiment</label>
            <br/>
            <p>제목</p>
            <S.TitleInput
                name='title'
            />
            <p>내용</p>
            <S.ContentInput
                name='content'
            />
            <p>장소</p>
            <S.PlaceInput
                name='place'
            />
            <p>보수</p>
            <S.PayInput
                name='pay'
            />
            <p>소요시간</p>
            <S.TimeInput
                name='time'
                type='time'
            />
            <p>연락처</p>
            <S.PhoneNumInput
                name='phoneNum'
                placeholder='000-0000-0000'
            />
            <p>마감날짜</p>
            <S.DueDateInput
                name='dueDate'
                type='date'
            />
            <p>실험/설문 날짜</p>
            <S.DoDateInput
                name='doDate'
                type='date'
            />
            <input type='submit'></input>
        </S.CreateFeedForm>
    )
}