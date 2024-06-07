package com.unicamp.br.mc322.lab07.Alugaveis;
import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

abstract public class Alugavel {
	//Essa classe abstrata tem a inte��o de definir atributos e opera��es comuns para os produtos do sistema
	
	protected Point endereco; //Todo produto tem um endere�o
	protected String id; //Todo produto tem uma identifica��o alfanum�rica
	protected double valorBase; //Todo produto tem um valor base
	public abstract double valorReserva(int dias, int adultos,int menores); //Todo produto deve saber calcular o custo de sua reserva
	
	protected Alugavel(String id, double valor, Point endereco){ //Contrutor padr�o da superclasse
		this.id=id;
		this.endereco=endereco;
		this.valorBase=valor;
	}
	
	public double distanciaDe(Point ponto) {//Toda produto tem um m�todo padronizado para descobrir sua dist�ncia a um ponto. A responsabilidade de calcular essa dist�ncia � terceirizada para a classe ponto.
		return endereco.distance(ponto);
	}
	
	public String getId() {//Getter para a identifica��o do produto. Essa informa��o ser� �til para o sistema de funcionalidades implementado
		return id;
	}
}
