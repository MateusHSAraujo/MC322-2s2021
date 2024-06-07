package com.unicamp.mc322.lab10.App.Usuario;

import com.unicamp.mc322.lab10.App.Date.Date;

public class Passageiro extends Usuario {
	//Essa classe representa um usu�rio do tipo passageiro, ele tem os mesmos atributos que um usu�rio normal por�m se difere dos motoristas e em implementa��esm menos abstratas que a indicada , provavelmente tamb�m teria m�todos distintas da clase de motoristas.
	
	public Passageiro(String nome,String cpf, String cartao, Date aniversario) {
		super(nome,cpf,cartao,aniversario);
	}
	
	public boolean compararCpf(String cpf) {
		//Esse m�todo retorna true se o cpf do passageiro for igual ao cpf passado como par�metro
		return this.cpf.equals(cpf);
	}
	
	@Override
	public String toString() {
		String txt="\tPassageiro "+nome+" :\n";
		txt+="\t-Cpf: "+cpf+"\n\t-Data de Nascimento: "+nascimento.toString()+"\n";
		return txt;
	}
}
