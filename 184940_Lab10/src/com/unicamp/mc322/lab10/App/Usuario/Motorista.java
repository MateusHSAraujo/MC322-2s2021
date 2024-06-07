package com.unicamp.mc322.lab10.App.Usuario;
import java.util.ArrayList;
import java.util.List;

import com.unicamp.mc322.lab10.App.Date.Date;
import com.unicamp.mc322.lab10.App.Veiculo.*;

public class Motorista extends Usuario{
	
	//Essa classe representa os Motoristas, que tamb�m s�o usu�rios. Al�m dos atributos de usu�rios, segundo o enunciado, motoristas tamb�m possuem uma habilita��o e uma lista de ve�culos

	private String cnh;
	private List<Veiculo> veiculos;
	
	public Motorista(String nome,String cpf, String cartao, Date aniversario,String habilitacao) {
		super(nome,cpf,cartao,aniversario);
		cnh=habilitacao;
		veiculos= new ArrayList<Veiculo>();
	}
	
	public void cadastrarVeiculo(Veiculo carro) {
		//Essa fun��o adiciona um ve�culo na lista e ve�culos do motorista
		veiculos.add(carro);
	}
	
	public boolean compararCnh(String cnh) {
		//Esse m�todo retorna true se a cnh do motorista for igual a cnh indicada como par�metro
		return this.cnh.equals(cnh);
	}
	
	public String getCnh(){
		//Essa fun��o retorna a cnh de um motorista
		return cnh;
	}
	
	public boolean possuiVeiculo(String placa) {
		//Essa fun��o retorna um booleano indicando se o motorista tem o ve�culo da placa indicada
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
		txt+="\t-Ve�culos cadastrados:\n";
		if(veiculos.size()==0) {
			txt+="\t\t*Nenhum ve�culo cadastrado ainda\n";
		}
		for(Veiculo carro : veiculos) {
			txt+=carro.toString();
		}
		txt+="\n";
		return txt;
	}
}
