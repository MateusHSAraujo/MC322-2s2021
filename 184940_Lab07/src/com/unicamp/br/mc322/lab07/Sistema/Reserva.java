package com.unicamp.br.mc322.lab07.Sistema;

class Reserva {
	//Classe criada com intuito de armazenar as informa��es de reserva
	
	//Como mencionado no enunciado, as reservas cont�m:
	private String id;//A ID do produto reservado
	private int participantes;//O n�mero de pessoas que utilizar�o do produto
	private int menores;//O n�mero de menores que usar�o do produto
	private int dias;//O n�mero de dias que foram reservados
	
	Reserva(String id, int participantes, int menores, int dias){
		//Construtor da reserva. Ele � o �nico modo de se alterar os atributos da reserva, por isso ela pode ser usada como comprovante
		this.id=id;
		this.participantes=participantes;
		this.menores=menores;
		this.dias=dias;
	}
	
	String getId(){//Getter para o ID que a reserva armazena
		return id;
	}
	
	int numAdultos() { //Retorna o n�mero de adultos que usufruir�o do produto
		return participantes-menores;
	}
	
	int numMenores() { //Retorno o n�mero de menores que usufruir�o do produto
		return menores;
	}
	
	int dias() {// Retorna o n�mero de dias de dura��o da reserva
		return dias;
	}
	
	@Override
	public String toString(){
		//Sobrescrita do m�todo da classe Object. � usado para passar todas as informa��es de uma reserva
		String txt="Reserva do produto "+id+": Para "+numAdultos()+" adultos e "+menores+" menores por "+dias+" dias";
		return txt;
	}
}
