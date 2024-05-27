package insper.classroom.monitoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.io.Serializable;


@Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Monitoria implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;
    private String id_professor;
    private String nome;
    private String descricao;
    private String data;
    private String duracao;
    private String departamento;
    
    
    
}
