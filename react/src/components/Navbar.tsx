import { Link } from "react-router-dom";
import { useContext } from "react";
import { CarrinhoContext } from "../context/carrinhoContext";
import "../css/geral.css";

function NavBar() {
  const carrinhoContext = useContext(CarrinhoContext);

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container">
        <Link className="navbar-brand" to="/">
          Troca Na Boa
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Produtos
              </a>
              <ul className="dropdown-menu">
                <li>
                  <Link className="dropdown-item" to="/produtos">
                    Listar todos
                  </Link>
                </li>
                <li>
                  <Link className="dropdown-item" to="/listar-meus-produtos">
                    Meus produtos
                  </Link>
                </li>
                <li>
                  <Link className="dropdown-item" to="/cadastrar-produto">
                    Cadastrar produto
                  </Link>
                </li>
              </ul>
            </li>
            <li className="nav-item dropdown me-5">
              <a
                className="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Categorias
              </a>
              <ul className="dropdown-menu">
                <li>
                  <Link className="dropdown-item" to="/categorias">
                    Listar categorias
                  </Link>
                </li>
                <li>
                  <Link className="dropdown-item" to="/cadastrar-categoria">
                    Cadastrar categoria
                  </Link>
                </li>
              </ul>
            </li>
            <li className="nav-item">
              <Link className="nav-link me-5" to="/carrinho">
                Produtos Selecionados
                {carrinhoContext && carrinhoContext.carrinho.length > 0 && (
                  <span className="badge bg-primary ms-2">
                    {carrinhoContext.carrinho.length}
                  </span>
                )}
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/login">
                Login
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default NavBar;
