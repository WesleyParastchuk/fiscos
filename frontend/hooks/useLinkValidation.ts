import { useState, useCallback } from "react";
import { Alert } from "react-native";
import * as linkService from "@/services/linkService";

export const useLinkValidation = () => {
  const [error, setError] = useState<string | null>(null);

  const validateLink = useCallback(
    async (data: string): Promise<"success" | "error"> => {
      if (!data) {
        setError("Link vazio.");
        return "error";
      }

      if (!(data.startsWith("http://") || data.startsWith("https://"))) {
        setError("Link deve iniciar com http:// ou https://");
        return "error";
      }

      const exists = await linkService.linkExists(data);
      if (exists) {
        setError("Link já está salvo.");
        return "error";
      }

      setError(null);
      return "success";
    },
    []
  );

  const saveLink = useCallback(async (link: string) => {
    try {
      await linkService.saveLink(link);
      setError(null);
    } catch (e) {
      setError("Não foi possível salvar o link.");
    }
  }, []);

  return {
    validateLink,
    saveLink,
    error,
  };
};
