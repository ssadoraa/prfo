class TokenClass {
  static token = {} as TokenClass;

  private umToken: string;

  private constructor(umToken: string) {
    this.umToken = umToken;
  }

  public static criarToken = (token: string): void => {
    // console.log("Dentro de criarToken = ", token);
    TokenClass.token = new TokenClass(token);
  };
  public static getToken = () => {
    // console.log("Recuperando o token = ", TokenClass.token.umToken)
    return TokenClass.token.umToken;
  };
  public static resetToken = () => {
    // console.log("Recuperando o token = ", TokenClass.token.umToken)
    TokenClass.token.umToken = "";
  };
}

export default TokenClass;
