import axios from 'axios';

const axiosInstance = axios.create();
axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const {
      config,
      response: { status },
    } = error;
    
    if (status === 401) {
      if (error.response.data.message === "Full authentication is required to access this resource") {
        
        const originalRequest = config;
        console.log(originalRequest)
        const data2  = await axios.post( 
          'http://3.36.95.15:8080/api/user/refreshtoken',{},// token refresh api
          );
        console.log(data2)
        // 새로운 토큰 저장
        
        localStorage.setItem("accessToken", data2.data.AccessToken);
        localStorage.setItem("accessTokenExpiredAt", data2.data.AccessTokenExpiredAt);
        originalRequest.headers.Authorization = `Bearer ${data2.data.AccessToken}`;
        // 401로 요청 실패했던 요청 새로운 accessToken으로 재요청
        return axios(originalRequest);
      }
    }
    return Promise.reject(error);
  }
);

  export default axiosInstance;