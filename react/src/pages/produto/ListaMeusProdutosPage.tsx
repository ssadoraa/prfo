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
      <Paginacao />
    </>
  );
};
export default ListaMeusProdutosPage;
