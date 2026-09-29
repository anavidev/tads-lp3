package br.edu.ifsp.orderflow.events;

import java.time.Instant;

/**
 * representa um fato, algo que aconteceu no domínio como
 * PedidoCriado, PagamentoAprovado etc. quem publica um evento
 * nao sabe, e nem precisa saber, quem vai consumir/reagir a ele
 */
// representa um fato que aconteceu no dominio
public interface IDomainEvent {
    Instant ocorridoEm();
}
