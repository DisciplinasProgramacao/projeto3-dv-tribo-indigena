package br.pucminas.titas.entidades;

import java.io.Serializable;

/**
 * Representa uma vaga de estacionamento.
 */
public class EstacionamentoVaga implements Serializable {

    private String id;
    private boolean disponivel;

    /**
     * Construtor da vaga de estacionamento.
     * @param fila Letra da fila onde a vaga está localizada.
     * @param numero Número da vaga na fila.
     */
    public EstacionamentoVaga(String fila, int numero) {
        this.id = fila + String.format("%02d", numero);
        this.disponivel = true;
    }

    /**
     * Tenta estacionar um carro na vaga.
     * @return true se foi possível estacionar, false caso contrário.
     */
    public boolean estacionarCarro() {
        if (isDisponivel()) {
            disponivel = false;
            return true;
        }
        return false;
    }

    /**
     * Tenta liberar a vaga.
     * @return true se foi possível liberar, false caso contrário.
     */
    public boolean liberarVaga() {
        if (!isDisponivel()) {
            disponivel = true;
            return true;
        }
        return false;
    }

    /**
     * Verifica se a vaga está disponível.
     * @return true se a vaga está disponível, false caso contrário.
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "Vaga ID: " + id + ", Disponível: " + (isDisponivel() ? "Sim" : "Não");
    }
}
