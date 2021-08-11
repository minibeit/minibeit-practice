import React, { useState } from 'react';

import * as S from '../style';

export default function PEditFeedForm(){
    return (
        <S.EditFeedForm
            encType='multipart/form-data'
        >
            <p>카테고리</p>
            <S.CategoryInput
                id='survey'
                type='radio'
                name='category'
                value='SURVEY'
                required='required'
            />
            <label for='survey'>survey</label>
            <S.CategoryInput
                id='experiment'
                type='radio'
                name='category'
                value='EXPERIMENT'
                required='required'
            />
            <label for='experiment'>experiment</label>
            <br/>
            <p>제목</p>
            <S.TitleInput
                name='title'
                required='required'
            />
            <p>내용</p>
            <S.ContentInput
                name='content'
                required='required'
            />
            <p>장소</p>
            <S.PlaceInput
                name='place'
                required='required'
            />
            <p>보수</p>
            <S.PayInput
                name='pay'
                required='required'
            />
            <p>소요시간</p>
            <S.TimeInput
                name='time'
                type='text'
                required='required'
            />
            <p>연락처</p>
            <S.PhoneNumInput
                name='phoneNum'
                placeholder='000-0000-0000'
                required='required'
            />
            <p>마감날짜</p>
            <S.DueDateInput
                name='dueDate'
                type='date'
                required='required'
            />
            <p>실험/설문 날짜</p>
            <S.DoDateInput
                name='doDate'
                type='datetime-local'
                required='required'
            />
            <p>업로드</p>
            <S.FileInput
                name='files'
                type='file'
            />
            <input type='submit'></input>
        </S.EditFeedForm>
    )
}