import { useEffect, useState } from "react";
import {
  View,
  Text,
  ActivityIndicator,
  Pressable,
  StyleSheet,
  ScrollView,
} from "react-native";
import { useLocalSearchParams } from "expo-router";
import { useClassification } from "@/hooks/useClassification";
import { ClassificationDTO } from "@/dto/ClassificationDTO";

export default function CategoriesView() {
  const { id } = useLocalSearchParams();
  const classificationId = Number(id);

  const { getById, toggleStatus } = useClassification();
  const [classification, setClassification] = useState<ClassificationDTO | null>(null);
  const [loading, setLoading] = useState(true);
  const [toggling, setToggling] = useState(false);

  const fetchData = async () => {
    setLoading(true);
    const data = await getById(classificationId);
    setClassification(data);
    setLoading(false);
  };

  const handleToggleStatus = async () => {
    if (!classification) return;
    setToggling(true);
    const updated = await toggleStatus(classification.id);
    if (updated) setClassification(updated);
    setToggling(false);
  };

  useEffect(() => {
    fetchData();
  }, [classificationId]);

  if (loading || !classification) {
    return (
      <View style={styles.centered}>
        <ActivityIndicator size="large" color="#4B5563" />
        <Text style={styles.loadingText}>Carregando classificação...</Text>
      </View>
    );
  }

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>{classification.name}</Text>
      <Text style={styles.description}>{classification.description}</Text>
      <View style={styles.statusContainer}>
        <Text style={styles.label}>Status:</Text>
        <Text style={[styles.status, { color: classification.active ? "#16a34a" : "#dc2626" }]}>
          {classification.active ? "Ativo" : "Inativo"}
        </Text>
      </View>

      <Pressable
        onPress={handleToggleStatus}
        disabled={toggling}
        style={[
          styles.button,
          classification.active ? styles.buttonInactive : styles.buttonActive,
        ]}
      >
        <Text style={styles.buttonText}>
          {classification.active ? "Desativar" : "Ativar"}
        </Text>
      </Pressable>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 16,
    backgroundColor: "#fff",
  },
  centered: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#fff",
  },
  loadingText: {
    marginTop: 12,
    color: "#6b7280",
  },
  title: {
    fontSize: 22,
    fontWeight: "bold",
    color: "#111827",
    marginBottom: 8,
  },
  description: {
    fontSize: 16,
    color: "#374151",
    marginBottom: 16,
  },
  statusContainer: {
    flexDirection: "row",
    alignItems: "center",
    marginBottom: 24,
  },
  label: {
    fontSize: 16,
    color: "#111827",
    marginRight: 8,
  },
  status: {
    fontSize: 16,
    fontWeight: "bold",
  },
  button: {
    paddingVertical: 12,
    paddingHorizontal: 16,
    borderRadius: 8,
    alignItems: "center",
  },
  buttonActive: {
    backgroundColor: "#16a34a",
  },
  buttonInactive: {
    backgroundColor: "#dc2626",
  },
  buttonText: {
    color: "#fff",
    fontWeight: "bold",
    fontSize: 16,
  },
});
