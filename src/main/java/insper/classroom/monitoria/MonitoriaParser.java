package insper.classroom.monitoria;

public class MonitoriaParser {

    public static Monitoria to(CreateMonitoriaIn in) {
        return Monitoria.builder()
                .id_professor(in.id_professor())
                .nome(in.nome())
                .descricao(in.descricao())
                .data(in.data())
                .duracao(in.duracao())
                .departamento(in.departamento())
                .build();
    }
    
    public static CreateMonitoriaOut to(Monitoria monitoria) {
        return CreateMonitoriaOut.builder()
                .id(monitoria.id())
                .build();

    }


}