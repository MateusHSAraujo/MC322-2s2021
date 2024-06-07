package com.unicamp.mc322.lab04.VacinaCovid;
import com.unicamp.mc322.lab04.Operadores.*;
class Reserva {
	//Essa classe serve como comprovante imut�vel do agendamento da vacina��o de um usu�rio
	private String cpfDoUsuario;
	private String nomeDoPosto;
	private String diaDaReserva;
	private Date dataDoComprovante;
	Reserva(String cpf, String nome, String dia){
		cpfDoUsuario=cpf;
		nomeDoPosto=nome;
		diaDaReserva=dia;
		dataDoComprovante= new Date();
	}
	public String toString() {
		String txt = "-----Comprovante-----\n"; 
		txt += "Cpf: "+cpfDoUsuario+";\nPosto : "+nomeDoPosto+";\nDia da Semana agendado: "+diaDaReserva+";\nDia de emiss�o do comprovante: "+dataDoComprovante.toString()+"\n";
		txt+="--------------------\n";
		return txt;
	}
}
