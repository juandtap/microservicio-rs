package ups.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ups.model.Curso;
import ups.services.CursoGestion;

import java.util.List;

@Path("/cursos")
public class CursoResource {

    @Inject
    private CursoGestion service;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCursos(){
        List<Curso> list =  service.getAll();
        return Response.ok().status(Response.Status.OK).entity(list).build();
    }

    @POST
    @Path("nuevocurso")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCurso(Curso curso){
        Curso c = service.save(curso);
        return Response.ok().status(Response.Status.CREATED).entity(c).build();
    }
}
