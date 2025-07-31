import { useEffect, useState, useCallback } from "react";
import { Alert } from "react-native";
import { ClassificationDTO } from "@/dto/ClassificationDTO";
import { ClassificationService } from "@/services/classificationService";

export const useClassification = () => {
  const [classifications, setClassifications] = useState<ClassificationDTO[]>(
    []
  );
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleError = (message: string) => {
    Alert.alert("Erro", message);
    setError(message);
  };

  const loadAll = useCallback(async () => {
    setLoading(true);
    try {
      const data = await ClassificationService.load();
      setClassifications(data);
    } catch {
      handleError("Erro ao carregar classificações.");
    } finally {
      setLoading(false);
    }
  }, []);

  const getById = useCallback(
    async (id: number): Promise<ClassificationDTO | null> => {
      try {
        return await ClassificationService.getById(id);
      } catch {
        handleError("Erro ao buscar classificação.");
        return null;
      }
    },
    []
  );

  const toggleStatus = useCallback(
    async (id: number): Promise<ClassificationDTO | null> => {
      try {
        const updated = await ClassificationService.toggleStatus(id);
        setClassifications((prev) =>
          prev.map((item) => (item.id === id ? updated : item))
        );
        return updated;
      } catch {
        handleError("Erro ao alternar status.");
        return null;
      }
    },
    []
  );

  useEffect(() => {
    loadAll();
  }, [loadAll]);

  return {
    classifications,
    loading,
    error,
    reload: loadAll,
    getById,
    toggleStatus,
  };
};
