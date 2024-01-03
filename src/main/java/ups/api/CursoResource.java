package ups.api;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ups.model.Curso;
import ups.services.CursoService;

import java.util.List;

@Path("/cursos")
public class CursoResource {

    @Inject
    private CursoService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCursos(){
        List<Curso> list =  service.listAll();
        return Response.ok().status(Response.Status.OK).entity(list).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCurso(Curso curso){
        Curso c =  service.save(curso);
        return Response.ok().status(Response.Status.CREATED).entity(c).build();
    }
}
