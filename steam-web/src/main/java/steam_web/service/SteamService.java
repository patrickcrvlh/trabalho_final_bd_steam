package steam_web.service;

import steam_web.dto.*;
import steam_web.dto.consultas.*;
import steam_web.dto.graficos.*;
import steam_web.repository.SteamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SteamService {

    private final SteamRepository repository;

    public SteamService(SteamRepository repository){

        this.repository = repository;

    }

    public List<EngajamentoDTO> rankingEngajamento(){

        return repository.rankingEngajamento();

    }

    public int totalJogos(){
        return repository.totalJogos();
    }

    public int totalIdiomas(){
        return repository.totalIdiomas();
    }

    public int totalGeneros(){
        return repository.totalGeneros();
    }

    public int totalTags(){
        return repository.totalTags();
    }

    public List<JogoDTO> buscarPorNome(String nome,
                                       int pagina,
                                       int tamanho){

        return repository.buscarPorNome(nome, pagina, tamanho);

    }

    public int contarJogos(String nome){

        return repository.contarJogos(nome);

    }

    public List<Consulta1DTO> consulta1(){

        return repository.consulta1();

    }

    public List<Consulta2DTO> consulta2(){

        return repository.consulta2();

    }

    public List<Consulta3DTO> consulta3(){

        return repository.consulta3();

    }

    public List<Consulta4DTO> consulta4(){

        return repository.consulta4();

    }

    public List<Consulta5DTO> consulta5(){

        return repository.consulta5();

    }

    public List<Consulta6DTO> consulta6(){

        return repository.consulta6();

    }

    public List<Consulta8DTO> consulta8(){

        return repository.consulta8();

    }

    public List<Consulta9DTO> consulta9(){

        return repository.consulta9();

    }

    public List<PontuacaoDTO> consulta10() {
        return repository.consulta10();
    }

    public EstatisticasDTO buscarEstatisticas() {

        EstatisticasDTO dto = new EstatisticasDTO();

        dto.setGeneros(

                repository.buscarGeneros()

        );

        dto.setIdiomas(

                repository.buscarIdiomas()

        );

        dto.setTags(

                repository.buscarTags()

        );

        dto.setPlataformas(

                repository.buscarPlataformas()

        );

        return dto;

    }

}