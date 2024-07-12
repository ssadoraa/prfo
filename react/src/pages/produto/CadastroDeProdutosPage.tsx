import { useEffect } from "react";
import { useParams } from "react-router-dom";
import useProdutoStore from "../../store/produtoStore";
import CadastroDeProdutosForm from "../../components/CadastroDeProdutosForm";
import "../../css/geral.css";
import useAPI from "../../hooks/useAPI";
import Produto from "../../interfaces/produto";
import { URL_PRODUTO } from "../../util/constants";

const CadastroDeProdutosPage = () => {
  const { id } = useParams();
  const setProdutoSelecionado = useProdutoStore((s) => s.setProdutoSelecionado);
  const { recuperarById } = useAPI<Produto>(URL_PRODUTO);

  useEffect(() => {
    if (id) {
      recuperarById(Number(id))
        .then(produto => {
          setProdutoSelecionado(produto);
        })
        .catch(error => {
          console.error("Erro ao obter o produto:", error);
        });
    } else {
      setProdutoSelecionado({} as Produto);
    }
  }, [id, setProdutoSelecionado, recuperarById]);

  return (
    <>
      <h4 className="mt-4">{id ? "Alterar Produto" : "Cadastrar Produto"}</h4>
      <hr className="mt-1 mb-4" />
      <CadastroDeProdutosForm />
    </>
  );
};

export default CadastroDeProdutosPage;
