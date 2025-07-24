package com.example.fiscos.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.fiscos.model.Classification;
import com.example.fiscos.repository.ClassificationRepository;

@Configuration
public class ClassificationSeeder {

        @Bean
        CommandLineRunner initClassifications(ClassificationRepository repo) {
                return args -> this.seed(repo);
        }

        public void seed(ClassificationRepository repo) {
                if (repo.count() == 0) {
                        // ========================== Moradia ==========================
                        Classification moradia = repo
                                        .save(new Classification("Moradia", "Moradia",
                                                        "Despesas relacionadas à moradia", null, false));
                        repo.save(new Classification("Aluguel", "Aluguel", "Pagamento mensal por locação", moradia,
                                        false));
                        repo.save(new Classification("Financiamento", "Financiamento",
                                        "Parcelas de financiamento imobiliário",
                                        moradia, false));
                        repo.save(new Classification("Condomínio", "Condomínio", "Taxas condominiais", moradia, false));
                        Classification contas = repo
                                        .save(new Classification("Contas", "Contas", "Despesas com serviços públicos",
                                                        moradia, false));
                        repo.save(new Classification("Energia", "Energia", "Conta de energia elétrica", contas, false));
                        repo.save(new Classification("Água/Esgoto", "Água/Esgoto", "Conta de água e esgoto", contas,
                                        false));
                        repo.save(new Classification("Gás", "Gás", "Consumo de gás", contas, false));
                        repo.save(new Classification("Internet/TV", "Internet/TV", "Serviços de internet e TV",
                                        contas, false));
                        repo.save(new Classification("Telefone/Fixo", "Telefone/Fixo", "Serviço de telefonia fixa",
                                        contas, false));
                        repo.save(new Classification("Manutenção", "Manutenção", "Reparos e manutenção do imóvel",
                                        moradia, false));

                        // ========================== Alimentação ==========================
                        Classification alimentacao = repo
                                        .save(new Classification("Alimentação", "Alimentação",
                                                        "Gastos com alimentos e refeições"));

                        repo.save(new Classification("Carnes", "Carnes", "Carnes resfriadas ou congeladas",
                                        alimentacao, true));
                        repo.save(new Classification("Embutidos", "Embutidos", "Presunto, mortadela, salsicha etc.",
                                        alimentacao, true));

                        repo.save(new Classification("Laticínios", "Laticínios", "Leite, queijo, iogurte e derivados",
                                        alimentacao, true));

                        repo.save(new Classification("Pães", "Pães", "Pães diversos, incluindo industrializados",
                                        alimentacao, true));
                        repo.save(new Classification("Bolos e Tortas", "Bolos e Tortas",
                                        "Produtos de padaria ou embalados", alimentacao, true));

                        repo.save(new Classification("Salgadinhos", "Salgadinhos", "Snacks industrializados",
                                        alimentacao, true));
                        repo.save(new Classification("Bolachas", "Bolachas", "Biscoitos doces e salgados", alimentacao,
                                        true));
                        repo.save(new Classification("Doces", "Doces", "Balas, chocolates e sobremesas", alimentacao,
                                        true));

                        repo.save(new Classification("Grãos", "Grãos", "Arroz, feijão, lentilha, etc.", alimentacao,
                                        true));
                        repo.save(new Classification("Massas", "Massas", "Macarrão e similares", alimentacao, true));
                        repo.save(new Classification("Temperos", "Temperos", "Condimentos, especiarias, molhos",
                                        alimentacao, true));
                        repo.save(new Classification("Cereais", "Cereais", "Cereais matinais, aveia, granola",
                                        alimentacao, true));

                        Classification hortifruti = repo.save(new Classification("Hortifruti", "Hortifruti",
                                        "Frutas, legumes e verduras", alimentacao, true));
                        repo.save(new Classification("Frutas", "Frutas", "Frutas frescas", hortifruti, true));
                        repo.save(new Classification("Legumes e Verduras", "Legumes e Verduras", "Vegetais in natura",
                                        hortifruti, true));

                        repo.save(new Classification("Bebidas", "Bebidas",
                                        "Bebidas não alcoólicas", alimentacao));

                        repo.save(new Classification("Alimentos Congelados", "Alimentos Congelados",
                                        "Pratos prontos, lasanhas, vegetais congelados", alimentacao, true));

                        repo.save(new Classification("Conservas", "Conservas", "Enlatados, molhos prontos, etc.",
                                        alimentacao, true));

                        Classification restaurantes = repo.save(
                                        new Classification("Restaurantes", "Restaurantes", "Refeições fora de casa",
                                                        alimentacao));
                        repo.save(new Classification("Restaurante", "Restaurante", "Consumo em restaurante",
                                        restaurantes, true));
                        repo.save(new Classification("Deliver", "Deliver", "Entrega de comida via aplicativos",
                                        restaurantes, true));
                        repo.save(new Classification("Lanches", "Lanches", "Cafés e lanches rápidos", alimentacao,
                                        true));

                        // ========================== Transporte ==========================
                        Classification transporte = repo
                                        .save(new Classification("Transporte", "Transporte", "Despesas com locomoção"));
                        repo.save(new Classification("Combustível", "Combustível", "Gasolina, etanol, diesel",
                                        transporte, false));
                        repo.save(new Classification("Público", "Público", "Transporte coletivo", transporte, false));
                        repo.save(new Classification("Aplicativos", "Aplicativos", "Uber, 99 e similares", transporte,
                                        false));
                        repo.save(new Classification("Manutenção", "Manutenção", "Revisões e consertos", transporte,
                                        false));
                        repo.save(new Classification("Estacionamento/Pedágio", "Estacionamento/Pedágio",
                                        "Vagas e pedágios",
                                        transporte, false));
                        repo.save(new Classification("Seguro", "Seguro", "Seguro do veículo", transporte, false));
                        repo.save(new Classification("Financiamento", "Financiamento", "Parcelas de veículo",
                                        transporte, false));

                        // ========================== Saúde ==========================
                        Classification saude = repo
                                        .save(new Classification("Saúde", "Saúde", "Cuidados e tratamentos", null,
                                                        false));
                        repo.save(new Classification("Plano", "Plano", "Mensalidade de plano de saúde", saude, false));
                        repo.save(new Classification("Consultas", "Consultas", "Atendimentos médicos", saude, false));
                        repo.save(new Classification("Exames", "Exames", "Análises laboratoriais e de imagem", saude,
                                        false));
                        repo.save(new Classification("Medicamentos", "Medicamentos", "Remédios e insumos", saude,
                                        false));
                        repo.save(new Classification("Terapias", "Terapias", "Sessões terapêuticas e psicológicas",
                                        saude, false));
                        repo.save(new Classification("Bucal", "Bucal", "Tratamentos odontológicos", saude, false));

                        // ========================== Educação ==========================
                        Classification educacao = repo
                                        .save(new Classification("Educação", "Educação",
                                                        "Investimento em aprendizado", null, false));
                        repo.save(new Classification("Mensalidade", "Mensalidade",
                                        "Pagamentos escolares e universitários",
                                        educacao, false));
                        repo.save(new Classification("Cursos", "Cursos", "Cursos livres e profissionalizantes",
                                        educacao, false));
                        repo.save(new Classification("Materiais", "Materiais", "Livros e material didático", educacao,
                                        false));
                        repo.save(new Classification("Plataformas", "Plataformas", "Plataformas de ensino online",
                                        educacao, false));

                        // ========================== Lazer ==========================
                        Classification lazer = repo
                                        .save(new Classification("Lazer", "Lazer",
                                                        "Atividades recreativas e culturais"));
                        Classification assinaturas = repo.save(
                                        new Classification("Assinaturas", "Assinaturas",
                                                        "Serviços digitais por assinatura", lazer, false));
                        repo.save(new Classification("Streaming", "Streaming", "Netflix, Spotify, etc.", assinaturas,
                                        false));
                        repo.save(new Classification("Games", "Games", "Jogos digitais", assinaturas));
                        repo.save(new Classification("Cinema/Teatro/Shows", "Cinema/Teatro/Shows", "Eventos culturais",
                                        lazer));
                        Classification viagens = repo
                                        .save(new Classification("Viagens", "Viagens", "Despesas de turismo", lazer,
                                                        false));
                        repo.save(new Classification("Passagens", "Passagens", "Bilhetes de viagem", viagens, false));
                        repo.save(new Classification("Hospedagem", "Hospedagem", "Hotéis e pousadas", viagens, false));
                        repo.save(new Classification("Passeios", "Passeios", "Atividades turísticas", viagens, false));
                        repo.save(new Classification("Atividades", "Atividades", "Experiências e tours", viagens,
                                        false));
                        repo.save(new Classification("Bares/Festas", "Bares/Festas", "Eventos sociais", lazer));
                        repo.save(new Classification("Hobbies", "Hobbies", "Atividades pessoais", lazer));

                        // ========================== Compras Pessoais ==========================
                        Classification comprasPessoais = repo
                                        .save(new Classification("Compras Pessoais", "Compras Pessoais",
                                                        "Aquisição de bens pessoais"));
                        repo.save(new Classification("Vestuário", "Vestuário", "Roupas e acessórios", comprasPessoais));
                        repo.save(new Classification("Calçados", "Calçados", "Sapatos e tênis", comprasPessoais));
                        repo.save(new Classification("Acessórios", "Acessórios", "Bijuterias, relógios etc.",
                                        comprasPessoais));
                        repo.save(new Classification("Cosméticos/Beleza", "Cosméticos/Beleza", "Produtos de beleza",
                                        comprasPessoais));
                        repo.save(
                                        new Classification("Eletrônicos", "Eletrônicos", "Dispositivos eletrônicos",
                                                        comprasPessoais));
                        repo.save(
                                        new Classification("Móveis/Decoração", "Móveis/Decoração", "Itens para casa",
                                                        comprasPessoais));

                        // ========================== Assinaturas/Serviços ==========================
                        Classification assinaturasServicos = repo.save(new Classification("Assinaturas/Serviços",
                                        "Assinaturas/Serviços", "Contratos contínuos e digitais", null, false));
                        Classification digitais = repo
                                        .save(new Classification("Digitais", "Digitais", "Serviços digitais",
                                                        assinaturasServicos, false));
                        repo.save(new Classification("Armazenamento em Nuvem", "Armazenamento em Nuvem",
                                        "Google Drive, iCloud, etc.", digitais, false));
                        repo.save(new Classification("Aplicativos/Softwares", "Aplicativos/Softwares",
                                        "Apps e programas pagos",
                                        digitais, false));
                        repo.save(new Classification("Assinaturas de Produtos", "Assinaturas de Produtos",
                                        "Clubes de assinatura", assinaturasServicos, false));
                        repo.save(new Classification("Serviços Profissionais", "Serviços Profissionais",
                                        "Consultoria e freelancing", assinaturasServicos, false));
                        repo.save(new Classification("Assinaturas de Conteúdo", "Assinaturas de Conteúdo",
                                        "Revistas, cursos e similares", assinaturasServicos, false));

                        // ========================== Família/Relacionamentos ==========================
                        Classification familiaRelacionamentos = repo.save(new Classification("Família/Relacionamentos",
                                        "Família/Relacionamentos", "Despesas familiares e sociais", null, false));
                        Classification gastosFilhos = repo
                                        .save(new Classification("Gastos com Filhos", "Gastos com Filhos",
                                                        "Despesas infantis", familiaRelacionamentos, false));
                        repo.save(new Classification("Educação", "Educação", "Mensalidades e material escolar",
                                        gastosFilhos, false));
                        repo.save(new Classification("Roupas/Brinquedos", "Roupas/Brinquedos", "Vestuário e brinquedos",
                                        gastosFilhos, false));
                        repo.save(new Classification("Saúde", "Saúde", "Cuidados médicos infantis", gastosFilhos,
                                        false));
                        repo.save(new Classification("Presentes/Datas Comemorativas", "Presentes/Datas Comemorativas",
                                        "Aniversários, Natal e eventos", familiaRelacionamentos, false));
                        repo.save(new Classification("Pensão/Acordos Judiciais", "Pensão/Acordos Judiciais",
                                        "Pagamentos legais", familiaRelacionamentos, false));
                        repo.save(new Classification("Doações/Caridade", "Doações/Caridade",
                                        "Contribuições voluntárias",
                                        familiaRelacionamentos, false));

                        // ========================== Investimentos ==========================
                        Classification investimentos = repo
                                        .save(new Classification("Investimentos", "Investimentos",
                                                        "Aplicações financeiras", null, false));
                        repo.save(new Classification("Renda Fixa", "Renda Fixa", "CDB, Tesouro Direto, etc.",
                                        investimentos, false));
                        repo.save(new Classification("Renda Variável", "Renda Variável", "Ações, FIIs", investimentos,
                                        false));
                        repo.save(new Classification("Criptoativos", "Criptoativos", "Bitcoin, Ethereum, etc.",
                                        investimentos, false));
                        repo.save(new Classification("Previdência Privada", "Previdência Privada",
                                        "Planos de aposentadoria",
                                        investimentos, false));
                        repo.save(new Classification("Aplicações em Fundos", "Aplicações em Fundos",
                                        "Fundos de investimento",
                                        investimentos, false));

                        // ========================== Impostos e Obrigações ==========================
                        Classification impostos = repo.save(
                                        new Classification("Impostos e Obrigações", "Impostos e Obrigações",
                                                        "Tributos e encargos", null, false));
                        repo.save(new Classification("Imposto de Renda", "Imposto de Renda", "IRPF e IRPJ", impostos,
                                        false));
                        repo.save(new Classification("IPVA", "IPVA", "Imposto sobre veículos", impostos, false));
                        repo.save(new Classification("IPTU", "IPTU", "Imposto sobre imóvel", impostos, false));
                        repo.save(new Classification("Taxas e Multas", "Taxas e Multas", "Encargos diversos",
                                        impostos, false));
                        repo.save(new Classification("Cartório e Documentações", "Cartório e Documentações",
                                        "Autenticações e registros", impostos, false));

                        // ========================== Pets ==========================
                        Classification pets = repo
                                        .save(new Classification("Pets", "Pets", "Gastos com animais de estimação",
                                                        null));
                        repo.save(new Classification("Ração e Petiscos", "Ração e Petiscos", "Alimentação para pets",
                                        pets, false));
                        repo.save(new Classification("Veterinário", "Veterinário", "Consultas e vacinas", pets, false));
                        repo.save(new Classification("Acessórios e Brinquedos", "Acessórios e Brinquedos",
                                        "Itens para entretenimento", pets, false));
                        repo.save(new Classification("Banho e Tosa", "Banho e Tosa", "Higiene e cuidados", pets,
                                        false));

                        // ========================== Finanças Pessoais ==========================
                        Classification financasPessoais = repo.save(
                                        new Classification("Finanças Pessoais", "Finanças Pessoais",
                                                        "Gestão financeira pessoal", null, false));
                        repo.save(new Classification("Pagamento de Cartão de Crédito", "Pagamento de Cartão de Crédito",
                                        "Fatura mensal", financasPessoais, false));
                        repo.save(new Classification("Parcelamentos", "Parcelamentos", "Pagamentos em parcelas",
                                        financasPessoais, false));
                        repo.save(new Classification("Empréstimos e Financiamentos", "Empréstimos e Financiamentos",
                                        "Crédito pessoal", financasPessoais, false));
                        repo.save(new Classification("Taxas Bancárias", "Taxas Bancárias", "Encargos bancários",
                                        financasPessoais, false));
                        repo.save(new Classification("Transferências e TEDs", "Transferências e TEDs",
                                        "Envio de valores",
                                        financasPessoais, false));

                        // ========================== Despesas Profissionais/Negócio
                        // ==========================
                        Classification despesasProfissionais = repo.save(new Classification(
                                        "Despesas Profissionais/Negócio",
                                        "Despesas Profissionais/Negócio", "Gastos ligados à atividade profissional",
                                        null, false));
                        repo.save(new Classification("Materiais", "Materiais", "Suprimentos e equipamentos",
                                        despesasProfissionais, false));
                        repo.save(new Classification("Marketing/Publicidade", "Marketing/Publicidade",
                                        "Promoção comercial",
                                        despesasProfissionais, false));
                        repo.save(new Classification("Serviços de Terceiros", "Serviços de Terceiros",
                                        "Contratação de prestadores", despesasProfissionais, false));
                        repo.save(new Classification("Softwares", "Softwares", "Ferramentas digitais",
                                        despesasProfissionais, false));
                        repo.save(new Classification("Viagens", "Viagens", "Deslocamentos corporativos",
                                        despesasProfissionais, false));

                        // ========================== Outros ==========================
                        Classification outros = repo
                                        .save(new Classification("Outros", "Outros", "Despesas não classificadas"));
                        repo.save(new Classification("Não Classificado", "Não Classificado",
                                        "Gastos sem categoria definida",
                                        outros));
                        repo.save(new Classification("Gasto Emergencial", "Gasto Emergencial", "Despesas imprevistas",
                                        outros, false));
                        repo.save(new Classification("Itens Perdidos ou Descartados", "Itens Perdidos ou Descartados",
                                        "Produtos extraviados ou descartados", outros, false));
                        repo.save(new Classification("Correções de Lançamento", "Correções de Lançamento",
                                        "Ajustes contábeis e registros", outros, false));
                        repo.save(new Classification("Produtos de Limpeza", "Produtos de Limpeza",
                                        "Sabão, desinfetante, etc.", outros, true));
                        repo.save(new Classification("Higiene Pessoal", "Higiene Pessoal",
                                        "Sabonetes, papel higiênico, etc.", outros, true));
                        repo.save(new Classification("Utensílios Domésticos", "Utensílios Domésticos",
                                        "Esponjas, vasilhas, etc.", outros, true));
                        repo.save(new Classification("Descartáveis", "Descartáveis", "Copos, pratos, sacos de lixo",
                                        outros, true));

                        System.out.println(">>> Classificações criadas com sucesso!");
                }
        };
}
