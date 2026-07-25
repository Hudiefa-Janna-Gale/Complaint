import { useEffect, useState } from "react";
import { api } from "../../api.js";

function Users() {
  const [users, setUsers] = useState([]);
  const [departments, setDepartments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [editing, setEditing] = useState(null);
  const [form, setForm] = useState({ fullName: "", email: "", phone: "", departmentId: "" });
  const [error, setError] = useState("");

  function load() {
    api
      .get("/users")
      .then(setUsers)
      .catch(() => setUsers([]))
      .finally(() => setLoading(false));
  }

  useEffect(() => {
    load();
    api
      .get("/departments")
      .then(setDepartments)
      .catch(() => setDepartments([]));
  }, []);

  function startEdit(u) {
    setError("");
    setEditing(u.id);
    setForm({
      fullName: u.fullName,
      email: u.email,
      phone: u.phone,
      departmentId: u.departmentId,
    });
  }

  async function handleUpdate(e) {
    e.preventDefault();
    setError("");
    try {
      await api.put(`/users/${editing}`, {
        ...form,
        departmentId: Number(form.departmentId),
      });
      setEditing(null);
      load();
    } catch (err) {
      setError(err.message);
    }
  }

  async function handleDelete(id) {
    if (!window.confirm("Delete this user and all their feedbacks?")) return;
    setError("");
    try {
      await api.del(`/users/${id}`);
      load();
    } catch (err) {
      setError(err.message);
    }
  }

  return (
    <div>
      <h2 className="page-title">Users</h2>
      <p className="page-subtitle">All registered users.</p>
      {error && <div className="alert">{error}</div>}
      {editing && (
        <div className="card form-card">
          <h3>Edit User</h3>
          <form onSubmit={handleUpdate}>
            <input
              value={form.fullName}
              onChange={(e) => setForm({ ...form, fullName: e.target.value })}
              placeholder="Full name"
              required
            />
            <input
              type="email"
              value={form.email}
              onChange={(e) => setForm({ ...form, email: e.target.value })}
              placeholder="Email"
              required
            />
            <input
              value={form.phone}
              onChange={(e) => setForm({ ...form, phone: e.target.value })}
              placeholder="Phone"
              required
            />
            <select
              value={form.departmentId}
              onChange={(e) => setForm({ ...form, departmentId: e.target.value })}
              required
            >
              <option value="">Select department</option>
              {departments.map((dep) => (
                <option key={dep.id} value={dep.id}>
                  {dep.depName}
                </option>
              ))}
            </select>
            <button type="submit" className="btn btn-primary">
              Save
            </button>{" "}
            <button
              type="button"
              className="btn btn-small"
              onClick={() => setEditing(null)}
            >
              Cancel
            </button>
          </form>
        </div>
      )}
      {loading ? (
        <p className="empty-state">Loading...</p>
      ) : (
        <div className="card table-card">
          <table>
            <thead>
              <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Department</th>
                <th>Role</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {users.map((u) => (
                <tr key={u.id}>
                  <td>{u.fullName}</td>
                  <td>{u.email}</td>
                  <td>{u.phone}</td>
                  <td>{u.department}</td>
                  <td>
                    <span className="badge">{u.role}</span>
                  </td>
                  <td>
                    <button className="btn btn-small" onClick={() => startEdit(u)}>
                      Edit
                    </button>{" "}
                    {u.role !== "ADMIN" && (
                      <button
                        className="btn btn-small btn-danger"
                        onClick={() => handleDelete(u.id)}
                      >
                        Delete
                      </button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default Users;
