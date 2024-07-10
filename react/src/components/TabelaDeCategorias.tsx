import dayjs from "dayjs";
import useRemoverCategoria from "../hooks/categoria/useRemoverCategoria";
import useCategoriaStore from "../store/categoriaStore";
import useCategoriasComPaginacao from "../hooks/categoria/useCategoriasComPaginacao";

const TabelaDeCategorias = () => {
  const pagina = useCategoriaStore((s) => s.pagina);
  const tamanho = useCategoriaStore((s) => s.tamanho);
  const nome = useCategoriaStore((s) => s.nome);
  const setPagina = useCategoriaStore((s) => s.setPagina);

  const { mutate: removerCategoria } = useRemoverCategoria();

  const tratarRemocao = (id: number) => {
    removerCategoria(id);
    setPagina(0);
  };

  const {
    data: resultadoPaginado,
    isPending: carregandoCategorias,
    error: errorCategorias,
  } = useCategoriasComPaginacao({ pagina, tamanho, nome });

  if (carregandoCategorias) return <h6>Carregando...</h6>;
  if (errorCategorias) throw errorCategorias;

  const categorias = resultadoPaginado.itens;

  return (
    <table className="table table-responsive table-sm table-hover table-bordered">
      <thead>
        <tr>
          <th className="align-middle text-center">Nome</th>
          <th className="align-middle text-center">Data de Cadastro</th>
          <th className="align-middle text-center"></th>
        </tr>
      </thead>
      <tbody>
        {categorias.map((produto) => (
          <tr key={produto.id}>
            <td width="20%" className="align-middle">
                {produto.nome}
            </td>
            <td width="12%" className="align-middle text-center">
              {dayjs(produto.dataCadastro).format("DD/MM/YYYY")}
            </td>
            <td width="12%" className="align-middle text-center">
              <button
                onClick={() => tratarRemocao(produto.id!)}
                className="btn btn-danger btn-sm"
              >
                Remover
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
export default TabelaDeCategorias;
