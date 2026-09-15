package br.edu.ifsp.orderflow.events;

import java.time.Instant;

/**
 *
 * Evento que sera publicado quando um pagamento for aprovado
 *
 * @param pedidoId
 * @param transacaoId
 */

public record PagamentoAprovado(

        String pedidoId,
        String transacaoId,
        Instant ocorridoEm

) implements IDomainEvent {}