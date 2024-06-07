package com.unicamp.br.mc322.lab07.Sistema;
import java.util.ArrayList;
import java.util.List;
import com.unicamp.br.mc322.lab07.Alugaveis.Alugavel;
import com.unicamp.br.mc322.lab07.Alugaveis.Experiencia.Experiencia;
import com.unicamp.br.mc322.lab07.Alugaveis.Residencias.*;
import com.unicamp.br.mc322.lab07.EstruturaDeDados.*;
public class Sistema {
	/*Essa classe implementa as funcionalidades do aplicativo, são elas:
	-Cadastrar um apartamento
	-Cadastrar uma casa
	-Cadastrar uma rede
	-Cadastrar uma mansão
	-Cadastrar uma experiência
	-Cadastrar um ponto de interesse
	-Fazer uma reserva por um certo período de tempo em um dos produtos cadastrados, devolvendo essa reserva como um comprovante inalterável
	-Imprimir a informação e o preço de todas as reservas
	-Imprimir os detalhes de todas as residências e experiências disponíveis, incluindo, para cada uma delas, a distância de todos os pontos de interesse cadastrados 
	*/
	private List<Alugavel> cadastroDeProdutos;//Lista que armazena as residências e experiências já cadastradas
	private List<PontoInteresse> cadastroDePontos;//Lista que armazena os pontos de interesse cadastrados
	private List<Reserva> reservasEfetuadas;//Lista que armazena todas as reservas já efetuadas
	
	public Sistema() {//Construtor da classe, o qual aloca todas as listas a serem usadas
		cadastroDeProdutos=new ArrayList<Alugavel>();
		cadastroDePontos=new ArrayList<PontoInteresse>();
		reservasEfetuadas=new ArrayList<Reserva>();
	}
	
	private boolean podeCadastrarId(String id) {
		//Método que verifica se já existe um produto com a ID dado como parâmetro na lista. Ele retorna um booleano indicando se pode ser feito ou não o cadastro de um novo produto com a ID.
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
		//Método que verifica se já existe um ponto cadastrado com mesmo nome e endereço que os passados como argumento. Se já houver, ele retorna false ou true dependendo se já existe cadastro ou não
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
		//Esse método cria um apartamento e cadastra ele na lista de produtos
		boolean podeCadastrar=podeCadastrarId(id);
		if(podeCadastrar && quartos>0 && banheiros>0 && valor>0 && andar>0) {//Condições necessárias realizar o cadastro
			Apartamento novoApto= new Apartamento(id,valor,endereco,nome,quartos,andar,temSacada,banheiros);
			cadastroDeProdutos.add(novoApto);
		} else if(podeCadastrar) {
			System.out.println("Parâmetros de cadastro inválidos, operação cancelada!\n");
		} else {
			System.out.println("Identificador já utilizado, cadastro não efetuado!\n");
		}
	}
	
	public void cadastrarCasa(String id, String nome, Point endereco,int quartos ,int banheiros ,double  valor,boolean temPiscina) {
		//Esse método cria uma casa e cadastra ela na lista de produtos
		boolean podeCadastrar=podeCadastrarId(id);
		if(podeCadastrar && quartos>0 && banheiros>0 && valor>0) {//Condições necessárias realizar o cadastro
			Casa novaCasa= new Casa(id,valor,endereco,nome,quartos,banheiros,temPiscina);
			cadastroDeProdutos.add(novaCasa);	
		}	else if(podeCadastrar) {
			System.out.println("Parâmetros de cadastro inválidos, operação cancelada!\n");
		} else {
			System.out.println("Identificador já utilizado, cadastro não efetuado!\n");
		}
	}
	
	public void cadastrarMansao(String id,String nome , Point endereco, double metrosQ,double valor) {
		//Esse método cria uma mansão e cadastra ela na lista de produtos
		boolean podeCadastrar=podeCadastrarId(id);
		if(podeCadastrar && valor>0 && metrosQ>0) {//Condições necessárias realizar o cadastro
			Mansao novaMansao= new Mansao(id,valor,endereco,nome,metrosQ);
			cadastroDeProdutos.add(novaMansao);
		}	else if(podeCadastrar) {
			System.out.println("Parâmetros de cadastro inválidos, operação cancelada!\n");
		} else {
			System.out.println("Identificador já utilizado, cadastro não efetuado!\n");
		}
	}
	
	public void cadastrarRede(String id,String nome , Point endereco, double valor) {
		//Esse método cria uma rede e cadastra ela na lista de produtos
		boolean podeCadastrar=podeCadastrarId(id);
		if(podeCadastrar && valor>0) {//Condições necessárias realizar o cadastro
			Rede noveRede= new Rede(id,valor,endereco,nome);
			cadastroDeProdutos.add(noveRede);
		} 	else if(podeCadastrar) {
			System.out.println("Parâmetros de cadastro inválidos, operação cancelada!\n");
		} 	else {
			System.out.println("Identificador já utilizado, cadastro não efetuado!\n");
		}
	}
	
	public void cadastrarExperiencia(String id,Point endereco,int participantes,double valorBase,double valorDescontado ) {
		boolean podeCadastrar=podeCadastrarId(id);
		//Esse método cria uma rede e cadastra ela na lista de produtos
		if(podeCadastrar && valorBase>valorDescontado && valorBase>0 && valorDescontado>0 && participantes>0) {//Condições necessárias realizar o cadastro
			Experiencia novaExp= new Experiencia(id,valorBase,valorDescontado,endereco,participantes);
			cadastroDeProdutos.add(novaExp);
		}	else if(podeCadastrar) {
			System.out.println("Parâmetros de cadastro inválidos, operação cancelada!\n");
		} 	else {
			System.out.println("Identificador já utilizado, cadastro não efetuado!\n");
		}
	}
	
	public void cadastrarPontoDeInteresse(String nome,Point endereco) {
		//Esse método cria um ponto de interesse e cadastra ele na lista de pontos
		if(podeCadastrarPonto(nome,endereco)) {//Condição necessárias realizar o cadastro
			PontoInteresse novoPonto=new PontoInteresse(nome,endereco);
			cadastroDePontos.add(novoPonto);
		} else {
			System.out.println("Ponto com mesmo nome já e endereço cadastrado, operação cancelada!\n");
		}
	}
	
	private Alugavel selecionaProduto(String id) {
		//Método que encontra e retorna o produto de ID indicada pelo parâmetro e o retorna, ou retorna null se não houver produto na lista com o Id buscado
		Alugavel produtoBuscado=null;
		for (Alugavel produtoIterado: cadastroDeProdutos) {
			if(produtoIterado.getId().compareTo(id)==0) {
				produtoBuscado=produtoIterado;
			}
		}
		return produtoBuscado;
	}
	
	public Reserva comprarProduto(String id,int dias, int pessoas,int menores) {
		//Método que realiza a reserva de um produto de ID indicada por uma certa quantidade de dias e para uma certa quantidade de pessoas. Como não foi estabelecido pelo enunciado que produtos já reservados não poderiam ser reservados novamente nem relações de datas ou estadia, a única condição leva em conta para a reserva foi a existência do produto.
		Reserva novaReserva=null;
		if(dias>0 && pessoas>0 && menores >=0) {
			Alugavel produto=selecionaProduto(id);
			if(produto!=null) {
				novaReserva= new Reserva(id,pessoas,menores,dias);
				reservasEfetuadas.add(novaReserva);
			} else {
				System.out.println("Produto com ID fornecido não encontrado, comprovante de reserva nulo retornado!\n");
			}	
		} else {
			System.out.println("Parâmetros de reserva inválidos, comprovante retornado nulo!");
		}
		return novaReserva;
	}
	
	public Reserva comprarProduto(String id, int pessoas,int menores) {
		//Método é uma sobrecarga do método acima para caso o número de dias não seja fornecido (deve ser considerado por padrão 1)
		Reserva novaReserva=null;
		if(pessoas>0 && menores >=0) {
			Alugavel produto=selecionaProduto(id);
			if(produto!=null) {
				novaReserva= new Reserva(id,pessoas,menores,1);
				reservasEfetuadas.add(novaReserva);
			} else {
				System.out.println("Produto com ID fornecido não encontrado, comprovante de reserva nulo retornado!\n");
			}	
		} else {
			System.out.println("Parâmetros de reserva inválidos, comprovante retornado nulo!");
		}
		return novaReserva;
	}	

	private boolean existeReserva(String id) {
		//Esse método verifica se uma determinada reserva existe na lista de reservas efetuadas
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
		//Esse método seleciona o índice de um produto com o ID indicado
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
		//Esse método remove um produto da lista de produtos cadastrados por seu id
		boolean existeCadastro= podeCadastrarId(id)? false:true; //Se eu posso cadastrar o produto é por que não existe cadastro
		if (existeCadastro) {
			int posCadastro=indexProduto(id);
			cadastroDeProdutos.remove(posCadastro);
			System.out.println("Cadastro apagado com sucesso!\n");
		} else {
			System.out.println("Não há produto cadastrado com essa ID, operação cancelada!");
		}
		
	}
	
	
	public double valorTotalListaDeReserva(Reserva[] reservas) {
		//Esse método é capaz de fazer a soma de um array de reservas dado como parâmetro. O penúltimo ponto do item 2 do enunciado ficou ambíguo no sentido de definir se esse input é externo ou inter, por isso a função foi mantida como public
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
		//Esse método é capaz de fazer a soma de todas as reservas da lista cadastradas. O penúltimo ponto do item 2 do enunciado ficou ambíguo, por isso esse método foi implementado.
		double valorTotal=0;
		for(Reserva reservaIterada: reservasEfetuadas) {
			String idDaReserva=reservaIterada.getId();
			if(existeReserva(idDaReserva)) {
				valorTotal += selecionaProduto(idDaReserva).valorReserva(reservaIterada.dias(),reservaIterada.numAdultos(),reservaIterada.numMenores());
			}
		}
		return valorTotal;
	}
	
	
	
	
	public String informaçãoDasReservas() {
		//Esse método retorna uma string com todas as informações das reservas feitas e disponíveis na lista de reservas
		String txt= "Não há nenhuma reserva!\n";
		if (reservasEfetuadas.size()>0) {
			txt="----------------------Informação das reservas cadastradas:----------------------\n";
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
		//Esse método retorna uma string com todas as informações de todos os produtos cadastrados, do mesmo modo que pedido no enunciado
		String txt="Nenhum produto cadastrado!";
		if(cadastroDeProdutos.size()>0) {
			txt="----------------------Informação dos produtos disponíveis:----------------------\n";
			for (Alugavel produto: cadastroDeProdutos) {
				txt+="\n";
				txt+=produto.toString();
				if(cadastroDePontos.size()>0) {
					txt+="\t\tDistância do produto aos pontos de interesse:\n";
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
