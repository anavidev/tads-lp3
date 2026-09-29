package br.edu.ifsp.orderflow.events;

/**
 * algo que ira consumir/reagir a tipo especifico de evento
 * (uma class que implementa IDomainEvent)
 * o parametro de tipo E garante, em tempo de compilacao, que
 * um handler de PagamentoAprovado nunca receba um PagamentoRecusado
 */
// contem reação a um tipo especifico de evento no metodo handle
public interface IEventHandler<E extends IDomainEvent> {

    // ja recebe o evento com o tipo certo (E)
    // eventType() informa, em tempo de execucao, a classe do evento tratado
    // handler é uma classe comum que implementa esta interface, fixando E
    // ex. -> implements IEventHandler<PagamentoAprovado>

    // handler depende de contratos
    // se o pedido nao existir ele simplesmente nao notifica
    void handle(E event);

    /**
     * qual tipo de evento este handler trata/consome?
     * necessario por causa do apagamento de tipo do Java (type erasure)
     */
    Class<E> eventType();

}
