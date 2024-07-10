import { isRouteErrorResponse, useRouteError } from "react-router-dom";
import NavBar from "../components/NavBar";
import CustomError from "../util/CustomError";
import "../css/geral.css";

const ErrorPage = () => {
  const error = useRouteError();

  return (
    <>
      <NavBar />
      <div className="container mt-3 error">
        {isRouteErrorResponse(error)
          ? "Página requisitada inválida."
          : error instanceof CustomError && error.errorCode === 422
          ? error.msgs?.join("\n")
          : error instanceof CustomError &&
            (error.errorCode === 401 || error.errorCode === 403)
          ? error.message
          : error instanceof Error
          ? error.message
          : "Erro desconhecido."}
      </div>
    </>
  );
};
export default ErrorPage;
