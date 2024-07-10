import { useMutation, useQueryClient } from "@tanstack/react-query";
import { URL_CATEGORIA } from "../../util/constants";
import useAPI from "../useAPI";
import Categoria from "../../interfaces/categoria";

const useCadastrarCategoria = () => {
  const { cadastrar } = useAPI<Categoria>(URL_CATEGORIA);

  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (categoria: Categoria) => cadastrar(categoria),
    onSuccess: () =>
      queryClient.invalidateQueries({
        queryKey: ["categorias"],
      }),
  });
};
export default useCadastrarCategoria;
