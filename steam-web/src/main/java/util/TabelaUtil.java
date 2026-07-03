package steam_web.util;

import steam_web.dto.LinhaConsultaDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TabelaUtil {

    public static <T> List<LinhaConsultaDTO> montarLinhas(
            List<T> lista,
            Function<T, List<String>> conversor){

        List<LinhaConsultaDTO> linhas = new ArrayList<>();

        for(T objeto : lista){

            linhas.add(

                    new LinhaConsultaDTO(

                            conversor.apply(objeto)

                    )

            );

        }

        return linhas;

    }

}
