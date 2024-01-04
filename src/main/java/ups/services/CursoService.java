package ups.services;

import jakarta.enterprise.inject.Default;
import ups.model.Curso;

import java.util.List;

public interface CursoService {
    Curso save(Curso curso);
    List<Curso> listAll();
    void delete(Integer id);
}
