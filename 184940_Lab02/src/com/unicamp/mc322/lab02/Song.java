package com.unicamp.mc322.lab02;
import java.lang.Integer;
public class Song {
	private String nome;
	private String genero;
	private String artista;
	private int duracao;
	
	public Song(String nome, String genero, String artista) {
		this.nome = nome;
		this.genero=genero;
		this.artista=artista;
	}
	
	public Song(String nome, String genero, String artista, int duracao) {
		this.nome = nome;
		this.genero=genero;
		this.artista=artista;
		this.duracao=duracao;
	}
	
	private void updateName(String novoNome) {
		this.nome=novoNome;
	}
	
	private void updateGender(String novoGenero) {
		this.genero=novoGenero;
	}
	
	private void updateArtist(String novoArtista) {
		this.artista=novoArtista;
	}
	
	private void updateDuration(int novaDuracao) {
		this.duracao=novaDuracao;
	}
	
	public void atualisaAtributo(String operacao,String novoAtributo) {
		switch(operacao) {
		case "nome":
			updateName(novoAtributo);
			System.out.println("Nome atualizado com sucesso");
			break;
		case "genero":
			updateGender(novoAtributo);
			System.out.println("Gênero atualizado com sucesso");
			break;
		case "artista":
			updateArtist(novoAtributo);
			System.out.println("Artista atualizado com sucesso");
			break;
		case "duracao":
			int conversao;
			try {
				conversao= Integer.parseInt(novoAtributo);
			}
			catch(NumberFormatException e) {
				break;
			}
			updateDuration(conversao);
			System.out.println("Duracao atualizado com sucesso");
			break;
		default:
			System.out.println("Digite 'nome','genero','artista' e 'duracao' e em seguida o novo valor para poder alterar esses atributos");
		}
	}
	
	public String getName() {
		return this.nome;
	}
	
	public String getArtist() {
		return this.artista;
	}
	public String getGenre() {
		return this.genero;
	}
	public int getDuration() {
		return this.duracao;
	}
	
}
