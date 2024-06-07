package com.unicamp.mc322.lab10.App.Veiculo;

import com.unicamp.mc322.lab10.App.Usuario.Motorista;

public class Veiculo {
	//Essa classe identifica um veículo a ser cadastrado no aplicativo. Segundo o enunciado, cada veículo tem uma placa, um ano de fabricação e uma referência para o proprietário
	private String placa;
	private int anoDeFabricacao;
	private Motorista proprietario;
	private boolean ehDeLuxo;//optei por criar um atributo a mais para poder diferenciar os veículos entre de luxo e comuns
	
	public Veiculo(String placa, int anoDeFabricacao, Motorista dono,boolean luxo) {
		this.placa=placa;
		this.anoDeFabricacao=anoDeFabricacao;
		proprietario=dono;
		ehDeLuxo=luxo;
	}
	
	public boolean getLuxo() {
		//Esse método responde se o veículo é de luxo ou não
		return ehDeLuxo;
	}
	
	public boolean compararPlaca(String placa) {
		//Esse método verifica se o veículo possui a placa indicada como parâmetro
		return this.placa.equals(placa);
	}
	
	
	@Override
	public String toString() {
		String txt="\t\t*Placa: "+placa+"; Ano de fabricação: "+anoDeFabricacao+"; "+(ehDeLuxo? "Luxo\n":"Comum\n");
		return txt;
	}
	
	public String toStringDetalhado() {
		String txt="\t-Placa: "+placa+"; Ano de fabricação: "+anoDeFabricacao+"; "+(ehDeLuxo? "Luxo; ":"Comum; ")+"Cnh do proprietário: "+proprietario.getCnh()+"\n";
		return txt; 
	}
	
}
