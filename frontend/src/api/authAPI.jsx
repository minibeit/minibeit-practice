import axios from "axios";

export const onSignup = (config, success, fail)=>{
    axios(config)
    .then((res)=>{
        success(res)
    })
    .catch((err=>{
        fail(err)
    }))
}