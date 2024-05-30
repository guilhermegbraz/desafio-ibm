package br.com.desafio_ibm.service.transacao;

import br.com.desafio_ibm.dto.CadastroTransacaoDto;
import br.com.desafio_ibm.dto.ViewTransacaoDto;
import br.com.desafio_ibm.model.entities.ContaEntity;
import br.com.desafio_ibm.model.entities.TipoTransacao;
import br.com.desafio_ibm.model.entities.TransacaoEntity;
import br.com.desafio_ibm.model.repository.ContaRepository;
import br.com.desafio_ibm.model.repository.TransacaoRepository;
import br.com.desafio_ibm.service.transacao.validacao.ValidadorTransacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final List<ValidadorTransacao> validacoesTransacao;
    private final ContaRepository contaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, List<ValidadorTransacao> validacoesTransacao, ContaRepository contaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.validacoesTransacao = validacoesTransacao;
        this.contaRepository = contaRepository;
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
        if(cadastroTransacaoDto.descricao() != null && cadastroTransacaoDto.descricao().isBlank())
            transacaoEntity.setDescricao(cadastroTransacaoDto.descricao());
        this.transacaoRepository.save(transacaoEntity);
        this.contaRepository.save(conta);

        return new ViewTransacaoDto(transacaoEntity);
    }

    public ViewTransacaoDto detalharTransacao(long id) {
        return new ViewTransacaoDto(this.transacaoRepository.getReferenceById(id));
    }

    public Page<ViewTransacaoDto> listarTransacaoContasPaginado(Long id, Pageable paginacao) {
        return this.transacaoRepository.findAllByContaId(id, paginacao).map(ViewTransacaoDto::new);
    }
}
