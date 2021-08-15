import React from 'react';

import PCreateFeedForm from './PCreateFeedForm';
import { createFeed } from '../../../api/feedDataAPI';

export default function CreateFeedForm(){
    function createFeedHandler(data){
        createFeed(data)
    }

    return <PCreateFeedForm createFeedHandler={createFeedHandler}/>
}