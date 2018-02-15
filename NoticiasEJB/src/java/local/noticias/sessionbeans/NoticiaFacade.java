package local.noticias.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import local.noticias.entities.Noticia;

/**
 *
 * @author DAM2
 */
@Stateless
public class NoticiaFacade extends AbstractFacade<Noticia> {

    @PersistenceContext(unitName = "NoticiasEJBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoticiaFacade() {
        super(Noticia.class);
    }

    public boolean existBySlug(String slug) {
        try {
            Query query = em.createNamedQuery("Noticia.findBySlug");
            query.setParameter("slug", slug);
            query.getSingleResult();
            return true;
        } catch (NoResultException nre) {
            return false;
        }
    }

}
