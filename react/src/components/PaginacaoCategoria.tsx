import useCategoriaComPaginacao from "../hooks/categoria/useCategoriasComPaginacao";
import useCategoriaStore from "../store/categoriaStore";

const Paginacao = () => {
  const pagina = useCategoriaStore((s) => s.pagina);
  const tamanho = useCategoriaStore((s) => s.tamanho);
  const nome = useCategoriaStore((s) => s.nome);
  const setPagina = useCategoriaStore((s) => s.setPagina);

  const tratarPaginacao = (pagina: number) => {
    setPagina(pagina);
  };

  const {
    data: resultadoPaginado,
    isPending: carregandoCategorias,
    error: errorCategorias,
  } = useCategoriaComPaginacao({ pagina, tamanho, nome });

  if (carregandoCategorias) return <h6>Carregando...</h6>;
  if (errorCategorias) throw errorCategorias;

  const totalDePaginas = resultadoPaginado.totalDePaginas;

  const arrayDePaginas = [];

  for (let i = 0; i < totalDePaginas; i++) {
    arrayDePaginas.push(
      <li key={i} className="page-item">
        <a
          onClick={() => tratarPaginacao(i)}
          className={pagina === i ? "page-link active" : "page-link"}
          href="#"
        >
          {i + 1}
        </a>
      </li>
    );
  }

  if (totalDePaginas < 2) return null;

  return (
    <nav aria-label="Paginação">
      <ul className="pagination">
        <li className={pagina === 0 ? "page-item disabled" : "page-item"}>
          <a onClick={() => tratarPaginacao(pagina - 1)} className="page-link">
            Anterior
          </a>
        </li>
        {arrayDePaginas}
        <li className={pagina === totalDePaginas - 1 ? "page-item disabled" : "page-item"}>
          <a onClick={() => tratarPaginacao(pagina + 1)} className="page-link">
            Próxima
          </a>
        </li>
      </ul>
    </nav>
  );
};
export default Paginacao;
