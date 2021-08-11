import React, { useState } from 'react';

import * as S from '../style';

export default function PCreateFeedForm(props){
    const date = new Date();
    const [feedData, setFeedData] = useState({
        category: '',
        content: '',
        place: '',
        pay: '',
        time: '',
        phoneNum: '',
        dueDate: '',
        doDate: '',
    })
    const [img, setImg] = useState();
    const onChange = (e)=>{
        const feedData_cp = feedData;
        feedData_cp[`${e.target.name}`] = e.target.value;
        if(e.target.name == 'doDate'){
            feedData_cp[`${e.target.name}`]=feedData_cp[`${e.target.name}`]+':00'
        }
        setFeedData(feedData_cp)
    }
    const fileChange = (e)=>{
        setImg(e.target.files[0])
    }
    const onSubmit = (e)=>{
        e.preventDefault();
        const formData = new FormData();
        Object.keys(feedData).map((key)=>{
            formData.append(key, feedData[key])
        })
        if(img !== undefined){
            formData.append('files', img)
        }
        props.createFeedHandler(formData)
    }
    return (
        <S.CreateFeedForm
            encType='multipart/form-data'
            onSubmit={onSubmit}
        >
            <p>카테고리</p>
            <S.CategoryInput
                id='survey'
                type='radio'
                name='category'
                value='SURVEY'
                required='required'
                onChange={onChange}
            />
            <label for='survey'>survey</label>
            <S.CategoryInput
                id='experiment'
                type='radio'
                name='category'
                value='EXPERIMENT'
                required='required'
                onChange={onChange}
            />
            <label for='experiment'>experiment</label>
            <br/>
            <p>제목</p>
            <S.TitleInput
                name='title'
                required='required'
                onChange={onChange}
            />
            <p>내용</p>
            <S.ContentInput
                name='content'
                required='required'
                onChange={onChange}
            />
            <p>장소</p>
            <S.PlaceInput
                name='place'
                required='required'
                onChange={onChange}
            />
            <p>보수</p>
            <S.PayInput
                name='pay'
                required='required'
                onChange={onChange}
            />
            <p>소요시간</p>
            <S.TimeInput
                name='time'
                type='text'
                required='required'
                onChange={onChange}
            />
            <p>연락처</p>
            <S.PhoneNumInput
                name='phoneNum'
                placeholder='000-0000-0000'
                required='required'
                onChange={onChange}
            />
            <p>마감날짜</p>
            <S.DueDateInput
                name='dueDate'
                type='date'
                required='required'
                onChange={onChange}
            />
            <p>실험/설문 날짜</p>
            <S.DoDateInput
                name='doDate'
                type='datetime-local'
                required='required'
                onChange={onChange}
            />
            <p>업로드</p>
            <S.FileInput
                name='files'
                type='file'
                onChange={fileChange}
            />
            <input type='submit'></input>
        </S.CreateFeedForm>
    )
}