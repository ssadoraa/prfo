
const CadastroDeUsuariosForm = () => {

  return (
    <form autoComplete="Off">
      <div className="mb-3">
        <label htmlFor="username" className="fw-bold">
          Nome de Usuário
        </label>
        <input
          type="text"
          id="username"
          className={"form-control form-control-sm"}
        />
      </div>

      <div className="mb-3">
        <label htmlFor="password" className="fw-bold">
          Senha
        </label>
        <input
          type="password"
          id="password"
          className={"form-control form-control-sm"
          }
        />
      </div>

      <div className="d-flex justify-content-end mt-2">
        <button type="submit" className="btn btn-success me-2">
          Cadastrar usuário
        </button>
        <button className="btn btn-outline-secondary">
          Cancelar
        </button>
      </div>
    </form>
  );
};

export default CadastroDeUsuariosForm;
