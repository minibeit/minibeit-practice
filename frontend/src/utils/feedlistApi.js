import axios from 'axios';

const feedlistApi = async (category) =>{
    const accessToken = localStorage.getItem('accessToken');
    const result = await axios
    .get(`http://3.36.95.15:8080/api/board/list?category=${category}`,{
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
export default feedlistApi;