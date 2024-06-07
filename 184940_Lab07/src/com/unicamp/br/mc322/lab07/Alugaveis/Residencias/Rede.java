package com.unicamp.br.mc322.lab07.Alugaveis.Residencias;

import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

public class Rede extends Residencia{
	//Classe para redes alugáveis. Ela herda de Residência o atributo nome, bem como os atributos e métodos padrões de podrutos de Alugáveis.
	public Rede(String id, double valor, Point endereco, String nome){
		//Construtor normal para mansão redes. Essa classe não tem atributos extras então ela seta os herdados
		super(id,valor,endereco,nome);
	}

	@Override
	public double valorReserva(int dias,  int adultos,int menores) {
		//Sobrescrita do método da classe Alugável, definido do mesmo modo que o indicado no enunciado
		int pessoas=adultos+menores;
		double valorFinal,valorPorDia=valorBase*dias*pessoas;//o preço por dia é multiplicado pelo número de pessoas e pelo número de dias
		if(dias>5) {
			valorFinal=0;//Para reservas mais longas o valor da reserva deve ser zero
		} else {
			valorFinal=valorPorDia*dias;
		}
		return valorFinal;
	}
	
	@Override
	public String toString() {
		//Sobrescrita do método da classe Object. É usado para passar todas as informações de uma rede
		String txt="";
		txt="\tRede "+nome+" ("+id+"):\n\t\tEndereço: "+endereco.toString()+"\n\t\tPreço base: "+valorBase+"\n";
		return txt;
	}
}
