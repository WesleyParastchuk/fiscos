import { ProductDTO } from "./ProductDTO";
import { QrCodeDTO } from "./QrCodeDTO";
import { SupplierDTO } from "./SupplierDTO";
import { TaxesDTO } from "./TaxesDTO";
import { UserDTO } from "./UserDTO";

export interface CompleteInvoiceDTO {
  id: number;
  issueDate: number;
  totalValue: number;
  user: UserDTO;
  supplier: SupplierDTO;
  taxes: TaxesDTO;
  products: ProductDTO[];
  qrcode: QrCodeDTO;
}
