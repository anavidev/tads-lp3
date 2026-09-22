package br.edu.ifsp.orderflow.events.handlers;

// armazena qtd de pagamentos aprovados e reprovados
public class Metricas {

    private int qtdAprovado = 0;
    private int qtdReprovado = 0;

    public void incrementarQtdAprovado(){
        this.qtdAprovado++;
    }

    public void incrementarQtdReprovado(){
        this.qtdReprovado++;
    }

    public int getQtdAprovado() {
        return qtdAprovado;
    }

    public int getQtdReprovado() {
        return qtdReprovado;
    }

    public String resumo(){
        return "Metricas: " + this.qtdAprovado + " aprovado(s)"
                + this.qtdReprovado + " reprovado(s)";
    }

}
