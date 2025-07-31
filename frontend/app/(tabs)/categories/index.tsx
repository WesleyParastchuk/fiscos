import React, { useEffect, useState } from "react";
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  SafeAreaView,
  TouchableOpacity,
  Switch,
  ActivityIndicator,
  RefreshControl,
} from "react-native";
import { Ionicons } from "@expo/vector-icons";

import { useClassification } from "@/hooks/useClassification";
import { ClassificationDTO } from "@/dto/ClassificationDTO";
import { router } from "expo-router";

// Construção da árvore hierárquica
const buildTree = (list: ClassificationDTO[]) => {
  const map = new Map<
    number,
    ClassificationDTO & { children: ClassificationDTO[] }
  >();
  const roots: (ClassificationDTO & { children: ClassificationDTO[] })[] = [];

  list.forEach((item) => {
    map.set(item.id, { ...item, children: [] });
  });

  map.forEach((item) => {
    if (item.parent && item.parent.id && map.has(item.parent.id)) {
      map.get(item.parent.id)!.children.push(item);
    } else {
      roots.push(item);
    }
  });

  return roots;
};

// Flatten filtrando itens visíveis baseado em expandido
const flattenVisible = (
  categories: (ClassificationDTO & {
    children: ClassificationDTO[];
    depth?: number;
  })[],
  expandedIds: Set<number>,
  depth = 0
): (ClassificationDTO & { children: ClassificationDTO[]; depth: number })[] => {
  let result: (ClassificationDTO & {
    children: ClassificationDTO[];
    depth: number;
  })[] = [];

  categories.forEach((cat) => {
    result.push({ ...cat, depth });
    if (cat.children.length > 0 && expandedIds.has(cat.id)) {
      result = result.concat(
        flattenVisible(cat.children, expandedIds, depth + 1)
      );
    }
  });

  return result;
};

const BORDER_COLORS = ["#004080", "#007AFF", "#66B2FF"];

export default function CategoriesScreen() {
  const { classifications, loading, error } = useClassification();
  const [treeCategories, setTreeCategories] = useState<
    (ClassificationDTO & { children: ClassificationDTO[] })[]
  >([]);
  const [expandedIds, setExpandedIds] = useState<Set<number>>(new Set());

  const [refreshing, setRefreshing] = useState(false);

  // Atualiza árvore quando classifications muda
  useEffect(() => {
    if (!loading && classifications.length > 0) {
      const tree = buildTree(classifications);
      setTreeCategories(tree);
      //setExpandedIds(new Set(tree.map((cat) => cat.id)));
    }
  }, [loading, classifications]);

  // Lista visível conforme expandido
  const visibleCategories = flattenVisible(treeCategories, expandedIds);

  const toggleExpand = (id: number) => {
    setExpandedIds((prev) => {
      const newSet = new Set(prev);
      if (newSet.has(id)) {
        newSet.delete(id);
      } else {
        newSet.add(id);
      }
      return newSet;
    });
  };

  const handleToggleStatus = (id: number) => {
    // Atualiza ativo no treeCategories mantendo a hierarquia
    const updateActive = (
      cats: (ClassificationDTO & { children: ClassificationDTO[] })[]
    ): (ClassificationDTO & { children: ClassificationDTO[] })[] =>
      cats.map((cat) =>
        cat.id === id
          ? { ...cat, active: !cat.active }
          : { ...cat, children: updateActive(cat.children) }
      );

    setTreeCategories((prev) => updateActive(prev));
  };

  const onRefresh = async () => {
    try {
      setRefreshing(true);
      // Se seu hook tiver reload, chame aqui
    } finally {
      setRefreshing(false);
    }
  };

  if (loading) {
    return (
      <SafeAreaView style={styles.loadingContainer}>
        <ActivityIndicator size="large" color="#007AFF" />
      </SafeAreaView>
    );
  }

  if (error) {
    return (
      <SafeAreaView style={styles.emptyContainer}>
        <Text style={styles.errorText}>{error}</Text>
      </SafeAreaView>
    );
  }

  return (
    <SafeAreaView style={styles.container}>
      <FlatList
        data={visibleCategories}
        keyExtractor={(item) => item.id.toString()}
        refreshControl={
          <RefreshControl
            refreshing={refreshing}
            onRefresh={onRefresh}
            colors={["#007AFF"]}
            tintColor="#007AFF"
          />
        }
        renderItem={({ item }) => {
          const hasChildren = item.children.length > 0;
          const isExpanded = expandedIds.has(item.id);

          return (
            <TouchableOpacity
              onPress={() => router.push({
                pathname: "/(tabs)/categories/[id]",
                params: { id: item.id },
              })}
              
              activeOpacity={0.7}
            >
              <View
                style={[
                  styles.categoryItem,
                  {
                    marginLeft: item.depth * 16,
                    borderLeftWidth: 4,
                    borderLeftColor:
                      item.depth < BORDER_COLORS.length
                        ? BORDER_COLORS[item.depth]
                        : "#B0B0B0",
                  },
                ]}
              >
                <Text
                  style={[
                    styles.categoryName,
                    { opacity: item.active ? 1 : 0.5 },
                  ]}
                >
                  {item.name}
                </Text>
                <View style={styles.actions}>
                  {hasChildren && (
                    <TouchableOpacity
                      onPress={(e) => {
                        e.stopPropagation();
                        toggleExpand(item.id);
                      }}
                      style={styles.iconButton}
                      accessibilityLabel={
                        isExpanded ? "Recolher filhos" : "Expandir filhos"
                      }
                    >
                      <Ionicons
                        name={
                          isExpanded
                            ? "chevron-down-outline"
                            : "chevron-forward-outline"
                        }
                        size={24}
                        color="#007AFF"
                      />
                    </TouchableOpacity>
                  )}
                  <Switch
                    value={item.active}
                    onValueChange={() => handleToggleStatus(item.id)}
                    thumbColor={item.active ? "#ffffff" : "#f4f3f4"}
                    trackColor={{ false: "#ccc", true: "#34C759" }}
                    style={{ marginLeft: hasChildren ? 8 : 0 }}
                  />
                </View>
              </View>
            </TouchableOpacity>
          );
        }}
        contentContainerStyle={
          visibleCategories.length === 0
            ? styles.emptyContainer
            : styles.listContent
        }
        ListEmptyComponent={
          <View style={styles.emptyContainer}>
            <Text style={styles.emptyText}>Nenhuma categoria cadastrada.</Text>
          </View>
        }
      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: "#f9f9f9" },
  listContent: {
    paddingHorizontal: 16,
    paddingVertical: 20,
  },
  categoryItem: {
    backgroundColor: "white",
    borderRadius: 8,
    paddingVertical: 12,
    paddingHorizontal: 12,
    marginBottom: 10,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    elevation: 2,
    shadowColor: "#000",
    shadowOpacity: 0.1,
    shadowRadius: 4,
    shadowOffset: { width: 0, height: 2 },
  },
  categoryName: {
    fontSize: 16,
    fontWeight: "500",
    color: "#333",
    flex: 1,
  },
  actions: {
    flexDirection: "row",
    alignItems: "center",
  },
  iconButton: {
    paddingHorizontal: 6,
    paddingVertical: 4,
  },
  emptyContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    paddingHorizontal: 16,
  },
  emptyText: {
    fontSize: 16,
    color: "gray",
    textAlign: "center",
  },
  errorText: {
    fontSize: 16,
    color: "#FF3B30",
    textAlign: "center",
  },
  loadingContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
});
