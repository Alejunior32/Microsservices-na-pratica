package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dto.pagamento.CadastroPagamentoDto;
import br.com.alurafood.pagamentos.dto.pagamento.DetalhamentoPagamentoDto;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<Page<DetalhamentoPagamentoDto>> listarPagamentos(@PageableDefault()Pageable pageable){
        return ResponseEntity.ok(pagamentoService.obterTdodos(pageable).map(DetalhamentoPagamentoDto:: new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoPagamentoDto> detalharPagamento(@PathVariable @NotNull Long id){
        Pagamento pagamentoBuscado = pagamentoService.buscarPorId(id);
        return ResponseEntity.ok(new DetalhamentoPagamentoDto(pagamentoBuscado));
    }

    @PostMapping
    public ResponseEntity<DetalhamentoPagamentoDto> cadastrarPagamento(@RequestBody @Valid CadastroPagamentoDto cadastroPagamentoDto, UriComponentsBuilder uriBuilder){
        Pagamento pagamentoSalvo = pagamentoService.cadastrar(new Pagamento(cadastroPagamentoDto));
        URI uri = uriBuilder.path("/pagamento/{id}").buildAndExpand(pagamentoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoPagamentoDto(pagamentoSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhamentoPagamentoDto> atualizarFormaPagamento(@PathVariable @NotNull Long id, @RequestBody @Valid CadastroPagamentoDto dto){
        Pagamento pagamento = pagamentoService.atualizar(id,new Pagamento(dto));
        return ResponseEntity.ok(new DetalhamentoPagamentoDto(pagamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetalhamentoPagamentoDto> deletarPa(@PathVariable @NotNull Long id){
        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirmar")
    public void confirmarPagamento(@PathVariable @NotNull Long id){
        pagamentoService.confirmarPagamento(id);
    }
}
