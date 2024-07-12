import "../../../css/geral.css";
import "../../../css/modal.css";
import { useContext, useState } from "react";
import { CarrinhoContext } from "../../../context/carrinhoContext";
import Produto from "../../../interfaces/produto";
import { Link } from "react-router-dom";

const CarrinhoPage = () => {
  const carrinhoContext = useContext(CarrinhoContext);
  const [showModal, setShowModal] = useState(false);
  const [produtoSelecionado, setProdutoSelecionado] = useState<Produto | null>(
    null
  );

  if (!carrinhoContext) {
    return <div>Erro ao carregar o carrinho.</div>;
  }

  const { carrinho, removerDoCarrinho } = carrinhoContext;

  const handleShowModal = (produto: Produto) => {
    setProdutoSelecionado(produto);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setProdutoSelecionado(null);
  };

  return (
    <div className="container mt-4">
      <h4>Produtos Selecionados Para Negociar</h4>
      <hr className="mt-1 mb-4" />
      {carrinho.length === 0 ? (
        <p>A lista está vazia</p>
      ) : (
        <table className="table table-responsive table-sm table-hover table-bordered">
          <thead>
            <tr>
              <th className="align-middle text-center">Nome</th>
              <th className="align-middle text-center">Condição</th>
              <th className="align-middle text-center">Valor Estimado</th>
              <th className="align-middle text-center">Ações</th>
            </tr>
          </thead>
          <tbody>
            {carrinho.map((produto) => (
              <tr key={produto.id}>
                <td width="60%" className="align-middle text-center">
                  <Link to={`/${produto.id}`} className="link-sem-underline">
                    {produto.nome}
                  </Link>
                </td>
                <td className="align-middle text-center">{produto.condicao}</td>
                <td width="13%" className="align-middle text-center">
                  {produto.valorEstimado.toLocaleString("pt-BR", {
                    minimumFractionDigits: 2,
                    maximumFractionDigits: 2,
                    useGrouping: true,
                  })}
                </td>
                <td className="align-middle text-center">
                  <button
                    className="btn btn-sm btn-warning"
                    onClick={() => handleShowModal(produto)}
                  >
                    Negociar
                  </button>
                  <button
                    className="btn btn-danger btn-sm ms-2"
                    onClick={() => removerDoCarrinho(produto.id || -1)}
                  >
                    X
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {showModal && produtoSelecionado && (
        <div
          className="modal fade show overlay"
          style={{ display: "block" }}
          role="dialog"
        >
          <div className="modal-dialog" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Negociar Produto</h5>
              </div>
              <div className="modal-body">
                <p>Você está negociando o produto:</p>
                <p>
                  <strong>Nome:</strong> {produtoSelecionado.nome}
                </p>
                <p>
                  <strong>Condição:</strong> {produtoSelecionado.condicao}
                </p>
                <p>
                  <strong>Valor Estimado:</strong>{" "}
                  {produtoSelecionado.valorEstimado.toLocaleString("pt-BR", {
                    minimumFractionDigits: 2,
                    maximumFractionDigits: 2,
                    useGrouping: true,
                  })}
                </p>

                <textarea
                  className="form-control"
                  rows={5}
                  defaultValue={`Olá! Estou interessado no produto "${produtoSelecionado.nome}". Gostaria de saber se você está disponível para entrar em contato e discutir uma possível troca. Tenho algumas opções que podem ser interessantes para nós dois. Agradeço a atenção!`}
                />
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-secondary"
                  onClick={handleCloseModal}
                >
                  Fechar
                </button>
                <button
                  type="button"
                  className="btn btn-success"
                  onClick={handleCloseModal}
                >
                  Enviar
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default CarrinhoPage;
