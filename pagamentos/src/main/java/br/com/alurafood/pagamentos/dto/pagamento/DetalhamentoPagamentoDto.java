package br.com.alurafood.pagamentos.dto.pagamento;

import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record DetalhamentoPagamentoDto(Long id, BigDecimal valor, String nome, String numero, String expiracao,
                                       String codigo, Status status, Long pedidoId, Long formaDePagamentoId) {

    public DetalhamentoPagamentoDto (Pagamento pagamento){
        this(pagamento.getId(),pagamento.getValor(),pagamento.getNome(),pagamento.getNumero(),pagamento.getExpiracao(),
        pagamento.getCodigo(),pagamento.getStatus(),pagamento.getPedidoId(),pagamento.getFormaDePagamentoId());
    }
}
