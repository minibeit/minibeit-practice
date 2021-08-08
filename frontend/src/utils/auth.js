import axios from 'axios';
import { API_URLS } from '../constants';


const { LOGIN, SIGNUP} = API_URLS;
 

  export const obtainToken = async (useremail, password) => {
    const data= {
      email: useremail,
      password: password,
    }
  const result = await axios
  .post(LOGIN,data)
  .then((res)=>{
    localStorage.setItem("accessToken", res.data.accessToken);
    localStorage.setItem("accessTokenExpiredAt", res.data.accessTokenExpiredAt);
    return true;
  })
  .catch((err)=>{
    return false
  });
  return result;
    };
    

  export const signUpfunc = async (username, useremail, password) => {
    const data= {
      name : username,
      email: useremail,
      password: password,
    }
  const result = await axios
  .post(SIGNUP,data )
  .then((res)=>{
    return true;
  })
  .catch((err)=>{
    return false
  });
  return result;
  
  }