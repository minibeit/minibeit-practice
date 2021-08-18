import axios from './refresh';
import { API_URLS } from '../constants';

const { FEED_NEW } = API_URLS;

export const feedCreateApi = async ( 
    title,
    dueDate,
    doDate,
    pay,
    time,
    place,
    content,
   phoneNum,
    files,
    category) =>{
    const formData = new FormData();
    formData.append('title', title);
    formData.append('content', content);
    formData.append('place', place);
    formData.append('pay', pay);
    formData.append('time', time);
    
    formData.append('files', files);
    
    console.log( title,
        dueDate,
        doDate,
        pay,
        time,
        place,
        content,
        phoneNum,
        files,
        category)
    formData.append('phoneNum', phoneNum);
    formData.append('category', category);
    formData.append('dueDate', dueDate);
    formData.append('doDate', doDate);
    const accessToken = localStorage.getItem('accessToken');  
    console.log(accessToken) 
    const result = await axios.post(
        FEED_NEW,formData,{
            headers:{
                Authorization: `Bearer ${accessToken}`,
            }
        }
    )
    .then((res)=>{
        return res;
      })
      .catch((err)=>{
        return err
      });
      console.log(result)
      return result.data;
}

export const feedDeleteApi = async (postId) =>{
    const accessToken = localStorage.getItem('accessToken');
   
    const result = await axios
    .delete(`http://3.36.95.15:8080/api/board/${postId}`,{
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    })
    .then((res)=>{
        return res;
      })
      .catch((err)=>{
        return false
      });
      return result.data;
    }

export const feedDetailApi = async (postId) =>{
    const accessToken = localStorage.getItem('accessToken');
    const result = await axios
    .get(`http://3.36.95.15:8080/api/board/${postId}`,{
        headers: {
            Authorization: `Bearer ${accessToken}`
        },cache: true,
    })
    .then((res)=>{
        return res;
        })
        .catch((err)=>{
        return false
        });
        console.log(result)
        return result.data;
}

export const feedEditApi = async ( 
    title,
    dueDate,
    doDate,
    pay,
    time,
    place,
    content,
    phoneNum,
    files,
    category,
    postId) =>{
    const formData = new FormData();
    formData.append('title', title);
    formData.append('content', content);
    formData.append('place', place);
    formData.append('pay', pay);
    formData.append('time', time);
    
    formData.append('files', files);
    
    console.log( title,
        dueDate,
        doDate,
        pay,
        time,
        place,
        content,
        phoneNum,
        files,
        category)
    formData.append('phoneNum', phoneNum);
    formData.append('category', category);
    formData.append('dueDate', dueDate);
    formData.append('doDate', doDate);
    const accessToken = localStorage.getItem('accessToken');  
    console.log(accessToken) 
    const result = await axios.post(
        `http://3.36.95.15:8080/api/board/${postId}`,formData,{
            headers:{
                Authorization: `Bearer ${accessToken}`,
            }
        }
    )
    .then((res)=>{
        return res;
        })
        .catch((err)=>{
        return err
        });
        console.log(result)
        return result.data;
}

export const feedlistApi = async (category, page, size) =>{
    const accessToken = localStorage.getItem('accessToken');
    const result = await axios
    .get(`http://3.36.95.15:8080/api/board/list?category=${category}&page=${page}&size=${size}`,{
        headers: {
            Authorization: `Bearer ${accessToken}`
        },cache: true
    })
    .then((res)=>{
        return res;
      })
      .catch((err)=>{
        return err
      });
      const data = {
        post : result.data.content,
        endpage : result.data.totalPages,
        totalElement : result.data.totalElements,
      }
      
      return data;
}

