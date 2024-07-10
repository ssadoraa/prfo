import { create } from "zustand";
import Categoria from "../interfaces/categoria";

interface CategoriaStore {
    pagina: number;
    tamanho: number;
    nome: string;
    categoriaSelecionada: Categoria;

    setPagina: (pagina: number) => void;
    setTamanho: (tamanho: number) => void;
    setNome: (nome: string) => void;
    setCategoriaSelecionada: (categoriaSelecionada: Categoria) => void;
}

const useCategoriaStore = create<CategoriaStore>((set) => ({
    pagina: 0,
    tamanho: 5,
    nome: "",
    categoriaSelecionada: {} as Categoria,

    setPagina: (pagina: number) => set(() => ({pagina: pagina})),
    setTamanho: (tamanho: number) => set(() => ({tamanho: tamanho})),
    setNome: (nome: string) => set(() => ({nome: nome})),
    setCategoriaSelecionada: (categoriaSelecionada: Categoria) => set(() => ({categoriaSelecionada: categoriaSelecionada}))
})) 
export default useCategoriaStore;