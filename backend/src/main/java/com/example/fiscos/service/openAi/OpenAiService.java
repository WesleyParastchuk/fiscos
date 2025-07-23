package com.example.fiscos.service.openAi;

import com.example.fiscos.dto.openAi.ProductClassifiedDTO;
import com.example.fiscos.model.RawProduct;
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

    private static final String EXEMPLOS = """
            [{"produto":"QJO RAL 50G","resumo":"queijo"},
             {"produto":"LEITE UHT TIROL INT 1L","resumo":"leite"},
             {"produto":"PAP TOALHA SOC 2X10M","resumo":"papel toalha"},
             {"produto":"COXAO MOLE TIRAS KG","resumo":"carne"},
             {"produto":"ALIM GATOS WHISKAS 900G","resumo":"ração gato"}]
            """;

    public OpenAiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public List<ProductClassifiedDTO> sendProductClassification(List<RawProduct> products) {
        try {
            String produtosJson = objectMapper.writeValueAsString(
                    products.stream()
                            .map(p -> Map.of("produto", p.getName()))
                            .toList());

            String prompt = "Categorize os produtos pelo tipo principal. Ignore marcas, pesos, medidas e variações.\n\n"
                    +
                    "Exemplo:\n" + EXEMPLOS + "\n\nAgora categorize:\n" + produtosJson;

            String response = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            System.out.println("OpenAI response:\n" + response);

            return objectMapper.readValue(response, new TypeReference<List<ProductClassifiedDTO>>() {
            });

        } catch (Exception e) {
            throw new RuntimeException("Erro ao classificar produtos com OpenAI", e);
        }
    }
}
