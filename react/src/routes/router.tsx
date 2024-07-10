import { createBrowserRouter } from "react-router-dom";
import Layout from "./Layout";
import ErrorPage from "../pages/geral/error/ErrorPage";
import HomePage from "../pages/geral/public/HomePage";
import ListaTodosProdutosPage from "../pages/produto/ListaTodosProdutosPage";
import CardsDeProdutosPage from "../pages/produto/CardsDeProdutosPage";
import SobrePage from "../pages/geral/public/SobrePage";
import SuportePage from "../pages/geral/public/SuportePage";
import LoginPage from "../pages/geral/public/LoginPage";
import PrivateRoutes from "./PrivateRoutes";
import CadastroDeProdutosPage from "../pages/produto/CadastroDeProdutosPage";
import CadastroDeCategoriasPage from "../pages/categoria/CadastroDeCategoriasPage";
import ListaMeusProdutosPage from "../pages/produto/ListaMeusProdutosPage";
import CarrinhoPage from "../pages/geral/autenticate/CarrinhoPage";
import ListaCategoriasPage from "../pages/categoria/ListaCategoriasPage";
import VerProdutoPage from "../pages/produto/VerProdutoPage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    errorElement: <ErrorPage />,
    children: [
      { path: "", element: <HomePage /> },
      {
        path: "listar-produtos",
        element: <ListaTodosProdutosPage />,
        children: [
          {
            path: ":slug?",
            element: <CardsDeProdutosPage />,
          },
        ],
      },
      { path: "sobre", element: <SobrePage /> },
      { path: "suporte", element: <SuportePage /> },
      { path: ":id", element: <VerProdutoPage /> },
      { path: "login", element: <LoginPage /> },
    ],
  },
  {
    path: "/",
    element: <PrivateRoutes />,
    errorElement: <ErrorPage />,
    children: [
      { path: ":id/edit", element: <CadastroDeProdutosPage /> },
      { path: "cadastrar-produto", element: <CadastroDeProdutosPage /> },
      { path: "cadastrar-categoria", element: <CadastroDeCategoriasPage /> },
      { path: "listar-meus-produtos", element: <ListaMeusProdutosPage /> },
      { path: "listar-categoria", element: <ListaCategoriasPage /> },
      { path: "carrinho", element: <CarrinhoPage /> },
    ],
  },
]);
export default router;
