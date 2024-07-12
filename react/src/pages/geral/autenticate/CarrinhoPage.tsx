import "../../../css/geral.css";
import { useContext } from "react";
import { CarrinhoContext } from "../../../context/carrinhoContext";

const CarrinhoPage = () => {
  const carrinhoContext = useContext(CarrinhoContext);

  if (!carrinhoContext) {
    return <div>Erro ao carregar o carrinho.</div>;
  }

  const { carrinho } = carrinhoContext;

  return (
    <div className="container mt-4">
      <h2>Carrinho de Compras</h2>
      {carrinho.length === 0 ? (
        <p>O carrinho está vazio.</p>
      ) : (
        <table className="table table-responsive table-sm table-hover table-bordered">
          <thead>
            <tr>
              <th className="align-middle text-center">Nome</th>
              <th className="align-middle text-center">Condição</th>
              <th className="align-middle text-center">Valor Estimado</th>
            </tr>
          </thead>
          <tbody>
            {carrinho.map((produto) => (
              <tr key={produto.id}>
                <td className="align-middle text-center">{produto.nome}</td>
                <td className="align-middle text-center">{produto.condicao}</td>
                <td className="align-middle text-center">
                  {produto.valorEstimado.toLocaleString("pt-BR", {
                    minimumFractionDigits: 2,
                    maximumFractionDigits: 2,
                    useGrouping: true,
                  })}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default CarrinhoPage;
