package br.edu.ifsp.orderflow.events;

/**
 * Um IEventBus (ou barramento de evento) funciona como um quadro de avisos
 * em que servicos publicam eventos e os handlers registrados são avisados
 */
public interface IEventBus {

    // E = tipo generico -> aceita qualquer tipo (PagamentoAprovado ou PagamentoRecusado)
    <E extends IDomainEvent> void publish(E event);

    <E extends IDomainEvent> void register(IEventHandler<E> handler);

}
