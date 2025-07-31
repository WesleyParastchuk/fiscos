package com.example.fiscos.service.openAi;

import com.example.fiscos.dto.external.openAi.ProcessedProductClassificationDTO;
import com.example.fiscos.dto.external.openAi.ProductClassifiedDTO;
import com.example.fiscos.model.ProcessedProduct;
import com.example.fiscos.model.RawProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

        private final ChatClient chatClient;
        private final ObjectMapper objectMapper = new ObjectMapper();

        private static final String EXAMPLEPRODUCTLIST = """
                        [{"produto":"QJO RAL 50G","resumo":"queijo"},
                         {"produto":"LEITE UHT TIROL INT 1L","resumo":"leite"},
                         {"produto":"PAP TOALHA SOC 2X10M","resumo":"papel toalha"},
                         {"produto":"COXAO MOLE TIRAS KG","resumo":"carne"},
                         {"produto":"ALIM GATOS WHISKAS 900G","resumo":"ração gato"}]
                        """;

        private static final String EXAMPLEPRODUCTLISTCLASSIFIED = """
                        [{"resumo":"queijo","classificacaoId": idRelacionadoALaticinio}, },
                         {"resumo":"leite","classificacaoId": idRelacionadoALaticinio},
                         {"resumo":"papel toalha","classificacaoId": idRelacionadoAProdutosDeLimpeza},
                         {"resumo":"carne","classificacaoId": idRelacionadoAPets},
                         {"resumo":"ração gato","classificacaoId": idRelacionadoAOutros}]
                        """;

        public OpenAiService(ChatClient.Builder builder) {
                this.chatClient = builder.build();
        }

        public List<ProductClassifiedDTO> sendProductClassification(List<RawProduct> products) {
                try {
                        String jsonProducts = objectMapper.writeValueAsString(
                                        products.stream()
                                                        .map(p -> Map.of("produto", p.getName()))
                                                        .toList());

                        String prompt = "Categorize os produtos pelo tipo principal. Ignore marcas, pesos, medidas e variações.\n\n"
                                        +
                                        "Exemplo:\n" + EXAMPLEPRODUCTLIST + "\n\nAgora categorize:\n" + jsonProducts;

                        String response = chatClient.prompt()
                                        .user(prompt)
                                        .call()
                                        .content();

                        return objectMapper.readValue(response, new TypeReference<List<ProductClassifiedDTO>>() {
                        });

                } catch (Exception e) {
                        throw new RuntimeException("Erro ao classificar produtos com OpenAI", e);
                }
        }

        public List<ProcessedProductClassificationDTO> classifyProducts(List<ProcessedProduct> list,
                        String classificationsTree) {
                try {
                        String jsonProducts = objectMapper.writeValueAsString(
                                        list.stream()
                                                        .map(p -> Map.of("resumo", p.getName()))
                                                        .distinct()
                                                        .toList());

                        String prompt = "Classifique os produtos a seguir usando APENAS os dados das classificações disponíveis. Caso nenhuma classificação se relacione, use os dados da classificação \"Outros\". NUNCA quebre o padrão de resposta dos exemplos.\n"
                                        + jsonProducts
                                        + "\n\nClassificações disponíveis:\n" + classificationsTree
                                        + "\n\nExemplo:\n" + EXAMPLEPRODUCTLISTCLASSIFIED;

                        String response = chatClient.prompt()
                                        .user(prompt)
                                        .call()
                                        .content();

                        System.out.println("Response from OpenAI: " + response);

                        return objectMapper.readValue(response,
                                        new TypeReference<List<ProcessedProductClassificationDTO>>() {
                                        });
                } catch (JsonProcessingException e) {
                        throw new RuntimeException("Erro ao classificar produtos com OpenAI", e);
                }
        }

}
