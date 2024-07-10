import { Outlet } from "react-router-dom";
import NavBar from "../components/Navbar";
import Footer from "../components/Footer";
import "../css/footer.css";

function Layout() {
  return (
    <>
      <NavBar />
      <div className="container mt-3">
        <Outlet />
      </div>
      <Footer />
    </>
  );
}
export default Layout;
