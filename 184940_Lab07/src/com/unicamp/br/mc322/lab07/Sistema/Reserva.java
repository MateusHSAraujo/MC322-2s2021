package com.unicamp.br.mc322.lab07.Sistema;

class Reserva {
	//Classe criada com intuito de armazenar as informações de reserva
	
	//Como mencionado no enunciado, as reservas contém:
	private String id;//A ID do produto reservado
	private int participantes;//O número de pessoas que utilizarão do produto
	private int menores;//O número de menores que usarão do produto
	private int dias;//O número de dias que foram reservados
	
	Reserva(String id, int participantes, int menores, int dias){
		//Construtor da reserva. Ele é o único modo de se alterar os atributos da reserva, por isso ela pode ser usada como comprovante
		this.id=id;
		this.participantes=participantes;
		this.menores=menores;
		this.dias=dias;
	}
	
	String getId(){//Getter para o ID que a reserva armazena
		return id;
	}
	
	int numAdultos() { //Retorna o número de adultos que usufruirão do produto
		return participantes-menores;
	}
	
	int numMenores() { //Retorno o número de menores que usufruirão do produto
		return menores;
	}
	
	int dias() {// Retorna o número de dias de duração da reserva
		return dias;
	}
	
	@Override
	public String toString(){
		//Sobrescrita do método da classe Object. É usado para passar todas as informações de uma reserva
		String txt="Reserva do produto "+id+": Para "+numAdultos()+" adultos e "+menores+" menores por "+dias+" dias";
		return txt;
	}
}
