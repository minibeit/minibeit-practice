import axios from "axios";

export const sendData = (config, success, fail)=>{
    axios(config)
    .then((res)=>{
        success(res)
    })
    .catch(err=>{
        fail(err)
    })
}

export const getFeedList = async (category, page)=>{
    const accessToken = localStorage.getItem('accessToken');
    return await axios({
        method: 'GET',
        url: `http://3.36.95.15:8080/api/board/list?category=${category}&page=${page}&size=5`,
        headers: {
          Authorization: `Bearer ${accessToken}`
        }
      })
    .then((res)=>{
        return res.data
    })
    .catch((err)=>{
        alert('불러오기 실패')
    })
}

export const getDetailFeed = async (postId)=>{
    const accessToken = localStorage.getItem('accessToken');
    return await axios({
        method: 'GET',
        url: `http://3.36.95.15:8080/api/board/${postId}`,
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    })
    .then((res)=>{
        return res.data
    })
    .catch((err)=>{
        alert('불러오기 실패')
    })
}

export const createFeed = (data)=>{
    const accessToken = localStorage.getItem('accessToken');
    axios({
        method: 'POST',
        url: 'http://3.36.95.15:8080/api/board',
        data: data,
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    })
    .then((res)=>{
        alert('생성 완료!')
        window.location.href = '/applyHome'
    })
    .catch((err)=>{
        alert(err)
    })
}

export const deleteFeed = (postId)=>{
    const accessToken = localStorage.getItem('accessToken');
    axios({
        method: 'DELETE',
        url: `http://3.36.95.15:8080/api/board/${postId}`,
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    })
    .then((res)=>{
        alert('삭제 성공!')
    })
    .catch((err)=>{
        alert(err)
    })
}