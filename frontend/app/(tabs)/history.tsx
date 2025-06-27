import React, { useState } from "react";
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  TouchableOpacity,
  Linking,
  Alert,
  SafeAreaView, // Importado para garantir que o layout fique bom em todos os dispositivos
} from "react-native";
import { useFocusEffect } from "expo-router";
import { LinkLocalStorage } from "@/data/links/LinkLocalStorage";
import { MaterialIcons } from "@expo/vector-icons";
import axios from "axios";

export default function HistoryScreen() {
  const [links, setLinks] = useState<string[]>([]);

  useFocusEffect(
    React.useCallback(() => {
      loadLinks();
    }, [])
  );

  const loadLinks = async () => {
    try {
      const loadedLinks = await LinkLocalStorage.getInstance().getAllLinks();
      setLinks(loadedLinks);
    } catch (e) {
      console.error("Erro ao carregar links:", e);
      Alert.alert("Erro", "Não foi possível carregar o histórico.");
    }
  };

  const handleDeleteItem = (linkToRemove: string) => {
    Alert.alert("Excluir Link", "Tem certeza que deseja excluir este link?", [
      { text: "Cancelar", style: "cancel" },
      {
        text: "Excluir",
        style: "destructive",
        onPress: async () => {
          try {
            await LinkLocalStorage.getInstance().removeLink(linkToRemove);
            setLinks((prevLinks) =>
              prevLinks.filter((link) => link !== linkToRemove)
            );
          } catch (e) {
            console.error("Erro ao excluir link:", e);
            Alert.alert("Erro", "Não foi possível excluir o link.");
          }
        },
      },
    ]);
  };

  const handleSendToBackend = async () => {
    if (links.length === 0) {
      Alert.alert("Aviso", "Não há links para enviar.");
      return;
    }

    await axios
      .post("https://joey-composed-early.ngrok-free.app/links", {
        links: links,
      })
      .then((response) => {
        if (response.status === 201) {
          Alert.alert("Sucesso", "Links enviados com sucesso!", [
            {
              text: "OK",
              onPress: () => {
                setLinks([]);
                LinkLocalStorage.getInstance().clearLinks();
              },
            },
          ]);
        } else {
          Alert.alert("Erro", "Servidor indisponível ou erro ao enviar links.");
        }
        return response.data;
      })
      .catch((error) => {
        console.error("Erro ao buscar dados:", error);
        Alert.alert("Erro", "Não foi possível buscar os dados do backend.");
        return [];
      });
  };

  return (
    <SafeAreaView style={styles.container}>
      {links.length === 0 ? (
        <View style={styles.emptyContainer}>
          <Text style={styles.emptyText}>Nenhum link salvo ainda.</Text>
        </View>
      ) : (
        <FlatList
          data={links}
          keyExtractor={(item, index) => `${item}-${index}`}
          renderItem={({ item }) => (
            <View style={styles.linkItemContainer}>
              <TouchableOpacity
                style={styles.linkTouchable}
                onPress={() => Linking.openURL(item)}
              >
                <Text style={styles.linkText} numberOfLines={1}>
                  {item}
                </Text>
              </TouchableOpacity>
              <TouchableOpacity
                style={styles.deleteButton}
                onPress={() => handleDeleteItem(item)}
              >
                <MaterialIcons name="delete-outline" size={24} color="#666" />
              </TouchableOpacity>
            </View>
          )}
          contentContainerStyle={styles.listContentContainer}
        />
      )}

      {links.length > 0 && (
        <View style={styles.buttonContainer}>
          <TouchableOpacity
            style={styles.sendButton}
            onPress={handleSendToBackend}
          >
            <Text style={styles.sendButtonText}>Enviar Histórico</Text>
            <MaterialIcons
              name="cloud-upload"
              size={20}
              color="white"
              style={{ marginLeft: 8 }}
            />
          </TouchableOpacity>
        </View>
      )}
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#f0f0f0",
  },
  emptyContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  emptyText: {
    fontSize: 16,
    color: "gray",
  },
  listContentContainer: {
    paddingHorizontal: 20,
    paddingTop: 20,
    paddingBottom: 100,
  },
  linkItemContainer: {
    backgroundColor: "white",
    paddingVertical: 8,
    paddingHorizontal: 15,
    borderRadius: 8,
    marginBottom: 10,
    elevation: 1,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  linkTouchable: {
    flex: 1,
  },
  linkText: {
    color: "#007AFF",
    fontSize: 16,
    paddingRight: 10,
  },
  deleteButton: {
    padding: 5,
  },
  buttonContainer: {
    position: "absolute",
    bottom: 0,
    left: 0,
    right: 0,
    paddingHorizontal: 20,
    paddingVertical: 20,
    backgroundColor: "transparent",
  },
  sendButton: {
    backgroundColor: "#007AFF",
    paddingVertical: 14,
    borderRadius: 8,
    alignItems: "center",
    justifyContent: "center",
    flexDirection: "row",
    elevation: 3,
    shadowOpacity: 0.25,
    shadowRadius: 4,
    shadowOffset: { height: 2, width: 0 },
  },
  sendButtonText: {
    color: "white",
    fontSize: 16,
    fontWeight: "bold",
  },
});
