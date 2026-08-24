package br.edu.ifsp.orderflow.domain;

import br.edu.ifsp.orderflow.domain.ItemPedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class Pedido {

    private final String id;
    private final Cliente cliente;
    private final List<ItemPedido> itens;
    private EStatusPedido status;

    public Pedido(Cliente cliente) {
        this.id = (UUID.randomUUID()).toString();
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = EStatusPedido.CRIADO;
    }

    public void adicionarItem(ItemPedido item){
        this.itens.add(item);
    }

    public BigDecimal calcularTotal(){

        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < this.itens.size(); i++){
            ItemPedido item = this.itens.get(i);
            total = total.add(item.calcularSubtotal());
        }

        return total;

    }

    public void marcarComoPago(){
        this.status = EStatusPedido.PAGO;
    }

    public void cancelar(){
        this.status = EStatusPedido.CANCELADO;
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public EStatusPedido getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Pedido[id: " + this.id + ", status: " + this.status + ", total: R$" + this.calcularTotal() + "]";
    }

}

