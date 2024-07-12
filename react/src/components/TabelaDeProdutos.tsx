import dayjs from "dayjs";
import useProdutosComPaginacao from "../hooks/produto/useProdutosComPaginacao";
import useProdutoStore from "../store/produtoStore";
import useRemoverProduto from "../hooks/produto/useRemoverProduto";
import { Link } from "react-router-dom";

const TabelaDeProdutos = () => {
  const pagina = useProdutoStore((s) => s.pagina);
  const tamanho = useProdutoStore((s) => s.tamanho);
  const nome = useProdutoStore((s) => s.nome);
  const setPagina = useProdutoStore((s) => s.setPagina);

  const { mutate: removerProduto } = useRemoverProduto();

  const tratarRemocao = (id: number) => {
    if (window.confirm("Tem certeza que deseja remover este produto?")) {
      removerProduto(id);
      setPagina(0);
    }
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
          <th className="align-middle text-center"></th>
        </tr>
      </thead>
      <tbody>
        {produtos.map((produto) => (
          <tr key={produto.id}>
            <td width="3%" className="align-middle text-center">
              {produto.id}
            </td>
            <td width="6%" className="align-middle text-center">
              {produto.categoria.nome}
            </td>
            <td width="30%" className="align-middle">
              <Link to={`/${produto.id}`} className="link-sem-underline">
                {produto.nome}
              </Link>
            </td>
            <td width="8%" className="align-middle text-center">
              {dayjs(produto.dataCadastro).format("DD/MM/YYYY")}
            </td>
            <td width="6%" className="align-middle text-center">
              <div className="dropdown">
                <button
                  className="btn btn-outline-info btn-sm dropdown-toggle"
                  type="button"
                  id={`dropdownMenuButton${produto.id}`}
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  Ações
                </button>
                <ul className="dropdown-menu" aria-labelledby={`dropdownMenuButton${produto.id}`}>
                  <li><button onClick={() => tratarRemocao(produto.id!)} className="dropdown-item">Remover</button></li>
                  <li><Link to={`/${produto.id}/edit`} className="link-sem-underline dropdown-item">Editar</Link></li>
                </ul>
              </div>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default TabelaDeProdutos;
