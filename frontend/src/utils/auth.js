import { API_URLS } from '../constants';
import fetchApi from './fetch';

const { TOKEN_OBTAIN, TOKEN_REFRESH, TOKEN_BLACKLIST } = API_URLS;


export const obtainToken = async (username, password) => {
    // obtain new access, refresh token with username, password info.
    const formData = new FormData();
    formData.append('username', username);
    formData.append('password', password);
  
    const headers = { 'Content-Type': 'multipart/form-data' };
    const config = {
      url: TOKEN_OBTAIN,
      method: 'POST',
      headers,
      data: formData,
    };
  
    const [data, error] = await fetchApi(config);
    if (data) {
      // set access, refresh token on localStorage
      localStorage.setItem('access-token', data.access);
      localStorage.setItem('refresh-token', data.refresh);
      localStorage.setItem('uuid', data.uuid);
      return data;
    }
    // localStorage.clear();
    localStorage.removeItem('uuid');
    localStorage.removeItem('access-token');
    localStorage.removeItem('refresh-token');
    // console.log('error', error);
    return Promise.reject(error);
  };
  