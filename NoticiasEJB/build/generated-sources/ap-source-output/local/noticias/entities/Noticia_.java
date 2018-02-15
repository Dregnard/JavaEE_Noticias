package local.noticias.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-15T03:42:02")
@StaticMetamodel(Noticia.class)
public class Noticia_ { 

    public static volatile SingularAttribute<Noticia, Date> timeInsert;
    public static volatile SingularAttribute<Noticia, String> titulo;
    public static volatile SingularAttribute<Noticia, Long> id;
    public static volatile SingularAttribute<Noticia, String> rutaImagenNoticia;
    public static volatile SingularAttribute<Noticia, String> autor;
    public static volatile SingularAttribute<Noticia, String> noticia;
    public static volatile SingularAttribute<Noticia, String> slug;

}