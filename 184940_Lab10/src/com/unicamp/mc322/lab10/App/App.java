package com.unicamp.mc322.lab10.App;

import java.util.ArrayList;
import java.util.List;

import com.unicamp.mc322.lab10.App.Date.Date;
import com.unicamp.mc322.lab10.App.Usuario.*;
import com.unicamp.mc322.lab10.App.Veiculo.Veiculo;
import com.unicamp.mc322.lab10.App.Viagem.Viagem;

public class App {
	/*Classe respons�vel pelo pr�prio aplicativo. Assim como pedido no enunciado, temos que ela...
	-Armazena as informa��es de todos os usu�rios da plataforma em duas listas (uma de passageiros e outra de motoristas).
	-� capaz de cadastrar passageiros, motoristas e ve�culos.
	-� capaz de criar, armazenar e gerenciar viagens, eventualmente adicionando paradas � elas.
	*/
	private List<Passageiro> passageirosCadastrados;
	private List<Motorista> motoristasCadastrados;
	private List<Veiculo> veiculosCadastrados;
	private List<Viagem> viagensRegistradas;
	
	App(){
		passageirosCadastrados=new ArrayList<Passageiro>();
		motoristasCadastrados=new ArrayList<Motorista>();
		veiculosCadastrados=new ArrayList<Veiculo>();
		viagensRegistradas=new ArrayList<Viagem>();
	}
	
	private boolean passageiroTemCadastrado(String cpf) {
		//Verifica se um passageiro j� est� cadastrado no app (cpf � �nico por pessoa)
		boolean cpfJaCadastrado=false;
		for(Passageiro passageiroIterado : passageirosCadastrados) {
			if (passageiroIterado.compararCpf(cpf)) {
				cpfJaCadastrado=true;
			}
		}
		return cpfJaCadastrado;
	}
	
	
	
	public void cadastrarPassageiro(String nome,String cpf, String cartao, Date aniversario) {
		//Cadastra um passageiro que ainda n�o esteja cadastrado recebendo os par�metros necess�rios indicados pelo enunciado
		if(!passageiroTemCadastrado(cpf)) {
			Passageiro NovoPassageiro = new Passageiro(nome,cpf,cartao,aniversario);
			passageirosCadastrados.add(NovoPassageiro);
		} else {
			System.out.println("Cpf j� cadastrado, cadastro n�o efetivado!\n");
		}
	}
	
	private boolean motoristaTemCadastro(String cnh) {
		//Verifica se um motorista j� est� cadastrado no app (cnh � �nica por motorista). Foi utilizada a cnh e n�o o cpf do motorista pois assim abre-se a possibilidade de um motorista se cadastrar como passageiro se desejar
		boolean cnhJaCadastrada=false;
		for(Motorista motoristaIterado : motoristasCadastrados) {
			if (motoristaIterado.compararCnh(cnh)) {
				cnhJaCadastrada=true;
			}
		}
		return cnhJaCadastrada;
	}
	
	private Motorista selecionaMotoristaCadastrado (String cnh) {
		//Seleciona o motorista indicado pela cnh passada como par�metro na lista de motorista cadastrados
		Motorista motoristaDesejado=null;
		for(Motorista motoristaIterado: motoristasCadastrados) {
			if(motoristaIterado.compararCnh(cnh)) {
				motoristaDesejado=motoristaIterado;
			}
		}
		return motoristaDesejado;
	}
	
	public void cadastrarMotorista(String nome,String cpf, String cartao, Date aniversario,String habilitacao) {
		//Cadastra um motorista que ainda n�o estaja cadastrado no app recebendo os par�metros necess�rios indicados pelo enunciado
		if(!motoristaTemCadastro(habilitacao)) {
			Motorista novoMotorista = new Motorista(nome,cpf,cartao,aniversario,habilitacao);
			motoristasCadastrados.add(novoMotorista);
		} else {
			System.out.println("Cnh j� cadastrada, cadastro n�o efetivado!\n");
		}
	}
	
	private boolean veiculoTemCadastro(String placa) {
		//Verifica se um ve�culo identificado pela placa passada como par�metro j� tem cadastro no app
		boolean jaTemCadastro=false;
		for(Veiculo veiculoIterado: veiculosCadastrados) {
			if(veiculoIterado.compararPlaca(placa)) {
				jaTemCadastro=true;
			}
		}
		return jaTemCadastro;
	}
	
	private Veiculo selecionaVeiculo(String placa) {
		//Seleciona o ve�culo indicado pela placa passada como par�metro na lista de ve�culos cadastrados
		Veiculo veiculoDesejado=null;
		for(Veiculo veiculoIterado: veiculosCadastrados) {
			if(veiculoIterado.compararPlaca(placa)) {
				veiculoDesejado=veiculoIterado;
			}
		}
		return veiculoDesejado;
	}
	
	private boolean motoristaPossuiVeiculo(String cnh, String placa) {
		//Verifica se o motorista identificado pela cnh fornecida possui o ve�culo identificado pela placa passada como par�metro 
		boolean retorno=false;
		Motorista motoristaDesejado=selecionaMotoristaCadastrado(cnh);
		if(motoristaDesejado!=null) {
			retorno=motoristaDesejado.possuiVeiculo(placa);
		}
		return retorno;
	}
	
	public void cadastrarVeiculo(String placa, int anoDeFabricacao, String cnhDoDono,boolean luxo) {
		//Cadastra um ve�culo que ainda n�o est� cadastrado no app recebendo os par�metros necess�rios indicados pelo enunciado
		boolean motoristaTemCadastro=motoristaTemCadastro(cnhDoDono),veiculoTemCadastro=veiculoTemCadastro(placa);
		if(motoristaTemCadastro && !veiculoTemCadastro) {
			Motorista dono = selecionaMotoristaCadastrado(cnhDoDono);
			Veiculo novoVeiculo = new Veiculo(placa,anoDeFabricacao,dono,luxo);
			veiculosCadastrados.add(novoVeiculo);
			dono.cadastrarVeiculo(novoVeiculo);
		} else if (!motoristaTemCadastro){
			System.out.println("Motorista n�o cadastrado, cadastro de ve�culo n�o efetuado!\n");
		} else {
			System.out.println("Veiculo j� cadastrado, cadastro de ve�culo n�o efetuado\n");
		}
	}
	
	private boolean existeViagem(String cnh) {
		//Verifica se uma viagem existe. Como o enunciado n�o tratou do modo que essas viagens s�o identificadas, adotei que um motorista s� pode fazer uma viagem por vez, portanto o identificador da viagem pode ser sua cnh
		boolean retorno = false;
		for(Viagem viagemIterada : viagensRegistradas) {
			if(viagemIterada.estaSendoDirigidaPor(cnh)) {
				retorno=true;
			}
		}
		return retorno;
	}
	
	private boolean condicoesNecessariasParaViagem(String cpf,String cnh, String placa,double distancia) {
		//Verifica se todas as condi��es essenciais para come�ar uma viagem s�o cumpridas. Basicamente, uma viagem s� pode ser iniciada se os passageiros, o motorista e o ve�culo est�o cadastrados no app, o motorista � dono do ve�culo indicado, o motorista n�o est� ocupado com outra viagem no momento e a dintancia � maior que zero
		boolean retorno=false;
		boolean passageiroTemCadastro=passageiroTemCadastrado(cpf), motoristaTemCadastro=motoristaTemCadastro(cnh),veiculoTemCadastro=veiculoTemCadastro(placa), motoristaPossuiVeiculo=motoristaPossuiVeiculo(cnh, placa), motoristaOcupado=existeViagem(cnh);
		if (passageiroTemCadastro && motoristaTemCadastro && veiculoTemCadastro && motoristaPossuiVeiculo && !motoristaOcupado && distancia>0) {
			retorno = true;
		} else if(!passageiroTemCadastro) {
			System.out.println("Passageiro n�o cadastrado, viagem n�o registrada!\n");
		} else if(!motoristaTemCadastro) {
			System.out.println("Motorista n�o cadastrado, viagem n�o registrada!\n");
		} else if(!veiculoTemCadastro){
			System.out.println("Ve�culo n�o cadastrado, viagem n�o registrada!\n");
		} else if(!motoristaPossuiVeiculo){
			System.out.println("Motorista desejado n�o possui o carro indicado, viagem n�o cadastrada!\n");
		} else if(motoristaOcupado){
			System.out.println("Esse motorista se encontra ocupado com outra viagem no momento, viagem n�o cadastrada!\n");
		} else {
			System.out.println("Dist�ncia fornecida inv�lida, viagem n�o cadastrada!\n");
		}
	
		return retorno;
	}

	private boolean condicoesNecessariasParaViagem(String[] cpfs,String cnh, String placa,double distancia) {
		//Sobrecarga do m�todo acima para o caso de um vetor de cpfs
		boolean retorno=false;
		boolean passageiroTemCadastro=passageirosTemCadastrado(cpfs), motoristaTemCadastro=motoristaTemCadastro(cnh),veiculoTemCadastro=veiculoTemCadastro(placa), motoristaPossuiVeiculo=motoristaPossuiVeiculo(cnh, placa), motoristaOcupado=existeViagem(cnh);
		if (passageiroTemCadastro && motoristaTemCadastro && veiculoTemCadastro && motoristaPossuiVeiculo && !motoristaOcupado && distancia>0) {
			retorno = true;
		} else if(!passageiroTemCadastro) {
			System.out.println("Passageiro n�o cadastrado, viagem n�o registrada!\n");
		} else if(!motoristaTemCadastro) {
			System.out.println("Motorista n�o cadastrado, viagem n�o registrada!\n");
		} else if(!veiculoTemCadastro){
			System.out.println("Ve�culo n�o cadastrado, viagem n�o registrada!\n");
		} else if(!motoristaPossuiVeiculo){
			System.out.println("Motorista desejado n�o possui o carro indicado, viagem n�o cadastrada!\n");
		} else if(motoristaOcupado){
			System.out.println("Esse motorista se encontra ocupado com outra viagem no momento, viagem n�o cadastrada!\n");
		} else {
			System.out.println("Dist�ncia fornecida inv�lida, viagem n�o cadastrada!\n");
		}
	
		return retorno;
	}
	
	private boolean passageirosTemCadastrado(String[] cpf) {
		//M�todo que verifica se todos os membros de um vetor de cpfs est�o cadastrados no app
		boolean cpfJaCadastrado=false;
		for(String cpfIterado : cpf) {
			for(Passageiro passageiroIterado : passageirosCadastrados) {
				if (passageiroIterado.compararCpf(cpfIterado)) {
					cpfJaCadastrado=true;
				}
			}
			if(cpfJaCadastrado==false) {
				break;
			}
		}
		return cpfJaCadastrado;
	}
	
	public void cadastrarViagem(String cpf,String cnh,String placa,double distancia) {
		//Cadastra uma viagem para um �nico passageiro
		if (condicoesNecessariasParaViagem(cpf, cnh, placa, distancia)) {
			Viagem novaViagem = new Viagem(cpf,cnh,selecionaVeiculo(placa),distancia);
			viagensRegistradas.add(novaViagem);
		}
		
	}
	
	public void cadastrarViagem(String[] cpfs,String cnh,String placa,double distancia) {
		//Cadastra uma viagem para v�rios passageiros ao mesmo tempo
		if (condicoesNecessariasParaViagem(cpfs, cnh, placa, distancia)) {
			Viagem novaViagem = new Viagem(cpfs,cnh,selecionaVeiculo(placa),distancia);
			viagensRegistradas.add(novaViagem);
		} 
	}
	
	private Viagem selecionaViagem(String cnh) {
		//Seleciona uma viagem por meio de seu identificador (cnh do motorista) da lista de viagens registradas do app
		Viagem viagemDesejada = null;
		for(Viagem viagemIterada : viagensRegistradas) {
			if(viagemIterada.estaSendoDirigidaPor(cnh)) {
				viagemDesejada=viagemIterada;
			}
		}
		return viagemDesejada;
	}
	
	
	private boolean condicoesNecessariasParaParada(String cpfEntrando, String cnh) {
		//Verifica as condi��es necess�rias para uma parada referente as informa��es armazenadas no app (basicamente verifica se o passageiro que vai entrar no ve�culo est� cadastrado e se a viagem em si existe)
		boolean existeViagem=motoristaTemCadastro(cnh),passageiroEntrandoTemCadastro=passageiroTemCadastrado(cpfEntrando),retorno=false;
		if((existeViagem && passageiroEntrandoTemCadastro)||(existeViagem && cpfEntrando==null)) {
			//Entende-se que, se o vetor de passageiros entrando for nulo, ainda � poss�vel que a parada seja feita pois podem existir s� passageiros saindo do ve�culo.
			retorno=true;
		} else if(!existeViagem) {
			System.out.println("Viagem indicada n�o existe, a��o interrompida!\n");
		} else {
			System.out.println("Passageiro indicado para entrar no ve�culo n�o cadastrado, a��o interrompida!\n");
		}
		return retorno;
	}
	
	private boolean condicoesNecessariasParaParada(String[] cpfsEntrando, String cnh) {
		//Sobrecarga do m�todo acima para o caso de um vetor de pessoas entrando no ve�culo
		boolean existeViagem=motoristaTemCadastro(cnh),passageirosEntrandoTemCadastro=passageirosTemCadastrado(cpfsEntrando),retorno=false;
		if((existeViagem && passageirosEntrandoTemCadastro)||(existeViagem && cpfsEntrando==null)) {
			retorno=true;
		} else if(!existeViagem) {
			System.out.println("Viagem indicada n�o existe, a��o interrompida!\n");
		} else {
			System.out.println("Um dos passageiros indicado para entrar no ve�culo n�o cadastrado, a��o interrompida!\n");
		}
		return retorno;
	}
	

	public void criarParadaNaViagem(String cnh, double distancia, String cpfPessoaSaindo,String cpfPessoaEntrando) {
		//M�todo para criar uma parada em uma viagem j� cadastrada com uma pessoa entrando e outra saindo do ve�culo ou uma pessoa s� saindo ou s� entrando no ve�culo
		if(cpfPessoaSaindo==null && cpfPessoaEntrando==null) {
			System.out.println("Parametros de parada inv�lidos, a��o n�o efetivada!\n");
		} else if(condicoesNecessariasParaParada(cpfPessoaEntrando,cnh)) {
				Viagem viagemDesejada=selecionaViagem(cnh);
				if(viagemDesejada.podeFazerParada(cpfPessoaSaindo, distancia)) {
					viagemDesejada.adicionarParada(distancia,cpfPessoaEntrando,cpfPessoaSaindo);
				} else {
					System.out.println("A pessoa indicada n�o se encontra nessa viagem ou a dist�ncia passada � inv�lida, a��o interrompida!\n");
				}
		}
	}
	
	public void criarParadaNaViagem(String cnh, double distancia, String[] cpfsPessoasSaindo,String[] cpfsPessoasEntrando) {
		//Sobrecarga do m�todo acima para o caso de um vetor de pessoas saindo ou entrando
		if(cpfsPessoasEntrando==null && cpfsPessoasEntrando==null) {
			System.out.println("Parametros de parada inv�lidos, a��o n�o efetivada!\n");
		} else if(condicoesNecessariasParaParada(cpfsPessoasEntrando,cnh)) {
			Viagem viagemDesejada=selecionaViagem(cnh);
			if(viagemDesejada.podeFazerParada(cpfsPessoasSaindo, distancia)) {
				viagemDesejada.adicionarParada(distancia,cpfsPessoasEntrando,cpfsPessoasSaindo);
			} else {
				System.out.println("Uma das pessoas indicadas para sair do ve�culo n�o se encontra nessa viagem ou a dist�ncia passada � inv�lida, a��o interrompida!\n");
			}
		}
	}
	
	public void criarParadaNaViagem(String cnh, double distancia) {
		//Sobrecarga do m�todo acima para o caso de nenhum dos passageiros sair ou entrar no ve�culo
		if(existeViagem(cnh)) {
			Viagem viagemDesejada = selecionaViagem(cnh);
			viagemDesejada.adicionarParada(distancia);
		}
	}
	
	public void encerrarViagem(String cnh) {
		//M�todo para encerrar uma viagem e liberar um motorista para outras viagens
		if(existeViagem(cnh)) {
			System.out.println("-------Sum�rio de viagem:------");
			System.out.print(selecionaViagem(cnh));
			viagensRegistradas.remove(selecionaViagem(cnh));
			System.out.println("Viagem de "+cnh+" encerrada com sucesso!\n");
		} else {
			System.out.println("N�o existe viagem sendo executada com o indentificador passado!\n");
		}
	}
	
	public String imprimirInformacaosUsuarios() {
		//M�todo para imprimir a informa��o de todos os usu�rios cadastrados da plataforma
		String txt="--------Usuarios Cadastrados--------\n";
		List<Usuario> todosUsuarios = new ArrayList<Usuario>();
		todosUsuarios.addAll(passageirosCadastrados);
		todosUsuarios.addAll(motoristasCadastrados);
		for(Usuario usuarioIterado : todosUsuarios) {
			txt+=usuarioIterado.toString();
			txt+="\n";
		}
		return txt;
	}
	
	public String imprimirInformacaosVeiculos() {
		//M�todo para imprimir informa��o sobre todos os ve�culos cadastrados da plataforma
		String txt="--------Ve�culos Cadastrados--------\n";
		for(Veiculo veiculoIterado : veiculosCadastrados) {
			txt+=veiculoIterado.toStringDetalhado();
		}
		return txt;
	}
	
	public String imprimirInformacaoViagens() {
		//M�todo para imprimir informa��o sobre todos as viagens cadastradas na plataforma
		String txt="--------Viagens Registradas--------\n";
		for(Viagem viagemIterada: viagensRegistradas) {
			txt+=viagemIterada.toString();
		}
		txt+="\n";
		return txt;
	}
}
