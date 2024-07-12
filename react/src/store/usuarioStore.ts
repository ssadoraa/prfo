import { create } from "zustand";

interface UsuarioStore {
  usuarioId: number;
  usuarioLogado: string;
  tentouLogar: boolean;

  setUsuarioId: (id: number) => void;
  setUsuarioLogado: (usuario: string) => void;
  setTentouLogar: (valor: boolean) => void;
}

const useUsuarioStore = create<UsuarioStore>((set) => ({
  usuarioId: 0,
  usuarioLogado: "",
  tentouLogar: false,

  setUsuarioId: (id: number) => set(() => ({ usuarioId: id })),
  setUsuarioLogado: (usuario: string) => set(() => ({ usuarioLogado: usuario })),
  setTentouLogar: (valor: boolean) => set(() => ({ tentouLogar: valor })),
}));
export default useUsuarioStore;
