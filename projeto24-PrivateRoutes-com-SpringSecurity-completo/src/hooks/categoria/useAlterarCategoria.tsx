import { useMutation, useQueryClient } from "@tanstack/react-query";
import { URL_CATEGORIA } from "../../util/constants";
import useAPI from "../useAPI";
import Categoria from "../../interfaces/categoria";

const useAlterarCategoria = () => {
  const { alterar } = useAPI<Categoria>(URL_CATEGORIA);

  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (categoria: Categoria) => alterar(categoria),
    onSuccess: () =>
      queryClient.invalidateQueries({
        queryKey: ["categorias"],
      }),
  });
};
export default useAlterarCategoria;
