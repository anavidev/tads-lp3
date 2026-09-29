package br.edu.ifsp.orderflow.service;

import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.ResultadoPagamento;

// interface é um contrato
// - define o quê (pagar e devolver um ResultadoPagamento)
// - classe define como (ex.: simulacao que recusa compras acima de R$5000.00)
// - desenho por interfaces permite trocá-las depois sem reescrever o restante do sistema

//                         classe abstrata x interface
// atributos de instancia:      sim        x    nao
// construtor:                  sim        x    nao
// metodos implementados:       sim        x    nao
// quantas uma classe usa: uma (extends)   x    varias (implements)
// instanciacao com new:        não        x    não
// relacao expressa:            é um       x    é capaz de

public interface IPagamentoGateway {
    ResultadoPagamento pagar(Pedido pedido);
}
