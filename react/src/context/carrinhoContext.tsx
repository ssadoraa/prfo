import { createContext, useState, ReactNode } from 'react';
import Produto from '../interfaces/produto';


interface CarrinhoContextType {
  carrinho: Produto[];
  adicionarAoCarrinho: (produto: Produto) => void;
}

export const CarrinhoContext = createContext<CarrinhoContextType | undefined>(undefined);

interface CarrinhoProviderProps {
  children: ReactNode;
}

export const CarrinhoProvider = ({ children }: CarrinhoProviderProps) => {
  const [carrinho, setCarrinho] = useState<Produto[]>([]);

  const adicionarAoCarrinho = (produto: Produto) => {
    setCarrinho((prevCarrinho) => [...prevCarrinho, produto]);
  };

  return (
    <CarrinhoContext.Provider value={{ carrinho, adicionarAoCarrinho }}>
      {children}
    </CarrinhoContext.Provider>
  );
};
