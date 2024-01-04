package ups.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
     private Integer id;
     private String nombre;
     private String descripcion;
     private String estado;
     private Integer duracion;
    
}
