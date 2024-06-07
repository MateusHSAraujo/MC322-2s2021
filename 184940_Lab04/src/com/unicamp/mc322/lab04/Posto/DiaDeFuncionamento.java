package com.unicamp.mc322.lab04.Posto;

import com.unicamp.mc322.lab04.Operadores.Erros;
import com.unicamp.mc322.lab04.Operadores.Semana;
class DiaDeFuncionamento{
	//Essa classe serve para organizar os dias de funcionamento de um posto. Ela permite a compara��o de anteced�ncia entre dias da semana, a normaliza��o de seu nomes bem como armazena os usu�rios agendados em um dia.  
	
	private Semana constante;
	private int valor;
	private String nome;
	private String[] agendados;
	
	private Semana normalizaDia(String dia) {
		// Esse m�todo usa de express�es regulares para interpretar a qual dia a argumento passado na constru��o do objeto se refere
		Semana retorno=Semana.DOMINGO;
		if (dia.matches("domingo(\\s.*)?")) {
			retorno = Semana.DOMINGO;
		} else if (dia.matches("s[a�]bado(\\s.*)?")) {
			retorno = Semana.SABADO;
		} else if (dia.matches("segunda(-feira)?(\\s.*)?")) {
			retorno = Semana.SEGUNDA;
		} else if (dia.matches("ter[c�]a(-feira)?(\\s.*)?")) {
			retorno = Semana.TERCA;
		} else if (dia.matches("quarta(-feira)?(\\s.*)?")) {
			retorno = Semana.QUARTA;
		} else if (dia.matches("quinta(-feira)?(\\s.*)?")) {
			retorno = Semana.QUINTA;
		} else if(dia.matches("sexta(-feira)?(\\s.*)?")) {
			retorno = Semana.SEXTA;
		} else {
			retorno=Semana.ERRO_DIA_INVALIDO;
		}
		return retorno;
	}
	
	private String identificaNome(Semana dia) {
		//Essa fun��o da o nome do dia baseando em sua constante
		String retorno="";
		switch (dia) {
		case DOMINGO:
			retorno="Domingo";
			break;
		case SABADO:
			retorno="S�bado";
			break;
		case SEGUNDA:
			retorno="Segunda-Feira";
			break;
		case TERCA:
			retorno="Ter�a-Feira";
			break;
		case QUARTA:
			retorno="Quarta-Feira";
			break;
		case QUINTA:
			retorno="Quinta-Feira";
			break;
		case SEXTA:
			retorno = "Sexta-Feira";
			break;
		default:
			break;
		}
		return retorno;
	}
	
	private int identificaValor(Semana dia) {
		//Essa fun��o da o nome do dia baseando em sua constante
		int retorno=-1;
		switch (dia) {
		case DOMINGO:
			retorno=1;
			break;
		case SABADO:
			retorno=7;
			break;
		case SEGUNDA:
			retorno=2;
			break;
		case TERCA:
			retorno=3;
			break;
		case QUARTA:
			retorno=4;
			break;
		case QUINTA:
			retorno=5;
			break;
		case SEXTA:
			retorno =6;
			break;
		default:
			break;
		}
		return retorno;
	}
	
	DiaDeFuncionamento(String dia, int atendimentosMax){
		//Construtor da classe dia de funcionamento.Ele recebe o nome de um dia da semana e a quantidade m�xima de atendimentos que o posto faz
		dia=dia.toLowerCase();
		Semana cteDoDia=normalizaDia(dia);
		this.constante=cteDoDia;
		this.nome=identificaNome(cteDoDia);
		this.valor=identificaValor(cteDoDia);
		if(cteDoDia==Semana.ERRO_DIA_INVALIDO) {
			System.out.println("Dia inv�lido, execu��o interrompida!");
			System.exit(0); //Esse erro est� sendo tratado de forma brusca pois n�o � a inten��o do laborat�rio
		}
		this.agendados= new String[atendimentosMax];
		
	}
	
	int antesOuDepois(DiaDeFuncionamento diaD){
		//Esse m�todo avalia se um dia D ocorre antes ou depois na semana do que o dia usado para a chamada de m�todo. Ele retorna -1 se o dia D ocorre antes, 1 se D ocorre depois e 0 se os dois dias s�o iguais. 
		int retorno = 0;
		if (this.valor<diaD.valor) {
			retorno=-1;
		} else if(this.valor==diaD.valor) {
			retorno=0;
		} else{
			retorno = 1;
		}
		return retorno;
	}
	
	boolean equals(DiaDeFuncionamento diaD) {
		return this.valor==diaD.valor;
	}
	
	int posicaoAgendamento(String cpf) {
		//Esse m�todo retorna a primeira posi��o vazia do dia
		int retorno=0;
		for(int i=0; i<agendados.length;i++) {
			if (agendados[i]==null) {
				retorno=i;
			}
		}
			
		return retorno;
	}
	
	Erros diaCheio () {
		//Esse m�todo verifica est� cheio
		Erros retorno = Erros.DIA_CHEIO;
		for(int i=0; i<agendados.length;i++) {
			if (agendados[i]==null) {
				retorno = Erros.SUCESSO;
			}
		}
		return retorno;
	}
	
	boolean temVaga() {
		//Esse m�todo retorna uma booleana indicando a lota��o do dia
		return (diaCheio()==Erros.DIA_CHEIO)? false:true;
	}
	
	Erros jaAgendado (String cpf) {
		//Esse m�todo verifica se um usu�rio j� est� agendado no dia
		Erros retorno = Erros.SUCESSO;
		for(int i=0; i<agendados.length;i++) {
			if (agendados[i]==cpf) {
				retorno = Erros.JA_AGENDADO;
			}
		}
		return retorno;
	}
	
	
	Erros agendarUsu�rio(String cpf) {
		//Esse m�todo agenda um cpf na fila do dia.
		Erros retorno, diaCheio=diaCheio();
		if (diaCheio != Erros.DIA_CHEIO) {
			agendados[posicaoAgendamento(cpf)]=cpf;
			retorno = Erros.SUCESSO;
		} else {
			retorno = diaCheio;
		}
		return retorno;
	}
	
	public String toString(){
		return this.nome;
	}
	
	public Semana getConstante() {
		//Esse m�todo retorna a constante que identifica o dia da semana representado pelo objeto
		return this.constante;
	}
	
	public int vagasLivre() {
		//Esse m�todo indica quantas vagas dispon�veis um dia tem
		int vagas=0;
		for (String cpf: agendados) {
			if (cpf==null) {
				vagas++;
			}
		}
		return vagas;
	}
	
}
