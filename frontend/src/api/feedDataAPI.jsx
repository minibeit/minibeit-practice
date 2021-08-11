import axios from "axios";

export const sendData = (config, success, fail)=>{
    axios(config)
    .then(async(res)=>{
        await success(res)
    })
    .catch((err=>{
        fail(err)
    }))
}