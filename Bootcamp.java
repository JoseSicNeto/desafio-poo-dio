package desafio_poo_dio;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

public class Bootcamp{
	private String nome;
	private String descricao;
	private LocalDate dataInicial = LocalDate.now();
	private LocalDate dataTermino = dataInicial.plusDays(30);
	private Set<Dev> devsInscritos = new HashSet<>();
	private Set<Conteudo> conteudos = new LinkedHashSet<>();
	public String getNome() {
		return nome;
	}
	
	public Bootcamp() {	}
	
	public void setNome(String _nome) {
		this.nome = _nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String _descricao) {
		this.descricao = _descricao;
	}
	
	public LocalDate getDataInicial() {
		return dataInicial;
	}
	
	public void setDataInicial(LocalDate _dataInicial) {
		this.dataInicial = _dataInicial;
	}
	
	public LocalDate getDataTermino() {
		return dataTermino;
	}
	
	public void setDataTermino(LocalDate _dataTermino) {
		this.dataTermino = _dataTermino;
	}
	
	public Set<Dev> getDevsInscritos() {
		return devsInscritos;
	}
	
	public void setDevsInscritos(Set<Dev> _devsInscritos) {
		this.devsInscritos = _devsInscritos;
	}
	
	public Set<Conteudo> getConteudos() {
		return conteudos;
	}
	
	public void setConteudos(Set<Conteudo> _conteudos) {
		this.conteudos = _conteudos;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(conteudos, dataInicial, dataTermino, descricao, devsInscritos, nome);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bootcamp other = (Bootcamp) obj;
		return Objects.equals(conteudos, other.conteudos) && Objects.equals(dataInicial, other.dataInicial)
				&& Objects.equals(dataTermino, other.dataTermino) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(devsInscritos, other.devsInscritos) && Objects.equals(nome, other.nome);
	}
	
	
	
}

class Dev{
	private String nome;
	private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
	private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();
	
	public Dev() { }
	
	public void inscrever(Bootcamp bootcamp){
		this.conteudosInscritos.addAll(bootcamp.getConteudos());
		bootcamp.getDevsInscritos().add(this);
	}
	

	public void progrediz(){
		Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
		if(conteudo.isPresent()) {
			this.conteudosConcluidos.add(conteudo.get());			
			this.conteudosInscritos.remove(conteudo.get());
		}else {
			System.err.println("Nao tem Bootcamps");
		}
	}
	
	public double calcularTotalXp(){
		return this.conteudosConcluidos
				.stream()
				.mapToDouble(Conteudo::calcularXp)
				.sum();
		}


	public String getNome() {
		return nome;
	}
	
	public Set<Conteudo> getConteudosInscritos() {
		return conteudosInscritos;
	}

	public void setConteudosInscritos(Set<Conteudo> _conteudosInscritos) {
		this.conteudosInscritos = _conteudosInscritos;
	}

	public Set<Conteudo> getConteudosConcluidos() {
		return conteudosConcluidos;
	}

	public void setConteudosConcluidos(Set<Conteudo> _conteudosConcluidos) {
		this.conteudosConcluidos = _conteudosConcluidos;
	}

	public void setNome(String _nome) {
		this.nome = _nome;
	}


	
	@Override
	public int hashCode() {
		return Objects.hash(conteudosConcluidos, conteudosInscritos, nome);
	}
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dev other = (Dev) obj;
		return Objects.equals(conteudosConcluidos, other.conteudosConcluidos)
				&& Objects.equals(conteudosInscritos, other.conteudosInscritos) && Objects.equals(nome, other.nome);
	}
	
	
}

class Cursos extends Conteudo{
	private double cargaHoraria;

	public Cursos() { }
	
	@Override
	public double calcularXp() {
		return padraoXP * cargaHoraria;
	}	

	public double getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(double _cargaHoraria) {
		this.cargaHoraria = _cargaHoraria;
	}

	@Override
	public String toString() {
		return "Curso -> " +
				"titulo: " + this.getTitulo() +
				" -- descricao: " + this.getDescricao() +
				" -- carga: " + this.getCargaHoraria();
	}
}

class Mentoria extends Conteudo{
	private LocalDate data;
	
	public Mentoria() { }
	
	@Override	
	public double calcularXp() {
		return padraoXP + 40d;
	}	
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate _data) {
		this.data = _data;
	}

	
	@Override
	public String toString() {
		return "Mentoria -> " +
				"titulo: " + this.getTitulo() +
				" -- descricao: " + this.getDescricao() +
				" -- data: " + this.getData();
	}
	
	
}

abstract class Conteudo{
	protected static final double padraoXP = 10d;
	
	private String titulo;
	private String descricao;
	
	public Conteudo() { }
	
	public abstract double calcularXp();

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String _titulo) {
		this.titulo = _titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String _descricao) {
		this.descricao = _descricao;
	}
}

class Main {
	public static void main(String[] args) {
		Cursos cursoOne = new Cursos();
		cursoOne.setTitulo("Java");
		cursoOne.setDescricao("Desenvolvimento Java");
		cursoOne.setCargaHoraria(10.0);
		
		Cursos cursoTwo = new Cursos();
		cursoTwo.setTitulo("Java POO");
		cursoTwo.setDescricao("Desenvolvimento Java POO");
		cursoTwo.setCargaHoraria(5.0);
		
		Mentoria mentoriaOne = new Mentoria();
		mentoriaOne.setTitulo("Java");
		mentoriaOne.setDescricao("Mentoria Java");
		mentoriaOne.setData(LocalDate.now());
		
  //==================================================================\\
		
		Bootcamp bootcamp = new Bootcamp();
		bootcamp.setNome("Bootcamp Java");
		bootcamp.setDescricao("Desenvolvimento Java");
		bootcamp.getConteudos().add(cursoOne);
		bootcamp.getConteudos().add(cursoTwo);
		bootcamp.getConteudos().add(mentoriaOne);
		
		System.out.println("============= Carlos Info =============\n");
		
		Dev devCarlos = new Dev();
		devCarlos.setNome("Carlos");
		System.out.println("Inscricoes do Carlos: " + devCarlos.getConteudosInscritos() + "\n");
				
		devCarlos.inscrever(bootcamp);
		System.out.println("Inscricoes do Carlos: " + devCarlos.getConteudosInscritos());
		System.out.println("Concluidos do Carlos: " + devCarlos.getConteudosConcluidos());
		System.out.println("XP Total do Carlos: " + devCarlos.calcularTotalXp() + "\n");
		
		devCarlos.progrediz();
		System.out.println("Inscricoes do Carlos: " + devCarlos.getConteudosInscritos());
		System.out.println("Concluidos do Carlos: " + devCarlos.getConteudosConcluidos());
		System.out.println("XP Total do Carlos: " + devCarlos.calcularTotalXp() + "\n");
		
		devCarlos.progrediz();
		System.out.println("Inscricoes do Carlos: " + devCarlos.getConteudosInscritos());
		System.out.println("Concluidos do Carlos: " + devCarlos.getConteudosConcluidos());
		System.out.println("XP Total do Carlos: " + devCarlos.calcularTotalXp());
		
		System.out.println("\n============= Gabi Info =============\n");
		
		Dev devGabi = new Dev();
		devGabi.setNome("Gabi");
		System.out.println("Inscricoes da Gabi: " + devGabi.getConteudosInscritos() + "\n");
		
		
		devGabi.inscrever(bootcamp);
		System.out.println("Inscricoes da Gabi: " + devGabi.getConteudosInscritos());
		System.out.println("Concluidos do Carlos: " + devGabi.getConteudosConcluidos());
		System.out.println("XP Total do Carlos: " + devGabi.calcularTotalXp() + "\n");
		
		devGabi.progrediz();
		System.out.println("Inscricoes da Gabi: " + devGabi.getConteudosInscritos());
		System.out.println("Concluidos do Carlos: " + devGabi.getConteudosConcluidos());
		System.out.println("XP Total do Carlos: " + devGabi.calcularTotalXp() + "\n");
		
		devGabi.progrediz();
		devGabi.progrediz();
		System.out.println("Inscricoes da Gabi: " + devGabi.getConteudosInscritos());
		System.out.println("Concluidos do Carlos: " + devGabi.getConteudosConcluidos());
		System.out.println("XP Total do Carlos: " + devGabi.calcularTotalXp());
	}
}