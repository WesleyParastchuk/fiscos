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
                            "Despesas relacionadas à moradia"));
            repo.save(new Classification("Aluguel", "Aluguel", "Pagamento mensal por locação", moradia));
            repo.save(new Classification("Financiamento", "Financiamento",
                    "Parcelas de financiamento imobiliário",
                    moradia));
            repo.save(new Classification("Condomínio", "Condomínio", "Taxas condominiais", moradia));
            Classification contas = repo
                    .save(new Classification("Contas", "Contas", "Despesas com serviços públicos",
                            moradia));
            repo.save(new Classification("Energia", "Energia", "Conta de energia elétrica", contas));
            repo.save(new Classification("Água/Esgoto", "Água/Esgoto", "Conta de água e esgoto", contas));
            repo.save(new Classification("Gás", "Gás", "Consumo de gás", contas));
            repo.save(new Classification("Internet/TV", "Internet/TV", "Serviços de internet e TV",
                    contas));
            repo.save(new Classification("Telefone/Fixo", "Telefone/Fixo", "Serviço de telefonia fixa",
                    contas));
            repo.save(new Classification("Manutenção", "Manutenção", "Reparos e manutenção do imóvel",
                    moradia));

            // ========================== Alimentação ==========================
            Classification alimentacao = repo
                    .save(new Classification("Alimentação", "Alimentação",
                            "Gastos com alimentos e refeições"));
            repo.save(
                    new Classification("Supermercado", "Supermercado",
                            "Compras gerais de alimentos", alimentacao));
            repo.save(new Classification("Hortifruti", "Hortifruti", "Frutas, verduras e legumes",
                    alimentacao));
            repo.save(new Classification("Açougue", "Açougue", "Carnes", alimentacao));
            repo.save(new Classification("Padaria", "Padaria", "Pães e produtos de padaria", alimentacao));
            Classification restaurantes = repo.save(
                    new Classification("Restaurantes", "Restaurantes", "Refeições fora de casa",
                            alimentacao));
            repo.save(new Classification("Restaurante", "Restaurante", "Consumo em restaurante",
                    restaurantes));
            repo.save(new Classification("Deliver", "Deliver", "Entrega de comida via aplicativos",
                    restaurantes));
            repo.save(new Classification("Lanches", "Lanches", "Cafés e lanches rápidos", alimentacao));

            // ========================== Transporte ==========================
            Classification transporte = repo
                    .save(new Classification("Transporte", "Transporte", "Despesas com locomoção"));
            repo.save(new Classification("Combustível", "Combustível", "Gasolina, etanol, diesel",
                    transporte));
            repo.save(new Classification("Público", "Público", "Transporte coletivo", transporte));
            repo.save(new Classification("Aplicativos", "Aplicativos", "Uber, 99 e similares", transporte));
            repo.save(new Classification("Manutenção", "Manutenção", "Revisões e consertos", transporte));
            repo.save(new Classification("Estacionamento/Pedágio", "Estacionamento/Pedágio",
                    "Vagas e pedágios",
                    transporte));
            repo.save(new Classification("Seguro", "Seguro", "Seguro do veículo", transporte));
            repo.save(new Classification("Financiamento", "Financiamento", "Parcelas de veículo",
                    transporte));

            // ========================== Saúde ==========================
            Classification saude = repo
                    .save(new Classification("Saúde", "Saúde", "Cuidados e tratamentos"));
            repo.save(new Classification("Plano", "Plano", "Mensalidade de plano de saúde", saude));
            repo.save(new Classification("Consultas", "Consultas", "Atendimentos médicos", saude));
            repo.save(new Classification("Exames", "Exames", "Análises laboratoriais e de imagem", saude));
            repo.save(new Classification("Medicamentos", "Medicamentos", "Remédios e insumos", saude));
            repo.save(new Classification("Terapias", "Terapias", "Sessões terapêuticas e psicológicas",
                    saude));
            repo.save(new Classification("Bucal", "Bucal", "Tratamentos odontológicos", saude));

            // ========================== Educação ==========================
            Classification educacao = repo
                    .save(new Classification("Educação", "Educação",
                            "Investimento em aprendizado"));
            repo.save(new Classification("Mensalidade", "Mensalidade",
                    "Pagamentos escolares e universitários",
                    educacao));
            repo.save(new Classification("Cursos", "Cursos", "Cursos livres e profissionalizantes",
                    educacao));
            repo.save(new Classification("Materiais", "Materiais", "Livros e material didático", educacao));
            repo.save(new Classification("Plataformas", "Plataformas", "Plataformas de ensino online",
                    educacao));

            // ========================== Lazer ==========================
            Classification lazer = repo
                    .save(new Classification("Lazer", "Lazer",
                            "Atividades recreativas e culturais"));
            Classification assinaturas = repo.save(
                    new Classification("Assinaturas", "Assinaturas",
                            "Serviços digitais por assinatura", lazer));
            repo.save(new Classification("Streaming", "Streaming", "Netflix, Spotify, etc.", assinaturas));
            repo.save(new Classification("Games", "Games", "Jogos digitais", assinaturas));
            repo.save(new Classification("Cinema/Teatro/Shows", "Cinema/Teatro/Shows", "Eventos culturais",
                    lazer));
            Classification viagens = repo
                    .save(new Classification("Viagens", "Viagens", "Despesas de turismo", lazer));
            repo.save(new Classification("Passagens", "Passagens", "Bilhetes de viagem", viagens));
            repo.save(new Classification("Hospedagem", "Hospedagem", "Hotéis e pousadas", viagens));
            repo.save(new Classification("Passeios", "Passeios", "Atividades turísticas", viagens));
            repo.save(new Classification("Atividades", "Atividades", "Experiências e tours", viagens));
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
                    "Assinaturas/Serviços", "Contratos contínuos e digitais"));
            Classification digitais = repo
                    .save(new Classification("Digitais", "Digitais", "Serviços digitais",
                            assinaturasServicos));
            repo.save(new Classification("Armazenamento em Nuvem", "Armazenamento em Nuvem",
                    "Google Drive, iCloud, etc.", digitais));
            repo.save(new Classification("Aplicativos/Softwares", "Aplicativos/Softwares",
                    "Apps e programas pagos",
                    digitais));
            repo.save(new Classification("Assinaturas de Produtos", "Assinaturas de Produtos",
                    "Clubes de assinatura", assinaturasServicos));
            repo.save(new Classification("Serviços Profissionais", "Serviços Profissionais",
                    "Consultoria e freelancing", assinaturasServicos));
            repo.save(new Classification("Assinaturas de Conteúdo", "Assinaturas de Conteúdo",
                    "Revistas, cursos e similares", assinaturasServicos));

            // ========================== Família/Relacionamentos ==========================
            Classification familiaRelacionamentos = repo.save(new Classification("Família/Relacionamentos",
                    "Família/Relacionamentos", "Despesas familiares e sociais"));
            Classification gastosFilhos = repo
                    .save(new Classification("Gastos com Filhos", "Gastos com Filhos",
                            "Despesas infantis", familiaRelacionamentos));
            repo.save(new Classification("Educação", "Educação", "Mensalidades e material escolar",
                    gastosFilhos));
            repo.save(new Classification("Roupas/Brinquedos", "Roupas/Brinquedos", "Vestuário e brinquedos",
                    gastosFilhos));
            repo.save(new Classification("Saúde", "Saúde", "Cuidados médicos infantis", gastosFilhos));
            repo.save(new Classification("Presentes/Datas Comemorativas", "Presentes/Datas Comemorativas",
                    "Aniversários, Natal e eventos", familiaRelacionamentos));
            repo.save(new Classification("Pensão/Acordos Judiciais", "Pensão/Acordos Judiciais",
                    "Pagamentos legais", familiaRelacionamentos));
            repo.save(new Classification("Doações/Caridade", "Doações/Caridade",
                    "Contribuições voluntárias",
                    familiaRelacionamentos));

            // ========================== Investimentos ==========================
            Classification investimentos = repo
                    .save(new Classification("Investimentos", "Investimentos",
                            "Aplicações financeiras"));
            repo.save(new Classification("Renda Fixa", "Renda Fixa", "CDB, Tesouro Direto, etc.",
                    investimentos));
            repo.save(new Classification("Renda Variável", "Renda Variável", "Ações, FIIs", investimentos));
            repo.save(new Classification("Criptoativos", "Criptoativos", "Bitcoin, Ethereum, etc.",
                    investimentos));
            repo.save(new Classification("Previdência Privada", "Previdência Privada",
                    "Planos de aposentadoria",
                    investimentos));
            repo.save(new Classification("Aplicações em Fundos", "Aplicações em Fundos",
                    "Fundos de investimento",
                    investimentos));

            // ========================== Impostos e Obrigações ==========================
            Classification impostos = repo.save(
                    new Classification("Impostos e Obrigações", "Impostos e Obrigações",
                            "Tributos e encargos"));
            repo.save(new Classification("Imposto de Renda", "Imposto de Renda", "IRPF e IRPJ", impostos));
            repo.save(new Classification("IPVA", "IPVA", "Imposto sobre veículos", impostos));
            repo.save(new Classification("IPTU", "IPTU", "Imposto sobre imóvel", impostos));
            repo.save(new Classification("Taxas e Multas", "Taxas e Multas", "Encargos diversos",
                    impostos));
            repo.save(new Classification("Cartório e Documentações", "Cartório e Documentações",
                    "Autenticações e registros", impostos));

            // ========================== Pets ==========================
            Classification pets = repo
                    .save(new Classification("Pets", "Pets", "Gastos com animais de estimação"));
            repo.save(new Classification("Ração e Petiscos", "Ração e Petiscos", "Alimentação para pets",
                    pets));
            repo.save(new Classification("Veterinário", "Veterinário", "Consultas e vacinas", pets));
            repo.save(new Classification("Acessórios e Brinquedos", "Acessórios e Brinquedos",
                    "Itens para entretenimento", pets));
            repo.save(new Classification("Banho e Tosa", "Banho e Tosa", "Higiene e cuidados", pets));

            // ========================== Finanças Pessoais ==========================
            Classification financasPessoais = repo.save(
                    new Classification("Finanças Pessoais", "Finanças Pessoais",
                            "Gestão financeira pessoal"));
            repo.save(new Classification("Pagamento de Cartão de Crédito", "Pagamento de Cartão de Crédito",
                    "Fatura mensal", financasPessoais));
            repo.save(new Classification("Parcelamentos", "Parcelamentos", "Pagamentos em parcelas",
                    financasPessoais));
            repo.save(new Classification("Empréstimos e Financiamentos", "Empréstimos e Financiamentos",
                    "Crédito pessoal", financasPessoais));
            repo.save(new Classification("Taxas Bancárias", "Taxas Bancárias", "Encargos bancários",
                    financasPessoais));
            repo.save(new Classification("Transferências e TEDs", "Transferências e TEDs",
                    "Envio de valores",
                    financasPessoais));

            // ========================== Despesas Profissionais/Negócio
            // ==========================
            Classification despesasProfissionais = repo.save(new Classification(
                    "Despesas Profissionais/Negócio",
                    "Despesas Profissionais/Negócio", "Gastos ligados à atividade profissional"));
            repo.save(new Classification("Materiais", "Materiais", "Suprimentos e equipamentos",
                    despesasProfissionais));
            repo.save(new Classification("Marketing/Publicidade", "Marketing/Publicidade",
                    "Promoção comercial",
                    despesasProfissionais));
            repo.save(new Classification("Serviços de Terceiros", "Serviços de Terceiros",
                    "Contratação de prestadores", despesasProfissionais));
            repo.save(new Classification("Softwares", "Softwares", "Ferramentas digitais",
                    despesasProfissionais));
            repo.save(
                    new Classification("Viagens", "Viagens", "Deslocamentos corporativos",
                            despesasProfissionais));

            // ========================== Outros ==========================
            Classification outros = repo
                    .save(new Classification("Outros", "Outros", "Despesas não classificadas"));
            repo.save(new Classification("Não Classificado", "Não Classificado",
                    "Gastos sem categoria definida",
                    outros));
            repo.save(new Classification("Gasto Emergencial", "Gasto Emergencial", "Despesas imprevistas",
                    outros));
            repo.save(new Classification("Itens Perdidos ou Descartados", "Itens Perdidos ou Descartados",
                    "Produtos extraviados ou descartados", outros));
            repo.save(new Classification("Correções de Lançamento", "Correções de Lançamento",
                    "Ajustes contábeis e registros", outros));

            System.out.println(">>> Classificações criadas com sucesso!");
        }
    };
}
