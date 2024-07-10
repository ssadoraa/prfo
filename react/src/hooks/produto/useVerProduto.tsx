import { useMutation, useQueryClient } from "@tanstack/react-query";
import Produto from "../../interfaces/produto";
import { URL_PRODUTO } from "../../util/constants";
import useAPI from "../useAPI";

const useVerProduto = () => {
  const { recuperarById } = useAPI<Produto>(URL_PRODUTO);

  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (id: number) => recuperarById(id),
    onSuccess: () =>
      queryClient.invalidateQueries({
        queryKey: ["produtos"],
      }),
  });
};
export default useVerProduto;
