package com.unicamp.mc322.lab04.Posto;

import com.unicamp.mc322.lab04.Operadores.*;

public class PostoSaude {

	private String nome;
	private int maxAtendimento;
	private DiaDeFuncionamento[] agenda;
	private Point endereco;
	
	public PostoSaude(String nome,Point endereco, int maxAtendimento ,String[] diasDaSemana ) {
		//Esse é o método construtor da classe posto de saúde. Ele recebe um nome, uma quantidade de atendimentos máximo por dia, os dias da semana que o posto opera (no máximo 3, do contrário gera um erro) e seu endereço (x,y)
		this.nome=nome;
		this.maxAtendimento=maxAtendimento;
		this.endereco=endereco;
		montaAgenda(diasDaSemana);
		
	}
	
	public String nome() {
		//Esse métdo retorna o nome do posto
		return nome;
	}
	
	
	private void montaAgenda(String[] diasDaSemana) {
		//Contrói a agenda do posto de saúde com elementos da classe DiaDeFuncionamento configurados da forma desejada (com número máximo de atendimentos estabelecido)
		int qtdDias = diasDaSemana.length;
		if (qtdDias<=3 && qtdDias>0) {
			this.agenda = new DiaDeFuncionamento[qtdDias];//Inicializando o array de dias de funcionamento do posto
			for(int i=0;i<qtdDias;i++) { 
				//Tranformando a informação dos dias da semanas passado pelo app em objetos do tipo dia
				DiaDeFuncionamento novoDia = new DiaDeFuncionamento(diasDaSemana[i],this.maxAtendimento);
				this.agenda[i]= novoDia; //Aloca os dias criados na agenda de funcionamento
			}
			sortAgenda(); //Ordena os dias por sua ordem de ocorrência na semana para facilitar operações futuras
		} else{ 
			//Pra evitar problemas, o método gera um erro se dois dias iguais forem adicionados na mesma agenda
			System.out.println("Número de dias iválido, execução encerrada!");
			System.exit(0);
		}
	}
	
	private void sortAgenda() {
		//Organiza os dias da agenda por sua ordem que ocorre na semana, chamada sempre que uma nova agenda é montada e alterada
		boolean houveTroca=false;
		while (houveTroca) {
			houveTroca = false;
			for (int i = 0; i < agenda.length; i++) {
				if (agenda[i].antesOuDepois(agenda[i+1])==1) {
					DiaDeFuncionamento auxiliar = agenda[i+1] ;
					agenda[i] = agenda[i+1];
					agenda[i+1] = auxiliar;
					houveTroca = true;
				} else if(i==0) {
					System.out.println("Dias repetidos adicionados na agenda, erro crítico!");
					System.exit(0);
				}
			}
		}
	}

	public double distanciaDeP(Point coordenadas) {
		//Esse método calcula a distância do posto para um certo ponto (x,y) retornando ela. Deve ser usada para estimar qual posto está mais perto do usuário
		return this.endereco.distance(coordenadas);
	}
	
	public String agendadoEmQualDia (String cpf) {
		//Esse método verifica em qual dia um certo usuário está cadastrado nesse posto
		String retorno = "";
		for (DiaDeFuncionamento dias : agenda) {
			if (dias.jaAgendado(cpf)==Erros.JA_AGENDADO) {
				retorno = dias.toString();
			}
		}
		return retorno;
	}
	
	public Erros agendarUsuarioNoDia(String cpf) {
		// Essa função agenda um usuário pelo seu cpf em um determinado dia, retornando SUCESSO se o agendamento tiver sido efetivado e DIA_CHEIO se o dia estiver cheio
		 Erros retorno = Erros.SUCESSO,tentativa; 
		for(DiaDeFuncionamento diaAgenda : agenda) {
			tentativa = diaAgenda.agendarUsuário(cpf);
			if (tentativa == Erros.SUCESSO) {
				retorno = Erros.SUCESSO;
				break;
			} else {
				retorno = Erros.DIA_CHEIO;
			}
		}
	
		return retorno;
	}
	
	public String info() {
		//Esse método retorna uma string contendo as informações do posto e de seus dias de funcionamento para eventual impressões
		String txt;
		txt = "\tNome: "+nome+";\n\tEndereço: "+endereco.toString()+";\n\tDias de Atendimento:\n";
		for (DiaDeFuncionamento diasDeAtendimento : agenda) {
			txt= txt+"\t\t"+diasDeAtendimento.toString()+": "+diasDeAtendimento.vagasLivre()+" vagas disponíveis\n";
		}
		return txt;
	}
	
	public boolean equals(PostoSaude postoA) {
		return nome==postoA.nome;
	}
		
	
	public Semana primeiroDiaComVaga() {
		//Esse método verifica qual o dia mais cedo ,dentre os dias de atuação, com uma vaga livre. Retornando ERRO_SEMANA_CHEIA se nenhum dia tiver vagas disponíveis. 
		Semana retorno = Semana.ERRO_SEMANA_CHEIA;
		for (DiaDeFuncionamento dia: agenda) {
			if (dia.temVaga()) {
				retorno = dia.getConstante();
				break;
			}
		}
		return retorno;
	}
	
	public void alterarAgenda(String[] novaAgenda) {
		//Esse método permite a alteração da agenda de um posto
		montaAgenda(novaAgenda);
	}
	
}
