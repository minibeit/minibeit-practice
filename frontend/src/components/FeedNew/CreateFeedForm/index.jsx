import React from 'react';

import PCreateFeedForm from './PCreateFeedForm';
import { sendFeedData } from '../../../api/feedDataAPI';

export default function CreateFeedForm(){
    const accessToken = localStorage.getItem('accessToken');
    function createFeedHandler(data){
        const config = {
            method: 'post',
            url: 'http://3.36.95.15:8080/api/board',
            data: data,
            headers: {
                Authorization: `Bearer ${accessToken}`
            }
        }
        const success = (res)=>{
            console.log(res.data)
        }
        const fail = (err)=>{
            console.log(err)
        }
        sendFeedData(config, success, fail);
    }

    return <PCreateFeedForm createFeedHandler={createFeedHandler}/>
}