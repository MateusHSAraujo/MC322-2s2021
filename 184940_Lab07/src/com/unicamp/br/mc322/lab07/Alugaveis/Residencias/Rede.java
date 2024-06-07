package com.unicamp.br.mc322.lab07.Alugaveis.Residencias;

import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

public class Rede extends Residencia{
	//Classe para redes alug�veis. Ela herda de Resid�ncia o atributo nome, bem como os atributos e m�todos padr�es de podrutos de Alug�veis.
	public Rede(String id, double valor, Point endereco, String nome){
		//Construtor normal para mans�o redes. Essa classe n�o tem atributos extras ent�o ela seta os herdados
		super(id,valor,endereco,nome);
	}

	@Override
	public double valorReserva(int dias,  int adultos,int menores) {
		//Sobrescrita do m�todo da classe Alug�vel, definido do mesmo modo que o indicado no enunciado
		int pessoas=adultos+menores;
		double valorFinal,valorPorDia=valorBase*dias*pessoas;//o pre�o por dia � multiplicado pelo n�mero de pessoas e pelo n�mero de dias
		if(dias>5) {
			valorFinal=0;//Para reservas mais longas o valor da reserva deve ser zero
		} else {
			valorFinal=valorPorDia*dias;
		}
		return valorFinal;
	}
	
	@Override
	public String toString() {
		//Sobrescrita do m�todo da classe Object. � usado para passar todas as informa��es de uma rede
		String txt="";
		txt="\tRede "+nome+" ("+id+"):\n\t\tEndere�o: "+endereco.toString()+"\n\t\tPre�o base: "+valorBase+"\n";
		return txt;
	}
}
