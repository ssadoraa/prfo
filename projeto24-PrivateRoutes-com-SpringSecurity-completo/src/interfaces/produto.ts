import Categoria from "./categoria";

interface Produto {
  id?: number;
  imagem: string;
  categoria: Categoria;
  nome: string;
  descricao: string;
  condicao: string;
  dataCadastro: Date;
  status: string;
  valorEstimado: number;
}
export default Produto;
