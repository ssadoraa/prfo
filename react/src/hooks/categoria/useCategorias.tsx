import { useQuery } from "@tanstack/react-query";
import useAPI from "../useAPI";
import { URL_CATEGORIA } from "../../util/constants";
import Categoria from "../../interfaces/categoria";

const useCategorias = () => {
  const {recuperar} = useAPI<Categoria>(URL_CATEGORIA);
  return useQuery({
    queryKey: ["categorias"],
    queryFn: () => recuperar(),
    staleTime: 10_000,
  })
};
export default useCategorias;
