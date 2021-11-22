import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/catdog`, { params: param }).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/catdog`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/catdog/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/catdog`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/catdog/${articleno}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };