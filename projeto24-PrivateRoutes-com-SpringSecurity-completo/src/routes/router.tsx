import { createBrowserRouter } from 'react-router-dom';
import CarrinhoPage from '../pages/CarrinhoPage';
import HomePage from '../pages/HomePage';
import LoginPage from '../pages/LoginPage';
import Layout from './Layout';
import CadastroDeProdutosPage from '../pages/CadastroDeProdutosPage';
import ListaDeProdutosPage from '../pages/ListaDeProdutosPage';
import ErrorPage from '../pages/ErrorPage';
import CardsDeProdutosPage from '../pages/CardsDeProdutosPage';
import PrivateRoutes from './PrivateRoutes';
import SobrePage from '../pages/SobrePage';
import SuportePage from '../pages/SuportePage';
import CadastroDeCategoriasPage from '../pages/CadastroDeCategoriasPage';

const router = createBrowserRouter([
    {
      path: "/",
      element: <Layout />,
      errorElement: <ErrorPage />,
      children: [
        {
          path: "",
          element: <HomePage />,
          children: [
            {
              path: ":slug?",
              element: <CardsDeProdutosPage />,
            },
          ],
        },
        { path: "listar-produtos", element: <ListaDeProdutosPage />},
        { path: "sobre", element: <SobrePage />},
        { path: "suporte", element: <SuportePage />},
        { path: "login", element: <LoginPage /> },
      ],
    },
    {
      path: "/",
      element: <PrivateRoutes />,
      errorElement: <ErrorPage />,
      children: [
        { path: "cadastrar-produto", element: <CadastroDeProdutosPage /> },
        { path: "cadastrar-categoria", element: <CadastroDeCategoriasPage /> },
        { path: "carrinho", element: <CarrinhoPage /> },
      ],
    },
  ]);
  export default router;
  