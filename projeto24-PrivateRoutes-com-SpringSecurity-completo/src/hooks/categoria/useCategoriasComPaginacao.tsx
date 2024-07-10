import { useQuery } from "@tanstack/react-query";
import { URL_CATEGORIA } from "../../util/constants";
import useAPI from "../useAPI";
import Categoria from "../../interfaces/categoria";

interface QueryString {
  pagina: number;
  tamanho: number;
  nome: string;
}

const useCategoriasComPaginacao = (query: QueryString) => {
  const { recuperarPagina } = useAPI<Categoria>(URL_CATEGORIA);

  return useQuery({
    queryKey: ["categorias", "paginacao", query],
    queryFn: () =>
      recuperarPagina({
        params: {
          ...query,
        },
      }),
    staleTime: 10_000,
  });
};
export default useCategoriasComPaginacao;
