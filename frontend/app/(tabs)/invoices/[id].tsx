import React, { useEffect, useState } from "react";
import {
  View,
  Text,
  StyleSheet,
  ActivityIndicator,
  ScrollView,
  Linking,
  TouchableOpacity,
} from "react-native";
import { useLocalSearchParams } from "expo-router";
import { getInvoiceById } from "@/services/invoiceService";
import { CompleteInvoiceDTO } from "@/dto/CompleteInvoiceDTO";
import { MaterialIcons } from "@expo/vector-icons";

export default function InvoiceDetail() {
  const { id } = useLocalSearchParams<{ id: string }>();
  const [invoice, setInvoice] = useState<CompleteInvoiceDTO | null>(null);
  const [loading, setLoading] = useState(true);

  const [expanded, setExpanded] = useState<Record<string, boolean>>({
    produtos: false,
    tributos: false,
    fornecedor: false,
    nota: false,
    usuario: false,
    qrcode: false,
  });

  const toggleSection = (section: string) => {
    setExpanded((prev) => ({ ...prev, [section]: !prev[section] }));
  };

  useEffect(() => {
    if (id) {
      getInvoiceById(parseInt(id))
        .then(setInvoice)
        .finally(() => setLoading(false));
    }
  }, [id]);

  if (loading) {
    return (
      <View style={styles.centered}>
        <ActivityIndicator size="large" color="#007AFF" />
      </View>
    );
  }

  if (!invoice) {
    return (
      <View style={styles.centered}>
        <Text>Nota fiscal não encontrada.</Text>
      </View>
    );
  }

  const { supplier, user, taxes, products, qrcode } = invoice;
  const address = supplier.address;

  return (
    <ScrollView style={styles.container} contentContainerStyle={{ paddingBottom: 32 }}>
      <CollapsibleSection
        title="Produtos"
        icon="inventory"
        expanded={expanded.produtos}
        onToggle={() => toggleSection("produtos")}
      >
        {products.map((product) => (
          <View key={product.id} style={styles.productCard}>
            <View style={styles.productHeader}>
              <Text style={styles.productName}>{product.rawProduct.name}</Text>
              <Text style={styles.productCode}>Cód. {product.rawProduct.code}</Text>
            </View>
            <View style={styles.productRow}>
              <Text style={styles.productLabel}>Classificação:</Text>
              <Text style={styles.productValue}>{product.classifiedProduct.name}</Text>
            </View>
            <View style={styles.productRow}>
              <Text style={styles.productLabel}>Quantidade:</Text>
              <Text style={styles.productValue}>
                {product.quantity} {product.unitOfMeasure}
              </Text>
            </View>
            <View style={styles.productRow}>
              <Text style={styles.productLabel}>Preço Unitário:</Text>
              <Text style={styles.productValue}>R$ {product.unitPrice.toFixed(2)}</Text>
            </View>
            <View style={styles.productRow}>
              <Text style={styles.productLabel}>Preço Total:</Text>
              <Text style={styles.productValueTotal}>R$ {product.totalPrice.toFixed(2)}</Text>
            </View>
          </View>
        ))}
      </CollapsibleSection>

      <CollapsibleSection
        title="Tributos"
        icon="payments"
        expanded={expanded.tributos}
        onToggle={() => toggleSection("tributos")}
        noBorder
      >
        <View style={styles.infoCardNoBorder}>
          <View style={styles.infoRow}>
            <Text style={styles.infoLabel}>Federal:</Text>
            <Text style={styles.infoValue}>R$ {taxes.federal.value.toFixed(2)}</Text>
          </View>
          <View style={styles.infoRow}>
            <Text style={styles.infoLabel}>Estadual:</Text>
            <Text style={styles.infoValue}>R$ {taxes.state.value.toFixed(2)}</Text>
          </View>
        </View>
      </CollapsibleSection>

      <CollapsibleSection
        title="Fornecedor"
        icon="store"
        expanded={expanded.fornecedor}
        onToggle={() => toggleSection("fornecedor")}
        noBorder
      >
        <View style={styles.infoCardNoBorder}>
          <Text style={styles.infoLabel}>Nome:</Text>
          <Text style={styles.infoValue}>{supplier.name}</Text>

          <Text style={styles.infoLabel}>CNPJ:</Text>
          <Text style={styles.infoValue}>{supplier.cnpj}</Text>

          <Text style={styles.infoLabel}>Endereço:</Text>
          <Text style={styles.infoValue}>
            {address.street}, {address.number}
            {address.complement && ` - ${address.complement}`}
          </Text>

          <Text style={styles.infoLabel}>Bairro:</Text>
          <Text style={styles.infoValue}>
            {address.district}, {address.city} - {address.uf}
          </Text>
        </View>
      </CollapsibleSection>

      <CollapsibleSection
        title="Dados da Nota"
        icon="description"
        expanded={expanded.nota}
        onToggle={() => toggleSection("nota")}
        noBorder
      >
        <View style={styles.infoCardNoBorder}>
          <View style={styles.infoRow}>
            <Text style={styles.infoLabel}>Data de emissão:</Text>
            <Text style={styles.infoValue}>{new Date(invoice.issueDate).toLocaleDateString()}</Text>
          </View>
          <View style={styles.infoRow}>
            <Text style={styles.infoLabel}>Valor total:</Text>
            <Text style={styles.infoValueTotal}>R$ {invoice.totalValue.toFixed(2)}</Text>
          </View>
        </View>
      </CollapsibleSection>

      <CollapsibleSection
        title="Emitido por"
        icon="person"
        expanded={expanded.usuario}
        onToggle={() => toggleSection("usuario")}
        noBorder
      >
        <View style={styles.infoCardNoBorder}>
          <Text style={[styles.infoLabel, styles.singleLineLabel]}>Usuário:</Text>
          <Text style={[styles.infoValue, styles.singleLineValue]}>{user.name}</Text>
        </View>
      </CollapsibleSection>

      <CollapsibleSection
        title="QR Code"
        icon="qr-code"
        expanded={expanded.qrcode}
        onToggle={() => toggleSection("qrcode")}
        noBorder
      >
        <TouchableOpacity onPress={() => Linking.openURL(qrcode.link)} style={styles.qrCard}>
          <MaterialIcons name="qr-code" size={24} color="#007AFF" style={{ marginRight: 8 }} />
          <Text style={styles.link}>{qrcode.link}</Text>
        </TouchableOpacity>
      </CollapsibleSection>
    </ScrollView>
  );
}

function CollapsibleSection({
  title,
  icon,
  expanded,
  onToggle,
  children,
  noBorder = false,
}: {
  title: string;
  icon: keyof typeof MaterialIcons.glyphMap;
  expanded: boolean;
  onToggle: () => void;
  children: React.ReactNode;
  noBorder?: boolean;
}) {
  return (
    <View style={styles.sectionWrapper}>
      <TouchableOpacity onPress={onToggle} style={styles.sectionHeader}>
        <View style={styles.headerLeft}>
          <MaterialIcons name={icon} size={22} color="#007AFF" style={{ marginRight: 8 }} />
          <Text style={styles.title}>{title}</Text>
        </View>
        <MaterialIcons
          name={expanded ? "expand-less" : "expand-more"}
          size={24}
          color="#007AFF"
        />
      </TouchableOpacity>
      {expanded && <View style={[styles.sectionBody, noBorder && { paddingHorizontal: 0 }]}>{children}</View>}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#f9f9f9",
    paddingHorizontal: 16,
  },
  centered: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  sectionWrapper: {
    marginBottom: 16,
    backgroundColor: "#fff",
    borderRadius: 10,
    padding: 12,
    shadowColor: "#000",
    shadowOpacity: 0.05,
    shadowOffset: { width: 0, height: 1 },
    shadowRadius: 2,
    elevation: 2,
  },
  sectionHeader: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  headerLeft: {
    flexDirection: "row",
    alignItems: "center",
  },
  title: {
    fontSize: 16,
    fontWeight: "600",
    color: "#1c1c1e",
  },
  sectionBody: {
    marginTop: 12,
    paddingHorizontal: 2,
  },
  productCard: {
    backgroundColor: "#FAFAFA",
    padding: 14,
    borderRadius: 10,
    marginBottom: 14,
    borderWidth: 1,
    borderColor: "#E1E1E1",
  },
  productHeader: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: 8,
  },
  productName: {
    fontSize: 16,
    fontWeight: "600",
    color: "#1A1A1A",
    flexShrink: 1,
    flexWrap: "wrap",
  },
  productCode: {
    fontSize: 12,
    color: "#555",
    marginLeft: 12,
  },
  productRow: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginBottom: 4,
  },
  productLabel: {
    fontSize: 14,
    color: "#666",
    fontWeight: "500",
  },
  productValue: {
    fontSize: 14,
    color: "#1C1C1C",
    fontWeight: "400",
  },
  productValueTotal: {
    fontSize: 14,
    color: "#007AFF",
    fontWeight: "600",
  },
  infoCardNoBorder: {
    backgroundColor: "#fff",
    paddingVertical: 8,
  },
  infoRow: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginBottom: 8,
    paddingHorizontal: 0,
  },
  infoLabel: {
    fontSize: 14,
    fontWeight: "600",
    color: "#555",
    flexShrink: 1,
    flexWrap: "wrap",
  },
  infoValue: {
    fontSize: 14,
    fontWeight: "400",
    color: "#222",
    flexShrink: 1,
    flexWrap: "wrap",
    textAlign: "right",
  },
  infoValueTotal: {
    fontSize: 14,
    fontWeight: "700",
    color: "#007AFF",
    flexShrink: 1,
    flexWrap: "wrap",
    textAlign: "right",
  },
  qrCard: {
    flexDirection: "row",
    alignItems: "center",
    backgroundColor: "#e6f0ff",
    borderRadius: 10,
    padding: 12,
    marginBottom: 14,
  },
  link: {
    color: "#007AFF",
    textDecorationLine: "underline",
    fontSize: 14,
  },

  singleLineLabel: {
    flexShrink: 0,
    flexWrap: "nowrap",
  },
  singleLineValue: {
    flexShrink: 1,
    flexWrap: "nowrap",
    textAlign: "left",
  },
});
