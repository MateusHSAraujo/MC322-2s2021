package com.unicamp.mc322.lab10.App;

import java.util.ArrayList;
import java.util.List;

import com.unicamp.mc322.lab10.App.Date.Date;
import com.unicamp.mc322.lab10.App.Usuario.*;
import com.unicamp.mc322.lab10.App.Veiculo.Veiculo;
import com.unicamp.mc322.lab10.App.Viagem.Viagem;

public class App {
	/*Classe responsável pelo próprio aplicativo. Assim como pedido no enunciado, temos que ela...
	-Armazena as informações de todos os usuários da plataforma em duas listas (uma de passageiros e outra de motoristas).
	-É capaz de cadastrar passageiros, motoristas e veículos.
	-É capaz de criar, armazenar e gerenciar viagens, eventualmente adicionando paradas à elas.
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
		//Verifica se um passageiro já está cadastrado no app (cpf é único por pessoa)
		boolean cpfJaCadastrado=false;
		for(Passageiro passageiroIterado : passageirosCadastrados) {
			if (passageiroIterado.compararCpf(cpf)) {
				cpfJaCadastrado=true;
			}
		}
		return cpfJaCadastrado;
	}
	
	
	
	public void cadastrarPassageiro(String nome,String cpf, String cartao, Date aniversario) {
		//Cadastra um passageiro que ainda não esteja cadastrado recebendo os parâmetros necessários indicados pelo enunciado
		if(!passageiroTemCadastrado(cpf)) {
			Passageiro NovoPassageiro = new Passageiro(nome,cpf,cartao,aniversario);
			passageirosCadastrados.add(NovoPassageiro);
		} else {
			System.out.println("Cpf já cadastrado, cadastro não efetivado!\n");
		}
	}
	
	private boolean motoristaTemCadastro(String cnh) {
		//Verifica se um motorista já está cadastrado no app (cnh é única por motorista). Foi utilizada a cnh e não o cpf do motorista pois assim abre-se a possibilidade de um motorista se cadastrar como passageiro se desejar
		boolean cnhJaCadastrada=false;
		for(Motorista motoristaIterado : motoristasCadastrados) {
			if (motoristaIterado.compararCnh(cnh)) {
				cnhJaCadastrada=true;
			}
		}
		return cnhJaCadastrada;
	}
	
	private Motorista selecionaMotoristaCadastrado (String cnh) {
		//Seleciona o motorista indicado pela cnh passada como parâmetro na lista de motorista cadastrados
		Motorista motoristaDesejado=null;
		for(Motorista motoristaIterado: motoristasCadastrados) {
			if(motoristaIterado.compararCnh(cnh)) {
				motoristaDesejado=motoristaIterado;
			}
		}
		return motoristaDesejado;
	}
	
	public void cadastrarMotorista(String nome,String cpf, String cartao, Date aniversario,String habilitacao) {
		//Cadastra um motorista que ainda não estaja cadastrado no app recebendo os parâmetros necessários indicados pelo enunciado
		if(!motoristaTemCadastro(habilitacao)) {
			Motorista novoMotorista = new Motorista(nome,cpf,cartao,aniversario,habilitacao);
			motoristasCadastrados.add(novoMotorista);
		} else {
			System.out.println("Cnh já cadastrada, cadastro não efetivado!\n");
		}
	}
	
	private boolean veiculoTemCadastro(String placa) {
		//Verifica se um veículo identificado pela placa passada como parâmetro já tem cadastro no app
		boolean jaTemCadastro=false;
		for(Veiculo veiculoIterado: veiculosCadastrados) {
			if(veiculoIterado.compararPlaca(placa)) {
				jaTemCadastro=true;
			}
		}
		return jaTemCadastro;
	}
	
	private Veiculo selecionaVeiculo(String placa) {
		//Seleciona o veículo indicado pela placa passada como parâmetro na lista de veículos cadastrados
		Veiculo veiculoDesejado=null;
		for(Veiculo veiculoIterado: veiculosCadastrados) {
			if(veiculoIterado.compararPlaca(placa)) {
				veiculoDesejado=veiculoIterado;
			}
		}
		return veiculoDesejado;
	}
	
	private boolean motoristaPossuiVeiculo(String cnh, String placa) {
		//Verifica se o motorista identificado pela cnh fornecida possui o veículo identificado pela placa passada como parâmetro 
		boolean retorno=false;
		Motorista motoristaDesejado=selecionaMotoristaCadastrado(cnh);
		if(motoristaDesejado!=null) {
			retorno=motoristaDesejado.possuiVeiculo(placa);
		}
		return retorno;
	}
	
	public void cadastrarVeiculo(String placa, int anoDeFabricacao, String cnhDoDono,boolean luxo) {
		//Cadastra um veículo que ainda não está cadastrado no app recebendo os parâmetros necessários indicados pelo enunciado
		boolean motoristaTemCadastro=motoristaTemCadastro(cnhDoDono),veiculoTemCadastro=veiculoTemCadastro(placa);
		if(motoristaTemCadastro && !veiculoTemCadastro) {
			Motorista dono = selecionaMotoristaCadastrado(cnhDoDono);
			Veiculo novoVeiculo = new Veiculo(placa,anoDeFabricacao,dono,luxo);
			veiculosCadastrados.add(novoVeiculo);
			dono.cadastrarVeiculo(novoVeiculo);
		} else if (!motoristaTemCadastro){
			System.out.println("Motorista não cadastrado, cadastro de veículo não efetuado!\n");
		} else {
			System.out.println("Veiculo já cadastrado, cadastro de veículo não efetuado\n");
		}
	}
	
	private boolean existeViagem(String cnh) {
		//Verifica se uma viagem existe. Como o enunciado não tratou do modo que essas viagens são identificadas, adotei que um motorista só pode fazer uma viagem por vez, portanto o identificador da viagem pode ser sua cnh
		boolean retorno = false;
		for(Viagem viagemIterada : viagensRegistradas) {
			if(viagemIterada.estaSendoDirigidaPor(cnh)) {
				retorno=true;
			}
		}
		return retorno;
	}
	
	private boolean condicoesNecessariasParaViagem(String cpf,String cnh, String placa,double distancia) {
		//Verifica se todas as condições essenciais para começar uma viagem são cumpridas. Basicamente, uma viagem só pode ser iniciada se os passageiros, o motorista e o veículo estão cadastrados no app, o motorista é dono do veículo indicado, o motorista não está ocupado com outra viagem no momento e a dintancia é maior que zero
		boolean retorno=false;
		boolean passageiroTemCadastro=passageiroTemCadastrado(cpf), motoristaTemCadastro=motoristaTemCadastro(cnh),veiculoTemCadastro=veiculoTemCadastro(placa), motoristaPossuiVeiculo=motoristaPossuiVeiculo(cnh, placa), motoristaOcupado=existeViagem(cnh);
		if (passageiroTemCadastro && motoristaTemCadastro && veiculoTemCadastro && motoristaPossuiVeiculo && !motoristaOcupado && distancia>0) {
			retorno = true;
		} else if(!passageiroTemCadastro) {
			System.out.println("Passageiro não cadastrado, viagem não registrada!\n");
		} else if(!motoristaTemCadastro) {
			System.out.println("Motorista não cadastrado, viagem não registrada!\n");
		} else if(!veiculoTemCadastro){
			System.out.println("Veículo não cadastrado, viagem não registrada!\n");
		} else if(!motoristaPossuiVeiculo){
			System.out.println("Motorista desejado não possui o carro indicado, viagem não cadastrada!\n");
		} else if(motoristaOcupado){
			System.out.println("Esse motorista se encontra ocupado com outra viagem no momento, viagem não cadastrada!\n");
		} else {
			System.out.println("Distância fornecida inválida, viagem não cadastrada!\n");
		}
	
		return retorno;
	}

	private boolean condicoesNecessariasParaViagem(String[] cpfs,String cnh, String placa,double distancia) {
		//Sobrecarga do método acima para o caso de um vetor de cpfs
		boolean retorno=false;
		boolean passageiroTemCadastro=passageirosTemCadastrado(cpfs), motoristaTemCadastro=motoristaTemCadastro(cnh),veiculoTemCadastro=veiculoTemCadastro(placa), motoristaPossuiVeiculo=motoristaPossuiVeiculo(cnh, placa), motoristaOcupado=existeViagem(cnh);
		if (passageiroTemCadastro && motoristaTemCadastro && veiculoTemCadastro && motoristaPossuiVeiculo && !motoristaOcupado && distancia>0) {
			retorno = true;
		} else if(!passageiroTemCadastro) {
			System.out.println("Passageiro não cadastrado, viagem não registrada!\n");
		} else if(!motoristaTemCadastro) {
			System.out.println("Motorista não cadastrado, viagem não registrada!\n");
		} else if(!veiculoTemCadastro){
			System.out.println("Veículo não cadastrado, viagem não registrada!\n");
		} else if(!motoristaPossuiVeiculo){
			System.out.println("Motorista desejado não possui o carro indicado, viagem não cadastrada!\n");
		} else if(motoristaOcupado){
			System.out.println("Esse motorista se encontra ocupado com outra viagem no momento, viagem não cadastrada!\n");
		} else {
			System.out.println("Distância fornecida inválida, viagem não cadastrada!\n");
		}
	
		return retorno;
	}
	
	private boolean passageirosTemCadastrado(String[] cpf) {
		//Método que verifica se todos os membros de um vetor de cpfs estão cadastrados no app
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
		//Cadastra uma viagem para um único passageiro
		if (condicoesNecessariasParaViagem(cpf, cnh, placa, distancia)) {
			Viagem novaViagem = new Viagem(cpf,cnh,selecionaVeiculo(placa),distancia);
			viagensRegistradas.add(novaViagem);
		}
		
	}
	
	public void cadastrarViagem(String[] cpfs,String cnh,String placa,double distancia) {
		//Cadastra uma viagem para vários passageiros ao mesmo tempo
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
		//Verifica as condições necessárias para uma parada referente as informações armazenadas no app (basicamente verifica se o passageiro que vai entrar no veículo está cadastrado e se a viagem em si existe)
		boolean existeViagem=motoristaTemCadastro(cnh),passageiroEntrandoTemCadastro=passageiroTemCadastrado(cpfEntrando),retorno=false;
		if((existeViagem && passageiroEntrandoTemCadastro)||(existeViagem && cpfEntrando==null)) {
			//Entende-se que, se o vetor de passageiros entrando for nulo, ainda é possível que a parada seja feita pois podem existir só passageiros saindo do veículo.
			retorno=true;
		} else if(!existeViagem) {
			System.out.println("Viagem indicada não existe, ação interrompida!\n");
		} else {
			System.out.println("Passageiro indicado para entrar no veículo não cadastrado, ação interrompida!\n");
		}
		return retorno;
	}
	
	private boolean condicoesNecessariasParaParada(String[] cpfsEntrando, String cnh) {
		//Sobrecarga do método acima para o caso de um vetor de pessoas entrando no veículo
		boolean existeViagem=motoristaTemCadastro(cnh),passageirosEntrandoTemCadastro=passageirosTemCadastrado(cpfsEntrando),retorno=false;
		if((existeViagem && passageirosEntrandoTemCadastro)||(existeViagem && cpfsEntrando==null)) {
			retorno=true;
		} else if(!existeViagem) {
			System.out.println("Viagem indicada não existe, ação interrompida!\n");
		} else {
			System.out.println("Um dos passageiros indicado para entrar no veículo não cadastrado, ação interrompida!\n");
		}
		return retorno;
	}
	

	public void criarParadaNaViagem(String cnh, double distancia, String cpfPessoaSaindo,String cpfPessoaEntrando) {
		//Método para criar uma parada em uma viagem já cadastrada com uma pessoa entrando e outra saindo do veículo ou uma pessoa só saindo ou só entrando no veículo
		if(cpfPessoaSaindo==null && cpfPessoaEntrando==null) {
			System.out.println("Parametros de parada inválidos, ação não efetivada!\n");
		} else if(condicoesNecessariasParaParada(cpfPessoaEntrando,cnh)) {
				Viagem viagemDesejada=selecionaViagem(cnh);
				if(viagemDesejada.podeFazerParada(cpfPessoaSaindo, distancia)) {
					viagemDesejada.adicionarParada(distancia,cpfPessoaEntrando,cpfPessoaSaindo);
				} else {
					System.out.println("A pessoa indicada não se encontra nessa viagem ou a distância passada é inválida, ação interrompida!\n");
				}
		}
	}
	
	public void criarParadaNaViagem(String cnh, double distancia, String[] cpfsPessoasSaindo,String[] cpfsPessoasEntrando) {
		//Sobrecarga do método acima para o caso de um vetor de pessoas saindo ou entrando
		if(cpfsPessoasEntrando==null && cpfsPessoasEntrando==null) {
			System.out.println("Parametros de parada inválidos, ação não efetivada!\n");
		} else if(condicoesNecessariasParaParada(cpfsPessoasEntrando,cnh)) {
			Viagem viagemDesejada=selecionaViagem(cnh);
			if(viagemDesejada.podeFazerParada(cpfsPessoasSaindo, distancia)) {
				viagemDesejada.adicionarParada(distancia,cpfsPessoasEntrando,cpfsPessoasSaindo);
			} else {
				System.out.println("Uma das pessoas indicadas para sair do veículo não se encontra nessa viagem ou a distância passada é inválida, ação interrompida!\n");
			}
		}
	}
	
	public void criarParadaNaViagem(String cnh, double distancia) {
		//Sobrecarga do método acima para o caso de nenhum dos passageiros sair ou entrar no veículo
		if(existeViagem(cnh)) {
			Viagem viagemDesejada = selecionaViagem(cnh);
			viagemDesejada.adicionarParada(distancia);
		}
	}
	
	public void encerrarViagem(String cnh) {
		//Método para encerrar uma viagem e liberar um motorista para outras viagens
		if(existeViagem(cnh)) {
			System.out.println("-------Sumário de viagem:------");
			System.out.print(selecionaViagem(cnh));
			viagensRegistradas.remove(selecionaViagem(cnh));
			System.out.println("Viagem de "+cnh+" encerrada com sucesso!\n");
		} else {
			System.out.println("Não existe viagem sendo executada com o indentificador passado!\n");
		}
	}
	
	public String imprimirInformacaosUsuarios() {
		//Método para imprimir a informação de todos os usuários cadastrados da plataforma
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
		//Método para imprimir informação sobre todos os veículos cadastrados da plataforma
		String txt="--------Veículos Cadastrados--------\n";
		for(Veiculo veiculoIterado : veiculosCadastrados) {
			txt+=veiculoIterado.toStringDetalhado();
		}
		return txt;
	}
	
	public String imprimirInformacaoViagens() {
		//Método para imprimir informação sobre todos as viagens cadastradas na plataforma
		String txt="--------Viagens Registradas--------\n";
		for(Viagem viagemIterada: viagensRegistradas) {
			txt+=viagemIterada.toString();
		}
		txt+="\n";
		return txt;
	}
}
