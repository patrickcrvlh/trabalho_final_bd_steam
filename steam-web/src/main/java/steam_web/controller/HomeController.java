package steam_web.controller;

import org.springframework.web.bind.annotation.RequestParam;
import steam_web.dto.JogoDTO;
import steam_web.util.TabelaUtil;
import steam_web.dto.LinhaConsultaDTO;
import steam_web.dto.consultas.*;
import steam_web.dto.graficos.EstatisticasDTO;
import steam_web.service.SteamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final SteamService service;

    public HomeController(SteamService service){

        this.service = service;

    }

    @GetMapping("/")
    public String home(Model model){

        model.addAttribute(
                "totalJogos",
                service.totalJogos()
        );

        model.addAttribute(
                "totalIdiomas",
                service.totalIdiomas()
        );

        model.addAttribute(
                "totalGeneros",
                service.totalGeneros()
        );

        model.addAttribute(
                "totalTags",
                service.totalTags()
        );

        return "index";

    }

    @GetMapping("/consultas")
    public String consultas() {
        return "consultas";
    }

    @GetMapping("/buscar")
    public String buscar(

            @RequestParam String nome,

            @RequestParam(defaultValue = "0") int pagina,

            @RequestParam(defaultValue = "12") int tamanho,

            Model model){

        model.addAttribute(
                "lista",
                service.buscarPorNome(nome, pagina, tamanho)
        );

        int totalResultados = service.contarJogos(nome);

        int totalPaginas =
                (int) Math.ceil((double) totalResultados / tamanho);

        List<Integer> paginas = new ArrayList<>();

        int inicio = Math.max(0, pagina - 2);
        int fim = Math.min(totalPaginas - 1, pagina + 2);

        for (int i = inicio; i <= fim; i++) {
            paginas.add(i);
        }

        model.addAttribute("paginas", paginas);

        model.addAttribute("nomePesquisado", nome);
        model.addAttribute("pagina", pagina);
        model.addAttribute("tamanho", tamanho);
        model.addAttribute("totalResultados", totalResultados);
        model.addAttribute("totalPaginas", totalPaginas);
        model.addAttribute("inicio", inicio);
        model.addAttribute("fim", fim);

        return "buscar";

    }

    private String mostrarConsulta(
            Model model,
            String titulo,
            String descricao,
            List<String> colunas,
            List<LinhaConsultaDTO> linhas){

        model.addAttribute("titulo", titulo);
        model.addAttribute("descricao", descricao);
        model.addAttribute("colunas", colunas);
        model.addAttribute("linhas", linhas);

        return "resultadoConsulta";
    }

    @GetMapping("/consulta1")
    public String consulta1(Model model){

        return mostrarConsulta(

                model,

                "Consulta 1",

                "Jogos cadastrados juntamente com seus respectivos gêneros.",

                List.of(

                        "Jogo",

                        "Gênero"

                ),

                TabelaUtil.montarLinhas(

                        service.consulta1(),

                        c -> List.of(

                                c.getJogo(),

                                c.getGenero()

                        )

                )

        );

    }

    @GetMapping("/consulta2")
    public String consulta2(Model model){

        return mostrarConsulta(

                model,

                "Consulta 2",

                "Jogos, categorias e idiomas suportados.",

                List.of(

                        "Jogo",

                        "Categoria",

                        "Idioma"

                ),

                TabelaUtil.montarLinhas(

                        service.consulta2(),

                        c -> List.of(

                                c.getJogo(),

                                c.getCategoria(),

                                c.getIdioma()

                        )

                )

        );

    }

    @GetMapping("/consulta3")
    public String consulta3(Model model){

        return mostrarConsulta(

                model,

                "Consulta 3",

                "Quantidade de jogos classificados em cada gênero.",

                List.of(

                        "Gênero",

                        "Quantidade"

                ),

                TabelaUtil.montarLinhas(

                        service.consulta3(),

                        c -> List.of(

                                c.getGenero(),

                                String.valueOf(c.getQuantidade())

                        )

                )

        );

    }

    @GetMapping("/consulta4")
    public String consulta4(Model model){

        return mostrarConsulta(

                model,

                "Consulta 4",

                "Jogos com preço acima da média do seu próprio gênero.",

                List.of(

                        "Jogo",

                        "Gênero",

                        "Preço",

                        "Preço Médio"

                ),

                TabelaUtil.montarLinhas(

                        service.consulta4(),

                        c -> List.of(

                                c.getNome(),

                                c.getGenero(),

                                String.format("R$ %.2f", c.getPreco()),

                                String.format("R$ %.2f", c.getPrecoMedio())

                        )

                )

        );

    }

    @GetMapping("/consulta5")
    public String consulta5(Model model){

        return mostrarConsulta(

                model,

                "Consulta 5",

                "Jogos com número de recomendações acima da média geral.",

                List.of(

                        "Jogo",

                        "Recomendações"

                ),

                TabelaUtil.montarLinhas(

                        service.consulta5(),

                        c -> List.of(

                                c.getNome(),

                                String.valueOf(c.getRecomendacoes())

                        )

                )

        );

    }

    @GetMapping("/consulta6")
    public String consulta6(Model model){

        return mostrarConsulta(

                model,

                "Consulta 6",

                "Todas as tags cadastradas, inclusive as que não possuem jogos associados.",

                List.of(

                        "Tag",

                        "Jogo"

                ),

                TabelaUtil.montarLinhas(

                        service.consulta6(),

                        c -> List.of(

                                c.getTag(),

                                c.getJogo() == null ? "-" : c.getJogo()

                        )

                )

        );

    }

    @GetMapping("/consulta7")
    public String consulta7(Model model){

        return mostrarConsulta(

                model,

                "Ranking de Engajamento",

                "Top 10 jogos pela soma dos votos das tags.",

                List.of(
                        "Jogo",
                        "Engajamento"
                ),

                TabelaUtil.montarLinhas(

                        service.rankingEngajamento(),

                        e -> List.of(

                                e.getNome(),

                                String.valueOf(e.getEngajamento())

                        )

                )

        );

    }

    @GetMapping("/consulta8")
    public String consulta8(Model model){

        return mostrarConsulta(

                model,

                "Consulta 8",

                "Jogos que possuem pelo menos uma tag exclusiva.",

                List.of(

                        "Jogo",

                        "Tag Exclusiva"

                ),

                TabelaUtil.montarLinhas(

                        service.consulta8(),

                        c -> List.of(

                                c.getNomeJogo(),

                                c.getTag()

                        )

                )

        );

    }

    @GetMapping("/consulta9")
    public String consulta9(Model model){

        return mostrarConsulta(

                model,

                "Consulta 9",

                "Jogo(s) com o maior preço registrado no banco de dados.",

                List.of(

                        "Jogo",

                        "Preço"

                ),

                TabelaUtil.montarLinhas(

                        service.consulta9(),

                        c -> List.of(

                                c.getNome(),

                                String.format("R$ %.2f", c.getPreco())

                        )

                )

        );

    }

    @GetMapping("/consulta10")
    public String consulta10(Model model){

        var lista = service.consulta10();

        model.addAttribute(
                "titulo",
                "Jogos mais e menos avaliados");

        model.addAttribute(
                "descricao",
                "Top 3 e Bottom 3 considerando o número total de reviews.");

        model.addAttribute(
                "colunas",
                List.of(
                        "Jogo",
                        "Total Reviews"
                ));

        model.addAttribute(
                "linhas",

                TabelaUtil.montarLinhas(

                        lista,

                        j -> List.of(

                                j.getNome(),

                                String.valueOf(j.getTotalReviews())

                        )

                )

        );

        return "resultadoConsulta";

    }

    @GetMapping("/estatisticas")
    public String estatisticas(Model model) {

        EstatisticasDTO estatisticas = service.buscarEstatisticas();

        model.addAttribute("generos", estatisticas.getGeneros());
        model.addAttribute("idiomas", estatisticas.getIdiomas());
        model.addAttribute("tags", estatisticas.getTags());
        model.addAttribute("plataformas", estatisticas.getPlataformas());

        model.addAttribute("totalJogos", service.totalJogos());
        model.addAttribute("totalIdiomas", service.totalIdiomas());
        model.addAttribute("totalGeneros", service.totalGeneros());
        model.addAttribute("totalTags", service.totalTags());

        return "estatisticas";
    }

}