import axios from 'axios';
import Validate from '../helpers/Validate';
import { API_URL, ERROR_MESSAGES } from '../services/ServiceConstants';

const validate = Validate();
export const checkAndReturnReponse = (response) => {
  if(validate.isNotEmpty(response) && validate.isNotEmpty(response.data) && validate.isNotEmpty(response.data.statusCode)) {
    return Promise.resolve(response.data);
  }
  return Promise.resolve(prepareResponse(ERROR_MESSAGES.UNABLE_TO_PROCESS));
}

export const errorHandling = (err) => {
    return Promise.reject(err.message);
  }

export const TOKEN_OBJECT={
  headerName: null,
  token: null
}
  
axios.interceptors.request.use((config)=> {
  config.headers.set(TOKEN_OBJECT.headerName,TOKEN_OBJECT.token);
  return config;
},err=>{console.log(err); Promise.reject(err)});

axios.interceptors.response.use((response) => {
    if (response && response.data) {
        if (response.data.message === 'USER_NOT_LOGGED_IN') {
            window.location.reload();
        }
    }
    return response;
}, (err) => {
    console.log("Actual server Error:", err.response);
   return err.response;
});

const prepareResponse = (message) => {
  return {'statusCode': 'FAILURE', 'message': message}
}

const serverRequest = async (requestInfo, data) => {
    try {
      switch (requestInfo.HEADER.method) {
        case 'POST':
        case 'post':
          return Validate().isEmpty(requestInfo.responseType) ? checkAndReturnReponse(await axios({
            method: 'POST',
            url: requestInfo.PATH,
            headers: { 'Content-Type': requestInfo.HEADER.contentType, 'X-Requested-With':'XMLHttpRequest' },
            data: data
          })) :
          checkAndReturnReponse(await axios({
            method: 'POST',
            url: requestInfo.PATH,
            headers: { 'Content-Type': requestInfo.HEADER.contentType, 'X-Requested-With':'XMLHttpRequest' },
            data: data,
            responseType: requestInfo.responseType
          }));
        case 'GET':
        case 'get':
          return checkAndReturnReponse(await axios({
            method: 'GET',
            url: requestInfo.PATH,
            contentType: requestInfo.HEADER.contentType,
            params: data,
            headers: {'X-Requested-With':'XMLHttpRequest'}
          }));
        case 'PATCH':
        case 'patch':
          return checkAndReturnReponse(await axios({
            method: 'PATCH',
            url: requestInfo.PATH,
            contentType: requestInfo.HEADER.contentType,
            params: data
          }));
        case 'POST_PARAMS':
          const urlPath = prepareUrl(requestInfo.PATH, data.requestParams)
          return checkAndReturnReponse(await axios({
            method: 'POST',
            url: urlPath,
            headers: { 'Content-Type': requestInfo.HEADER.contentType, 'X-Requested-With':'XMLHttpRequest' },
            data: data.requestBody,
            responseType: requestInfo.responseType
          }));
        default: return Promise.reject(prepareResponse(ERROR_MESSAGES.UNABLE_TO_PROCESS));
      }
    } catch (error) {
      console.log(error);
      return errorHandling(ERROR_MESSAGES.UNABLE_TO_PROCESS);
    }
  }
export default serverRequest;

const prepareUrl = (path, params) => {
  let url = path;
  const queryParams = new URLSearchParams(params).toString();
  if (queryParams) {
    url += '?' + queryParams;
  }
  return url;
}

export const downloadExcellServerRequest = async (requestInfo, data) => {
    const response =  await axios({
      method: 'GET',
      url: requestInfo.PATH,
      params: data,
      responseType: 'blob'
    }).then(response => {return response}).catch(error => {
      console.log(error);
      return errorHandling(ERROR_MESSAGES.UNABLE_TO_DOWNLOAD_FILE);
    });
    return response;
}

export const downloadExcellServerRequestPost = async (requestInfo, data) => {
  const response =  await axios({
    method: 'POST',
    url: requestInfo.PATH,
    data: data,
    responseType: 'blob'
  }).then(response => {return response}).catch(error => {
    return errorHandling(ERROR_MESSAGES.UNABLE_TO_DOWNLOAD_FILE);
  });
  return response;
}


export const getImageServerDetailsUploadImage = async (image, imageType) => {
  let response = await getImageServerDetails(imageType);
  let serverDetails = response.data;
  let filesFormData = new FormData();
  filesFormData.append("files", image, image.name);
  const url = serverDetails.imageServerUrl + "/upload?token=" + serverDetails.accessToken + "&clientId=" + serverDetails.clientId + "&imageType=" + imageType;
  let responseObject = await uploadFilesToServer(url, filesFormData);
  if (responseObject && responseObject.statusCode === "SUCCESS" && responseObject.response) {
    return Promise.resolve({
      ...responseObject.response,
      imageServerUrl: serverDetails.imageServerUrl,
    });
  }
  return Promise.reject(responseObject.message);
}

const getImageServerDetails = async (imageType) => {
  try {
    return await axios({
      method: "GET",
      url: API_URL + "get-image-server-details",
      contentType: "application/json; charset=utf-8",
      params: { imageType },
      headers: { "X-Requested-With": "XMLHttpRequest" },
    });
  } catch (error) {
    console.log(error);
    return setToastContent({ toastMessage: "Something went wrong. Please try again!" });
  }
};

const uploadFilesToServer = async (url, filesFormData, requestConfig) => {
  return await axios
    .post(url, filesFormData, requestConfig)
    .then((response) => {
      if (response.status !== 200) {
        return new Promise((resolve) => {
          return resolve({
            statusCode: "CLIENT_ERROR",
            message: "Something went wrong. Please try again!",
          });
        });
      }
      return response.data;
    });
};