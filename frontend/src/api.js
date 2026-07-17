const BASE_URL = "http://localhost:6000/api";

async function request(path, options) {
  const res = await fetch(BASE_URL + path, options);
  const text = await res.text();
  let data;
  try {
    data = JSON.parse(text);
  } catch {
    data = text;
  }
  if (!res.ok) {
    throw new Error(data && data.message ? data.message : "Something went wrong");
  }
  return data;
}

export const api = {
  get: (path) => request(path),
  post: (path, body) =>
    request(path, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    }),
  put: (path) => request(path, { method: "PUT" }),
  del: (path) => request(path, { method: "DELETE" }),
};
