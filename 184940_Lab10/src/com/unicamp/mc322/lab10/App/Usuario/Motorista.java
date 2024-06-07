package com.unicamp.mc322.lab10.App.Usuario;
import java.util.ArrayList;
import java.util.List;

import com.unicamp.mc322.lab10.App.Date.Date;
import com.unicamp.mc322.lab10.App.Veiculo.*;

public class Motorista extends Usuario{
	
	//Essa classe representa os Motoristas, que também são usuários. Além dos atributos de usuários, segundo o enunciado, motoristas também possuem uma habilitação e uma lista de veículos

	private String cnh;
	private List<Veiculo> veiculos;
	
	public Motorista(String nome,String cpf, String cartao, Date aniversario,String habilitacao) {
		super(nome,cpf,cartao,aniversario);
		cnh=habilitacao;
		veiculos= new ArrayList<Veiculo>();
	}
	
	public void cadastrarVeiculo(Veiculo carro) {
		//Essa função adiciona um veículo na lista e veículos do motorista
		veiculos.add(carro);
	}
	
	public boolean compararCnh(String cnh) {
		//Esse método retorna true se a cnh do motorista for igual a cnh indicada como parâmetro
		return this.cnh.equals(cnh);
	}
	
	public String getCnh(){
		//Essa função retorna a cnh de um motorista
		return cnh;
	}
	
	public boolean possuiVeiculo(String placa) {
		//Essa função retorna um booleano indicando se o motorista tem o veículo da placa indicada
		boolean retorno = false;
		for (Veiculo carro : veiculos) {
			if (carro.compararPlaca(placa)) {
				retorno=true;
			}
		}
		return retorno;
	}
	
	@Override
	public String toString() {
		String txt="\tMotorista "+nome+" :\n";
		txt+="\t-Cpf: "+cpf+"\n\t-Data de Nascimento: "+nascimento.toString()+"\n\t-Cnh: "+cnh+"\n";
		txt+="\t-Veículos cadastrados:\n";
		if(veiculos.size()==0) {
			txt+="\t\t*Nenhum veículo cadastrado ainda\n";
		}
		for(Veiculo carro : veiculos) {
			txt+=carro.toString();
		}
		txt+="\n";
		return txt;
	}
}
