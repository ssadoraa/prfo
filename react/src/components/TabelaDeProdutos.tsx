import { useState } from "react";
import dayjs from "dayjs";
import { BiUpArrow, BiDownArrow } from "react-icons/bi";
import { Link } from "react-router-dom";
import useProdutoStore from "../store/produtoStore";
import useUsuarioStore from "../store/usuarioStore";
import useProdutosComPaginacao from "../hooks/produto/useProdutosComPaginacao";
import useRemoverProduto from "../hooks/produto/useRemoverProduto";

const TabelaDeProdutos = () => {
  const [ordenacao, setOrdenacao] = useState({ coluna: 'id', direcao: 'asc' });
  const pagina = useProdutoStore((s) => s.pagina);
  const tamanho = useProdutoStore((s) => s.tamanho);
  const nome = useProdutoStore((s) => s.nome);
  const setPagina = useProdutoStore((s) => s.setPagina);
  const usuarioId = useUsuarioStore((s) => s.usuarioId);

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
  } = useProdutosComPaginacao({ pagina, tamanho, nome, usuarioId, ...ordenacao });

  const ordenarPor = (coluna: string) => {
    const novaDirecao = coluna === ordenacao.coluna && ordenacao.direcao === 'asc' ? 'desc' : 'asc';
    setOrdenacao({ coluna, direcao: novaDirecao });
    setPagina(0);
  };

  const iconOrdenacao = (coluna: string) => {
    if (ordenacao.coluna !== coluna) {
      return null;
    }

    return ordenacao.direcao === 'asc' ? <BiUpArrow /> : <BiDownArrow />;
  };

  if (carregandoProdutos) return <h6>Carregando...</h6>;
  if (errorProdutos) throw errorProdutos;

  const produtos = resultadoPaginado.itens;

  return (
    <table className="table table-responsive table-sm table-hover table-bordered">
      <thead>
        <tr>
          <th className="align-middle text-center" onClick={() => ordenarPor('id')}>
            Id {iconOrdenacao('id')}
          </th>
          <th className="align-middle text-center" onClick={() => ordenarPor('categoria')}>
            Categoria {iconOrdenacao('categoria')}
          </th>
          <th className="align-middle text-center" onClick={() => ordenarPor('nome')}>
            Nome {iconOrdenacao('nome')}
          </th>
          <th className="align-middle text-center" onClick={() => ordenarPor('dataCadastro')}>
            Data de Cadastro {iconOrdenacao('dataCadastro')}
          </th>
          <th className="align-middle text-center"></th>
        </tr>
      </thead>
      <tbody>
        {produtos.map((produto) => (
          <tr key={produto.id}>
            <td width="3%" className="align-middle text-center">{produto.id}</td>
            <td width="6%" className="align-middle text-center">{produto.categoria.nome}</td>
            <td width="30%" className="align-middle">
              <Link to={`/${produto.id}`} className="link-sem-underline">{produto.nome}</Link>
            </td>
            <td width="8%" className="align-middle text-center">{dayjs(produto.dataCadastro).format("DD/MM/YYYY")}</td>
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
                  <li>
                    <button onClick={() => tratarRemocao(produto.id!)} className="dropdown-item">Remover</button>
                  </li>
                  <li>
                    <Link to={`/${produto.id}/edit`} className="link-sem-underline dropdown-item">Editar</Link>
                  </li>
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
