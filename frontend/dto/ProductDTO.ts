import { ClassifiedProductDTO } from "./ClassifiedProductDTO";
import { RawProductDTO } from "./RawProductDTO";

export interface ProductDTO {
  id: number;
  rawProduct: RawProductDTO;
  classifiedProduct: ClassifiedProductDTO;
  quantity: number;
  unitPrice: number;
  totalPrice: number;
  unitOfMeasure: string;
}
