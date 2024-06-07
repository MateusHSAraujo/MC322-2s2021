package com.unicamp.br.mc322.lab07.Alugaveis.Residencias;

import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

public class Casa extends Residencia{
	//Classe para casas alugáveis. Ela herda de Residência o atributo nome, bem como os atributos e métodos padrões de podrutos de Alugáveis.
	
	//Pelo enunciado, toda casa tem as seguintes informações extras:
	private int quartos;//número de quartos
	private int banheiros;//numero de banheiros
	private boolean temPiscina;//Se tem ou não piscina

	public Casa(String id, double valor, Point endereco, String nome, int quartos, int banheiros,boolean temPiscina){
		//Construtor normal para casa alugável, setando seus atributos e atributos herdados
		super(id,valor,endereco,nome);
		this.banheiros=banheiros;
		this.quartos=quartos;
		this.temPiscina=temPiscina;
	}

	@Override
	public double valorReserva(int dias,  int adultos,int menores) {
		//Sobrescrita do método da classe Alugável, definido do mesmo modo que o indicado no enunciado
		double numBanheiros=this.banheiros,numQuartos=quartos;//convertendo o número de quartos e de banheiros de int para double
		double valorFinal, valorPorDia=valorBase*(numBanheiros/numQuartos);//o valor da diária é ajustada com um fator baseado na proporção entre banheiros e quartos
		if (temPiscina && dias<=7) {//Se tem piscina é cobrada uma diária efetiva extra nos primeiros 7 dias
			valorFinal=valorPorDia*2*dias;
		} else if(dias>7){
			valorFinal=valorPorDia*(dias+7);
		} else {//Se não tem piscina, são cobradas diárias efetivas para cada dia de reserva
			valorFinal=valorPorDia*dias;
		}
		return valorFinal;
	}
	
	@Override
	public String toString() {
		//Sobrescrita do método da classe Object. É usado para passar todas as informações de uma casa
		String txt="";
		txt="\tCasa "+nome+" ("+id+"):\n\t\tEndereço: "+endereco.toString()+"\n\t\tQuantidade de quartos: "+quartos+"\n\t\tQuantidade de banheiros:"+banheiros+"\n\t\tTem pisicna? "+(temPiscina? "Sim":"Não")+"\n\t\tPreço base: "+valorBase+"\n";
		return txt;
	}
}
