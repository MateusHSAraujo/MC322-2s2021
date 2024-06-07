package com.unicamp.mc322.lab01;

import java.util.Scanner;

public class Calculadora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		boolean repetir=true;
		while(repetir) {
			System.out.println("Escolha uma operação abaixo:");
			int i=0;
			String[] possiveisOperacoes = {"somar","subtração","multiplicar","dividir","calcular fatorial","verificar se um número é primo"};
			while(i<7) {
				int d=i+1;
				if (i==6) {
					System.out.printf("(7) Digite qualquer outro valor para sair do programa\n");
					break;
				} else {
					System.out.printf("(%d) Digite %d para %s\n",d,d,possiveisOperacoes[i]);
					i++;
				}
			}
			int operacao = input.nextInt();
			Operacoes ops = new Operacoes();
			boolean operou=false;
			
			switch(operacao) {
			case 1:
				if (operou==false) {
					ops.soma(input);	
					operou=true;
				}
			case 2:
				if (operou==false) {
					ops.subtracao(input);
					operou=true;
				}
			case 3:
				if (operou==false) {
					ops.multiplicar(input);
					operou=true;
				}
			case 4:
				if (operou==false) {
					ops.dividir(input);
					operou=true;
				}
			case 5:
				if (operou==false) {
					ops.opfatorial(input);
					operou=true;
				}
			case 6:
				if (operou==false) {
					ops.primo(input);
					operou=true;
				}
			default:
				if (operou) {
					System.out.println("Deseja executar outra função?(s/n)");
					String resposta=input.next();
					if (resposta.equals("s")) {
						repetir=true;
					} else {
						repetir=false;
					}
					
				} else {
					repetir =false;
				}
			}
			
		}
		System.out.println("Você escolheu sair.");
		input.close();
		return;
			
	}

}
