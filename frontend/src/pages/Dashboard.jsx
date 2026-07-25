import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../AuthContext.jsx";
import ThemeToggle from "../components/ThemeToggle.jsx";
import SubmitFeedback from "./user/SubmitFeedback.jsx";
import MyFeedbacks from "./user/MyFeedbacks.jsx";
import Notifications from "./user/Notifications.jsx";
import AllFeedbacks from "./admin/AllFeedbacks.jsx";
import Departments from "./admin/Departments.jsx";
import Users from "./admin/Users.jsx";

const userTabs = [
  { key: "submit", label: "Submit Feedback" },
  { key: "myFeedbacks", label: "My Feedbacks" },
  { key: "notifications", label: "Notifications" },
];

const adminTabs = [
  { key: "allFeedbacks", label: "All Feedbacks" },
  { key: "notifications", label: "Notifications" },
  { key: "departments", label: "Departments" },
  { key: "users", label: "Users" },
];

function Dashboard() {
  const navigate = useNavigate();
  const { user, logout } = useAuth();
  const isAdmin = user.role === "ADMIN";
  const tabs = isAdmin ? adminTabs : userTabs;
  const [active, setActive] = useState(tabs[0].key);

  async function handleLogout() {
    await logout();
    navigate("/", { replace: true });
  }

  return (
    <div className="dashboard">
      <aside className="sidebar">
        <div className="sidebar-logo">
          Complaint<span>Hub</span>
        </div>
        <div className="sidebar-user">
          <div className="sidebar-avatar">{user.fullName.charAt(0)}</div>
          <div>
            <strong>{user.fullName}</strong>
            <small>{isAdmin ? "Administrator" : user.department}</small>
          </div>
        </div>
        <nav className="sidebar-nav">
          {tabs.map((tab) => (
            <button
              key={tab.key}
              className={active === tab.key ? "active" : ""}
              onClick={() => setActive(tab.key)}
            >
              {tab.label}
            </button>
          ))}
        </nav>
        <ThemeToggle className="sidebar-toggle" />
        <Link to="/" className="sidebar-back">
          ← Back to home
        </Link>
        <button className="sidebar-logout" onClick={handleLogout}>
          Logout
        </button>
      </aside>
      <main className="dashboard-content">
        {active === "submit" && <SubmitFeedback user={user} />}
        {active === "myFeedbacks" && <MyFeedbacks user={user} />}
        {active === "notifications" && <Notifications user={user} />}
        {active === "allFeedbacks" && <AllFeedbacks />}
        {active === "departments" && <Departments />}
        {active === "users" && <Users />}
      </main>
    </div>
  );
}

export default Dashboard;
