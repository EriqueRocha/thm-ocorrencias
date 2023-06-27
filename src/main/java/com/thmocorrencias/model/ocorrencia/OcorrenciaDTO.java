package com.thmocorrencias.model.ocorrencia;

import com.thmocorrencias.enums.Classificacao;
import com.thmocorrencias.enums.Intensidade;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OcorrenciaDTO {
    private Integer id;

    private LocalDateTime data;

    private String endereco;

    private Intensidade intensidade;

    private Classificacao classificacao;

    private List<MultipartFile> arquivos;


}
