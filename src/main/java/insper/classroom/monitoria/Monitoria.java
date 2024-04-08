package insper.classroom.monitoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Monitoria {

    private String id;
    private String id_professor;
    private String nome;
    private String descricao;
    private String data;
    private String duracao;
    private String departamento;
    
    
    
}
