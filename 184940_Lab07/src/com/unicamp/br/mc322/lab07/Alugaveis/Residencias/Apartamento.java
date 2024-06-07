package com.unicamp.br.mc322.lab07.Alugaveis.Residencias;

import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

public class Apartamento extends Residencia{
	//Classe para apartamentos alugáveis. Ela herda de Residência o atributo nome, bem como os atributos e métodos padrões de podrutos de Alugáveis.
	
	//Pelo enunciado, todo apartamento tem as seguintes informações extras:
	private int quartos;//número de quartos
	private int andar;//Andar do apto
	private int banheiros;//número de banheiros
	private boolean temSacada;//Se tem ou não sacada
	
	public Apartamento(String id, double valor, Point endereco, String nome, int quartos, int andar, boolean temSacada,int banheiros){
		//Construtor normal para apartamento alugável, setando seus atributos e atributos herdados
		super(id,valor,endereco,nome);
		this.andar=andar;
		this.quartos=quartos;
		this.banheiros=banheiros;
		this.temSacada=temSacada;
	}

	@Override
	public double valorReserva(int dias, int adultos,int menores) {
		//Sobrescrita do método da classe Alugável, definido do mesmo modo que o indicado no enunciado
		double valorFinal,valorPorDia= valorBase+(valorBase*(andar/100));//valor da diária é o valor base mais 1% para cada andar de altura
		if (temSacada) {
			valorFinal=valorPorDia*(dias+1);//Se tem sacada, é adicionada mais uma diária no valor final
		} else {
			valorFinal=valorPorDia*dias;//Se não tem sacada, o valor da reserva vai ser o calor diário vezes o número de dias
		}
		return valorFinal;
	}
	
	@Override
	public String toString() {
		//Sobrescrita do método da classe Object. É usado para passar todas as informações de um apartamento
		String txt="";
		txt="\tApartamento "+nome+"("+id+"):\n\t\tEndereço: "+endereco.toString()+"\n\t\tAndar: "+andar+"°\n\t\tQuantidade de de quartos:"+quartos+"\n\t\tQuantidade de banheiros: "+banheiros+"\n\t\tTem sacada? "+(temSacada? "Sim":"Não")+"\n\t\tPreço base: "+valorBase+"\n";
		return txt;
	}
	
}
