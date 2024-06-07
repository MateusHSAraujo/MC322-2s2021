package com.unicamp.mc322.lab10.App.Veiculo;

import com.unicamp.mc322.lab10.App.Usuario.Motorista;

public class Veiculo {
	//Essa classe identifica um ve�culo a ser cadastrado no aplicativo. Segundo o enunciado, cada ve�culo tem uma placa, um ano de fabrica��o e uma refer�ncia para o propriet�rio
	private String placa;
	private int anoDeFabricacao;
	private Motorista proprietario;
	private boolean ehDeLuxo;//optei por criar um atributo a mais para poder diferenciar os ve�culos entre de luxo e comuns
	
	public Veiculo(String placa, int anoDeFabricacao, Motorista dono,boolean luxo) {
		this.placa=placa;
		this.anoDeFabricacao=anoDeFabricacao;
		proprietario=dono;
		ehDeLuxo=luxo;
	}
	
	public boolean getLuxo() {
		//Esse m�todo responde se o ve�culo � de luxo ou n�o
		return ehDeLuxo;
	}
	
	public boolean compararPlaca(String placa) {
		//Esse m�todo verifica se o ve�culo possui a placa indicada como par�metro
		return this.placa.equals(placa);
	}
	
	
	@Override
	public String toString() {
		String txt="\t\t*Placa: "+placa+"; Ano de fabrica��o: "+anoDeFabricacao+"; "+(ehDeLuxo? "Luxo\n":"Comum\n");
		return txt;
	}
	
	public String toStringDetalhado() {
		String txt="\t-Placa: "+placa+"; Ano de fabrica��o: "+anoDeFabricacao+"; "+(ehDeLuxo? "Luxo; ":"Comum; ")+"Cnh do propriet�rio: "+proprietario.getCnh()+"\n";
		return txt; 
	}
	
}
