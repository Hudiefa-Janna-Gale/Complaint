import { Navigate } from "react-router-dom";
import { useAuth, roleHome } from "../AuthContext.jsx";

// Guest-only pages (login / register). If the visitor is already logged in,
// send them to their own dashboard instead of showing the auth page.
function GuestRoute({ children }) {
  const { user, loading } = useAuth();

  if (loading) {
    return <div className="page-loading">Loading...</div>;
  }
  if (user) {
    return <Navigate to={roleHome(user)} replace />;
  }
  return children;
}

export default GuestRoute;
