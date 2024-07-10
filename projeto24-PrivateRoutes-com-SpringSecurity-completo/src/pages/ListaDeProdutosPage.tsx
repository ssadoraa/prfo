import Paginacao from "../components/Paginacao";
import Pesquisa from "../components/Pesquisa";
import TabelaDeProdutos from "../components/TabelaDeProdutos";
import "../css/geral.css";

const ListaDeProdutosPage = () => {
  return (
    <>
      <h4>Lista de Produtos</h4>
      <hr className="mt-1" />
      <Pesquisa />
      <TabelaDeProdutos />
      <Paginacao />
    </>
  );
};
export default ListaDeProdutosPage;
