import axios from "axios";
import { API_BASE_URL } from "@/constants/Api";
import { CompleteInvoiceDTO } from "@/dto/CompleteInvoiceDTO";

export const loadInvoices = async (): Promise<CompleteInvoiceDTO[]> => {
  const response = await axios.get(`${API_BASE_URL}/invoice`);
  return response.data;
};

export const getInvoiceById = async (id: number): Promise<CompleteInvoiceDTO> => {
  const response = await axios.get(`${API_BASE_URL}/invoice/${id}`);
  return response.data;
};
