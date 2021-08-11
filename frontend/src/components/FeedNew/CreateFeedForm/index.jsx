import React from 'react';
import { useHistory } from 'react-router-dom';

import PCreateFeedForm from './PCreateFeedForm';
import { sendData } from '../../../api/feedDataAPI';

export default function CreateFeedForm(){
    const history = useHistory();
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
            history.push('/')
        }
        const fail = (err)=>{
            console.log(err)
        }
        sendData(config, success, fail);
    }

    return <PCreateFeedForm createFeedHandler={createFeedHandler}/>
}