package br.edu.ifsp.orderflow.events;

/**
 * Um IEventBus (ou barramento de evento) funciona como um quadro de avisos
 * em que servicos publicam eventos e os handlers registrados são avisados
 */
// entrega
// inscreve handlers (register) e entrega cada evento publicado aos interessados (publish)

// fluxo
// quem publica chama bus.publish(evento) ->
// barramento encontra os handlers registrados para o tipo daquele evento ->
// chama handle(evento) em cada um
public interface IEventBus {

    // E = tipo generico -> aceita qualquer tipo (PagamentoAprovado ou PagamentoRecusado)

    // anuncia que um evento ocorreu e o entrega aos handlers inscritos para aquele tipo
    <E extends IDomainEvent> void publish(E event);

    // inscreve um handler, que passa a ser avisado dos eventos do tipo que ele trata
    <E extends IDomainEvent> void register(IEventHandler<E> handler);

}