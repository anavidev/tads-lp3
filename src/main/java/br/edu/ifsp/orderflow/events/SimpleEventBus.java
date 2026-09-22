package br.edu.ifsp.orderflow.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleEventBus implements IEventBus {

    // handlers -> estao interessados nos eventos
    // chave: qualquer coisa* que seja do subtipo IDomainEvent | valor: lista de handlers
    // * -> se fosse E (evento generico), teria que aceitar apenas subtipos determinados de IDomainEvent
    private Map<Class<? extends IDomainEvent>, List<IEventHandler<? extends IDomainEvent>>> handlers = new HashMap<>();


    // publisher: chama todos os handlers que possuem interesse no evento
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
