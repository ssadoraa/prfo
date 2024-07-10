import '../css/geral.css';

const SuportePage = () => {
    return (
        <div>
            <section id="faq" className="py-5">
                <div className="container">
                    <h2 className="text-center mb-4">Perguntas Frequentes</h2>
                    <div className="row">
                        <div className="col-lg-6 mx-auto">
                            <div className="accordion" id="accordionInfos">
                                <div className="accordion-item">
                                    <h2 className="accordion-header" id="headingOne">
                                        <button className="accordion-button collapsed maq fw-bold" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                            Como posso listar um produto para troca?
                                        </button>
                                    </h2>
                                    <div id="collapseOne" className="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionInfos">
                                        <div className="accordion-body maq">
                                            Para listar um produto para troca, você precisa criar uma conta em nosso site. Depois de fazer login, vá para a seção "Meus Produtos" e clique em "Adicionar Produto". Preencha as informações solicitadas e seu produto estará listado para troca.
                                        </div>
                                    </div>
                                </div>

                                <div className="accordion-item">
                                    <h2 className="accordion-header" id="headingTwo">
                                        <button className="accordion-button collapsed maq fw-bold" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                            Como posso encontrar produtos para trocar?
                                        </button>
                                    </h2>
                                    <div id="collapseTwo" className="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionInfos">
                                        <div className="accordion-body maq">
                                            Você pode encontrar produtos para troca navegando pela nossa lista de produtos. Quando encontrar um produto que lhe interesse, entre em contato com o usuário para discutir a troca.
                                        </div>
                                    </div>
                                </div>

                                <div className="accordion-item">
                                    <h2 className="accordion-header" id="headingThree">
                                        <button className="accordion-button collapsed maq fw-bold" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                            Quais são as taxas envolvidas na troca de produtos?
                                        </button>
                                    </h2>
                                    <div id="collapseThree" className="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionInfos">
                                        <div className="accordion-body maq">
                                            Não cobramos taxas pela troca de produtos em nosso site. A troca é feita diretamente entre os usuários sem custos adicionais.
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section id="support" className="bg-light py-5">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-8 mx-auto text-center">
                            <h2 className="mb-4">Precisa de Ajuda?</h2>
                            <p className="lead">Preencha o formulário abaixo e entraremos em contato o mais breve possível.</p>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg-6 mx-auto">
                            <form action="#" method="post">
                                <div className="mb-3">
                                    <label htmlFor="name" className="form-label">Nome</label>
                                    <input type="text" className="form-control" id="name" name="name" required />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="email" className="form-label">E-mail</label>
                                    <input type="email" className="form-control" id="email" name="email" required />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="message" className="form-label">Mensagem</label>
                                    <textarea className="form-control" id="message" name="message" required></textarea>
                                </div>
                                <button type="submit" className="btn btn-primary">Enviar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    )
  }
  export default SuportePage