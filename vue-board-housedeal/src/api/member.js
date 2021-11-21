import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}
async function registerUser(user, success, fail) {
  await api
    .post(`/user/register`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}
async function modifyUserById(user, success, fail) {
  await api.put(`/user/update`, JSON.stringify(user)).then(success).catch(fail);
}
async function deleteUserById(userid, success, fail) {
  await api.delete(`/user/delete/${userid}`).then(success).catch(fail);
}
async function checkRepeatIdById(userid, success, fail) {
  await api.get(`/user/idcheck/${userid}`).then(success).catch(fail);
}
// function logout(success, fail)

export {
  login,
  registerUser,
  findById,
  modifyUserById,
  deleteUserById,
  checkRepeatIdById,
};
