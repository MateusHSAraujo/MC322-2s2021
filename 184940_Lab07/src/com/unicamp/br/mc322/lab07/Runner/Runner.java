package com.unicamp.br.mc322.lab07.Runner;

import com.unicamp.br.mc322.lab07.EstruturaDeDados.Point;
import com.unicamp.br.mc322.lab07.Sistema.Sistema;

public class Runner {

	public static void main(String[] args) {
		Sistema app = new Sistema();
		//Cadastrando pontos de interesse:
		app.cadastrarPontoDeInteresse("Parque Ibirapuera", new Point(20,-30));
		app.cadastrarPontoDeInteresse("Catedral Metropolitana", new Point(5,40));
		
		//Cadastrando produtos (Residências e Experiências):
		app.cadastrarApartamento("AP14","Taquaral Flat", new Point(10,10), 1, 3, 2, 150, true);
		app.cadastrarApartamento("AP99", "Laraqual Tlaf", new Point(-15,7), 9, 2, 2, 180,false);
		app.cadastrarCasa("DECOURT01","Pousada do Jorge", new Point(37,81), 5, 4, 450, true);		
		app.cadastrarMansao("GASTEI01RIM", "A função exponensial do enunciado tá quebrada",new Point(-10,-10), 1200, 2000);
		app.cadastrarRede("RED1","Rede Simples", new Point(1,1),20);
		app.cadastrarExperiencia("RODIZIOPIZZA", new Point(70,70), 10, 59, 39);
		
		//Imprimindo situação do sistema:
		System.out.println(app.getInfo());
		
		//Provando que dois itens de mesmo Id não são cadastrados:
		app.cadastrarRede("AP14","Rede Simples", new Point(1,1),20);
		
		//Comprando reservas e armazenando-as na lista cadastrada no sistema:
		app.comprarProduto("AP14", 3, 4, 2);
		app.comprarProduto("RODIZIOPIZZA", 0, 4, 2);
		app.comprarProduto("DECOURT01", 10, 2, 0);
		app.comprarProduto("GASTEI01RIM", 5, 5, 0);
		app.comprarProduto("RED1", 10, 5, 0);
		
		//Identificando a informação de cada reserva no sistema:
		System.out.println(app.informaçãoDasReservas());
		
		//Cancelando cadastros:
		app.removerProduto("AP14");
		app.removerProduto("RODIZIOPIZZA");
		app.removerProduto("DECOURT01");
		app.removerProduto("GASTEI01RIM");
		app.removerProduto("RED1");
		
		//Mostrando que os produtos foram excluídas:
		System.out.println(app.getInfo());
		
	}

}
