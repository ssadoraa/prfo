import { ReactNode } from "react";
import { Link } from "react-router-dom";

interface Props {
  id?: number;
  imagem: string;
  titulo: string;
  descricao: string;
  valorEstimado: string;
  footer: ReactNode;
}

const Card = ({ id, imagem, titulo, descricao, valorEstimado, footer }: Props) => {
  return (
    <div className="card h-100 border-0">
      <Link to={`/${id}`} className="link-sem-underline dropdown-item">
        <img src={imagem} className="card-img-top" alt={titulo} />
      </Link>
      <div className="card-body">
        <h5 className="card-title">{titulo}</h5>
        <p className="card-text">{descricao}</p>
        <p className="card-text fw-bold" style={{ color: "rgb(220, 53, 69)" }}>
          R$ {valorEstimado}
        </p>
      </div>
      <div className="card-footer border-0 p-0">{footer}</div>
    </div>
  );
};
export default Card;
