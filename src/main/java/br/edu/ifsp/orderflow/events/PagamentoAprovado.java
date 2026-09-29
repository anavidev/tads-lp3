package br.edu.ifsp.orderflow.events;

import java.time.Instant;

/**
 *
 * Evento que sera publicado quando um pagamento for aprovado
 *
 * @param pedidoId
 * @param transacaoId
 */
// um fato no passado imutável
// nome no passado: o fato já ocorreu
// gera construtor, acessor, equals, hashcode, toString e é imutavel
// acessor ocorridoEm() já cumpre o contrato de IDomainEvent
// acessores nao tem get: necessario utilizar event.pedidoId(), event.motivo()
// ja event.getPedidoId() nao compila (cannot find symbol)
public record PagamentoAprovado(

        String pedidoId,
        String transacaoId,
        Instant ocorridoEm

) implements IDomainEvent {}