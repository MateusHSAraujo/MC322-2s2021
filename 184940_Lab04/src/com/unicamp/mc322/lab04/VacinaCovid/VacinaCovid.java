package com.unicamp.mc322.lab04.VacinaCovid;
import java.util.ArrayList;
import java.util.List;
import com.unicamp.mc322.lab04.Operadores.*;
import com.unicamp.mc322.lab04.Posto.*;
import com.unicamp.mc322.lab04.Usuario.*;

public class VacinaCovid {
	/*
	 * Essa classe tem a inten��o de funcionar como o pr�prio aplicativo da vacina��o, suas caracter�sticas s�o:
	 * -Ela � capaz de cadastrar usu�rios registrando seus nomes, cpfs, datas de nascimento, e endere�os
	 * -Ela � capaz de cadastrar postos de saude registrando seus nomes, endere�os, n�meros m�ximos de atendimentos por dia e os dias de funcionamento por semana
	 * -Ela funciona se baseando em uma idade m�nima atendida para todos os postos
	 * -Ela pode agendar um usu�rio que n�o tenha agendamentos pendentes  e tenha a idade m�nima em um posto espec�fico, no posto mais pr�ximo a esse usu�rio, ou no posto com atendimento na semana mais pr�ximo, retornando um comprovante imut�vel 
	 * -Ela pode imprimir as informa��es de todos os usu�rios e postos cadastrados
	 * -Ela pode alterar os dias de funcionamento de um posto
	 * */
	private List<PostoSaude> postosCadastrados;
	private List<Usuario> usuariosCadastrados;
	private int idadeAtendida;
	
	VacinaCovid() {
		//Esse m�todo constr�i o app iniciando as listas que armazenar�o os cadastros
		postosCadastrados = new ArrayList<PostoSaude>();
		usuariosCadastrados = new ArrayList<Usuario>();
	}
	
	void cadastrarUsuario(String nome, String cpf, Date data, Point endereco) {
		//Esse m�todo cadastra um novo usu�rio com nome,cpf,data e endere�o fornecidos
		Usuario novoUsuario = new Usuario(nome,cpf,data,endereco);
		usuariosCadastrados.add(novoUsuario);
	}
	
	void cadastrarPosto(String nome,Point endereco,int maxAtendimento ,String[] diasDaSemana) {
		//Esse m�todo cadastra um novo posto de sa�de recebendo seu nome, endere�o, n�mero m�ximo de atendimentos em um dia e dias atendidos na semana
		PostoSaude novoPosto = new PostoSaude(nome,endereco,maxAtendimento,diasDaSemana);
		postosCadastrados.add(novoPosto);
	}
	
	void setIdadeMinimaAtendida(int novaIdade) {
		//Esse m�todo atualiza a idade m�nima abrangida na vacina��o dos postos
		this.idadeAtendida=novaIdade;
	}
	
	private Erros usuarioTemCadastro(String cpf) {
		//Esse m�todo verifica se o usu�rio est� cadastrado no app para poder fazer seu agendamento, retornando um erro se n�o houver cadastro
		Erros retorno = Erros.USUARIO_NAO_CADASTRADO;
		for (Usuario usuarioIterado : usuariosCadastrados){
			if (usuarioIterado.cpf().equals(cpf)) {
				retorno = Erros.SUCESSO; 
			}
		}
		return retorno;
	}
	
	private int encontraUsuarioNaLista(String cpf) {
		//Esse m�todo encontra a posi��o de um usu�rio cadastrado na lista de cadastros do app e retorna essa posi��o
		int retorno=0;
		for (int i=0;i<usuariosCadastrados.size();i++) {
			Usuario usuarioIterado = usuariosCadastrados.get(i);
			if (usuarioIterado.cpf().equals(cpf)) {
				retorno = i; 
			}
		}
		return retorno;
	}
	

	private Usuario selecionaUsuario(String cpf) {
		//Esse m�todo seleciona um usu�rio cadastrado na lista de cadastros do app e retorna esse usu�rio
		int posUsuario = encontraUsuarioNaLista(cpf);
		Usuario usuarioDesejado = usuariosCadastrados.get(posUsuario);
		return usuarioDesejado;
	}
	
	private boolean postoTemCadastro(String nome) {
		//Esse m�todo verifica se um posto est� cadastrado no app e retorna um booleano em resposta
		boolean retorno = false;
		for (PostoSaude postoIterado : postosCadastrados){
			if (postoIterado.nome().equals(nome)) {
				retorno = true; 
			}
		}
		return retorno;
	}
	
	private int encontraPostoNaLista(String nome) {
		//Esse m�todo encontra a posi��o de um posto cadastrado na lista de cadastros do app e retorna essa posi��o
		int retorno=0;
		for (int i=0;i<postosCadastrados.size();i++) {
			PostoSaude postoIterado = postosCadastrados.get(i);
			if (postoIterado.nome().equals(nome)) {
				retorno = i; 
			}
		}
		return retorno;
	}
	
	
	private PostoSaude selecionaPosto(String nome) {
		//Esse m�todo seleciona um posto de sa�de cadastrado na lista de cadastros do app e retorna esse posto
		int posPosto = encontraPostoNaLista(nome);
		PostoSaude postoDesejado = postosCadastrados.get(posPosto);
		return postoDesejado;
	}
	
	private boolean usuarioPodeAgendar(String cpf) {
		//Esse m�todo verifica se um usu�rio n�o tem agendamentos pendentes, retornando um booleano em resposta
		Erros  usuarioTemCadastro = usuarioTemCadastro(cpf);
		boolean retorno=false;
		if (usuarioTemCadastro!=Erros.USUARIO_NAO_CADASTRADO) {
			Usuario usuarioDesejado = selecionaUsuario(cpf);
			if (!usuarioDesejado.possuiReserva() && usuarioDesejado.temIdade(idadeAtendida)){
				retorno=true;
			}
		}
		return retorno;
	}
	
	private boolean tentaAgendamento(PostoSaude posto, String cpf) {
		return (posto.agendarUsuarioNoDia(cpf)==Erros.SUCESSO)? true:false;
	}
	
	Reserva agendar(String cpf,String nomeDoPosto) {
		//Esse metodo deve agendar um usuario em um posto desejado. Ele verifica se o usuario e o ponto tem cadastro, se o usu�rio j� fez uma reserva e se ele tem idade apta para a vacina��o. Se uma dessas condi��es n�o passarem ou seu a agenda do posto j� tiver cheia para todos os dias, o m�todo retorna uma reserva nula. Se todas as condi��es forem cumpridas e o posto tiver hor�rio dispon�vel, um comprovante com data, cpf do usu�rio, nome do posto e data de realiza��o da e o dia da semana da reserva � retornado.
		Reserva retorno=null;
		if (usuarioPodeAgendar(cpf) && postoTemCadastro(nomeDoPosto)) {
			Usuario usuarioDesejado = selecionaUsuario(cpf);
			PostoSaude postoDesejado = selecionaPosto(nomeDoPosto);
			if (tentaAgendamento(postoDesejado,cpf)) {
				usuarioDesejado.setReserva(true);
				Reserva comprovante = new Reserva(usuarioDesejado.cpf(),postoDesejado.nome(),postoDesejado.agendadoEmQualDia(cpf));
				retorno = comprovante;
			} else {
				System.out.println("Semana lotada, comprovante retornado nulo!");
				retorno=null;
			}
		} else {
			if (!usuarioPodeAgendar(cpf)) {
				System.out.println("Usu�rio n�o cadastrado ou com idade insuficiente, comprovante retornado nulo!");
			}else { 
				System.out.println("Posto n�o cadastrado, comprovante retornado nulo!");
			}
			retorno = null;
		}
		
		return retorno;
	}
	
	Reserva agendarPerto(String cpf) {
		//Esse m�todo inicia o agendamento de um usu�rio v�lido no posto mais pr�ximo ao seu endere�o, verificando se a lista de postos cadastrados n�o � vazia;
		Reserva retorno =null;
		if (postosCadastrados.size()!=0) {
			retorno = realizaAgendamentoPerto(cpf); 
		} else {
			System.out.println("N�o h� postos cadastrados, comprovante retornado nulo!");
		}
		return retorno;
	}
	
	private Reserva realizaAgendamentoPerto(String cpf) {
		//Esse m�todo efetiva o agendamento de um usu�rio v�lido no posto mais pr�ximo a ele com vagas dispon�veis
		Reserva retorno=null;
		if (usuarioPodeAgendar(cpf)) {
			Usuario usuarioDesejado =selecionaUsuario(cpf);
			PostoSaude postoDesejado = postoMaisPerto(usuarioDesejado.getEndereco());
			if (postoDesejado!=null) {
				if(tentaAgendamento(postoDesejado,cpf)) {
					usuarioDesejado.setReserva(true);
					Reserva comprovante = new Reserva(usuarioDesejado.cpf(),postoDesejado.nome(),postoDesejado.agendadoEmQualDia(cpf));
					retorno = comprovante;
				} 
			} else {
				System.out.println("Nenhum posto cadastrado dispon�vel, comprovante retornado nulo!");
			}
		} else {
			System.out.println("Usu�rio n�o cadastrado ou com idade insuficiente, comprovante retornado nulo!");
		}
		return retorno;
	}
	
	Reserva agendarCedo(String cpf) {
		//Esse m�todo inicia o agendamento de um usu�rio v�lido no posto com vagas no dia mais cedo da semana, verificando se a lista de postos cadastrados n�o � vazia;
		Reserva retorno =null;
		if (postosCadastrados.size()!=0) {
			retorno = realizaAgendamentoCedo(cpf); 
		} else {
			System.out.println("N�o h� postos cadastrados, comprovante retornado nulo!");
		}
		return retorno;
	}
	
	
	
	private Reserva realizaAgendamentoCedo(String cpf) {
		//Esse m�todo efetiva o agendamento de um usu�rio v�lido no posto com vagas no dia mais cedo da semana
		Reserva retorno=null;
		if (usuarioPodeAgendar(cpf)) {
			Usuario usuarioDesejado =selecionaUsuario(cpf);
			PostoSaude postoDesejado = postoComVagaMaisCedo();
			if (postoDesejado!=null) {
				if(tentaAgendamento(postoDesejado,cpf)) {
					usuarioDesejado.setReserva(true);
					Reserva comprovante = new Reserva(usuarioDesejado.cpf(),postoDesejado.nome(),postoDesejado.agendadoEmQualDia(cpf));
					retorno = comprovante;
				}
			} else {
				System.out.println("Nenhum posto cadastrado dispon�vel, comprovante retornado nulo!");
			}
		} else {
			System.out.println("Usu�rio n�o cadastrado ou com idade insuficiente, comprovante retornado nulo!");
		}
		return retorno;
	}
		
	private PostoSaude postoComVagaMaisCedo() {
		//Esse m�todo verifica qual � o posto com vaga mais cedo na lista de postos cadastrados
		PostoSaude postoDesejado = postosCadastrados.get(0);
		int diaMaisCedo =converteCteDiaEmInt(postoDesejado.primeiroDiaComVaga());
		for(int i=1; i<postosCadastrados.size();i++) {
			PostoSaude postoIterado = postosCadastrados.get(i);
			int possivelDiaMaisCedo = converteCteDiaEmInt(postoIterado.primeiroDiaComVaga());
			if(possivelDiaMaisCedo>=0) {
				if (diaMaisCedo>possivelDiaMaisCedo) {
					diaMaisCedo=possivelDiaMaisCedo;
					postoDesejado = postoIterado;
				}
			}
		}
		if(diaMaisCedo<0) {
			postoDesejado=null;
		}
		return postoDesejado;
		
	}
	
	private PostoSaude postoMaisPerto(Point coordenadas) {
		//Esse m�todo verifica qual � o posto mais pr�ximo com vagas a um ponto na lista de postos cadastrados
		PostoSaude postoDesejado = postosCadastrados.get(0);
		double menorDistancia =postoDesejado.distanciaDeP(coordenadas);
		for(int i=1; i<postosCadastrados.size();i++) {
			PostoSaude postoIterado = postosCadastrados.get(i);
			if(postoIterado.primeiroDiaComVaga()!=Semana.ERRO_SEMANA_CHEIA) {
				double possivelMenorDist�ncia = postoIterado.distanciaDeP(coordenadas);
				if(menorDistancia>possivelMenorDist�ncia) {
					menorDistancia = possivelMenorDist�ncia;
					postoDesejado = postoIterado;
				}
			}
		}
		
		if(postoDesejado.primeiroDiaComVaga()==Semana.ERRO_SEMANA_CHEIA) {
			postoDesejado = null;
		}
		
		return postoDesejado;
	}
	
	private int converteCteDiaEmInt(Semana cteDia) {
		//Esse m�todo converte uma constante de dias da semana em um valor inteiro para eventuais mapula��es alg�bricas
		int retorno=0;	
		switch (cteDia) {
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
				retorno = -1;
				break;
			}
			return retorno;
		}
		
		public void imprimirSituacaoPostos() {
			//Esse m�todo imprime a situa��o de todos os postos cadastrados no app
			System.out.println("------Postos cadastrados------");
			for (PostoSaude postoIterado : postosCadastrados) {
				System.out.println(postoIterado.info()+"\n\n");
			}
		}
	
		public void imprimirCadastroUsuarios() {
			//Esse m�todo imprime a situa��o de todos os usu�rios cadastrados no app
			System.out.println("------Usu�rios cadastrados------");
			for (Usuario usuarioIterado : usuariosCadastrados) {
				System.out.println(usuarioIterado.info()+"\n\n");
			}
		}
		
		public void mudarDiasDeAtendimento(String[] novosDias,String nomeDoPosto) {
			//Esse m�todo permite a altera��o da agenda de um posto cadastrado
			if (postoTemCadastro(nomeDoPosto)) {
				selecionaPosto(nomeDoPosto).alterarAgenda(novosDias);
			} else {
				System.out.println("Posto n�o cadastrado, opera��o inv�lida!");
			}

			
			
		}
		
		
	}
	
	
	
	

