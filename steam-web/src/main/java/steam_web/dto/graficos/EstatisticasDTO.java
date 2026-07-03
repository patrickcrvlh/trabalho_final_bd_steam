package steam_web.dto.graficos;

import java.util.List;

public class EstatisticasDTO {

    private List<GeneroGraficoDTO> generos;
    private List<IdiomaGraficoDTO> idiomas;
    private List<TagGraficoDTO> tags;
    private List<PlataformaGraficoDTO> plataformas;

    public List<GeneroGraficoDTO> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroGraficoDTO> generos) {
        this.generos = generos;
    }

    public List<IdiomaGraficoDTO> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<IdiomaGraficoDTO> idiomas) {
        this.idiomas = idiomas;
    }

    public List<TagGraficoDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagGraficoDTO> tags) {
        this.tags = tags;
    }

    public List<PlataformaGraficoDTO> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(List<PlataformaGraficoDTO> plataformas) {
        this.plataformas = plataformas;
    }
}
