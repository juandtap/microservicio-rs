package ups.services;

import jakarta.inject.Named;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ups.model.Curso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class CursoServiceImp implements  CursoService, Serializable {

    @PersistenceContext
    private EntityManager em;

    private static final long serialVersionUID = 1L;

    //public static List<Curso> listaCurso = new ArrayList<>();

    @Override
    public Curso save(Curso curso) {
        //listaCurso.add(curso);
        em.persist(curso);
        return curso;
    }

    @Override
    public List<Curso> listAll() {
        String jpql = "SELECT c FROM Curso c";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public void delete(Integer id) {
        System.out.println("metodo eliminar no implementado");
    }
}
