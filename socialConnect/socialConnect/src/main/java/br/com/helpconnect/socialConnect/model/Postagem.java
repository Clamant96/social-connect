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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "postagem")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String img;
	
	@NotNull
	private String descricao;
	
	@ManyToOne
	@JsonIgnoreProperties("postagens")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "postagem", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("postagem")
	private List<Mensagem> mensagens;
	
	@ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinTable(
	  name = "likes_usuario_postagem", 
	  joinColumns = @JoinColumn(name = "postagem_id"), 
	  inverseJoinColumns = @JoinColumn(name = "usuario_id")
	  )
	@JsonIgnoreProperties({"img", "nome", "username", "site", "senha", "biografia", "postagens", "like", "seguindo", "listaSeguindo", "storys"})
	private List<Usuario> likePostagem = new ArrayList<>();

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public List<Usuario> getLikePostagem() {
		return likePostagem;
	}

	public void setLikePostagem(List<Usuario> likePostagem) {
		this.likePostagem = likePostagem;
	}

}
