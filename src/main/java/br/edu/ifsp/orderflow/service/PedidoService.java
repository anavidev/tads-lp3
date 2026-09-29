package br.edu.ifsp.orderflow.service;

import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.ResultadoPagamento;

import javax.xml.transform.Result;

// depende apenas de contratos (a classe nao sabe e nem precisa saber qual implementacao recebeu)
// atributos, construtor e chamadas usam apenas interfaces
public class PedidoService {

    private final IEstoqueService estoqueService;
    private final IPedidoRepository pedidoRepository;
    private final IPagamentoGateway pagamentoGateway;
    private final INotificacaoService notificacaoService;

    // publica um evento

    // vantagens
    // 1- baixo acoplamento: publicador e consumidor nao se conhecem (diferente de execução em paralelo pois os handlers rodam um de cada vez)
    // 2- extensibilidade: novo comportamento = novo handler
    // 3- responsabilidade unica por handler
    // 4- testabilidade: cada handler é testável isoladamente

    // desvantagens
    // 1- perde-se rastreabilidade pois nao está em um unico lugar
    // 2- deuparacao exige acompanhar o fluxo pelo barramento
    public PedidoService(
        IEstoqueService estoqueService,
        IPedidoRepository pedidoRepository,
        IPagamentoGateway pagamentoGateway,
        INotificacaoService notificacaoService
    ) {
        this.estoqueService = estoqueService;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoGateway = pagamentoGateway;
        this.notificacaoService = notificacaoService;
    }

    public Pedido processar(Pedido pedido) {

        boolean foiReservado = this.estoqueService.reservar(pedido);

        if (!foiReservado) {
            pedido.cancelar();
            this.pedidoRepository.save(pedido);
            return pedido;
        }

        ResultadoPagamento resultado = this.pagamentoGateway.pagar(pedido);
        boolean naoAprovado = !resultado.isAprovado();

        if (naoAprovado) {
            this.estoqueService.liberar(pedido);
            pedido.cancelar();
            this.pedidoRepository.save(pedido);
            return pedido;
        }

        // a cada pagamento aprovado é necessário registrar métricas etc
        // pela abordagem direta, PedidoService teria de conhecer e chamar cada um desses serviços
        // sendo alterado a cada novo interessado
        // acoplamento alto -> PedidoService precisa conhecer todos que reagem ao pagamento
        // responsabilidade misturada -> processar pedido nao deveria decidir quem reage a isso
        // baixa extensibilidade -> um novo interessado exige alterar classe de pedido
        // dificulta testes -> testar o processamento do pedido arrasta todas as dependências
        pedido.marcarComoPago();
        this.pedidoRepository.save(pedido);

        this.notificacaoService.notificar(
            pedido.getCliente(),
            "Pagamento aprovado! Pedido " + pedido.getIdCurto() + " confirmado"
        );

        return pedido;
    }
}
