import { useForm } from "react-hook-form";
import databaseAdd from "../assets/skin/database_add.png";
import databaseEdit from "../assets/skin/database_edit.png";
import databaseCancel from "../assets/skin/multiply.png";
import Categoria from "../interfaces/categoria";
import useCadastrarCategoria from "../hooks/categoria/useCadastrarCategoria";
import { useEffect } from "react";
import { z } from "zod";
import dataValida from "../util/dataValida";
import { zodResolver } from "@hookform/resolvers/zod";
import useCategoriaStore from "../store/categoriaStore";
import dayjs from "dayjs";
import useAlterarCategoria from "../hooks/categoria/useAlterarCategoria";
import CustomError from "../util/CustomError";
import { useNavigate } from "react-router-dom";

const schema = z.object({
  nome: z
    .string()
    .min(1, { message: "O nome deve ser informado." })
    .min(3, { message: "O nome deve ter pelo menos 3 caracteres." }),
  data_cadastro: z
    .string()
    .min(1, { message: "A data de cadastro deve ser informada." })
    .refine(dataValida, { message: "Data inválida." }),
});

const CadastroDeCategoriasForm = () => {
  const navigate = useNavigate();

  const categoriaSelecionado = useCategoriaStore((s) => s.categoriaSelecionada);
  const setCategoriaSelecionado = useCategoriaStore(
    (s) => s.setCategoriaSelecionada
  );

  type FormCategoria = z.infer<typeof schema>;

  const {
    register,
    handleSubmit,
    reset,
    setFocus,
    setValue,
    formState: { isSubmitSuccessful, errors },
  } = useForm<FormCategoria>({ resolver: zodResolver(schema) });

  useEffect(() => {
    setFocus("nome");
    reset();
    setCategoriaSelecionado({} as Categoria);
    setValue("data_cadastro", dayjs().format("DD/MM/YYYY"));
  }, [isSubmitSuccessful]);

  useEffect(() => {
    setFocus("nome");
    reset();
    if (categoriaSelecionado.id) {
      setValue("nome", categoriaSelecionado.nome);
      setValue(
        "data_cadastro",
        dayjs(categoriaSelecionado.dataCadastro).format("DD/MM/YYYY")
      );
    } else {
      setValue("data_cadastro", dayjs().format("DD/MM/YYYY"));
    }
  }, [categoriaSelecionado]);

  const { mutate: cadastrarCategoria, error: errorCadastrarCategoria } =
    useCadastrarCategoria();
  const { mutate: alterarCategoria, error: errorAlterarCategoria } =
    useAlterarCategoria();

  const onSubmit = ({ nome, data_cadastro }: FormCategoria) => {
    const categoria: Categoria = {
      nome: nome,
      dataCadastro: new Date(
        data_cadastro.substring(6, 10) +
          "-" +
          data_cadastro.substring(3, 5) +
          "-" +
          data_cadastro.substring(0, 2)
      ),
    };
    if (categoriaSelecionado.id) {
      categoria.id = categoriaSelecionado.id;
      alterarCategoria(categoria);
    } else {
      cadastrarCategoria(categoria, {
        onError: (error) => {
          if (error instanceof CustomError && error.errorCode === 401) {
            navigate("/login", { state: { from: "/listar-categorias" } });
          }
        },
      });
    }
  };

  if (errorCadastrarCategoria) throw errorCadastrarCategoria;
  if (errorAlterarCategoria) throw errorAlterarCategoria;

  return (
    <form onSubmit={handleSubmit(onSubmit)} autoComplete="Off">
      <div className="mb-3">
        <label htmlFor="nome" className="fw-bold">
          Nome
        </label>
        <input
          {...register("nome")}
          type="text"
          id="nome"
          className={
            errors.nome
              ? "form-control form-control-sm is-invalid"
              : "form-control form-control-sm"
          }
        />
        <div className="invalid-feedback">{errors.nome?.message}</div>
      </div>

      <div className="mb-3">
        <label htmlFor="data_cadastro" className="fw-bold">
          Data Cadastro
        </label>
        <input
          {...register("data_cadastro")}
          type="text"
          id="data_cadastro"
          className={
            errors.data_cadastro
              ? "form-control form-control-sm is-invalid"
              : "form-control form-control-sm"
          }
          readOnly
        />
        <div className="invalid-feedback">{errors.data_cadastro?.message}</div>
      </div>

      <div className="d-flex justify-content-end mt-2">
        <button type="submit" className="btn btn-outline-primary btn-sm me-2">
          <img
            src={categoriaSelecionado.id ? databaseEdit : databaseAdd}
            alt={
              categoriaSelecionado.id
                ? "Alterar categoria"
                : "Cadastrar categoria"
            }
            width="15"
            height="15"
            className="me-1"
          />
          {categoriaSelecionado.id
            ? "Alterar categoria"
            : "Cadastrar categoria"}
        </button>
        <button className="btn btn-outline-secondary btn-sm">
          <img
            src={databaseCancel}
            alt="Cancelar"
            width="15"
            height="15"
            className="me-1"
          />
          Cancelar
        </button>
      </div>
    </form>
  );
};

export default CadastroDeCategoriasForm;
