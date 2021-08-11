import axios from './refresh';

const feedDeleteApi = async (postId) =>{
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
export default feedDeleteApi;