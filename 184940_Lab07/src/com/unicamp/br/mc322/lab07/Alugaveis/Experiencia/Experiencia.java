package com.unicamp.br.mc322.lab07.Alugaveis.Experiencia;
import com.unicamp.br.mc322.lab07.Alugaveis.Alugavel;
import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

public class Experiencia extends Alugavel{
	//Classe que defini as experiencias cadastr�veis como produtos. Ela herda atributos e m�todo de Alug�veis por tamb�m ser um produto oferecido pelo sistema
	
	//Pelo enunciado, experi�ncias tem como atributos extras:
	private int participantes;//O n�mero m�ximo de pessoas que podem participar dela
	private double valorDescontado;//Um valor menor que o padr�o para menores de idade
	public Experiencia(String id, double valorBase,double valorDescontado,Point endereco, int participantes) {
		//Construtor padr�o para a classe, o qual seta seus atributos originais e herdados
		super(id, valorBase, endereco);
		this.participantes=participantes;
		this.valorDescontado=valorDescontado;
	}

	@Override
	public double valorReserva(int dias, int adultos, int menores) {
		//Sobrescrita do m�todo da classe Alug�vel, definido do mesmo modo que o indicado no enunciado
		double valorFinal=0;
		if(adultos+menores<=participantes) {
			valorFinal=adultos*valorBase+menores*valorDescontado;//O valor � dado simplesmente pelo n�mero de adultos multiplicado pelo pre�o base, mais o n�mero de menores de idade multiplicado pelo pre�o descontado
		} else {
			valorFinal=0;// Se o n�mero de pessoas totais da reserva for maior do n�mero m�ximo admitido o valor da reserva deve ser zero.
		}
		return valorFinal;
	}
	
	@Override
	public String toString() {
		//Sobrescrita do m�todo da classe Object. � usado para passar todas as informa��es de uma experi�ncia
		String txt="";
		txt="\tExperi�ncia "+id+":\n\t\tEndere�o: "+endereco.toString()+"\n\t\tTotal de participantes: "+participantes+"\n\t\tPre�o base: "+valorBase+"\n\t\tPre�o com desconte: "+valorDescontado+"\n";
		return txt;
	}
}
