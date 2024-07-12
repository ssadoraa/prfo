import { useEffect } from "react";
import useVerProduto from "../../hooks/produto/useVerProduto";
import { useParams } from "react-router-dom";
import "../../css/geral.css";

function VerProdutoPage() {
  const { id } = useParams<{ id: string }>();
  const { mutate, data, isError } = useVerProduto();

  useEffect(() => {
    if (id) {
      mutate(parseInt(id));
    }
  }, [id, mutate]);

  if (isError)
    return (<div className="container mt-5">Ocorreu um erro ao carregar o produto.</div>);

  if (!data)
    return (<div className="container mt-5">Dados do produto não encontrados.</div>);

  return (
    <div className="container mt-4">
      <h3>Detalhes do Produto</h3>
      <hr className="mt-1 mb-4" />
      <div className="row mt-4">
        <div className="col-md-5 me-5 mb-4">
          <h4>Imagens</h4>
          <ul className="nav nav-tabs" id="imagensTab" role="tablist">
            <li className="nav-item" role="presentation">
              <button
                className="nav-link active"
                id="imagem1-tab"
                data-bs-toggle="tab"
                data-bs-target="#imagem1"
                type="button"
                role="tab"
                aria-controls="imagem1"
                aria-selected="true"
              >
                Imagem 1
              </button>
            </li>
            <li className="nav-item" role="presentation">
              <button
                className="nav-link"
                id="imagem2-tab"
                data-bs-toggle="tab"
                data-bs-target="#imagem2"
                type="button"
                role="tab"
                aria-controls="imagem2"
                aria-selected="false"
              >
                Imagem 2
              </button>
            </li>
            <li className="nav-item" role="presentation">
              <button
                className="nav-link"
                id="imagem3-tab"
                data-bs-toggle="tab"
                data-bs-target="#imagem3"
                type="button"
                role="tab"
                aria-controls="imagem3"
                aria-selected="false"
              >
                Imagem 3
              </button>
            </li>
          </ul>
          <div className="tab-content" id="imagensTabContent">
            <div
              className="tab-pane fade show active"
              id="imagem1"
              role="tabpanel"
              aria-labelledby="imagem1-tab"
            >
              <img
                src={data.imagem}
                className="img-fluid mb-2"
                alt="Imagem 1"
              />
            </div>
            <div
              className="tab-pane fade"
              id="imagem2"
              role="tabpanel"
              aria-labelledby="imagem2-tab"
            >
              <img
                src="caminho_da_imagem2.jpg"
                className="img-fluid mb-2"
                alt="Imagem 2"
              />
            </div>
            <div
              className="tab-pane fade"
              id="imagem3"
              role="tabpanel"
              aria-labelledby="imagem3-tab"
            >
              <img
                src="caminho_da_imagem3.jpg"
                className="img-fluid mb-2"
                alt="Imagem 3"
              />
            </div>
          </div>
        </div>

        <div className="col-md-6">
          <h4>Informações do Produto</h4>
          <div className="row mb-3">
            <label htmlFor="nome" className="col-sm-4 col-form-label">
              Nome:
            </label>
            <div className="col-sm-8 maq">{data.nome}</div>
          </div>

          <div className="row mb-3">
            <label htmlFor="categoria" className="col-sm-4 col-form-label">
              Categoria:
            </label>
            <div className="col-sm-8 maq">{data.categoria.nome}</div>
          </div>

          <div className="row mb-3">
            <label htmlFor="condicao" className="col-sm-4 col-form-label">
              Condição:
            </label>
            <div className="col-sm-8 maq">{data.condicao}</div>
          </div>

          <div className="row mb-3">
            <label htmlFor="valor" className="col-sm-4 col-form-label">
              Valor Estimado:
            </label>
            <div className="col-sm-8 maq">R$: {data.valorEstimado}</div>
          </div>

          <div className="row mb-3">
            <label htmlFor="status" className="col-sm-4 col-form-label">
              Status:
            </label>
            <div className="col-sm-8 maq">{data.status}</div>
          </div>
        </div>

        <div className="mb-5">
          <label htmlFor="descricao" className="col-form-label">
            Descrição:
          </label>
          <div className="maq">{data.descricao}</div>
        </div>
      </div>
    </div>
  );
}

export default VerProdutoPage;
