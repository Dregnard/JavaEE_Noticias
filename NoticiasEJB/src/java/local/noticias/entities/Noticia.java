/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.noticias.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DAM2
 */
@Entity
@Table(name = "noticia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticia.findAll", query = "SELECT n FROM Noticia n")
    , @NamedQuery(name = "Noticia.findById", query = "SELECT n FROM Noticia n WHERE n.id = :id")
    , @NamedQuery(name = "Noticia.findByAutor", query = "SELECT n FROM Noticia n WHERE n.autor = :autor")
    , @NamedQuery(name = "Noticia.findByNoticia", query = "SELECT n FROM Noticia n WHERE n.noticia = :noticia")
    , @NamedQuery(name = "Noticia.findByTitulo", query = "SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    , @NamedQuery(name = "Noticia.findBySlug", query = "SELECT n FROM Noticia n WHERE n.slug = :slug")
    , @NamedQuery(name = "Noticia.findByRutaImagenNoticia", query = "SELECT n FROM Noticia n WHERE n.rutaImagenNoticia = :rutaImagenNoticia")
    , @NamedQuery(name = "Noticia.findByTimeInsert", query = "SELECT n FROM Noticia n WHERE n.timeInsert = :timeInsert")
    , @NamedQuery(name = "Noticia.findByPage", query = "SELECT n FROM Noticia n ORDER BY n.timeInsert DESC")})
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "autor")
    private String autor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "noticia")
    private String noticia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "slug")
    private String slug;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "ruta_imagen_noticia")
    private String rutaImagenNoticia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_insert")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeInsert;

    public Noticia() {
    }

    public Noticia(Long id) {
        this.id = id;
    }

    public Noticia(Long id, String autor, String noticia, String titulo, String slug, String rutaImagenNoticia, Date timeInsert) {
        this.id = id;
        this.autor = autor;
        this.noticia = noticia;
        this.titulo = titulo;
        this.slug = slug;
        this.rutaImagenNoticia = rutaImagenNoticia;
        this.timeInsert = timeInsert;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getRutaImagenNoticia() {
        return rutaImagenNoticia;
    }

    public void setRutaImagenNoticia(String rutaImagenNoticia) {
        this.rutaImagenNoticia = rutaImagenNoticia;
    }

    public Date getTimeInsert() {
        return timeInsert;
    }

    public void setTimeInsert(Date timeInsert) {
        this.timeInsert = timeInsert;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "local.noticias.entities.Noticia[ id=" + id + " ]";
    }
    
}
