import { Link, useNavigate } from "react-router-dom";
import { useAuth, roleHome } from "../AuthContext.jsx";
import ThemeToggle from "./ThemeToggle.jsx";

function Navbar() {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  async function handleLogout() {
    await logout();
    navigate("/", { replace: true });
  }

  return (
    <nav className="navbar">
      <Link to="/" className="navbar-logo">
        Complaint<span>Hub</span>
      </Link>
      <div className="navbar-links">
        <ThemeToggle />
        {user ? (
          <>
            <Link to={roleHome(user)} className="btn btn-outline-light">
              Dashboard
            </Link>
            <button onClick={handleLogout} className="btn btn-light">
              Logout
            </button>
          </>
        ) : (
          <>
            <Link to="/login" className="btn btn-outline-light">
              Login
            </Link>
            <Link to="/register" className="btn btn-light">
              Get Started
            </Link>
          </>
        )}
      </div>
    </nav>
  );
}

export default Navbar;
