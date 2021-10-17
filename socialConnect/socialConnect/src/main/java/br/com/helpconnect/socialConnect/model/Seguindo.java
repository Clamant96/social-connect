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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "seguindo")
public class Seguindo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(mappedBy = "seguindo", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnoreProperties("seguindo")
	private Usuario usuario;
	
	@ManyToMany(mappedBy = "listaSeguindo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"img", "nome", "username", "site", "senha", "biografia", "postagens", "like", "seguindo", "listaSeguindo", "storys"})
	private List<Usuario> listaDeSeguindo = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaDeSeguindo() {
		return listaDeSeguindo;
	}

	public void setListaDeSeguindo(List<Usuario> listaDeSeguindo) {
		this.listaDeSeguindo = listaDeSeguindo;
	}
	

}
