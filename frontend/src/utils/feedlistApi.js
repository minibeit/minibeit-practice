import axios from './refresh';

const feedlistApi = async (category, page, size) =>{
    const accessToken = localStorage.getItem('accessToken');
    const result = await axios
    .get(`http://3.36.95.15:8080/api/board/list?category=${category}&page=${page}&size=${size}`,{
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
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
export default feedlistApi;