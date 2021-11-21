import { apiInstance } from "./index.js";

const api = apiInstance();

function writeReply(reply, success, fail) {
  api.post(`/reply`, JSON.stringify(reply)).then(success).catch(fail);
}

function listReply(boardid, success, fail) {
  api.get(`/reply/${boardid}`).then(success).catch(fail);
}

export { writeReply, listReply };
