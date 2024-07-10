import { useEffect } from "react";
import { useParams } from "react-router-dom";
import useProdutoStore from "../../store/produtoStore";
import CadastroDeProdutosForm from "../../components/CadastroDeProdutosForm";
import "../../css/geral.css";
import useAPI from "../../hooks/useAPI";
import Produto from "../../interfaces/produto";

const CadastroDeProdutosPage = () => {
  const { id } = useParams();
  const { setProdutoSelecionado } = useProdutoStore();
  const { recuperarById } = useAPI<Produto>("produtos");

  useEffect(() => {
    const loadProduto = async () => {
      if (id) {
        try {
          const produto: Produto = await recuperarById(Number(id));
          setProdutoSelecionado(produto);
        } catch (error) {
          console.error("Erro ao carregar produto:", error);
        }
      }
    };
    loadProduto();
  }, [id, recuperarById, setProdutoSelecionado]);

  return (
    <>
      <h4 className="mt-4">Cadastro de Produtos</h4>
      <hr className="mt-1 mb-4" />
      <CadastroDeProdutosForm />
    </>
  );
};

export default CadastroDeProdutosPage;
