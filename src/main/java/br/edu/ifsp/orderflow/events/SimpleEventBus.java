package br.edu.ifsp.orderflow.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// local em que handlers ficam. é um mapa de listas
// depois de registrar o handler de notificacao (PagamentoAprovado) e log (PedidoCriado) e o de metricas (PagamentoAprovado), o mapa fica:
// chave (classe do evento) | valor (lista, na ordem de registros)
// PagamentoAprovado.class  | [notificacao, metricas]
// PedidoCriado.class       | [log]
public class SimpleEventBus implements IEventBus {

    // handlers -> estao interessados nos eventos
    // chave: qualquer coisa* que seja do subtipo IDomainEvent | valor: lista de handlers
    // * -> se fosse E (evento generico), teria que aceitar apenas subtipos determinados de IDomainEvent
    private Map<Class<? extends IDomainEvent>, List<IEventHandler<? extends IDomainEvent>>> handlers = new HashMap<>();


    // publisher: chama todos os handlers que possuem interesse no evento
    // 1- procura a lista pela classe exata do objeto publicado (event.getClass()) verificando a chave do mapa
    // 2- se nao há lista para essa classe: return. nada acontece
    // 3- se há, percorre a lista na ordem de registro e chama handle(event) em cada handler, um de cada vez, na mesma thread
    // classe real do evento decide qual lista é percorrida
    @Override
    public <E extends IDomainEvent> void publish(E event) {

        // identifica o evento registrado
        // getClass(): retorna classe em que o objeto foi instanciado
        List<IEventHandler<? extends IDomainEvent>> listHandlers = this.handlers.get(event.getClass());

        if(listHandlers == null){
            return; // quebra fluxo (ninguem interessado nesse evento)
        }

        // garante que tipo é o mesmo passado ao metodo
        for (IEventHandler<? extends IDomainEvent> registered : listHandlers){
            IEventHandler<E> handler = (IEventHandler<E>) registered;
            handler.handle(event);
        }

    }

    // 1- procura lista usando como chave handler.eventType()
    // 2- se ainda nao ha lista para essa chave, cria uma vazia e a coloca no mapa (put so acontece na primeira vez)
    // 3- acrescenta handler ao fim da lista (add)
    // tipo declarado pelo handler decide onde ele é guardado
    @Override
    public <E extends IDomainEvent> void register(IEventHandler<E> handler) {

        List<IEventHandler<? extends IDomainEvent>> listHandlers = this.handlers.get(handler.eventType());

        // se estiver vazio, adiciona um handler ao valor
        if(listHandlers == null){
            listHandlers = new ArrayList<>();
            this.handlers.put(handler.eventType(),listHandlers);
        }

        listHandlers.add(handler);

    }

}
