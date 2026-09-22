package br.edu.ifsp.orderflow.events.handlers;

import br.edu.ifsp.orderflow.events.IEventHandler;
import br.edu.ifsp.orderflow.events.PagamentoRecusado;

public class PagamentoRecusadoNotificacaoHandler implements IEventHandler<PagamentoRecusado> {

    @Override
    public void handle(PagamentoRecusado event) {

    }

    @Override
    public Class<PagamentoRecusado> eventType() {
        return null;
    }
}
