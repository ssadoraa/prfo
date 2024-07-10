import { useMutation, useQueryClient } from "@tanstack/react-query";
import { URL_CATEGORIA } from "../../util/constants";
import useAPI from "../useAPI";
import Categoria from "../../interfaces/categoria";

const useRemoverCategoria = () => {
  const {remover} = useAPI<Categoria>(URL_CATEGORIA);

  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (id: number) => remover(id),
    onSuccess: () =>
      queryClient.invalidateQueries({
        queryKey: ["categorias"],
      }),
  });
};
export default useRemoverCategoria;
