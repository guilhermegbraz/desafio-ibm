package br.com.desafio_ibm.domain.usecase.cliente.validacao;

import br.com.desafio_ibm.domain.repository.ClienteRepository;
import br.com.desafio_ibm.dto.CadastroClienteDto;
import br.com.desafio_ibm.excecao.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.core.io.buffer.DataBufferUtils.matcher;

@Component
public class ValidarEmailCliente implements ValidacoesCliente{

    private final ClienteRepository clienteRepository;
    private static final String REGEX = "^[\\w\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public ValidarEmailCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public void validar(CadastroClienteDto cliente) {
        if(cliente.email() == null || cliente.email().isBlank())
            throw new RegraNegocioException("Campo email é obrigatório");

        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cliente.email());
        if(!matcher.matches())throw new RegraNegocioException("E-mail inválido!");

        if(this.clienteRepository.existsByEnderecoEmail(cliente.email()))
            throw new RegraNegocioException("E-mail já cadastrado!");

    }
}
