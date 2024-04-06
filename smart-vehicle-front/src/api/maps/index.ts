import axios from "axios";

let api = '';

// 获取全国JSON
export const getChinaJson = (params) => axios.get(api+`/map/china.json`, params).then(checkStatus);

// 获取省JSON
export const getProvinceJSON = (path,params='') => axios.get(api+`/map/province/${path}.json`, params).then(checkStatus);

// 获取市JSON
export const getCityJSON = (path,params='') => axios.get(api+`/map/city/${path}.json`, params).then(checkStatus);

// 获取天气
export const getWeather = (params) => axios.get(`https://api.asilu.com/weather_v2/`, params).then(checkStatus);

function checkStatus(response) {
    if (response.status === 200 || response.status === 304) {
        return response['data'];
    } else {
        throw new Error(response.data.message); // eslint-disable-line
    }
    throw new Error(response.statusText); // eslint-disable-line
}
