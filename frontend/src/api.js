import axios from "axios";

const client = axios.create({
  baseURL: "http://localhost:8081/api",
  withCredentials: true,
});

client.interceptors.response.use(
  (res) => res,
  (error) => {
    const message = error.response?.data?.message || "Something went wrong";
    return Promise.reject(new Error(message));
  }
);

export const api = {
  get: (path) => client.get(path).then((res) => res.data),
  post: (path, body) => client.post(path, body).then((res) => res.data),
  put: (path, body) => client.put(path, body).then((res) => res.data),
  del: (path) => client.delete(path).then((res) => res.data),
};
