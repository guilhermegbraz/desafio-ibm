package br.com.desafio_ibm.excecao;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@RestControllerAdvice

public class TratadorExcecao {

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
    private record RetornoErro (String mensagem, List<DadosErroValidacao> errosValidacao) {}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(RegraNegocioClienteException.class)
    public ResponseEntity tratarErroRegraNegocioCliente(RegraNegocioClienteException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
