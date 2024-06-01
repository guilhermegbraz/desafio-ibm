package br.com.desafio_ibm.domain.usecase.transacao;

import br.com.desafio_ibm.domain.repository.TransacaoRepository;
import br.com.desafio_ibm.domain.usecase.transacao.validacao.ValidadorTransacao;
import br.com.desafio_ibm.dto.CadastroTransacaoDto;
import br.com.desafio_ibm.dto.ViewTransacaoDto;
import br.com.desafio_ibm.domain.entities.ContaEntity;
import br.com.desafio_ibm.domain.entities.TipoTransacao;
import br.com.desafio_ibm.domain.entities.TransacaoEntity;
import br.com.desafio_ibm.domain.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CadastrarTransacaoUsecase {
    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;
    private final List<ValidadorTransacao> validacoesTransacao;

    public CadastrarTransacaoUsecase(TransacaoRepository transacaoRepository, ContaRepository contaRepository, List<ValidadorTransacao> validadorTransacaos) {
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
        this.validacoesTransacao = validadorTransacaos;
    }

    @Transactional
    public ViewTransacaoDto cadastrarTransacao(CadastroTransacaoDto cadastroTransacaoDto) {
        this.validacoesTransacao.forEach(validacao -> validacao.validar(cadastroTransacaoDto));
        ContaEntity conta = this.contaRepository.getReferenceById(cadastroTransacaoDto.idConta());
        TipoTransacao tipo;

        if(cadastroTransacaoDto.tipo().equalsIgnoreCase("credito")) {
            tipo = TipoTransacao.CREDITO;
            conta.setCreditoDisponivel(conta.getCreditoDisponivel().add(cadastroTransacaoDto.valor()));
        } else {
            tipo = TipoTransacao.DEBITO;
            conta.setSaldo(conta.getSaldo().add(cadastroTransacaoDto.valor()));
        }

        TransacaoEntity transacaoEntity =  new TransacaoEntity(tipo, cadastroTransacaoDto.valor(), conta);
        transacaoEntity.setData(LocalDateTime.now());
        if(cadastroTransacaoDto.descricao() != null && !cadastroTransacaoDto.descricao().isBlank())
            transacaoEntity.setDescricao(cadastroTransacaoDto.descricao());
        this.transacaoRepository.save(transacaoEntity);
        this.contaRepository.save(conta);

        return new ViewTransacaoDto(transacaoEntity);
    }
}
