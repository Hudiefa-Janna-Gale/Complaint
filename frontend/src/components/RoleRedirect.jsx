import { Navigate } from "react-router-dom";
import { useAuth, roleHome } from "../AuthContext.jsx";

// Sends the visitor to the dashboard that matches their role
// (or to /login when they are not authenticated).
function RoleRedirect() {
  const { user, loading } = useAuth();

  if (loading) {
    return <div className="page-loading">Loading...</div>;
  }
  return <Navigate to={roleHome(user)} replace />;
}

export default RoleRedirect;
