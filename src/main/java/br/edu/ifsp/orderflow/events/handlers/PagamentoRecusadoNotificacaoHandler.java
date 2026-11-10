package br.edu.ifsp.orderflow.events.handlers;

import br.edu.ifsp.orderflow.events.IEventHandler;
import br.edu.ifsp.orderflow.events.PagamentoRecusado;
import br.edu.ifsp.orderflow.service.INotificacaoService;
import br.edu.ifsp.orderflow.service.IPedidoRepository;

public class PagamentoRecusadoNotificacaoHandler implements IEventHandler<PagamentoRecusado> {

    public PagamentoRecusadoNotificacaoHandler(IPedidoRepository pedidoRepository, INotificacaoService notificacaoService) {
    }

    @Override
    public void handle(PagamentoRecusado event) {

    }

    @Override
    public Class<PagamentoRecusado> eventType() {
        return null;
    }
}
