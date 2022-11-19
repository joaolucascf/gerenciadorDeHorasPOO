package poofinal.entities;

import java.io.Serializable;

public enum Activities implements Serializable {
    MONITOR("Monitor", "51 Horas"),
    BOLSISTA_PESQUISA("Voluntário em Projetos de Pesquisa", "51 Horas"),
    BOLSISTA_EXTENSAO("Voluntário em Projetos de Extensão", "51 Horas"),
    BOLSISTA_ENSINO("Voluntário em Projetos de Ensino", "51 Horas"),
    PARTICIPACAO_ORGANIZADOR("Participação em Atividades como Organizador", "34 Horas"),
    PARTICIPACAO_COLABORADOR("Participação em Atividades como Colaborador", "34 Horas"),
    PARTICIPACAO_MINISTRANTE("Participação em Atividades como Ministrante", "34 Horas"),
    PARTICIPACAO_SACOMP("Participação na SACOMP", "34 Horas"),
    PARTICIPACAO_CURSOS_ESCOLAS("Participação em Cursos e Escolas", "51 Horas"),
    PARTICIPACAO_EVENTO_REGIONAL("Participação em Evento Regional", "17 Horas"),
    PARTICIPACAO_EVENTO_NACIONAL("Participação em Evento Nacional", "34 Horas"),
    PARTICIPACAO_EVENTO_INTERNACIONAL("Participação em Evento Internacional", "34 Horas"),
    PUBLICACAO_ARTIGO_REGIONAL("Publicação de Arigo Regional", "34 Horas"),
    PUBLICACAO_ARTIGO_NACIONAL("Publicação de Arigo Nacional", "51 Horas"),
    PUBLICACAO_ARTIGO_INTERNACIONAL("Publicação de Arigo Internacional", "68 Horas"),
    REPRESENTACAO_ESTUDANTIL("Representação Estudantil", "51 Horas"),
    PREMIOS_DISTINCOES("Obtenção de Prêmios e Distinções", "68 Horas"),
    CERTIFICADOS_PROFISSIONAIS("Certificações profissionais", "51 Horas");

    private String description;
    private String hours;
    Activities(String description, String hours){
        this.description = description;
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }
}
