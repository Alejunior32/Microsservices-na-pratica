package br.com.alurafood.pagamentos.model;

import br.com.alurafood.pagamentos.dto.pagamento.CadastroPagamentoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "pagamentos")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 19)
    private String numero;

    @NotBlank
    @Size(max = 7)
    private String expiracao;

    @NotBlank
    @Size(min = 3,max = 3)
    private String codigo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Long pedidoId;

    @NotNull
    private Long formaDePagamentoId;

    public Pagamento(CadastroPagamentoDto dto) {
        this.id = dto.id();
        this.valor =dto.valor();
        this.nome=dto.nome();
        this.numero = dto.numero();
        this.expiracao = dto.expiracao();
        this.codigo=dto.codigo();
        this.status = dto.status();
        this.pedidoId = dto.pedidoId();
        this.formaDePagamentoId = dto.formaDePagamentoId();
    }
}

