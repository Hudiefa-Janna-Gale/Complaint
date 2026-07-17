import { useEffect, useState } from "react";
import { api } from "../../api.js";

function SubmitFeedback({ user }) {
  const [departments, setDepartments] = useState([]);
  const [title, setTitle] = useState("");
  const [message, setMessage] = useState("");
  const [departmentId, setDepartmentId] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    api
      .get("/departments")
      .then(setDepartments)
      .catch(() => setDepartments([]));
  }, []);

  async function handleSubmit(e) {
    e.preventDefault();
    setError("");
    setSuccess("");
    setLoading(true);
    try {
      await api.post("/feedbacks", {
        title,
        message,
        userId: user.id,
        departmentId: Number(departmentId),
      });
      setSuccess("Your feedback was submitted successfully");
      setTitle("");
      setMessage("");
      setDepartmentId("");
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div>
      <h2 className="page-title">Submit Feedback</h2>
      <p className="page-subtitle">
        Tell us what's on your mind and we'll route it to the right department.
      </p>
      <div className="card form-card">
        {error && <div className="alert">{error}</div>}
        {success && <div className="alert alert-success">{success}</div>}
        <form onSubmit={handleSubmit}>
          <label>Title</label>
          <input
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="Short summary of your feedback"
            required
          />
          <label>Department</label>
          <select
            value={departmentId}
            onChange={(e) => setDepartmentId(e.target.value)}
            required
          >
            <option value="">Select a department</option>
            {departments.map((dep) => (
              <option key={dep.id} value={dep.id}>
                {dep.DepName}
              </option>
            ))}
          </select>
          <label>Message</label>
          <textarea
            rows="5"
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            placeholder="Describe your feedback in detail"
            required
          />
          <button type="submit" className="btn btn-primary" disabled={loading}>
            {loading ? "Submitting..." : "Submit Feedback"}
          </button>
        </form>
      </div>
    </div>
  );
}

export default SubmitFeedback;
