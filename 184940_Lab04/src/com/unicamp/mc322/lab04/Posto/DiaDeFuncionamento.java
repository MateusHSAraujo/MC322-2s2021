package com.unicamp.mc322.lab04.Posto;

import com.unicamp.mc322.lab04.Operadores.Erros;
import com.unicamp.mc322.lab04.Operadores.Semana;
class DiaDeFuncionamento{
	//Essa classe serve para organizar os dias de funcionamento de um posto. Ela permite a comparação de antecedência entre dias da semana, a normalização de seu nomes bem como armazena os usuários agendados em um dia.  
	
	private Semana constante;
	private int valor;
	private String nome;
	private String[] agendados;
	
	private Semana normalizaDia(String dia) {
		// Esse método usa de expressões regulares para interpretar a qual dia a argumento passado na construção do objeto se refere
		Semana retorno=Semana.DOMINGO;
		if (dia.matches("domingo(\\s.*)?")) {
			retorno = Semana.DOMINGO;
		} else if (dia.matches("s[aá]bado(\\s.*)?")) {
			retorno = Semana.SABADO;
		} else if (dia.matches("segunda(-feira)?(\\s.*)?")) {
			retorno = Semana.SEGUNDA;
		} else if (dia.matches("ter[cç]a(-feira)?(\\s.*)?")) {
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
		//Essa função da o nome do dia baseando em sua constante
		String retorno="";
		switch (dia) {
		case DOMINGO:
			retorno="Domingo";
			break;
		case SABADO:
			retorno="Sábado";
			break;
		case SEGUNDA:
			retorno="Segunda-Feira";
			break;
		case TERCA:
			retorno="Terça-Feira";
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
		//Essa função da o nome do dia baseando em sua constante
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
		//Construtor da classe dia de funcionamento.Ele recebe o nome de um dia da semana e a quantidade máxima de atendimentos que o posto faz
		dia=dia.toLowerCase();
		Semana cteDoDia=normalizaDia(dia);
		this.constante=cteDoDia;
		this.nome=identificaNome(cteDoDia);
		this.valor=identificaValor(cteDoDia);
		if(cteDoDia==Semana.ERRO_DIA_INVALIDO) {
			System.out.println("Dia inválido, execução interrompida!");
			System.exit(0); //Esse erro está sendo tratado de forma brusca pois não é a intenção do laboratório
		}
		this.agendados= new String[atendimentosMax];
		
	}
	
	int antesOuDepois(DiaDeFuncionamento diaD){
		//Esse método avalia se um dia D ocorre antes ou depois na semana do que o dia usado para a chamada de método. Ele retorna -1 se o dia D ocorre antes, 1 se D ocorre depois e 0 se os dois dias são iguais. 
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
		//Esse método retorna a primeira posição vazia do dia
		int retorno=0;
		for(int i=0; i<agendados.length;i++) {
			if (agendados[i]==null) {
				retorno=i;
			}
		}
			
		return retorno;
	}
	
	Erros diaCheio () {
		//Esse método verifica está cheio
		Erros retorno = Erros.DIA_CHEIO;
		for(int i=0; i<agendados.length;i++) {
			if (agendados[i]==null) {
				retorno = Erros.SUCESSO;
			}
		}
		return retorno;
	}
	
	boolean temVaga() {
		//Esse método retorna uma booleana indicando a lotação do dia
		return (diaCheio()==Erros.DIA_CHEIO)? false:true;
	}
	
	Erros jaAgendado (String cpf) {
		//Esse método verifica se um usuário já está agendado no dia
		Erros retorno = Erros.SUCESSO;
		for(int i=0; i<agendados.length;i++) {
			if (agendados[i]==cpf) {
				retorno = Erros.JA_AGENDADO;
			}
		}
		return retorno;
	}
	
	
	Erros agendarUsuário(String cpf) {
		//Esse método agenda um cpf na fila do dia.
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
		//Esse método retorna a constante que identifica o dia da semana representado pelo objeto
		return this.constante;
	}
	
	public int vagasLivre() {
		//Esse método indica quantas vagas disponíveis um dia tem
		int vagas=0;
		for (String cpf: agendados) {
			if (cpf==null) {
				vagas++;
			}
		}
		return vagas;
	}
	
}
