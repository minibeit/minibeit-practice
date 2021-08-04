import axios from 'axios';

const axiosInstance = axios.create();
const fetchApi = async (config) => {
    try {
      const { data } = await axiosInstance(config);
      return [data, null];
    } catch (e) {
      return [null, e];
    }
  };
  export default fetchApi;