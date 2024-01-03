package ups.services;

import jakarta.inject.Named;

import ups.model.Curso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class CursoServiceImp implements  CursoService{

    public static List<Curso> listaCurso = new ArrayList<>();

    @Override
    public Curso save(Curso curso) {
        listaCurso.add(curso);
        return curso;
    }

    @Override
    public List<Curso> listAll() {
        return listaCurso;
    }

    @Override
    public void delete(Integer id) {
        System.out.println("metodo no implementado");
    }
}
