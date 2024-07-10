import Paginacao from "../../components/PaginacaoCategoria";
import TabelaDeCategorias from "../../components/TabelaDeCategorias";
import "../../css/geral.css";

const ListaCategoriasPage = () => {
  return (
    <>
      <h4>Lista de Categorias</h4>
      <hr className="mt-1" />
      <TabelaDeCategorias />
      <Paginacao />
    </>
  );
};
export default ListaCategoriasPage;
