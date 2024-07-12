import { Link } from 'react-router-dom';
import '../../../css/geral.css';
import '../../../css/home.css';

const HomePage = () => {
    return (
        <div>
            <div id="carouselExampleCaptions" className="carousel slide" data-bs-ride="carousel">
                <div className="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                </div>
                <div className="carousel-inner">
                    <div className="carousel-item active">
                        <img src="/home1.jpg" height="500px" className="d-block w-100" alt="..." />
                        <div className="carousel-caption d-none d-md-block">
                            <h4>Troque seus produtos agora mesmo!</h4>
                            <p>Encontre uma variedade de categorias para trocar com outros usuários.</p>
                        </div>
                    </div>
                    <div className="carousel-item">
                        <img src="/home1.jpg" height="500px" className="d-block w-100" alt="..." />
                        <div className="carousel-caption d-none d-md-block">
                            <h5>Second slide label</h5>
                            <p>Some representative placeholder content for the second slide.</p>
                        </div>
                    </div>
                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>

            <div className="container mt-5 mb-5">

                <div className="mt-5" id="categorias">
                    <h3 className="mb-4 text-center">Principais Categorias</h3>
                    <div className="row d-flex flex-wrap">
                        <div className="col-lg-3 col-md-6">
                            <Link to={"/produtos/roupas"} className="card-link">
                                <div className="card card-custom">
                                    <img src="/roupas.jpg" alt="Roupas" height="205px" />
                                    <div className="card-body">
                                        <h5 className="card-title">Roupas</h5>
                                        <p className="card-text text-justify">Troque roupas novas ou usadas com os outros usuários.</p>
                                    </div>
                                </div>
                            </Link>
                        </div>
                        <div className="col-lg-3 col-md-6">
                            <Link to={"/produtos/eletronicos"} className="card-link">
                                <div className="card card-custom">
                                    <img src="/eletronico.jpg" alt="Eletronicos" />
                                    <div className="card-body">
                                        <h5 className="card-title">Eletrônicos</h5>
                                        <p className="card-text text-justify">Encontre uma variedade de eletrônicos para trocar.</p>
                                    </div>
                                </div>
                            </Link>
                        </div>
                        <div className="col-lg-3 col-md-6">
                            <Link to={"/produtos/ferramentas"} className="card-link">
                                <div className="card card-custom">
                                    <img src="/ferramentas.jpg" alt="Ferramentas" />
                                    <div className="card-body">
                                        <h5 className="card-title">Ferramentas</h5>
                                        <p className="card-text text-justify">Troque roupas novas ou usadas com os outros usuários.</p>
                                    </div>
                                </div>
                            </Link>
                        </div>
                        <div className="col-lg-3 col-md-6">
                            <Link to={"/produtos/livros"} className="card-link">
                                <div className="card card-custom">
                                    <img src="/livros.jpg" alt="Livros" />
                                    <div className="card-body">
                                        <h5 className="card-title">Livros</h5>
                                        <p className="card-text text-justify">Terminou de ler um livro, troque por outro.</p>
                                    </div>
                                </div>
                            </Link>
                        </div>
                    </div>
                </div>

                <div className="mt-5" id="depoimentos">
                    <h3 className="mb-3 text-center">Depoimentos de Usuários</h3>
                    <div className="media row">
                        <div className="d-flex align-items-center">
                            <img src="/boy.png" className="me-3 img-circle" alt="Imagem de usuário" />
                            <div>
                                <h6 className="mb-0">Claúdio Silva</h6>
                                <span className="mb-0">"Minha experiência foi surpreendentemente boa! Encontrei uma troca perfeita para um item que não usava mais e recebi em troca algo que realmente queria. O processo foi simples e direto, e a comunicação com o outro usuário foi amigável e eficiente."</span>
                            </div>
                        </div>
                    </div>
                    <div className="media row mt-4">
                        <div className="d-flex align-items-center">
                            <img src="/boy.png" className="me-3 img-circle" alt="Imagem de usuário" />
                            <div>
                                <h6 className="mb-0">Luiza Pacheco</h6>
                                <span className="mb-0">"Adorei usar o site para minhas trocas! A facilidade de listar meus próprios produtos e receber propostas foi muito proveitoso. Estou muito satisfeito com as trocas que fiz até agora e continuarei a usar este site no futuro."</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default HomePage;
