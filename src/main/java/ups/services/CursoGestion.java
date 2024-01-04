package ups.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.data.CursoDAO;
import ups.model.Curso;

import java.util.List;

@Stateless
public class CursoGestion {

    @Inject
    private CursoDAO cursoDAO;

    public Curso save(Curso curso){
        this.cursoDAO.save(curso);
        return curso;
    }

    public List<Curso> getAll(){
        return cursoDAO.listAll();
    }
}
