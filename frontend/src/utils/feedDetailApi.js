import axios from './refresh';

const feedDetailApi = async (postId) =>{
    const accessToken = localStorage.getItem('accessToken');
    const result = await axios
    .get(`http://3.36.95.15:8080/api/board/${postId}`,{
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
      console.log(result)
      return result.data;
}
export default feedDetailApi;