package com.unicamp.br.mc322.lab07.Alugaveis.Residencias;

import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;

public class Casa extends Residencia{
	//Classe para casas alug�veis. Ela herda de Resid�ncia o atributo nome, bem como os atributos e m�todos padr�es de podrutos de Alug�veis.
	
	//Pelo enunciado, toda casa tem as seguintes informa��es extras:
	private int quartos;//n�mero de quartos
	private int banheiros;//numero de banheiros
	private boolean temPiscina;//Se tem ou n�o piscina

	public Casa(String id, double valor, Point endereco, String nome, int quartos, int banheiros,boolean temPiscina){
		//Construtor normal para casa alug�vel, setando seus atributos e atributos herdados
		super(id,valor,endereco,nome);
		this.banheiros=banheiros;
		this.quartos=quartos;
		this.temPiscina=temPiscina;
	}

	@Override
	public double valorReserva(int dias,  int adultos,int menores) {
		//Sobrescrita do m�todo da classe Alug�vel, definido do mesmo modo que o indicado no enunciado
		double numBanheiros=this.banheiros,numQuartos=quartos;//convertendo o n�mero de quartos e de banheiros de int para double
		double valorFinal, valorPorDia=valorBase*(numBanheiros/numQuartos);//o valor da di�ria � ajustada com um fator baseado na propor��o entre banheiros e quartos
		if (temPiscina && dias<=7) {//Se tem piscina � cobrada uma di�ria efetiva extra nos primeiros 7 dias
			valorFinal=valorPorDia*2*dias;
		} else if(dias>7){
			valorFinal=valorPorDia*(dias+7);
		} else {//Se n�o tem piscina, s�o cobradas di�rias efetivas para cada dia de reserva
			valorFinal=valorPorDia*dias;
		}
		return valorFinal;
	}
	
	@Override
	public String toString() {
		//Sobrescrita do m�todo da classe Object. � usado para passar todas as informa��es de uma casa
		String txt="";
		txt="\tCasa "+nome+" ("+id+"):\n\t\tEndere�o: "+endereco.toString()+"\n\t\tQuantidade de quartos: "+quartos+"\n\t\tQuantidade de banheiros:"+banheiros+"\n\t\tTem pisicna? "+(temPiscina? "Sim":"N�o")+"\n\t\tPre�o base: "+valorBase+"\n";
		return txt;
	}
}
