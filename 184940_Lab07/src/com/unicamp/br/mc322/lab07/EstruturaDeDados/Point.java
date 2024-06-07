package com.unicamp.br.mc322.lab07.EstruturaDeDados;
public class Point {
	//Essa classe serve basicamente como estrutura de dados para a operação das demais classes
	private int x;
	private int y;
	
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public double distance(Point b) {//Essa classe é a responsável por eventualmente calcular distância entre pontos
		return Math.sqrt(Math.pow(x-b.x, 2)+Math.pow(y-b.y, 2));
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
	
}
