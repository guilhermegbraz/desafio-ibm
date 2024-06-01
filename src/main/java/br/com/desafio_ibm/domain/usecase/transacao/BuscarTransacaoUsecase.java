package br.com.desafio_ibm.domain.usecase.transacao;

import br.com.desafio_ibm.domain.repository.TransacaoRepository;
import br.com.desafio_ibm.dto.ViewTransacaoDto;
import br.com.desafio_ibm.excecao.RegraNegocioException;
import br.com.desafio_ibm.domain.repository.ContaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscarTransacaoUsecase {
    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    public BuscarTransacaoUsecase(ContaRepository contaRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public Page<ViewTransacaoDto> listarPorContasPaginado(long id, Pageable paginacao) {
        if(!this.contaRepository.existsById(id))
            throw new RegraNegocioException("Não existe conta com esse id");

        return this.transacaoRepository.findAllByContaId(id, paginacao).map(ViewTransacaoDto::new);
    }

    public ViewTransacaoDto detalharTransacao(long id) {
        return new ViewTransacaoDto(this.transacaoRepository.getReferenceById(id));
    }
}
