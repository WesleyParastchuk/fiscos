import { useState, useEffect, useCallback } from "react";
import { Alert } from "react-native";
import { CompleteInvoiceDTO } from "@/dto/CompleteInvoiceDTO";
import * as invoiceService from "@/services/invoiceService";

export const useInvoices = () => {
  const [invoices, setInvoices] = useState<CompleteInvoiceDTO[]>([]);
  const [loading, setLoading] = useState(false);

  const loadAllInvoices = useCallback(async () => {
    try {
      setLoading(true);
      const data = await invoiceService.loadInvoices();
      setInvoices(data);
    } catch {
      Alert.alert("Erro", "Não foi possível carregar as notas fiscais.");
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    loadAllInvoices();
  }, [loadAllInvoices]);

  return {
    invoices,
    loading,
    loadAllInvoices,
  };
};
