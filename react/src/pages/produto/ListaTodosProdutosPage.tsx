import { NavLink, Outlet } from "react-router-dom";
import "../../css/geral.css";

const ListaTodosProdutosPage = () => {
  return (
    <div className="row">
      <div className="col-lg-2">
        <h5>Categorias</h5>
        <div className="nav flex-column nav-pills">
          <NavLink aria-current="page" className="nav-link" to="/listar-produtos">
            Todos
          </NavLink>
          <NavLink aria-current="page" className="nav-link" to="/listar-produtos/frutas">
            Frutas
          </NavLink>
          <NavLink aria-current="page" className="nav-link" to="/listar-produtos/legumes">
            Legumes
          </NavLink>
          <NavLink aria-current="page" className="nav-link" to="/listar-produtos/verduras">
            Verduras
          </NavLink>
        </div>
      </div>
      <div className="col-lg-10">
        <Outlet />
      </div>
    </div>
  );
};

export default ListaTodosProdutosPage;
