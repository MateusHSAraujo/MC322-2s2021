package com.unicamp.mc322.lab10.App;

import com.unicamp.mc322.lab10.App.Date.*;

public class Runner {
	public static void main(String args[]) {
		//A execu��o do cen�rio proposto de exemplo no enunciado come�a na linha 102. As linhas de c�digo at� l� t�m o intuito de facilitar a corre��o e verifica��o das funcionalidades pedidas. Agrade�o a compreens�o!
		App app = new App();
		
		//Cadastrando passageiros:
		app.cadastrarPassageiro("Joao", "171.048.825-06", "0400 2257 4865 7978", new Date(1,1,2001));
		app.cadastrarPassageiro("Maria", "332.685.289-65", "5632 0946 5972 8348", new Date(2,2,2002));
		app.cadastrarPassageiro("Antonio", "972.896.756-89", "5976 6975 2876 8968", new Date(3,3,203));
		app.cadastrarPassageiro("Jose", "021.152.892-054", "2875 6548 2975 4912", new Date(4,4,2004));
		
		//Imprimindo informa��es desses passageiros:
		System.out.println(app.imprimirInformacaosUsuarios());
		
		//Passageiros com cpf iguais n�o s�o cadastrados:
		app.cadastrarPassageiro("Joao2", "171.048.825-06", "0400 2257 4865 7978", new Date(11,11,2001));
		System.out.println(app.imprimirInformacaosUsuarios());
		
		//Cadastrando Motoristas
		app.cadastrarMotorista("Pedro", "758.250.380-51", "5244 8217 3762 0058", new Date(5,5,2005), "17563547430");
		app.cadastrarMotorista("Carlos", "324.336.460-49", "2149 7389512 0897", new Date(6,6,2006), "77480892712");
		app.cadastrarMotorista("Marcela", "412.462.900-14", "3002 089576 9323", new Date(7,7,2007), "31528144808");
		app.cadastrarMotorista("Alexia", "991.142.760-97", "3792 271209 42133", new Date(8,8,2008), "28854045341");
		
		//Imprimindo informa��es dos usu�rios cadastrados:
		System.out.println(app.imprimirInformacaosUsuarios());
		
		//Motoristas com cnh iguais n�o s�o cadastrados:
		app.cadastrarMotorista("Pedro2", "758.250.380-51", "5244 8217 3762 0058", new Date(10,5,2005), "17563547430");
		System.out.println(app.imprimirInformacaosUsuarios());
		
		//Cadastrando Ve�culos:
		app.cadastrarVeiculo("KVX-5306", 2020, "17563547430", false);
		app.cadastrarVeiculo("AWC-0064", 2010, "77480892712", false);
		app.cadastrarVeiculo("IAM-8336", 2000, "31528144808", false);
		app.cadastrarVeiculo("JGW-3812", 2005, "28854045341", false);
		app.cadastrarVeiculo("NAQ-4366", 2021, "17563547430", true);
		
		//Imprimindo informa��es desses ve�culos:
		System.out.println(app.imprimirInformacaosVeiculos());
		
		//Ve�culos de mesma placa n�o ser�o cadastrados:
		app.cadastrarVeiculo("NAQ-4366", 2005, "28854045341", false);
		System.out.println(app.imprimirInformacaosVeiculos());
		
		//Depois que cadastrados, os propriet�rios de cada ve�culo passam a t�-los em seus cadastros:
		System.out.println(app.imprimirInformacaosUsuarios());
		
		//Registando uma viagem:
		//-Com um �nico passageiro:
		app.cadastrarViagem("171.048.825-06", "17563547430", "KVX-5306", 100);
		//-Com mais de um passageiros:
		app.cadastrarViagem(new String[] {"332.685.289-65","171.048.825-06"}, "77480892712", "AWC-0064", 200);
		
		//Imprimindo viagens registradas:
		System.out.println(app.imprimirInformacaoViagens());
		
		//O mesmo motorista n�o pode executar duas viagens ao mesmo tempo:
		app.cadastrarViagem("972.896.756-89", "17563547430", "KVX-5306", 500);
		System.out.println(app.imprimirInformacaoViagens());
		
		
		//Se o motorista, o passageiro, ou o ve�culo n�o estiverem cadastrados, a dist�ncia for inv�lida ou o motorista n�o possuir o ve�culo indicado a viagem n�o � registradas:
		app.cadastrarViagem("000.000.000-00", "31528144808", "IAM-8336", 100);
		app.cadastrarViagem("021.152.892-054", "00000000000", "IAM-8336", 100);
		app.cadastrarViagem("021.152.892-054", "31528144808", "KVX-5306", 100);
		app.cadastrarViagem("021.152.892-054", "31528144808", "IAM-8336", -100);
		System.out.println(app.imprimirInformacaoViagens());
		
		//Para cadastrar uma parada fornece-se a cnh do motorista respons�vel pela viagem (uma vez que um motorista s� faz uma viagem por vez):
		//-Cadastrando para sa�da e entrada de uma �nica pessoa:
		app.criarParadaNaViagem("17563547430", 100, "171.048.825-06", "021.152.892-054");
		System.out.println(app.imprimirInformacaoViagens());
		//-Cadastrando para sa�da e entrada de mais de uma pessoa:
		app.criarParadaNaViagem("77480892712", 100, new String[] {"332.685.289-65","171.048.825-06"},new String[] {"021.152.892-054","972.896.756-89"});
		System.out.println(app.imprimirInformacaoViagens());
		
		//*Se a pessoa saindo n�o estiver no ve�culo, a dist�ncia da parada n�o for compat�vel, a pessoa entrando n�o estiver cadastrada ou a viagem n�o existir, a parada n�o � criada
		app.cadastrarViagem(new String[] {"021.152.892-054","171.048.825-06"}, "28854045341", "JGW-3812", 200);
		app.criarParadaNaViagem("28854045341", 100, "332.685.289-65", "021.152.892-054");
		app.criarParadaNaViagem("28854045341", 300, "171.048.825-06", "332.685.289-65");
		app.criarParadaNaViagem("28854045341", 100, "171.048.825-06", "000.000.000-00");
		app.criarParadaNaViagem("00000000000", 100, "171.048.825-06", "332.685.289-65");
		System.out.println(app.imprimirInformacaoViagens());
		
		//Tamb�m � poss�vel adiconar uma parada para uma �nica pessoa descer ou subir:
		app.criarParadaNaViagem("28854045341", 100, "021.152.892-054", null);
		app.criarParadaNaViagem("28854045341", 50, null, "332.685.289-65");
		app.criarParadaNaViagem("28854045341", 50);
		System.out.println(app.imprimirInformacaoViagens());
		
		//Encerrando viagens para que os motoristas fiquem livres para criar novas:
		app.encerrarViagem("17563547430");
		app.encerrarViagem("77480892712");
		app.encerrarViagem("28854045341");
		System.out.println(app.imprimirInformacaoViagens());
		
		
		//------------------Execu��o do cen�rio passsado como exemplo no enunciado:------------------
		app=new App(); //Instanciei um novo app para tornar mais claro o fluxo pedido
		app.cadastrarPassageiro("Marcos","145678798","369874",new Date(15,7,1998));
		app.cadastrarPassageiro("Jo�o", "658973652", "785632", new Date(3,1,2002));
		app.cadastrarMotorista("Maria", "248679108", "483530", new Date(12,02,1997), "987654");
		app.cadastrarVeiculo("ABC-1234", 2009, "987654", true);
		app.cadastrarVeiculo("OOP-2020", 2013, "987654", false);
		//Impress�o para comprovar o cadastro das informa��es:
		System.out.println(app.imprimirInformacaosUsuarios());
		System.out.println(app.imprimirInformacaosVeiculos());
		
		//Gerando a viagem de Marcos com Maria em seu carro de luxo com dist�ncia de 500 metros:
		app.cadastrarViagem("145678798", "987654", "ABC-1234", 500);
		//Adicionando duas paradas � viagem de Marcos:
		app.criarParadaNaViagem("987654", 100);
		app.criarParadaNaViagem("987654", 200);
		//Encerrando a viagem de Marcos para liberar Maria:
		app.encerrarViagem("987654");
		
		//Gerando a viagem de Marcos e Jo�o com Maria em seu carro comum com dist�ncia de 2 km:
		app.cadastrarViagem(new String[]{ "658973652","145678798"}, "987654", "OOP-2020", 2000);
		//Adicionando cinco paradas � viagem de Marcos e Jo�o, sendo que jo�o descer� do ve�culo na terceira parada:
		app.criarParadaNaViagem("987654", 10);
		app.criarParadaNaViagem("987654", 20);
		app.criarParadaNaViagem("987654", 40,"658973652",null);
		app.criarParadaNaViagem("987654", 80);
		app.criarParadaNaViagem("987654", 900);
		//Encerrando a viagem para liberar Maria:
		app.encerrarViagem("987654");
		
		//Gerando a viagem de Jo�o com Maria em seu carro de luxo com dist�ncia de 700 metros:
		app.cadastrarViagem("658973652", "987654", "ABC-1234", 700);
		//Adicionando tr�s paradas � viagem de Jo�o:
		app.criarParadaNaViagem("987654", 10);
		app.criarParadaNaViagem("987654", 20);
		app.criarParadaNaViagem("987654", 30);
		//Encerrando a viagem:
		app.encerrarViagem("987654");
	}
}
