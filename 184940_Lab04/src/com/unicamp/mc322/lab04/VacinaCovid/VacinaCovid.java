package com.unicamp.mc322.lab04.VacinaCovid;
import java.util.ArrayList;
import java.util.List;
import com.unicamp.mc322.lab04.Operadores.*;
import com.unicamp.mc322.lab04.Posto.*;
import com.unicamp.mc322.lab04.Usuario.*;

public class VacinaCovid {
	/*
	 * Essa classe tem a intenção de funcionar como o próprio aplicativo da vacinação, suas características são:
	 * -Ela é capaz de cadastrar usuários registrando seus nomes, cpfs, datas de nascimento, e endereços
	 * -Ela é capaz de cadastrar postos de saude registrando seus nomes, endereços, números máximos de atendimentos por dia e os dias de funcionamento por semana
	 * -Ela funciona se baseando em uma idade mínima atendida para todos os postos
	 * -Ela pode agendar um usuário que não tenha agendamentos pendentes  e tenha a idade mínima em um posto específico, no posto mais próximo a esse usuário, ou no posto com atendimento na semana mais próximo, retornando um comprovante imutável 
	 * -Ela pode imprimir as informações de todos os usuários e postos cadastrados
	 * -Ela pode alterar os dias de funcionamento de um posto
	 * */
	private List<PostoSaude> postosCadastrados;
	private List<Usuario> usuariosCadastrados;
	private int idadeAtendida;
	
	VacinaCovid() {
		//Esse método constrói o app iniciando as listas que armazenarão os cadastros
		postosCadastrados = new ArrayList<PostoSaude>();
		usuariosCadastrados = new ArrayList<Usuario>();
	}
	
	void cadastrarUsuario(String nome, String cpf, Date data, Point endereco) {
		//Esse método cadastra um novo usuário com nome,cpf,data e endereço fornecidos
		Usuario novoUsuario = new Usuario(nome,cpf,data,endereco);
		usuariosCadastrados.add(novoUsuario);
	}
	
	void cadastrarPosto(String nome,Point endereco,int maxAtendimento ,String[] diasDaSemana) {
		//Esse método cadastra um novo posto de saúde recebendo seu nome, endereço, número máximo de atendimentos em um dia e dias atendidos na semana
		PostoSaude novoPosto = new PostoSaude(nome,endereco,maxAtendimento,diasDaSemana);
		postosCadastrados.add(novoPosto);
	}
	
	void setIdadeMinimaAtendida(int novaIdade) {
		//Esse método atualiza a idade mínima abrangida na vacinação dos postos
		this.idadeAtendida=novaIdade;
	}
	
	private Erros usuarioTemCadastro(String cpf) {
		//Esse método verifica se o usuário está cadastrado no app para poder fazer seu agendamento, retornando um erro se não houver cadastro
		Erros retorno = Erros.USUARIO_NAO_CADASTRADO;
		for (Usuario usuarioIterado : usuariosCadastrados){
			if (usuarioIterado.cpf().equals(cpf)) {
				retorno = Erros.SUCESSO; 
			}
		}
		return retorno;
	}
	
	private int encontraUsuarioNaLista(String cpf) {
		//Esse método encontra a posição de um usuário cadastrado na lista de cadastros do app e retorna essa posição
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
		//Esse método seleciona um usuário cadastrado na lista de cadastros do app e retorna esse usuário
		int posUsuario = encontraUsuarioNaLista(cpf);
		Usuario usuarioDesejado = usuariosCadastrados.get(posUsuario);
		return usuarioDesejado;
	}
	
	private boolean postoTemCadastro(String nome) {
		//Esse método verifica se um posto está cadastrado no app e retorna um booleano em resposta
		boolean retorno = false;
		for (PostoSaude postoIterado : postosCadastrados){
			if (postoIterado.nome().equals(nome)) {
				retorno = true; 
			}
		}
		return retorno;
	}
	
	private int encontraPostoNaLista(String nome) {
		//Esse método encontra a posição de um posto cadastrado na lista de cadastros do app e retorna essa posição
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
		//Esse método seleciona um posto de saúde cadastrado na lista de cadastros do app e retorna esse posto
		int posPosto = encontraPostoNaLista(nome);
		PostoSaude postoDesejado = postosCadastrados.get(posPosto);
		return postoDesejado;
	}
	
	private boolean usuarioPodeAgendar(String cpf) {
		//Esse método verifica se um usuário não tem agendamentos pendentes, retornando um booleano em resposta
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
		//Esse metodo deve agendar um usuario em um posto desejado. Ele verifica se o usuario e o ponto tem cadastro, se o usuário já fez uma reserva e se ele tem idade apta para a vacinação. Se uma dessas condições não passarem ou seu a agenda do posto já tiver cheia para todos os dias, o método retorna uma reserva nula. Se todas as condições forem cumpridas e o posto tiver horário disponível, um comprovante com data, cpf do usuário, nome do posto e data de realização da e o dia da semana da reserva é retornado.
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
				System.out.println("Usuário não cadastrado ou com idade insuficiente, comprovante retornado nulo!");
			}else { 
				System.out.println("Posto não cadastrado, comprovante retornado nulo!");
			}
			retorno = null;
		}
		
		return retorno;
	}
	
	Reserva agendarPerto(String cpf) {
		//Esse método inicia o agendamento de um usuário válido no posto mais próximo ao seu endereço, verificando se a lista de postos cadastrados não é vazia;
		Reserva retorno =null;
		if (postosCadastrados.size()!=0) {
			retorno = realizaAgendamentoPerto(cpf); 
		} else {
			System.out.println("Não há postos cadastrados, comprovante retornado nulo!");
		}
		return retorno;
	}
	
	private Reserva realizaAgendamentoPerto(String cpf) {
		//Esse método efetiva o agendamento de um usuário válido no posto mais próximo a ele com vagas disponíveis
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
				System.out.println("Nenhum posto cadastrado disponível, comprovante retornado nulo!");
			}
		} else {
			System.out.println("Usuário não cadastrado ou com idade insuficiente, comprovante retornado nulo!");
		}
		return retorno;
	}
	
	Reserva agendarCedo(String cpf) {
		//Esse método inicia o agendamento de um usuário válido no posto com vagas no dia mais cedo da semana, verificando se a lista de postos cadastrados não é vazia;
		Reserva retorno =null;
		if (postosCadastrados.size()!=0) {
			retorno = realizaAgendamentoCedo(cpf); 
		} else {
			System.out.println("Não há postos cadastrados, comprovante retornado nulo!");
		}
		return retorno;
	}
	
	
	
	private Reserva realizaAgendamentoCedo(String cpf) {
		//Esse método efetiva o agendamento de um usuário válido no posto com vagas no dia mais cedo da semana
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
				System.out.println("Nenhum posto cadastrado disponível, comprovante retornado nulo!");
			}
		} else {
			System.out.println("Usuário não cadastrado ou com idade insuficiente, comprovante retornado nulo!");
		}
		return retorno;
	}
		
	private PostoSaude postoComVagaMaisCedo() {
		//Esse método verifica qual é o posto com vaga mais cedo na lista de postos cadastrados
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
		//Esse método verifica qual é o posto mais próximo com vagas a um ponto na lista de postos cadastrados
		PostoSaude postoDesejado = postosCadastrados.get(0);
		double menorDistancia =postoDesejado.distanciaDeP(coordenadas);
		for(int i=1; i<postosCadastrados.size();i++) {
			PostoSaude postoIterado = postosCadastrados.get(i);
			if(postoIterado.primeiroDiaComVaga()!=Semana.ERRO_SEMANA_CHEIA) {
				double possivelMenorDistância = postoIterado.distanciaDeP(coordenadas);
				if(menorDistancia>possivelMenorDistância) {
					menorDistancia = possivelMenorDistância;
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
		//Esse método converte uma constante de dias da semana em um valor inteiro para eventuais mapulações algébricas
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
			//Esse método imprime a situação de todos os postos cadastrados no app
			System.out.println("------Postos cadastrados------");
			for (PostoSaude postoIterado : postosCadastrados) {
				System.out.println(postoIterado.info()+"\n\n");
			}
		}
	
		public void imprimirCadastroUsuarios() {
			//Esse método imprime a situação de todos os usuários cadastrados no app
			System.out.println("------Usuários cadastrados------");
			for (Usuario usuarioIterado : usuariosCadastrados) {
				System.out.println(usuarioIterado.info()+"\n\n");
			}
		}
		
		public void mudarDiasDeAtendimento(String[] novosDias,String nomeDoPosto) {
			//Esse método permite a alteração da agenda de um posto cadastrado
			if (postoTemCadastro(nomeDoPosto)) {
				selecionaPosto(nomeDoPosto).alterarAgenda(novosDias);
			} else {
				System.out.println("Posto não cadastrado, operação inválida!");
			}

			
			
		}
		
		
	}
	
	
	
	

