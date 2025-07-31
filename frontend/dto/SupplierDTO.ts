export interface SupplierDTO {
  id: number;
  name: string;
  cnpj: string;
  address: {
    id: number;
    street: string;
    number: string;
    complement: string;
    district: string;
    city: string;
    uf: string;
  };
}
