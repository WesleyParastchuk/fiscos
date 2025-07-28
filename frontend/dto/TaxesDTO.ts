export interface TaxDTO {
    id: number;
    value: number;
}

export interface TaxesDTO {
    federal: TaxDTO;
    state: TaxDTO;
}