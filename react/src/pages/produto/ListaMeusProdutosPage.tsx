import { Link } from "react-router-dom";
import Paginacao from "../../components/PaginacaoProduto";
import Pesquisa from "../../components/Pesquisa";
import TabelaDeProdutos from "../../components/TabelaDeProdutos";
import "../../css/geral.css";

const ListaMeusProdutosPage = () => {
  return (
    <>
      <h4>Lista de Produtos</h4>
      <hr className="mt-1" />
      <Pesquisa />
      <TabelaDeProdutos />
      <div className="d-flex justify-content-between">
        <Paginacao />
        <div>
          <Link className="btn btn-sm btn-success mt-1" to="/cadastrar-produto">
            Cadastrar
          </Link>
        </div>
      </div>
    </>
  );
};
export default ListaMeusProdutosPage;
