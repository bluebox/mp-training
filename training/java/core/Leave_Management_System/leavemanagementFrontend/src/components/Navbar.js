// src/components/Navbar.js
export default function Navbar({ role, setRole }) {
  return (
    <nav style={{ padding: 12, background: "#f5f5f5", display: "flex", gap: 12 }}>
      <button onClick={() => setRole("EMPLOYEE")}>Employee</button>
      <button onClick={() => setRole("MANAGER")}>Manager</button>
      <button onClick={() => setRole("ADMIN")}>Admin</button>
    </nav>
  );
}
