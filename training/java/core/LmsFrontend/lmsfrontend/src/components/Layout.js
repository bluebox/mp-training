import Sidebar from "./Sidebar";

export default function Layout({ children }) {
  return (
    <div className="d-flex">
      <Sidebar />
      <main className="flex-grow-1 p-4">{children}</main>
    </div>
  );
}
