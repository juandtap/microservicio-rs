package ups.data;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ups.model.Curso;

import java.io.Serializable;
import java.util.List;

@Stateless
public class CursoDAO implements Serializable {
    @PersistenceContext
    private EntityManager em;

    private static final long serialVersionUID = 1L;

    //public static List<Curso> listaCurso = new ArrayList<>();


    public Curso save(Curso curso) {
        //listaCurso.add(curso);
        em.persist(curso);
        return curso;
    }


    public List<Curso> listAll() {
        String jpql = "SELECT c FROM Curso c";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }


    public void delete(Integer id) {
        System.out.println("metodo eliminar no implementado");
    }
}
