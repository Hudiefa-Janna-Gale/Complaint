import { useEffect, useState } from "react";
import { api } from "../../api.js";

function Notifications({ user }) {
  const [notifications, setNotifications] = useState([]);
  const [loading, setLoading] = useState(true);

  function load() {
    api
      .get(`/notifications/user/${user.id}`)
      .then(setNotifications)
      .catch(() => setNotifications([]))
      .finally(() => setLoading(false));
  }

  useEffect(load, [user.id]);

  async function markAsRead(id) {
    await api.put(`/notifications/${id}/read`);
    load();
  }

  return (
    <div>
      <h2 className="page-title">Notifications</h2>
      <p className="page-subtitle">Updates about your feedback.</p>
      {loading ? (
        <p className="empty-state">Loading...</p>
      ) : notifications.length === 0 ? (
        <p className="empty-state">No notifications yet.</p>
      ) : (
        <div className="card-list">
          {notifications.map((n) => (
            <div
              className={`card notification-card ${n.isRead ? "read" : ""}`}
              key={n.notificationId}
            >
              <div className="feedback-header">
                <h3>{n.title}</h3>
                {n.isRead ? (
                  <span className="badge badge-light">Read</span>
                ) : (
                  <button
                    className="btn btn-small"
                    onClick={() => markAsRead(n.notificationId)}
                  >
                    Mark as read
                  </button>
                )}
              </div>
              <p>{n.message}</p>
              <small>{new Date(n.createdAt).toLocaleString()}</small>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Notifications;
