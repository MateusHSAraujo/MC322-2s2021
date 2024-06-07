package com.unicamp.br.mc322.lab07.Alugaveis.Residencias;

import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

public class Apartamento extends Residencia{
	//Classe para apartamentos alug�veis. Ela herda de Resid�ncia o atributo nome, bem como os atributos e m�todos padr�es de podrutos de Alug�veis.
	
	//Pelo enunciado, todo apartamento tem as seguintes informa��es extras:
	private int quartos;//n�mero de quartos
	private int andar;//Andar do apto
	private int banheiros;//n�mero de banheiros
	private boolean temSacada;//Se tem ou n�o sacada
	
	public Apartamento(String id, double valor, Point endereco, String nome, int quartos, int andar, boolean temSacada,int banheiros){
		//Construtor normal para apartamento alug�vel, setando seus atributos e atributos herdados
		super(id,valor,endereco,nome);
		this.andar=andar;
		this.quartos=quartos;
		this.banheiros=banheiros;
		this.temSacada=temSacada;
	}

	@Override
	public double valorReserva(int dias, int adultos,int menores) {
		//Sobrescrita do m�todo da classe Alug�vel, definido do mesmo modo que o indicado no enunciado
		double valorFinal,valorPorDia= valorBase+(valorBase*(andar/100));//valor da di�ria � o valor base mais 1% para cada andar de altura
		if (temSacada) {
			valorFinal=valorPorDia*(dias+1);//Se tem sacada, � adicionada mais uma di�ria no valor final
		} else {
			valorFinal=valorPorDia*dias;//Se n�o tem sacada, o valor da reserva vai ser o calor di�rio vezes o n�mero de dias
		}
		return valorFinal;
	}
	
	@Override
	public String toString() {
		//Sobrescrita do m�todo da classe Object. � usado para passar todas as informa��es de um apartamento
		String txt="";
		txt="\tApartamento "+nome+"("+id+"):\n\t\tEndere�o: "+endereco.toString()+"\n\t\tAndar: "+andar+"�\n\t\tQuantidade de de quartos:"+quartos+"\n\t\tQuantidade de banheiros: "+banheiros+"\n\t\tTem sacada? "+(temSacada? "Sim":"N�o")+"\n\t\tPre�o base: "+valorBase+"\n";
		return txt;
	}
	
}
