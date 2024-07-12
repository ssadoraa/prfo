import Categoria from "./categoria";

interface Produto {
  id?: number;
  imagem: string;
  categoria: Categoria;
  nome: string;
  descricao: string;
  condicao: string;
  dataCadastro: Date;
  valorEstimado: number;
  usuarioId: number;
}
export default Produto;
