import { useEffect, useState } from "react";
import { api } from "../../api.js";

function AllFeedbacks() {
  const [feedbacks, setFeedbacks] = useState([]);
  const [loading, setLoading] = useState(true);

  function load() {
    api
      .get("/feedbacks")
      .then(setFeedbacks)
      .catch(() => setFeedbacks([]))
      .finally(() => setLoading(false));
  }

  useEffect(load, []);

  async function handleDelete(id) {
    await api.del(`/feedbacks/${id}`);
    load();
  }

  async function handleResolve(id) {
    await api.put(`/feedbacks/${id}/resolve`);
    load();
  }

  return (
    <div>
      <h2 className="page-title">All Feedbacks</h2>
      <p className="page-subtitle">Every feedback submitted by users.</p>
      {loading ? (
        <p className="empty-state">Loading...</p>
      ) : feedbacks.length === 0 ? (
        <p className="empty-state">No feedbacks yet.</p>
      ) : (
        <div className="card-list">
          {feedbacks.map((fb) => (
            <div className="card feedback-card" key={fb.feedbackId}>
              <div className="feedback-header">
                <h3>{fb.title}</h3>
                <div>
                  <span className="badge">{fb.departmentName}</span>{" "}
                  <span
                    className={`badge ${
                      fb.status === "RESOLVED" ? "badge-light" : ""
                    }`}
                  >
                    {fb.status === "RESOLVED" ? "Resolved" : "Pending"}
                  </span>
                </div>
              </div>
              <p>{fb.message}</p>
              <div className="feedback-footer">
                <small>
                  By {fb.userFullName} • {new Date(fb.createdAt).toLocaleString()}
                </small>
                <div>
                  {fb.status !== "RESOLVED" && (
                    <button
                      className="btn btn-small"
                      onClick={() => handleResolve(fb.feedbackId)}
                    >
                      Resolve
                    </button>
                  )}{" "}
                  <button
                    className="btn btn-small btn-danger"
                    onClick={() => handleDelete(fb.feedbackId)}
                  >
                    Delete
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default AllFeedbacks;
