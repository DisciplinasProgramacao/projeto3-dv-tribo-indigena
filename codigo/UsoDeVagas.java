package br.pucminas.titas.entidades;

import br.pucminas.titas.excecoes.VeiculoJaSaiuException;
import br.pucminas.titas.excecoes.ServicoNaoTerminadoException;
import br.pucminas.titas.excecoes.VagaNaoDisponivelException;
import br.pucminas.titas.enums.Servico;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class UsoDeVaga implements Serializable {

	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private Servico servico;

	public UsoDeVaga(Vaga vaga) throws VagaNaoDisponivelException {
		this(vaga, null);
	}

	public UsoDeVaga(Vaga vaga, Servico servico) throws VagaNaoDisponivelException {
		if (!vaga.disponivel()) {
			throw new VagaNaoDisponivelException("Vaga não disponível");
		}
		this.vaga = vaga;
		this.servico = servico;
		this.entrada = LocalDateTime.now();
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public double sair() throws ServicoNaoTerminadoException, VeiculoJaSaiuException {
		if (saida != null) {
			throw new VeiculoJaSaiuException();
		}
		if (!podeSair(LocalDateTime.now())) {
			throw new ServicoNaoTerminadoException("Os serviços solicitados ainda não foram concluídos");
		}
		saida = LocalDateTime.now();
		vaga.sair();
		return calcularValorPago();
	}

	private double calcularValorPago() {
		if (saida == null) {
			return 0;
		}
		Duration duration = Duration.between(entrada, saida);
		double minutos = duration.toMinutes();
		valorPago = Math.floor(minutos / 15) * VALOR_FRACAO;

		if (valorPago > VALOR_MAXIMO) {
			valorPago = VALOR_MAXIMO;
		}
		if (servico != null) {
			valorPago += servico.getPreco();
		}
		return valorPago;
	}

	public void adicionaServico(Servico servico) {
		this.servico = servico;
	}

	public double getServicoPrecoTotal() {
		return servico.getPreco();
	}

	public boolean podeSair(LocalDateTime saida) {
		if (servico == null) {
			return true;
		}
		Duration duration = Duration.between(entrada, saida);
		return duration.toHours() >= servico.getHorasMinimas();
	}
}
