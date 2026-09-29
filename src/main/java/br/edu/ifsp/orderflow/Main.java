package br.edu.ifsp.orderflow;


import br.edu.ifsp.orderflow.domain.Cliente;
import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import br.edu.ifsp.orderflow.events.IEventBus;
import br.edu.ifsp.orderflow.events.PagamentoAprovado;
import br.edu.ifsp.orderflow.events.SimpleEventBus;
import br.edu.ifsp.orderflow.events.handlers.PagamentoAprovadoNotificacaoHandler;
import br.edu.ifsp.orderflow.infra.ConsoleNotificacaoService;
import br.edu.ifsp.orderflow.infra.FakePagamentoGateway;
import br.edu.ifsp.orderflow.infra.InMemoryEstoqueService;
import br.edu.ifsp.orderflow.infra.InMemoryPedidoRepository;
import br.edu.ifsp.orderflow.service.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

// escolhe as implementacoes
// para trocar implementacao:
// 1- criar a classe com implements IPedidoRepository, implementando save, findById e findAll
// 2- trocar o new no Main: IPedidoRepository pedidoRepository = new ArquivoPedidoRepository();
public class Main {

    public static void main(String[] args) {

        // o tipo declarado delimita o que pode ser chamado. o objeto real decide qual implementacao atende a cada chamada (despacho dinamico)
        // tipo declarado: IEstoqueService (compilador considera somente ele. o programa compila ou não)
        // objeto real: InMemoryEstoqueService (a JVM considera o objeto. qual codigo roda no momento da execução)
        IEstoqueService estoqueService = new InMemoryEstoqueService();
        IPedidoRepository pedidoRepository = new InMemoryPedidoRepository();
        INotificacaoService notificacaoService = new ConsoleNotificacaoService();
        IPagamentoGateway pagamentoGateway = new FakePagamentoGateway();
        IEventBus eventBus = new SimpleEventBus();

        PagamentoAprovadoNotificacaoHandler evento1Handler = new PagamentoAprovadoNotificacaoHandler(
            pedidoRepository, notificacaoService
        );

        eventBus.register(evento1Handler);

        PagamentoAprovado evento1 = new PagamentoAprovado(
                "pedido-1",
                "transacao-1",
                Instant.now()
        );

        eventBus.publish(evento1);

//        PedidoService pedidoService = new PedidoService(
//            estoqueService,
//            pedidoRepository,
//            pagamentoGateway,
//            notificacaoService
//        );
//
//        Produto mouse = new Produto(
//                "SKU-1",
//                "Mouse sem fio",
//                new BigDecimal("120.00")
//        );
//
//        Produto teclado = new Produto(
//                "SKU-2",
//                "Teclado Mecânico",
//                new BigDecimal("350.00")
//        );
//
//        Produto monitor = new Produto(
//                "SKU-3",
//                "Monitor 27 pol",
//                new BigDecimal("1800.00")
//        );
//
//        estoqueService.adicionarEstoque(mouse, 10);
//        estoqueService.adicionarEstoque(teclado, 6);
//        estoqueService.adicionarEstoque(monitor, 2);
//
//        Cliente ana = new Cliente("Ana", "ana@email.com");
//        Cliente bruno = new Cliente("Bruno", "bruno@email.com");
//
//        Pedido pedido1 = new Pedido(ana);
//
//        pedido1.adicionarItem(new ItemPedido(mouse, 2));
//        pedido1.adicionarItem(new ItemPedido(teclado, 2));
//
//        Pedido pedido = pedidoService.processar(pedido1);
//
//        System.out.println(pedido);
    }
}
