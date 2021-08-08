import axios from 'axios';

axios.interceptors.response.use(
    (response) => {
      return response;
    },
    async (error) => {
      const {
        config,
        response: { status },
      } = error;
      if (status === 401) {
        if (error.response.data.message === "TokenExpiredError") {
          const originalRequest = config;
          const preAccessToken = localStorage.getItem("accessToken");
          // token refresh 요청
          const { data } = await axios.post(
            'http://3.36.95.15:8080/api/user/refreshtoken', // token refresh api
            {
                headers: {
                    Authorization: `Bearer ${preAccessToken}`
                }
            });
          // 새로운 토큰 저장
          const {
            accessToken: newAccessToken,
            accessTokenExpiredAt: newAccessTokenExpiredAt,
          } = data;
          localStorage.setItem("accessToken", newAccessToken);
          localStorage.setItem("accessTokenExpiredAt", newAccessTokenExpiredAt);
          axios.defaults.headers.common.Authorization = `Bearer ${newAccessToken}`;
          originalRequest.headers.Authorization = `Bearer ${newAccessToken}`;
          // 401로 요청 실패했던 요청 새로운 accessToken으로 재요청
          return axios(originalRequest);
        }
      }
      return Promise.reject(error);
    }
  );