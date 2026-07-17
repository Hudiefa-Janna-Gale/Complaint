import { Link, useNavigate } from "react-router-dom";
import { getUser, clearUser } from "../auth.js";

function Navbar() {
  const navigate = useNavigate();
  const user = getUser();

  function handleLogout() {
    clearUser();
    navigate("/");
  }

  return (
    <nav className="navbar">
      <Link to="/" className="navbar-logo">
        Complaint<span>Hub</span>
      </Link>
      <div className="navbar-links">
        {user ? (
          <>
            <Link to="/dashboard" className="btn btn-outline-light">
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
