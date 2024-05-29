package br.com.desafio_ibm.excecao;

public class RegraNegocioClienteException extends RuntimeException{
    public RegraNegocioClienteException(String mensagem) {
        super(mensagem);
    }
}
