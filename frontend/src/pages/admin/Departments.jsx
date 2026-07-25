import { useEffect, useState } from "react";
import { api } from "../../api.js";

function Departments() {
  const [departments, setDepartments] = useState([]);
  const [name, setName] = useState("");
  const [error, setError] = useState("");

  function load() {
    api
      .get("/departments")
      .then(setDepartments)
      .catch(() => setDepartments([]));
  }

  useEffect(load, []);

  async function handleAdd(e) {
    e.preventDefault();
    setError("");
    try {
      await api.post("/departments", { depName: name });
      setName("");
      load();
    } catch (err) {
      setError(err.message);
    }
  }

  async function handleDelete(id) {
    setError("");
    try {
      await api.del(`/departments/${id}`);
      load();
    } catch (err) {
      setError(err.message);
    }
  }

  return (
    <div>
      <h2 className="page-title">Departments</h2>
      <p className="page-subtitle">Manage the departments of your organization.</p>
      {error && <div className="alert">{error}</div>}
      <div className="card form-card">
        <form onSubmit={handleAdd} className="inline-form">
          <input
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="New department name"
            required
          />
          <button type="submit" className="btn btn-primary">
            Add
          </button>
        </form>
      </div>
      <div className="card-list">
        {departments.map((dep) => (
          <div className="card department-card" key={dep.id}>
            <span>{dep.depName}</span>
            <button
              className="btn btn-small btn-danger"
              onClick={() => handleDelete(dep.id)}
            >
              Delete
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Departments;
