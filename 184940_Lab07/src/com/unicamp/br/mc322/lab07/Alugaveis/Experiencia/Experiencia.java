package com.unicamp.br.mc322.lab07.Alugaveis.Experiencia;
import com.unicamp.br.mc322.lab07.Alugaveis.Alugavel;
import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

public class Experiencia extends Alugavel{
	//Classe que defini as experiencias cadastráveis como produtos. Ela herda atributos e método de Alugáveis por também ser um produto oferecido pelo sistema
	
	//Pelo enunciado, experiências tem como atributos extras:
	private int participantes;//O número máximo de pessoas que podem participar dela
	private double valorDescontado;//Um valor menor que o padrão para menores de idade
	public Experiencia(String id, double valorBase,double valorDescontado,Point endereco, int participantes) {
		//Construtor padrão para a classe, o qual seta seus atributos originais e herdados
		super(id, valorBase, endereco);
		this.participantes=participantes;
		this.valorDescontado=valorDescontado;
	}

	@Override
	public double valorReserva(int dias, int adultos, int menores) {
		//Sobrescrita do método da classe Alugável, definido do mesmo modo que o indicado no enunciado
		double valorFinal=0;
		if(adultos+menores<=participantes) {
			valorFinal=adultos*valorBase+menores*valorDescontado;//O valor é dado simplesmente pelo número de adultos multiplicado pelo preço base, mais o número de menores de idade multiplicado pelo preço descontado
		} else {
			valorFinal=0;// Se o número de pessoas totais da reserva for maior do número máximo admitido o valor da reserva deve ser zero.
		}
		return valorFinal;
	}
	
	@Override
	public String toString() {
		//Sobrescrita do método da classe Object. É usado para passar todas as informações de uma experiência
		String txt="";
		txt="\tExperiência "+id+":\n\t\tEndereço: "+endereco.toString()+"\n\t\tTotal de participantes: "+participantes+"\n\t\tPreço base: "+valorBase+"\n\t\tPreço com desconte: "+valorDescontado+"\n";
		return txt;
	}
}
