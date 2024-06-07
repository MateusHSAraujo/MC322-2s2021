package com.unicamp.br.mc322.lab07.Alugaveis;
import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

abstract public class Alugavel {
	//Essa classe abstrata tem a inteção de definir atributos e operações comuns para os produtos do sistema
	
	protected Point endereco; //Todo produto tem um endereço
	protected String id; //Todo produto tem uma identificação alfanumérica
	protected double valorBase; //Todo produto tem um valor base
	public abstract double valorReserva(int dias, int adultos,int menores); //Todo produto deve saber calcular o custo de sua reserva
	
	protected Alugavel(String id, double valor, Point endereco){ //Contrutor padrão da superclasse
		this.id=id;
		this.endereco=endereco;
		this.valorBase=valor;
	}
	
	public double distanciaDe(Point ponto) {//Toda produto tem um método padronizado para descobrir sua distância a um ponto. A responsabilidade de calcular essa distância é terceirizada para a classe ponto.
		return endereco.distance(ponto);
	}
	
	public String getId() {//Getter para a identificação do produto. Essa informação será útil para o sistema de funcionalidades implementado
		return id;
	}
}
