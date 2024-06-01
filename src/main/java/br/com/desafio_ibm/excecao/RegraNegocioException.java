package br.com.desafio_ibm.excecao;

public class RegraNegocioException extends RuntimeException{
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
