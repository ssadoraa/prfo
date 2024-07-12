import { createContext, useState, ReactNode } from 'react';
import Produto from '../interfaces/produto';

interface CarrinhoContextType {
  carrinho: Produto[];
  adicionarAoCarrinho: (produto: Produto) => void;
  removerDoCarrinho: (produtoId: number) => void;
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

  const removerDoCarrinho = (produtoId: number) => {
    setCarrinho((prevCarrinho) => prevCarrinho.filter(produto => produto.id !== produtoId));
  };

  return (
    <CarrinhoContext.Provider value={{ carrinho, adicionarAoCarrinho, removerDoCarrinho }}>
      {children}
    </CarrinhoContext.Provider>
  );
};
