package ups.services;

import ups.model.Curso;

import java.util.List;

public interface CursoService {
    Curso save(Curso curso);
    List<Curso> listAll();
    void delete(Integer id);
}
