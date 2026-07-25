import { createContext, useContext, useEffect, useReducer } from "react";
import { api } from "./api.js";

const AuthContext = createContext(null);

// Where a logged-in user should land, based on their role.
export function roleHome(user) {
  if (!user) return "/login";
  return user.role === "ADMIN" ? "/admin" : "/user";
}

const initialState = { user: null, loading: true };

function authReducer(state, action) {
  switch (action.type) {
    case "SET_USER":
      return { ...state, user: action.payload };
    case "READY":
      return { ...state, loading: false };
    case "LOGOUT":
      return { ...state, user: null };
    default:
      return state;
  }
}

export function AuthProvider({ children }) {
  const [state, dispatch] = useReducer(authReducer, initialState);

  // On first load, ask the backend who we are using the session cookie.
  useEffect(() => {
    api
      .get("/auth/me")
      .then((u) => dispatch({ type: "SET_USER", payload: u }))
      .catch(() => dispatch({ type: "SET_USER", payload: null }))
      .finally(() => dispatch({ type: "READY" }));
  }, []);

  async function login(email, password) {
    const u = await api.post("/auth/login", { email, password });
    dispatch({ type: "SET_USER", payload: u });
    return u;
  }

  async function register(form) {
    const u = await api.post("/auth/register", form);
    dispatch({ type: "SET_USER", payload: u });
    return u;
  }

  async function logout() {
    try {
      await api.post("/auth/logout");
    } finally {
      dispatch({ type: "LOGOUT" });
    }
  }

  return (
    <AuthContext.Provider
      value={{ user: state.user, loading: state.loading, login, register, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
