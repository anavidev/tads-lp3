package br.edu.ifsp.orderflow.service;

import br.edu.ifsp.orderflow.domain.Pedido;

public class PedidoService {

    private IEstoqueService estoqueService;

    public PedidoService(IEstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    public Pedido processar(Pedido pedido){

        boolean foiReservado = this.estoqueService.reservar(pedido);

        if (!foiReservado){ // foiReservado == false
            pedido.cancelar();
            // 1- salvar o pedido
            return pedido;
        }

        // 2- processar pagamento
        // 3- salvar se pagamento ocorreu com sucesso
        // 4- notificar cliente
        // 5- retorna o pedido
        return pedido;

    }

}
