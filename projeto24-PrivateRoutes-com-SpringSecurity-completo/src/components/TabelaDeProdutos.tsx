import dayjs from "dayjs";
import deleteIcon from "../assets/skin/database_delete.png";
import useProdutosComPaginacao from "../hooks/produto/useProdutosComPaginacao";
import useProdutoStore from "../store/produtoStore";
import useRemoverProduto from "../hooks/produto/useRemoverProduto";

const TabelaDeProdutos = () => {
  const pagina = useProdutoStore((s) => s.pagina);
  const tamanho = useProdutoStore((s) => s.tamanho);
  const nome = useProdutoStore((s) => s.nome);
  const setPagina = useProdutoStore((s) => s.setPagina);
  const setProdutoSelecionado = useProdutoStore((s) => s.setProdutoSelecionado);

  const { mutate: removerProduto } = useRemoverProduto();

  const tratarRemocao = (id: number) => {
    removerProduto(id);
    setPagina(0);
  };

  const {
    data: resultadoPaginado,
    isPending: carregandoProdutos,
    error: errorProdutos,
  } = useProdutosComPaginacao({ pagina, tamanho, nome });

  if (carregandoProdutos) return <h6>Carregando...</h6>;
  if (errorProdutos) throw errorProdutos;

  const produtos = resultadoPaginado.itens;

  return (
    <table className="table table-responsive table-sm table-hover table-bordered">
      <thead>
        <tr>
          <th className="align-middle text-center">Id</th>
          <th className="align-middle text-center">Categoria</th>
          <th className="align-middle text-center">Nome</th>
          <th className="align-middle text-center">Data de Cadastro</th>
          <th className="align-middle text-center">Ação</th>
        </tr>
      </thead>
      <tbody>
        {produtos.map((produto) => (
          <tr key={produto.id}>
            <td width="8%" className="align-middle text-center">
              {produto.id}
            </td>
            <td width="12%" className="align-middle text-center">
              {produto.categoria.nome}
            </td>
            <td width="20%" className="align-middle">
              <a
                className="link-underline"
                onClick={() => {
                  setProdutoSelecionado(produto);
                }}
              >
                {produto.nome}
              </a>
            </td>
            <td width="12%" className="align-middle text-center">
              {dayjs(produto.dataCadastro).format("DD/MM/YYYY")}
            </td>
            <td width="12%" className="align-middle text-center">
              <button
                onClick={() => tratarRemocao(produto.id!)}
                className="btn btn-danger btn-sm"
              >
                <img src={deleteIcon} /> Remover
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
export default TabelaDeProdutos;
