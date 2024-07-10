import axios, { AxiosRequestConfig } from "axios";
import { URL_BASE } from "../util/constants";
import CustomError from "../util/CustomError";
import TokenClass from "../util/TokenClass";

const useAPI = <T>(endpoint: string) => {
  const axiosInstance = axios.create({
    baseURL: URL_BASE,
  });

  const recuperar = () =>
    axiosInstance
      .get<T[]>(endpoint)
      .then((response) => response.data)
      .catch((error) => {
        if (error.response) {
          throw new CustomError(
            error.response.data.message,
            error.response.data.errorCode
          );
          // significa servidor respondeu
        } else if (error.request) {
          throw error;
          // significa que o servidor não respondeu
        } else {
          throw error;
          // erro desconhecido
        }
      });

  const remover = (id: number) => {
    const token: string = TokenClass.getToken(); // <====================================
    return axiosInstance
      .delete<T>(endpoint + "/" + id, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => response.data)
      .catch((error) => {
        if (error.response) {
          throw new CustomError(
            error.response.data.message,
            error.response.data.errorCode
          );
          // significa servidor respondeu
        } else if (error.request) {
          throw error;
          // significa que o servidor não respondeu
        } else {
          throw error;
          // erro desconhecido
        }
      });
  };

  const recuperarPagina = (config: AxiosRequestConfig) =>
    axiosInstance
      .get<ResultadoPaginado<T>>(endpoint + "/paginacao", config)
      .then((response) => response.data)
      .catch((error) => {
        if (error.response) {
          throw new CustomError(
            error.response.data.message,
            error.response.data.errorCode
          );
          // significa servidor respondeu
        } else if (error.request) {
          throw error;
          // significa que o servidor não respondeu
        } else {
          throw error;
          // erro desconhecido
        }
      });

  const cadastrar = (obj: T) => {
    const token: string = TokenClass.getToken(); // <====================================
    return axiosInstance
      .post<T>(endpoint, obj, { headers: { Authorization: `Bearer ${token}` } })
      .then((response) => response.data)
      .catch((error) => {
        console.log(error.response);
        if (error.response) {
          // significa servidor respondeu
          if (error.response.data.errorCode === 422) {
            throw new CustomError(
              error.response.data.message,
              error.response.data.errorCode,
              Object.values(error.response.data.map)
            );
          } else if (error.response.status === 401) {
            throw new CustomError("Você não está autenticado.", 401);
          } else if (error.response.status === 403) {
            throw new CustomError(
              "Você nãao tem permissão para acessar este recurso.",
              403
            );
          } else {
            throw new CustomError(
              error.response.data.message,
              error.response.data.errorCode
            );
          }
        } else if (error.request) {
          throw error;
          // significa que o servidor não respondeu
        } else {
          throw error;
          // erro desconhecido
        }
      });
  };

  const alterar = (obj: T) => {
    const token: string = TokenClass.getToken(); // <====================================
    return axiosInstance
      .put<T>(endpoint, obj, { headers: { Authorization: `Bearer ${token}` } })
      .then((response) => response.data)
      .catch((error) => {
        console.log(error.response);
        if (error.response) {
          // significa servidor respondeu
          if (error.response.data.errorCode === 422) {
            throw new CustomError(
              error.response.data.message,
              error.response.data.errorCode,
              Object.values(error.response.data.map)
            );
          } else {
            throw new CustomError(
              error.response.data.message,
              error.response.data.errorCode
            );
          }
        } else if (error.request) {
          throw error;
          // significa que o servidor não respondeu
        } else {
          throw error;
          // erro desconhecido
        }
      });
  };

  return { recuperar, remover, recuperarPagina, cadastrar, alterar };
};
export default useAPI;
