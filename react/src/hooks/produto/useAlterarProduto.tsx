import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";
import Produto from "../../interfaces/produto";
import { URL_PRODUTO } from "../../util/constants";
import useAPI from "../useAPI";

const useAlterarProduto = () => {
  const { alterar } = useAPI<Produto>(URL_PRODUTO);
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  const mutation = useMutation({
    mutationFn: (produto: Produto) => alterar(produto),
    onSuccess: (data: Produto) => {
      queryClient.invalidateQueries({
        queryKey: ["produtos"],
      });
      navigate(`/${data.id}`);
    },
  });

  return mutation;
};

export default useAlterarProduto;
