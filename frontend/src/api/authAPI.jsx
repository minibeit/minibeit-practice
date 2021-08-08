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

export const onLogin = (config, success, fail)=>{
    axios(config)
    .then((res)=>{
        success(res)
    })
    .catch((err)=>{
        fail(err)
    })
}

/* 수정 필!! */
export const getRefreshToken = ()=>{
    axios({
        method: 'post',
        url: 'http://3.36.95.15:8080/api/user/refreshtoken'
    })
    .then((res)=>{
        console.log(res.status)
    })
    .catch((err)=>{
        console.log(err)
    })
}