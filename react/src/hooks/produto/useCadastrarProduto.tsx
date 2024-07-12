import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";
import Produto from "../../interfaces/produto";
import { URL_PRODUTO } from "../../util/constants";
import useAPI from "../useAPI";

const useCadastrarProduto = () => {
  const { cadastrar } = useAPI<Produto>(URL_PRODUTO);
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  const mutation = useMutation({
    mutationFn: (produto: Produto) => cadastrar(produto),
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ["produtos"],
      });
      navigate("/meus_produtos");
    },
  });

  return mutation;
};

export default useCadastrarProduto;
