package br.com.alurafood.pagamentos.dto.pagamento;

import br.com.alurafood.pagamentos.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CadastroPagamentoDto(
        Long id,

        @NotNull
        @Positive BigDecimal valor,

        @Size(max = 100)
         String nome,

        @Size(max = 19)
         String numero,

        @Size(max = 7)
        String expiracao,
        
        @Size(min = 3,max = 3)
         String codigo,

        @NotNull
        @Enumerated(EnumType.STRING)
        Status status,

        @NotNull
         Long pedidoId,

        @NotNull
         Long formaDePagamentoId) {

}
