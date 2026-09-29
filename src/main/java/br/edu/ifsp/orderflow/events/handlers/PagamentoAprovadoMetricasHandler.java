package br.edu.ifsp.orderflow.events.handlers;

import br.edu.ifsp.orderflow.events.IEventHandler;
import br.edu.ifsp.orderflow.events.PagamentoAprovado;

// novo interessado = novo handler
// contar pagamentos aprovados sem tocar em quem publica
// nada mudou em quem publica o evento, nem no barramento
// surgiram uma classe nova e uma linha de registro

// no repositorio, o construtor deste handler (e o de PagamentoRecusadoMetricasHandler) está sem public
// Main fica em outro pacote, o registro nao compila ("is not public")
// solucao: declarar o construtor public
public class PagamentoAprovadoMetricasHandler implements IEventHandler<PagamentoAprovado> {

    private final Metricas metricas;

    PagamentoAprovadoMetricasHandler(Metricas metricas){
        this.metricas = metricas;
    }

    @Override
    public void handle(PagamentoAprovado event) {
        this.metricas.incrementarQtdAprovado();
    }

    @Override
    public Class<PagamentoAprovado> eventType() {
        return PagamentoAprovado.class;
    }

}
