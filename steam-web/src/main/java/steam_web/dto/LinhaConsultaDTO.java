package steam_web.dto;

import java.util.List;

public class LinhaConsultaDTO {

    private List<String> valores;

    public LinhaConsultaDTO(List<String> valores) {
        this.valores = valores;
    }

    public List<String> getValores() {
        return valores;
    }

}
