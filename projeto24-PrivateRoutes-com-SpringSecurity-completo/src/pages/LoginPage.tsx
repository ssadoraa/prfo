import LoginForm from "../components/LoginForm";
import "../css/geral.css";

const LoginPage = () => {
  return (
    <>
      <div className="mb-4">
        <h5>Página de Login</h5>
        <hr className="mt-0" />
      </div>

      <LoginForm />
    </>
  );
};
export default LoginPage;
