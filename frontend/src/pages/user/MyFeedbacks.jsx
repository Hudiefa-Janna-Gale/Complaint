import { useEffect, useState } from "react";
import { api } from "../../api.js";

function MyFeedbacks({ user }) {
  const [feedbacks, setFeedbacks] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api
      .get(`/feedbacks/user/${user.id}`)
      .then(setFeedbacks)
      .catch(() => setFeedbacks([]))
      .finally(() => setLoading(false));
  }, [user.id]);

  return (
    <div>
      <h2 className="page-title">My Feedbacks</h2>
      <p className="page-subtitle">All the feedback you have submitted.</p>
      {loading ? (
        <p className="empty-state">Loading...</p>
      ) : feedbacks.length === 0 ? (
        <p className="empty-state">You haven't submitted any feedback yet.</p>
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
              <small>{new Date(fb.createdAt).toLocaleString()}</small>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default MyFeedbacks;
