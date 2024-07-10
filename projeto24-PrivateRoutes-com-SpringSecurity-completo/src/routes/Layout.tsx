import { Outlet } from "react-router-dom";
import NavBar from "../components/NavBar";
import NavBar2 from "../components/Navbar2";
import Footer from "../components/Footer";
import '../css/footer.css';

function Layout() {
  return (
    <>
      <NavBar2 />
      <div className="container mt-3">
        <Outlet />
      </div>
      <Footer />
    </>
  );
}
export default Layout;
