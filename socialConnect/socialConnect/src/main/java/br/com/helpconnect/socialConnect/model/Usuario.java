package br.com.helpconnect.socialConnect.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String img;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String username;
	
	@URL
	private String site;
	
	@NotNull
	private String senha;
	
	private String biografia;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagens;
	
	@ManyToMany(mappedBy = "likePostagem", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"img", "descricao", "usuario", "mensagens", "likePostagem"})
	private List<Postagem> like = new ArrayList<>();
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "seguindo_id")
	@JsonIgnoreProperties("usuario")
	private Seguindo seguindo;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
	  name = "lista_seguindo",
	  joinColumns = @JoinColumn(name = "usuario_id"),
	  inverseJoinColumns = @JoinColumn(name = "seguindo_id")
	)
	@JsonIgnoreProperties({"usuario", "listaDeSeguindo"})
	private List<Seguindo> listaSeguindo = new ArrayList<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Postagem> getLike() {
		return like;
	}

	public void setLike(List<Postagem> like) {
		this.like = like;
	}

	public Seguindo getSeguindo() {
		return seguindo;
	}

	public void setSeguindo(Seguindo seguindo) {
		this.seguindo = seguindo;
	}

	public List<Seguindo> getListaSeguindo() {
		return listaSeguindo;
	}

	public void setListaSeguindo(List<Seguindo> listaSeguindo) {
		this.listaSeguindo = listaSeguindo;
	}
	
}
