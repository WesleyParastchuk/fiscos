import React, { useState } from "react";
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  SafeAreaView,
  RefreshControl,
  TouchableOpacity,
} from "react-native";
import { useInvoices } from "@/hooks/useInvoice";
import { router } from "expo-router";

export default function InvoicesScreen() {
  const { invoices, loadAllInvoices } = useInvoices();
  const [refreshing, setRefreshing] = useState(false);

  const onRefresh = async () => {
    try {
      setRefreshing(true);
      await loadAllInvoices();
    } finally {
      setRefreshing(false);
    }
  };

  const formatDate = (timestamp: number): string => {
    const date = new Date(timestamp);
    return date.toLocaleDateString();
  };

  return (
    <SafeAreaView style={styles.container}>
      <FlatList
        data={invoices}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <TouchableOpacity
            onPress={() =>
              router.push({
                pathname: "/invoices/[id]",
                params: { id: item.id.toString() },
              })
            }
            activeOpacity={1}
          >
            <View style={styles.invoiceItem}>
              <Text style={styles.supplierName}>{item.supplier.name}</Text>
              <Text style={styles.issueDate}>
                Data: {formatDate(item.issueDate)}
              </Text>
              <Text style={styles.totalValue}>
                Valor Total: R$ {item.totalValue.toFixed(2)}
              </Text>
            </View>
          </TouchableOpacity>
        )}
        contentContainerStyle={
          invoices.length === 0 ? styles.emptyContainer : styles.listContent
        }
        refreshControl={
          <RefreshControl
            refreshing={refreshing}
            onRefresh={onRefresh}
            colors={["#007AFF"]}
            tintColor="#007AFF"
          />
        }
        ListEmptyComponent={
          <View style={styles.emptyContainer}>
            <Text style={styles.emptyText}>
              Nenhuma nota fiscal encontrada.
            </Text>
          </View>
        }
      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "#f9f9f9" },
  listContent: { paddingHorizontal: 16, paddingVertical: 20 },
  invoiceItem: {
    backgroundColor: "white",
    borderRadius: 8,
    padding: 16,
    marginBottom: 12,
    elevation: 2,
    shadowColor: "#000",
    shadowOpacity: 0.1,
    shadowRadius: 4,
    shadowOffset: { width: 0, height: 2 },
  },
  supplierName: {
    fontSize: 18,
    fontWeight: "600",
    color: "#222",
    marginBottom: 6,
  },
  issueDate: { fontSize: 14, color: "#555", marginBottom: 4 },
  totalValue: {
    fontSize: 16,
    fontWeight: "bold",
    color: "#007AFF",
  },
  emptyContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  emptyText: { fontSize: 16, color: "gray" },
});
