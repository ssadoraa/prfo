import { useForm } from "react-hook-form";
import Produto from "../interfaces/produto";
import Categoria from "../interfaces/categoria";
import useCadastrarProduto from "../hooks/produto/useCadastrarProduto";
import { useEffect } from "react";
import { z } from "zod";
import dataValida from "../util/dataValida";
import { zodResolver } from "@hookform/resolvers/zod";
import useProdutoStore from "../store/produtoStore";
import dayjs from "dayjs";
import useAlterarProduto from "../hooks/produto/useAlterarProduto";
import CustomError from "../util/CustomError";
import { Link, useNavigate } from "react-router-dom";
import useCategorias from "../hooks/categoria/useCategorias";
import useUsuarioStore from "../store/usuarioStore";

const categoriaValida = (categoria: string) => {
  return categoria !== "0";
};

const regexData = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;
const regexImagem = /^[a-z]+\.(gif|jpg|png|bmp)$/;
const schema = z.object({
  nome: z
    .string()
    .min(1, { message: "O nome deve ser informado." })
    .min(3, { message: "O nome deve ter pelo menos 3 caracteres." }),
  descricao: z.string().min(1, { message: "A descrição deve ser informada." }),
  categoria: z
    .string()
    .refine(categoriaValida, { message: "A categoria deve ser informada." }),
  data_cadastro: z
    .string()
    .min(1, { message: "A data de cadastro deve ser informada." })
    .regex(regexData, { message: "Data inválida." })
    .refine(dataValida, { message: "Data inválida." }),
  valorEstimado: z
    .number({ invalid_type_error: "O preço deve ser informado." })
    .min(0.1, { message: "O preço deve ser maior ou igual a R$ 0.10" }),
  imagem: z
    .string()
    .min(1, { message: "A imagem deve ser informada." })
    .regex(regexImagem, { message: "Nome de imagem inválido." }),
  condicao: z.string().min(1, { message: "A condição deve ser informada." }),
  usuarioId: z.number(),
});

const CadastroDeProdutosForm = () => {
  const navigate = useNavigate();

  const produtoSelecionado = useProdutoStore((s) => s.produtoSelecionado);
  const setProdutoSelecionado = useProdutoStore((s) => s.setProdutoSelecionado);
  const usuarioId = useUsuarioStore((s) => s.usuarioId);

  const { data: categorias = [] } = useCategorias();

  type FormProduto = z.infer<typeof schema>;

  const {
    register,
    handleSubmit,
    reset,
    setFocus,
    setValue,
    formState: { isSubmitSuccessful, errors },
  } = useForm<FormProduto>({ resolver: zodResolver(schema) });

  useEffect(() => {
    setFocus("nome");
    reset();
    setProdutoSelecionado({} as Produto);
    setValue("data_cadastro", dayjs().format("DD/MM/YYYY"));
  }, [isSubmitSuccessful]);

  useEffect(() => {
    setFocus("nome");
    reset();
    if (produtoSelecionado.id) {
      setValue("nome", produtoSelecionado.nome);
      setValue("descricao", produtoSelecionado.descricao);
      setValue("categoria", String(produtoSelecionado.categoria.id));
      setValue(
        "data_cadastro",
        dayjs(produtoSelecionado.dataCadastro).format("DD/MM/YYYY")
      );
      setValue("valorEstimado", produtoSelecionado.valorEstimado);
      setValue("imagem", produtoSelecionado.imagem);
      setValue("condicao", produtoSelecionado.condicao);
    } else {
      setValue("data_cadastro", dayjs().format("DD/MM/YYYY"));
    }
  }, [produtoSelecionado]);

  const { mutate: cadastrarProduto, error: errorCadastrarProduto } =
    useCadastrarProduto();
  const { mutate: alterarProduto, error: errorAlterarProduto } =
    useAlterarProduto();

  const onSubmit = ({
    nome,
    descricao,
    categoria,
    data_cadastro,
    valorEstimado,
    imagem,
    condicao,
  }: FormProduto) => {
    const produto: Produto = {
      nome,
      descricao,
      imagem,
      categoria: { id: parseInt(categoria) } as Categoria,
      condicao,
      dataCadastro: new Date(
        data_cadastro.substring(6, 10) +
          "-" +
          data_cadastro.substring(3, 5) +
          "-" +
          data_cadastro.substring(0, 2)
      ),
      valorEstimado,
      usuarioId,
    };
    if (produtoSelecionado.id) {
      produto.id = produtoSelecionado.id;
      alterarProduto(produto);
    } else {
      cadastrarProduto(produto, {
        onError: (error) => {
          if (error instanceof CustomError && error.errorCode === 401) {
            navigate("/login", { state: { from: "/produtos" } });
          }
        },
      });
    }
  };

  if (errorCadastrarProduto) throw errorCadastrarProduto;
  if (errorAlterarProduto) throw errorAlterarProduto;

  return (
    <form onSubmit={handleSubmit(onSubmit)} autoComplete="Off">
      <input
        type="hidden"
        {...register("usuarioId", { valueAsNumber: true })}
        value={usuarioId}
      />
      <div className="mb-3">
        <label htmlFor="nome" className="fw-bold">
          Nome
        </label>
        <input
          {...register("nome")}
          type="text"
          id="nome"
          placeholder="Digite o nome do produto"
          className={
            errors.nome
              ? "form-control form-control-sm is-invalid"
              : "form-control form-control-sm"
          }
        />
        <div className="invalid-feedback">{errors.nome?.message}</div>
      </div>

      <div className="mb-3">
        <label htmlFor="descricao" className="fw-bold">
          Descrição
        </label>
        <textarea
          {...register("descricao")}
          id="descricao"
          placeholder="Digite uma descrição do produto"
          className={
            errors.descricao
              ? "form-control form-control-sm is-invalid"
              : "form-control form-control-sm"
          }
        />
        <div className="invalid-feedback">{errors.descricao?.message}</div>
      </div>

      <div className="mb-3">
        <label htmlFor="categoria" className="fw-bold">
          Categoria
        </label>
        <select
          {...register("categoria")}
          id="categoria"
          className={
            errors.categoria
              ? "form-control form-control-sm is-invalid"
              : "form-control form-control-sm"
          }
        >
          <option value="0">Selecione uma categoria</option>
          {categorias.map((categoria) => (
            <option key={categoria.id} value={categoria.id}>
              {categoria.nome}
            </option>
          ))}
        </select>
        <div className="invalid-feedback">{errors.categoria?.message}</div>
      </div>

      <div className="mb-3">
        <label htmlFor="valorEstimado" className="fw-bold">
          Valor Estimado
        </label>
        <input
          {...register("valorEstimado", { valueAsNumber: true })}
          type="number"
          id="valorEstimado"
          placeholder="Digite o valor estimado"
          className={
            errors.valorEstimado
              ? "form-control form-control-sm is-invalid"
              : "form-control form-control-sm"
          }
        />
        <div className="invalid-feedback">{errors.valorEstimado?.message}</div>
      </div>

      <div className="mb-3">
        <label className="fw-bold">Condição</label>
        <div className="d-flex">
          <div className="form-check me-3">
            <input
              {...register("condicao")}
              type="radio"
              id="novo"
              value="novo"
              className="form-check-input"
            />
            <label htmlFor="novo" className="form-check-label">
              Novo
            </label>
          </div>
          <div className="form-check me-3">
            <input
              {...register("condicao")}
              type="radio"
              id="usado"
              value="usado"
              className="form-check-input"
            />
            <label htmlFor="usado" className="form-check-label">
              Usado
            </label>
          </div>
          <div className="form-check">
            <input
              {...register("condicao")}
              type="radio"
              id="sem_utilidade"
              value="sem utilidade"
              className="form-check-input"
            />
            <label htmlFor="sem_utilidade" className="form-check-label">
              Sem Utilidade
            </label>
          </div>
        </div>
        <div className="invalid-feedback">{errors.condicao?.message}</div>
      </div>

      <div className="mb-3">
        <label htmlFor="imagem" className="fw-bold">
          Imagem
        </label>
        <input
          {...register("imagem")}
          type="text"
          id="imagem"
          placeholder="Digite o nome da imagem"
          className={
            errors.imagem
              ? "form-control form-control-sm is-invalid"
              : "form-control form-control-sm"
          }
        />
        <div className="invalid-feedback">{errors.imagem?.message}</div>
      </div>

      <div className="mb-3">
        <label htmlFor="data_cadastro" className="fw-bold">
          Data Cadastro
        </label>
        <input
          {...register("data_cadastro")}
          type="text"
          id="data_cadastro"
          placeholder="DD/MM/YYYY"
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
        <button type="submit" className="btn btn-success me-2">
          {produtoSelecionado.id ? "Salvar produto" : "Cadastrar produto"}
        </button>
        <Link to={"/meus_produtos"} className="btn btn-outline-secondary">
          Cancelar
        </Link>
      </div>
    </form>
  );
};

export default CadastroDeProdutosForm;
