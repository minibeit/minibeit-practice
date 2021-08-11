import axios from 'axios';
import { API_URLS } from '../constants';

const { FEED_NEW } = API_URLS;

const feedCreateApi = async ( 
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
export default feedCreateApi;