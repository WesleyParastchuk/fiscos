import axios from "axios";
import { API_BASE_URL } from "@/constants/Api";
import { ClassificationDTO } from "@/dto/ClassificationDTO";

export class ClassificationService {
  static async load(): Promise<ClassificationDTO[]> {
    try {
      const response = await axios.get(`${API_BASE_URL}/classification`);
      return response.data;
    } catch (error) {
      console.error("Erro ao carregar classificações:", error);
      throw error;
    }
  }

  static async getById(id: number): Promise<ClassificationDTO> {
    try {
      const response = await axios.get(`${API_BASE_URL}/classification/${id}`);
      return response.data;
    } catch (error) {
      console.error("Erro ao carregar classificação por ID:", error);
      throw error;
    }
  }

  static async toggleStatus(
    id: number
  ): Promise<ClassificationDTO> {
    try {
      const response = await axios.post(
        `${API_BASE_URL}/classification/${id}/toggle`
      );
      return response.data;
    } catch (error) {
      console.error("Erro ao alternar status da classificação:", error);
      throw error;
    }
  }
}
