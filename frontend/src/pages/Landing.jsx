import { Link } from "react-router-dom";
import Navbar from "../components/Navbar.jsx";

function Landing() {
  return (
    <div className="landing">
      <header className="hero">
        <Navbar />
        <div className="hero-content">
          <span className="hero-badge">Simple. Fast. Transparent.</span>
          <h1>
            Your Voice Matters.
            <br />
            We Make Sure It's Heard.
          </h1>
          <p>
            ComplaintHub is the easiest way to submit feedback and complaints
            to the right department, track their progress, and get notified
            the moment something happens.
          </p>
          <div className="hero-actions">
            <Link to="/register" className="btn btn-light btn-lg">
              Get Started Free
            </Link>
            <Link to="/login" className="btn btn-outline-light btn-lg">
              Sign In
            </Link>
          </div>
          <div className="hero-stats">
            <div>
              <strong>1 min</strong>
              <span>To submit feedback</span>
            </div>
            <div>
              <strong>24/7</strong>
              <span>Always available</span>
            </div>
            <div>
              <strong>100%</strong>
              <span>Tracked & notified</span>
            </div>
          </div>
        </div>
      </header>

      <section className="features">
        <h2>Everything you need in one place</h2>
        <p className="features-subtitle">
          Built to make feedback simple for users and powerful for admins.
        </p>
        <div className="features-grid">
          <div className="feature-card">
            <div className="feature-icon">✉</div>
            <h3>Submit Feedback</h3>
            <p>
              Send your complaint or feedback directly to the right department
              in seconds with a simple, clean form.
            </p>
          </div>
          <div className="feature-card">
            <div className="feature-icon">🔔</div>
            <h3>Instant Notifications</h3>
            <p>
              Get notified automatically the moment your feedback is received,
              so you always know what's happening.
            </p>
          </div>
          <div className="feature-card">
            <div className="feature-icon">📊</div>
            <h3>Admin Dashboard</h3>
            <p>
              Admins see every feedback in one place, manage departments and
              users, and keep everything organized.
            </p>
          </div>
        </div>
      </section>

      <section className="cta">
        <h2>Ready to be heard?</h2>
        <p>Create your free account and submit your first feedback today.</p>
        <Link to="/register" className="btn btn-light btn-lg">
          Create Account
        </Link>
      </section>

      <footer className="footer">
        <span className="footer-logo">
          Complaint<span>Hub</span>
        </span>
        <p>© 2026 ComplaintHub. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default Landing;
