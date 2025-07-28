import { useState, useEffect, useCallback } from "react";
import { Alert } from "react-native";
import * as linkService from "../services/linkService";

export const useLinks = () => {
  const [links, setLinks] = useState<string[]>([]);
  const [loading, setLoading] = useState(false);

  const loadAllLinks = useCallback(async () => {
    try {
      setLoading(true);
      const loaded = await linkService.loadLinks();
      setLinks(loaded);
    } catch {
      Alert.alert("Erro", "Não foi possível carregar o histórico.");
    } finally {
      setLoading(false);
    }
  }, []);

  const deleteLink = useCallback(
    async (link: string) => {
      try {
        await linkService.removeLink(link);
        setLinks((prev) => prev.filter((l) => l !== link));
      } catch {
        Alert.alert("Erro", "Não foi possível excluir o link.");
      }
    },
    []
  );

  const sendLinks = useCallback(async () => {
    if (links.length === 0) {
      Alert.alert("Aviso", "Não há links para enviar.");
      return;
    }
    try {
      setLoading(true);
      const response = await linkService.sendLinksToBackend(links, "1");
      if (response.status === 201) {
        Alert.alert("Sucesso", "Links enviados com sucesso!", [
          {
            text: "OK",
            onPress: async () => {
              setLinks([]);
              await linkService.clearLinks();
            },
          },
        ]);
      } else {
        Alert.alert("Erro", "Servidor indisponível ou erro ao enviar links.");
      }
    } catch {
      Alert.alert("Erro", "Não foi possível enviar os links.");
    } finally {
      setLoading(false);
    }
  }, [links]);

  return {
    links,
    loading,
    loadAllLinks,
    deleteLink,
    sendLinks,
  };
};
