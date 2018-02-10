package br.com.duxus.insight.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "portifolio")
@Component
public class Portifolio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotEmpty(message = "Nome é obrigatório.")
	@Size(max = 100, message = "Nome muito longo.")
	@Column(name = "nome")
	private String nome;
	
	@NotEmpty(message = "Descrição é obrigatória.")
	@Size(max = 1000, message = "Descrição muito longa.")
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "arquivo")
	private String caminhoArquivo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idCliente")
	@Valid
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusPortfolio status;
	
	@Transient
	@JsonIgnore
	private MultipartFile arquivo;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(
        name = "tagPortifolio", 
        joinColumns = { @JoinColumn(name = "idPortifolio") }, 
        inverseJoinColumns = { @JoinColumn(name = "idTag") }
    )
	@JsonIgnore
	private Set<Tag> listaTags = new HashSet<>();
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private List<Tag> lista = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatusPortfolio getStatus() {
		return status;
	}

	public void setStatus(StatusPortfolio status) {
		this.status = status;
	}

	@Transient
	public MultipartFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(MultipartFile arquivo) {
		this.arquivo = arquivo;
	}

	@JsonIgnore
	public Set<Tag> getListaTags() {
		return listaTags;
	}

	public void setListaTags(Set<Tag> listaTags) {
		this.listaTags = listaTags;
	}

	public List<Tag> getLista() {
		return lista;
	}

	public void setLista(List<Tag> lista) {
		this.lista = lista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caminhoArquivo == null) ? 0 : caminhoArquivo.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listaTags == null) ? 0 : listaTags.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Portifolio other = (Portifolio) obj;
		if (caminhoArquivo == null) {
			if (other.caminhoArquivo != null)
				return false;
		} else if (!caminhoArquivo.equals(other.caminhoArquivo))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listaTags == null) {
			if (other.listaTags != null)
				return false;
		} else if (!listaTags.equals(other.listaTags))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public boolean equals (Long id) {
		
		boolean isEqual = this.getId() == id;
    	
    	return isEqual;
		
	}
	
}
