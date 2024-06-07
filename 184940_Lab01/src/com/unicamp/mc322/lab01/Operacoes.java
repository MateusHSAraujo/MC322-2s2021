package com.unicamp.mc322.lab01;
import java.util.Scanner;
public class Operacoes {
	
	public void soma(Scanner input) {
		System.out.println("Digite dois números para somar:");
		float x= input.nextFloat();
		float y=input.nextFloat();
		System.out.printf("%.2f + %.2f = %.2f\n",x,y,x+y);
		return;
	}
	
	public void subtracao(Scanner input) {
		System.out.println("Digite dois números para subtrair:");
		float x= input.nextFloat();
		float y=input.nextFloat();
		System.out.printf("%.2f - %.2f = %.2f\n",x,y,x-y);
		return;
	}
	
	public void multiplicar(Scanner input) {
		System.out.println("Digite dois números para multiplicar:");
		float x= input.nextFloat();
		float y=input.nextFloat();
		System.out.printf("%.2f x %.2f = %.2f\n",x,y,x*y);
		return;
	}
	
	public void dividir(Scanner input) {
		System.out.println("Digite dois números para dividir:");
		float x= input.nextFloat();
		float y=input.nextFloat();
		System.out.printf("%.2f / %.2f = %.2f\n",x,y,x/y);
		return;
	}
	
	public long fatorial(long x) {
		if(x==0) {
			return 1;
		} else {
			return x*fatorial(x-1);
		}
	}
	
	public void opfatorial(Scanner input) {
		System.out.println("Digite um inteiro entre 0 e 20 para calcular seu fatorial:");
		long x= input.nextLong();
		if (x>20) {
			System.out.println("Valor inválido");
		} else {
			System.out.printf("%d! = %d\n",x,fatorial(x));
		}
		return;
	}
	
	public void primo(Scanner input) {
		System.out.println("Digite um inteiro para verificar se ele é primo:");
		int x= input.nextInt();
		boolean ehPrimo=true;
		for(int i=2;i<x;i++) {
			if (x%i==0){
				ehPrimo = false;
			}
		}
		if (ehPrimo) {
			System.out.printf("%d é primo\n",x);
		} else {
			System.out.printf("%d não é primo\n",x);
		}
		
		return;
	}

	
	
	
}
