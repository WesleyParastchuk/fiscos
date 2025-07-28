import { ClassificationDTO } from "./ClassificationDTO";

export interface ClassifiedProductDTO {
  id: number;
  name: string;
  classifications: ClassificationDTO[];
}