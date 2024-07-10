import '../css/geral.css';
import '../css/footer.css';

const Footer = () => {
    return (
        <footer className="bg-secondary text-white py-3">
          <div className="container">
            <div className="row justify-content-center">
              <div className="col-md-4 mb-3">
                <h5>Links Úteis</h5>
                <ul className="list-unstyled">
                  <li><a href="/sobre" className="text-white">Sobre</a></li>
                  <li><a href="/suporte" className="text-white">Suporte</a></li>
                </ul>
              </div>
              <div className="col-md-4 mb-3">
                <h5>Contato</h5>
                <ul className="list-unstyled">
                  <li>Email: <a href="mailto:suporte@trocadeprodutos.com" className="text-white">suporte@trocadeprodutos.com</a></li>
                  <li>Telefone: <a href="tel:+551198765432" className="text-white">(11) 9876-5432</a></li>
                </ul>
              </div>
            </div>
          </div>
          <div className="bg-secondary text-center py-1">
            <p className="mb-0">&copy; 2024 Troca Na Boa. Todos os direitos reservados.</p>
          </div>
        </footer>
    );
           
}
export default Footer;
