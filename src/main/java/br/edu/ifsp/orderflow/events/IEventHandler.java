package br.edu.ifsp.orderflow.events;

/**
 * Algo que ira consumir/reagir a tipo especifico de evento
 * (uma class que implementa IDomainEvent)
 *
 * O parametro de tipo E garante, em tempo de compilacao, que
 * um handler de PagamentoAprovado nunca receba um PagamentoRecusado
 */

public interface IEventHandler<E extends IDomainEvent> {

    void handle(E event);

    /**
     * Qual tipo de evento este handler trata/consome?
     *
     * Necessario por causa do apagamento de tipo do Java (type erasure)
     *
     */
    Class<E> eventType();

}
