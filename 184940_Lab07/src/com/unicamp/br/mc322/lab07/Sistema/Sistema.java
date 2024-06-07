package com.unicamp.br.mc322.lab07.Sistema;
import java.util.ArrayList;
import java.util.List;
import com.unicamp.br.mc322.lab07.Alugaveis.Alugavel;
import com.unicamp.br.mc322.lab07.Alugaveis.Experiencia.Experiencia;
import com.unicamp.br.mc322.lab07.Alugaveis.Residencias.*;
import com.unicamp.br.mc322.lab07.EstruturaDeDados.*;
public class Sistema {
	/*Essa classe implementa as funcionalidades do aplicativo, s�o elas:
	-Cadastrar um apartamento
	-Cadastrar uma casa
	-Cadastrar uma rede
	-Cadastrar uma mans�o
	-Cadastrar uma experi�ncia
	-Cadastrar um ponto de interesse
	-Fazer uma reserva por um certo per�odo de tempo em um dos produtos cadastrados, devolvendo essa reserva como um comprovante inalter�vel
	-Imprimir a informa��o e o pre�o de todas as reservas
	-Imprimir os detalhes de todas as resid�ncias e experi�ncias dispon�veis, incluindo, para cada uma delas, a dist�ncia de todos os pontos de interesse cadastrados 
	*/
	private List<Alugavel> cadastroDeProdutos;//Lista que armazena as resid�ncias e experi�ncias j� cadastradas
	private List<PontoInteresse> cadastroDePontos;//Lista que armazena os pontos de interesse cadastrados
	private List<Reserva> reservasEfetuadas;//Lista que armazena todas as reservas j� efetuadas
	
	public Sistema() {//Construtor da classe, o qual aloca todas as listas a serem usadas
		cadastroDeProdutos=new ArrayList<Alugavel>();
		cadastroDePontos=new ArrayList<PontoInteresse>();
		reservasEfetuadas=new ArrayList<Reserva>();
	}
	
	private boolean podeCadastrarId(String id) {
		//M�todo que verifica se j� existe um produto com a ID dado como par�metro na lista. Ele retorna um booleano indicando se pode ser feito ou n�o o cadastro de um novo produto com a ID.
		boolean podeCadastrar=true;
		for(Alugavel produto : cadastroDeProdutos) {
			if(produto.getId().compareTo(id)==0) {
				podeCadastrar=false;
				break;
			}
		}
		return podeCadastrar;
	}
	
	private boolean podeCadastrarPonto(String nome,Point endereco) {
		//M�todo que verifica se j� existe um ponto cadastrado com mesmo nome e endere�o que os passados como argumento. Se j� houver, ele retorna false ou true dependendo se j� existe cadastro ou n�o
		boolean podeCadastrar=true;
		for(PontoInteresse pontoInteresse : cadastroDePontos) {
			if(pontoInteresse.getNome().compareTo(nome)==0 && pontoInteresse.getPoint()==endereco) {
				podeCadastrar=false;
				break;
			}
		}
		return podeCadastrar;
	}
	
	public void cadastrarApartamento(String id,String nome ,Point endereco, int andar , int quartos, int banheiros,double valor ,boolean temSacada ) {
		//Esse m�todo cria um apartamento e cadastra ele na lista de produtos
		boolean podeCadastrar=podeCadastrarId(id);
		if(podeCadastrar && quartos>0 && banheiros>0 && valor>0 && andar>0) {//Condi��es necess�rias realizar o cadastro
			Apartamento novoApto= new Apartamento(id,valor,endereco,nome,quartos,andar,temSacada,banheiros);
			cadastroDeProdutos.add(novoApto);
		} else if(podeCadastrar) {
			System.out.println("Par�metros de cadastro inv�lidos, opera��o cancelada!\n");
		} else {
			System.out.println("Identificador j� utilizado, cadastro n�o efetuado!\n");
		}
	}
	
	public void cadastrarCasa(String id, String nome, Point endereco,int quartos ,int banheiros ,double  valor,boolean temPiscina) {
		//Esse m�todo cria uma casa e cadastra ela na lista de produtos
		boolean podeCadastrar=podeCadastrarId(id);
		if(podeCadastrar && quartos>0 && banheiros>0 && valor>0) {//Condi��es necess�rias realizar o cadastro
			Casa novaCasa= new Casa(id,valor,endereco,nome,quartos,banheiros,temPiscina);
			cadastroDeProdutos.add(novaCasa);	
		}	else if(podeCadastrar) {
			System.out.println("Par�metros de cadastro inv�lidos, opera��o cancelada!\n");
		} else {
			System.out.println("Identificador j� utilizado, cadastro n�o efetuado!\n");
		}
	}
	
	public void cadastrarMansao(String id,String nome , Point endereco, double metrosQ,double valor) {
		//Esse m�todo cria uma mans�o e cadastra ela na lista de produtos
		boolean podeCadastrar=podeCadastrarId(id);
		if(podeCadastrar && valor>0 && metrosQ>0) {//Condi��es necess�rias realizar o cadastro
			Mansao novaMansao= new Mansao(id,valor,endereco,nome,metrosQ);
			cadastroDeProdutos.add(novaMansao);
		}	else if(podeCadastrar) {
			System.out.println("Par�metros de cadastro inv�lidos, opera��o cancelada!\n");
		} else {
			System.out.println("Identificador j� utilizado, cadastro n�o efetuado!\n");
		}
	}
	
	public void cadastrarRede(String id,String nome , Point endereco, double valor) {
		//Esse m�todo cria uma rede e cadastra ela na lista de produtos
		boolean podeCadastrar=podeCadastrarId(id);
		if(podeCadastrar && valor>0) {//Condi��es necess�rias realizar o cadastro
			Rede noveRede= new Rede(id,valor,endereco,nome);
			cadastroDeProdutos.add(noveRede);
		} 	else if(podeCadastrar) {
			System.out.println("Par�metros de cadastro inv�lidos, opera��o cancelada!\n");
		} 	else {
			System.out.println("Identificador j� utilizado, cadastro n�o efetuado!\n");
		}
	}
	
	public void cadastrarExperiencia(String id,Point endereco,int participantes,double valorBase,double valorDescontado ) {
		boolean podeCadastrar=podeCadastrarId(id);
		//Esse m�todo cria uma rede e cadastra ela na lista de produtos
		if(podeCadastrar && valorBase>valorDescontado && valorBase>0 && valorDescontado>0 && participantes>0) {//Condi��es necess�rias realizar o cadastro
			Experiencia novaExp= new Experiencia(id,valorBase,valorDescontado,endereco,participantes);
			cadastroDeProdutos.add(novaExp);
		}	else if(podeCadastrar) {
			System.out.println("Par�metros de cadastro inv�lidos, opera��o cancelada!\n");
		} 	else {
			System.out.println("Identificador j� utilizado, cadastro n�o efetuado!\n");
		}
	}
	
	public void cadastrarPontoDeInteresse(String nome,Point endereco) {
		//Esse m�todo cria um ponto de interesse e cadastra ele na lista de pontos
		if(podeCadastrarPonto(nome,endereco)) {//Condi��o necess�rias realizar o cadastro
			PontoInteresse novoPonto=new PontoInteresse(nome,endereco);
			cadastroDePontos.add(novoPonto);
		} else {
			System.out.println("Ponto com mesmo nome j� e endere�o cadastrado, opera��o cancelada!\n");
		}
	}
	
	private Alugavel selecionaProduto(String id) {
		//M�todo que encontra e retorna o produto de ID indicada pelo par�metro e o retorna, ou retorna null se n�o houver produto na lista com o Id buscado
		Alugavel produtoBuscado=null;
		for (Alugavel produtoIterado: cadastroDeProdutos) {
			if(produtoIterado.getId().compareTo(id)==0) {
				produtoBuscado=produtoIterado;
			}
		}
		return produtoBuscado;
	}
	
	public Reserva comprarProduto(String id,int dias, int pessoas,int menores) {
		//M�todo que realiza a reserva de um produto de ID indicada por uma certa quantidade de dias e para uma certa quantidade de pessoas. Como n�o foi estabelecido pelo enunciado que produtos j� reservados n�o poderiam ser reservados novamente nem rela��es de datas ou estadia, a �nica condi��o leva em conta para a reserva foi a exist�ncia do produto.
		Reserva novaReserva=null;
		if(dias>0 && pessoas>0 && menores >=0) {
			Alugavel produto=selecionaProduto(id);
			if(produto!=null) {
				novaReserva= new Reserva(id,pessoas,menores,dias);
				reservasEfetuadas.add(novaReserva);
			} else {
				System.out.println("Produto com ID fornecido n�o encontrado, comprovante de reserva nulo retornado!\n");
			}	
		} else {
			System.out.println("Par�metros de reserva inv�lidos, comprovante retornado nulo!");
		}
		return novaReserva;
	}
	
	public Reserva comprarProduto(String id, int pessoas,int menores) {
		//M�todo � uma sobrecarga do m�todo acima para caso o n�mero de dias n�o seja fornecido (deve ser considerado por padr�o 1)
		Reserva novaReserva=null;
		if(pessoas>0 && menores >=0) {
			Alugavel produto=selecionaProduto(id);
			if(produto!=null) {
				novaReserva= new Reserva(id,pessoas,menores,1);
				reservasEfetuadas.add(novaReserva);
			} else {
				System.out.println("Produto com ID fornecido n�o encontrado, comprovante de reserva nulo retornado!\n");
			}	
		} else {
			System.out.println("Par�metros de reserva inv�lidos, comprovante retornado nulo!");
		}
		return novaReserva;
	}	

	private boolean existeReserva(String id) {
		//Esse m�todo verifica se uma determinada reserva existe na lista de reservas efetuadas
		boolean existeReserva=false;
		for(Reserva reservaIterada: reservasEfetuadas) {
			if(reservaIterada.getId().compareTo(id)==0) {
				existeReserva=true;
				break;
			}
		}
		return existeReserva;
	}
	
	
	public int indexProduto(String id) {
		//Esse m�todo seleciona o �ndice de um produto com o ID indicado
			int i=0;
			for(Alugavel produtoIterado : cadastroDeProdutos) {
				if (produtoIterado.getId().compareTo(id)==0) {
					break;
				}
				i++;
			}
		return i;
	}
	
	
	
	public void removerProduto(String id) {
		//Esse m�todo remove um produto da lista de produtos cadastrados por seu id
		boolean existeCadastro= podeCadastrarId(id)? false:true; //Se eu posso cadastrar o produto � por que n�o existe cadastro
		if (existeCadastro) {
			int posCadastro=indexProduto(id);
			cadastroDeProdutos.remove(posCadastro);
			System.out.println("Cadastro apagado com sucesso!\n");
		} else {
			System.out.println("N�o h� produto cadastrado com essa ID, opera��o cancelada!");
		}
		
	}
	
	
	public double valorTotalListaDeReserva(Reserva[] reservas) {
		//Esse m�todo � capaz de fazer a soma de um array de reservas dado como par�metro. O pen�ltimo ponto do item 2 do enunciado ficou amb�guo no sentido de definir se esse input � externo ou inter, por isso a fun��o foi mantida como public
		double valorTotal=0;
		for(Reserva reservaIterada: reservas) {
			String idDaReserva=reservaIterada.getId();
			if(existeReserva(idDaReserva)) {
				valorTotal += selecionaProduto(idDaReserva).valorReserva(reservaIterada.dias(),reservaIterada.numAdultos(),reservaIterada.numMenores());
			}
		}
		return valorTotal;
	}
	
	
	public double valorTotalReservasCadastradas() {
		//Esse m�todo � capaz de fazer a soma de todas as reservas da lista cadastradas. O pen�ltimo ponto do item 2 do enunciado ficou amb�guo, por isso esse m�todo foi implementado.
		double valorTotal=0;
		for(Reserva reservaIterada: reservasEfetuadas) {
			String idDaReserva=reservaIterada.getId();
			if(existeReserva(idDaReserva)) {
				valorTotal += selecionaProduto(idDaReserva).valorReserva(reservaIterada.dias(),reservaIterada.numAdultos(),reservaIterada.numMenores());
			}
		}
		return valorTotal;
	}
	
	
	
	
	public String informa��oDasReservas() {
		//Esse m�todo retorna uma string com todas as informa��es das reservas feitas e dispon�veis na lista de reservas
		String txt= "N�o h� nenhuma reserva!\n";
		if (reservasEfetuadas.size()>0) {
			txt="----------------------Informa��o das reservas cadastradas:----------------------\n";
			for(Reserva reservaIterada : reservasEfetuadas) {
				String idDaReserva=reservaIterada.getId();
				txt+="\t";
				txt+=reservaIterada.toString();
				double valorTotal = selecionaProduto(idDaReserva).valorReserva(reservaIterada.dias(),reservaIterada.numAdultos(),reservaIterada.numMenores());
				txt+="\n\tcom o custo de "+valorTotal+"\n\n";
			} 
			txt+="---------------------------------------------------------------------------------\n";
		}
		
		return txt;
	}
	
	public String getInfo() {
		//Esse m�todo retorna uma string com todas as informa��es de todos os produtos cadastrados, do mesmo modo que pedido no enunciado
		String txt="Nenhum produto cadastrado!";
		if(cadastroDeProdutos.size()>0) {
			txt="----------------------Informa��o dos produtos dispon�veis:----------------------\n";
			for (Alugavel produto: cadastroDeProdutos) {
				txt+="\n";
				txt+=produto.toString();
				if(cadastroDePontos.size()>0) {
					txt+="\t\tDist�ncia do produto aos pontos de interesse:\n";
					for(PontoInteresse ponto: cadastroDePontos) {
						double distancia=produto.distanciaDe(ponto.getPoint());
						txt+="\t\t\t"+ponto.getNome()+": "+distancia+"\n";
					}
				txt+="\n";
				}
			}
			txt+="-------------------------------------------------------------------------------------\n";
			return txt;	
		}
		return txt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
