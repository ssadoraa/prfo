import { NavLink, Outlet } from "react-router-dom";
import "../../css/geral.css";
import useCategorias from "../../hooks/categoria/useCategorias";

const ListaTodosProdutosPage = () => {
  const { data: categorias, isLoading, error } = useCategorias();

  if (isLoading) return <div>Carregando...</div>;
  if (error) return <div>Erro ao carregar categorias</div>;

  return (
    <div className="row">
      <div className="col-lg-2">
        <h5>Categorias</h5>
        <div className="nav flex-column nav-pills">
          <NavLink aria-current="page" className="nav-link" to="/produtos">
            Todos
          </NavLink>
          {categorias?.map((categoria) => (
            <NavLink
              key={categoria.id}
              aria-current="page"
              className="nav-link"
              to={`/produtos/${categoria.nome.toLowerCase()}`}
            >
              {categoria.nome}
            </NavLink>
          ))}
        </div>
      </div>
      <div className="col-lg-10">
        <Outlet />
      </div>
    </div>
  );
};

export default ListaTodosProdutosPage;
