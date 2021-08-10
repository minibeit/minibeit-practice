import axios from "axios";

export const getFeedData = (config, success, fail)=>{
    axios(config)
    .then(async(res)=>{
        await success(res)
    })
    .catch((err=>{
        fail(err)
    }))
}

export const sendFeedData = (config, success, fail)=>{
    axios(config)
    .then(async(res)=>{
        await success(res)
    })
    .catch((err=>{
        fail(err)
    }))
}