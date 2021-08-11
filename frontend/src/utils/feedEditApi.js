import axios from './refresh';

const feedEditApi = async ( 
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
export default feedEditApi;