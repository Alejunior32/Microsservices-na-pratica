package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.http.PedidoClient;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.model.Status;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoClient pedido;


    public Page<Pagamento> obterTdodos(Pageable pageable){
        return pagamentoRepository.findAll(pageable);
    }

    public Pagamento buscarPorId(Long id){
        return pagamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Pagamento cadastrar(Pagamento pagamento){
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento atualizar(Long id, Pagamento pagamento){
        buscarPorId(id);
        pagamento.setId(id);
        return pagamentoRepository.save(pagamento);
    }

    public void deletar(Long id){
        buscarPorId(id);
        pagamentoRepository.deleteById(id);
    }

    public void confirmarPagamento(Long id){
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);

        if (!pagamento.isPresent()) {
            throw new EntityNotFoundException();
        }

        pagamento.get().setStatus(Status.CONFIRMADO);
        pagamentoRepository.save(pagamento.get());
        pedido.atualizaPagamento(pagamento.get().getPedidoId());
    }
}
